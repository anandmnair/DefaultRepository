package com.anand.demo.security;

import org.jasig.cas.client.proxy.ProxyGrantingTicketStorage;
import org.jasig.cas.client.proxy.ProxyGrantingTicketStorageImpl;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.authentication.EhCacheBasedTicketCache;
import org.springframework.security.cas.authentication.StatelessTicketCache;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import net.sf.ehcache.Cache;

@Configuration
@EnableWebSecurity
public class CasConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${cas.server.url.prefix}")
	private String casServerUrlPrefix;
	
	@Value("${cas.service.url.prefix}")
	private String casServiceUrlPrefix;
	
	@Bean
	public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
	    CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
	    casAuthenticationEntryPoint.setLoginUrl(casServerUrlPrefix + "/login");
	    casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
	    return casAuthenticationEntryPoint;
	}

	@Bean
	public ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new  ServiceProperties();
		serviceProperties.setService(casServiceUrlPrefix + "/j_spring_cas_security_check");
		serviceProperties.setSendRenew(false);
		return serviceProperties;
	}
	
	@Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        casAuthenticationProvider.setAuthenticationUserDetailsService(authenticationUserDetailsService());
        //casAuthenticationProvider.setUserDetailsService(userDetailsService());
        casAuthenticationProvider.setServiceProperties(serviceProperties());
        casAuthenticationProvider.setTicketValidator(casTicketValidator());
        casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
        casAuthenticationProvider.setStatelessTicketCache(statelessTicketCache());
        return casAuthenticationProvider;
    }
	
	@Bean
	public StatelessTicketCache statelessTicketCache(){
		EhCacheBasedTicketCache cacheBasedTicketCache = new EhCacheBasedTicketCache();
		cacheBasedTicketCache.setCache(cache());
		return cacheBasedTicketCache;
	}
	
	@Bean(initMethod="initialise", destroyMethod="dispose")
	public Cache cache() {
		Cache cache = new Cache("casTickets", 50, false, false, 3600, 900);
		return cache;
	}
	
	@Bean
    public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> authenticationUserDetailsService() {
        return new CasAuthenticationUserDetailsService();
    }
	
    @Bean
    public TicketValidator casTicketValidator() {
    	Cas20ServiceTicketValidator casTicketValidator = new Cas20ServiceTicketValidator(casServerUrlPrefix);
    	//casTicketValidator.setProxyCallbackUrl(casServiceUrlPrefix + "/j_spring_cas_security_proxyreceptor");
    	//casTicketValidator.setProxyGrantingTicketStorage(proxyGrantingTicketStorage());
    	//cas20ServiceTicketValidator.setAcceptAnyProxy(true);
    	return casTicketValidator;
    }
    
    @Bean 
	public ProxyGrantingTicketStorage proxyGrantingTicketStorage() {
    	ProxyGrantingTicketStorage proxyGrantingTicketStorage = new ProxyGrantingTicketStorageImpl();
    	return proxyGrantingTicketStorage;
    }
    
    @Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
        casAuthenticationFilter.setAuthenticationManager(authenticationManager());
        casAuthenticationFilter.setFilterProcessesUrl("/j_spring_cas_security_check");
        //casAuthenticationFilter.setProxyReceptorUrl("/j_spring_cas_security_proxyreceptor");
        return casAuthenticationFilter;
    }
    
    //@Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(casServerUrlPrefix);
        return singleSignOutFilter;
    }
    
    //@Bean
    public LogoutFilter requestSingleLogoutFilter() {
    	LogoutFilter logoutFilter = new LogoutFilter(casServerUrlPrefix + "/logout", securityContextLogoutHandler());
    	logoutFilter.setFilterProcessesUrl("/j_spring_cas_security_logout");
    	return logoutFilter;
	}

    @Bean
    public SecurityContextLogoutHandler securityContextLogoutHandler() {
    	 SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
    	 securityContextLogoutHandler.setClearAuthentication(true);
    	 securityContextLogoutHandler.setInvalidateHttpSession(true);
    	 return securityContextLogoutHandler;
    }
    
//    @Bean
//    public ProxyTicketSampleServlet proxyTicketSampleServlet() {
//    	return proxyTicketSampleServlet();
//    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .addFilter(casAuthenticationFilter())
        .addFilterBefore(requestSingleLogoutFilter(), LogoutFilter.class)
        .addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class);
        http
            .exceptionHandling()
                .authenticationEntryPoint(casAuthenticationEntryPoint());
        http.authorizeRequests().antMatchers("/**").authenticated();
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/cas-logout");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .authenticationProvider(casAuthenticationProvider());
    }
	
}


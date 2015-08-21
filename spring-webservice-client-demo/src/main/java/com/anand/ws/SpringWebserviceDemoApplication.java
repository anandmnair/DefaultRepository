package com.anand.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.anand.ws.service.impl.Person;

@SpringBootApplication
public class SpringWebserviceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebserviceDemoApplication.class, args);
    }
    
    @Bean
    public WebServiceTemplate webServiceTemplate() {
    	WebServiceTemplate webServiceTemplate = new WebServiceTemplate(messageFactory());
    	webServiceTemplate.setMarshaller(marshaller());
    	webServiceTemplate.setUnmarshaller(unmarshaller());
    	webServiceTemplate.setMessageSender(httpComponentsMessageSender());
    	webServiceTemplate.setDefaultUri("http://localhost:8080/demo");
		return webServiceTemplate;
    }
    
    @Bean
    public WebServiceMessageFactory messageFactory() {
    	return new SaajSoapMessageFactory();
    }
    
    @Bean
    public Marshaller marshaller() {
    	Jaxb2Marshaller Jaxb2Marshaller = new Jaxb2Marshaller();
    	Jaxb2Marshaller.setClassesToBeBound(Person.class);
		return Jaxb2Marshaller;
    }
    
    @Bean
    public Unmarshaller unmarshaller() {
    	Jaxb2Marshaller Jaxb2Marshaller = new Jaxb2Marshaller();
    	Jaxb2Marshaller.setClassesToBeBound(Person.class);
		return Jaxb2Marshaller;
    }
    
    public HttpComponentsMessageSender httpComponentsMessageSender() {
    	return new HttpComponentsMessageSender();
    }
}

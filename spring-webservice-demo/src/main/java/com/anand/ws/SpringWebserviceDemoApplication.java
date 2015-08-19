package com.anand.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.anand.ws.model.Person;

@SpringBootApplication
public class SpringWebserviceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebserviceDemoApplication.class, args);
    }
    
    @Bean
    public WebServiceTemplate webServiceTemplate() {
    	WebServiceTemplate webServiceTemplate = new WebServiceTemplate(saajSoapMessageFactory());
    	webServiceTemplate.setMarshaller(jaxb2Marshaller());
    	webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
    	webServiceTemplate.setMessageSender(httpComponentsMessageSender());
    	webServiceTemplate.setDefaultUri("http://localhost:8080/demo");
		return webServiceTemplate;
    }
    
    @Bean
    public SaajSoapMessageFactory saajSoapMessageFactory() {
    	return new SaajSoapMessageFactory();
    }
    
    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
    	Jaxb2Marshaller Jaxb2Marshaller = new Jaxb2Marshaller();
    	Jaxb2Marshaller.setClassesToBeBound(Person.class);
		return Jaxb2Marshaller;
    }
    
    public HttpComponentsMessageSender httpComponentsMessageSender() {
    	return new HttpComponentsMessageSender();
    }
}

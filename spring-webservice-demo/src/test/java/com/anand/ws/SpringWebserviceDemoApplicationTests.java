package com.anand.ws;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Endpoint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import com.anand.ws.model.Person;
import com.anand.ws.service.impl.HelloServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringWebserviceDemoApplication.class)
@WebAppConfiguration
public class SpringWebserviceDemoApplicationTests {

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	@Before
	public void setup() {
		Endpoint endpoint = Endpoint.publish("http://localhost:8080/demo", new HelloServiceImpl());
		assertTrue(endpoint.isPublished());
	}

	@Test
	public void webserviceTest() {
		Object r = null;
		// r=webServiceTemplate.sendAndReceive(new WebServiceMessageCallback() {
		//
		// @Override
		// public void doWithMessage(WebServiceMessage arg0) throws IOException,
		// TransformerException {
		// System.out.println(arg0);
		// SaajSoapMessage saajSoapMessage = (SaajSoapMessage) arg0;
		// saajSoapMessage.
		// }
		// }, new WebServiceMessageCallback() {
		//
		// @Override
		// public void doWithMessage(WebServiceMessage arg0) throws IOException,
		// TransformerException {
		// System.out.println(arg0);
		// }
		//
		// });
		// webServiceTemplate.marshalSendAndReceive(new Person("Anand",
		// "Manissery"));

		final StringWriter out = new StringWriter();
		jaxb2Marshaller.marshal(new Person("aaa", "bbb"), new StreamResult(out));
		StreamSource source = new StreamSource(new StringReader(
					// "<Envelope
					// xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">"
					// +"<Body>"
					"" + "<sayHello xmlns=\"http://impl.service.ws.anand.com/\">" + "<arg0 xmlns=\"\">"
					// +"<firstname>Anand</firstname>"
					// +"<lastname>[string?]</lastname>"
						+ "<person><firstname>aaa</firstname><lastname>bbb</lastname></person>" + "</arg0>"
						+ "</sayHello>"
		// +"</Body>"
		// +"</Envelope>"
		));
		
		StreamSource sourceIn = new StreamSource(new StringReader(out.toString()));

		StreamResult result = new StreamResult(System.out);
		// r =
		// webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:8080/demo",
		// source, result);

		r = webServiceTemplate.sendAndReceive("http://localhost:8080/demo/sayHello",new SoapActionCallback("") {
			
		}, new SoapActionCallback("") {

		});

		System.out.println(r);
	}

}

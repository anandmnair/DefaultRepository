package itec.fcc.rpp.event.resource;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.SpringLifecycleListener;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import itec.fcc.rpp.event.EventMicroServiceApplication;

public class EventResourceTest extends JerseyTest {

	private ResourceConfig rc;
	
	@Override
	protected Application configure() {
		//if (rc == null) {
			ResourceConfig rc = new ResourceConfig();
			rc.property("contextConfig", new AnnotationConfigApplicationContext(EventMicroServiceApplication.class));
			rc.register(SpringLifecycleListener.class).register(RequestContextFilter.class);
			rc.registerClasses(EventResource.class);
		//}
		return rc;
	}

	/*
	 * @Override protected TestContainerFactory getTestContainerFactory() {
	 * return new GrizzlyWebTestContainerFactory(); }
	 */

	/*
	 * @Override protected DeploymentContext configureDeployment(){ return
	 * ServletDeploymentContext .forServlet(new ServletContainer(new
	 * JerseyConfig())) .addListener(ContextLoaderListener.class)
	 * //.contextParam("contextConfigLocation",
	 * "classpath:applicationContext.xml") .build(); }
	 */

	@Test
	public void test() {
		String response = target("event").request().get(String.class);
		Assert.assertEquals("event-service :: 1", response);
		System.out.println(response);

		response = target("event").request().get(String.class);
		Assert.assertEquals("event-service :: 2", response);
		System.out.println(response);

	}

	@Test
	public void testAdd() {
		String response = target("event").request().get(String.class);
		Assert.assertEquals("event-service :: 1", response);
		System.out.println(response);
	}

}
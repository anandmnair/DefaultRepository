package itec.fcc.rpp.event.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		packages(true,"itec.fcc.rpp.event");
	}

}
package application;

import org.glassfish.jersey.jettison.JettisonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.json.stream.JsonGenerator;

/**
 * Created by david on 7/2/16.
 */
public class MiAplicacion extends ResourceConfig {
    public MiAplicacion() {
        packages("services");
        register(new JettisonFeature());
        property(JsonGenerator.PRETTY_PRINTING, true);
    }
}
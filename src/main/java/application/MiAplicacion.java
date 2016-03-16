package application;

import database.NotesDataBaseManager;
import database.UsersDataBaseManager;
import filters.FilterAuthentication;
import org.glassfish.jersey.jettison.JettisonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.json.stream.JsonGenerator;

/**
 * Configuration class
 * Created by david on 7/2/16.
 */
public class MiAplicacion extends ResourceConfig {

    private static final String LOCAL_BD = "local";
    private static final String HEROKU_BD = "heroku";
    public static String DATA_BASE_PERSISTENCE_UNIT = LOCAL_BD;

    public MiAplicacion() {
        packages("services");
        register(new JettisonFeature());
        register(FilterAuthentication.class ); //We register the filter for allow authentification
        property(JsonGenerator.PRETTY_PRINTING, true);
        configureDaos();
    }

    private static final void configureDaos() {
        UsersDataBaseManager.createDao( DATA_BASE_PERSISTENCE_UNIT );
        NotesDataBaseManager.createDao( DATA_BASE_PERSISTENCE_UNIT );
    }
}
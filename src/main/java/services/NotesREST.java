package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by david on 7/2/16.
 */
@Path( "notes" )
public class NotesREST {

    public NotesREST() {
        super();
    }

    @GET
    @Path( "test" )
    @Produces( MediaType.TEXT_PLAIN )
    public Response test() {
        return Response.ok( "test!" ).build();
    }

}

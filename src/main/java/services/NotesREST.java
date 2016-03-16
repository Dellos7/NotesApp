package services;

import annotations.Secured;
import database.NotesDataBaseManager;
import model.data.ConversionUtils;
import model.data.Note;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

/**
 * Created by david on 7/2/16.
 */
@Path( "notes" )
public class NotesREST {

    @Context
    private UriInfo uriInfo;

    public NotesREST() {
        super();
    }

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    //@Secured( role = Secured.Role.user )
    public Response getNotes() {
        List<Note> notes = NotesDataBaseManager.getNotes();
        Note[] notesArray = ConversionUtils.fromListObjectToArray( notes );
        return Response.ok( notesArray ).build();
    }

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @Secured( role = Secured.Role.user )
    public Response newNote( Note note ) {
        NotesDataBaseManager.newNote( note );
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( note.getId() + "" ).build();
        return Response.created( uri ).entity( note ).build();
    }

    @DELETE
    @Path("{id}")
    @Secured( role = Secured.Role.user )
    public Response deleteNote( @PathParam( "id" ) Long id  ) {
        if( id != null ) {
            if( NotesDataBaseManager.deleteNote( id ) ) {
                return Response.status( Response.Status.ACCEPTED ).build();
            }
            else {
                return Response.status( Response.Status.NOT_FOUND ).build();
            }
        }
        return Response.status( Response.Status.BAD_REQUEST ).build();
    }

    @GET
    @Path( "test" )
    @Produces( MediaType.TEXT_PLAIN )
    @Secured( role = Secured.Role.admin )
    public Response test() {
        return Response.ok( "test!" ).build();
    }

}

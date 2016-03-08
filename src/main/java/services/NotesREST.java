package services;

import annotations.AuthenticationRequired;
import database.DataBaseManager;
import model.data.Note;
import model.data.NoteUtils;

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
        DataBaseManager.setDataBase( DataBaseManager.LOCAL_BD );
    }

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    @AuthenticationRequired
    public Response getNotes() {
        List<Note> notes = DataBaseManager.getNotes();
        Note[] notesArray = NoteUtils.fromListObjectToArray( notes );
        return Response.ok( notesArray ).build();
    }

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @AuthenticationRequired
    public Response newNote( Note note ) {
        DataBaseManager.newNote( note );
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( note.getId() + "" ).build();
        return Response.created( uri ).entity( note ).build();
    }

    @DELETE
    @Path("{id}")
    @AuthenticationRequired
    public Response deleteNote( @PathParam( "id" ) Long id  ) {
        if( id != null ) {
            if( DataBaseManager.deleteNote( id ) ) {
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
    public Response test() {
        return Response.ok( "test!" ).build();
    }

}

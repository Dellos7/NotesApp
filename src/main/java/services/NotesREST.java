package services;

import annotations.Secured;
import authentication.AuthenticationService;
import database.DataBaseManager;
import model.data.Note;
import model.data.NoteUtils;
import model.data.beans.UserInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
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
    @Secured( role = Secured.Role.user )
    public Response getNotes() {
        List<Note> notes = DataBaseManager.getNotes();
        Note[] notesArray = NoteUtils.fromListObjectToArray( notes );
        return Response.ok( notesArray ).build();
    }

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @Secured( role = Secured.Role.user )
    public Response newNote( Note note ) {
        DataBaseManager.newNote( note );
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( note.getId() + "" ).build();
        return Response.created( uri ).entity( note ).build();
    }

    @DELETE
    @Path("{id}")
    @Secured( role = Secured.Role.user )
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
    @Secured( role = Secured.Role.admin )
    public Response test() {
        return Response.ok( "test!" ).build();
    }

    @GET
    @Path( "token" )
    @Produces( MediaType.TEXT_PLAIN )
    public Response token() {
        String token = "no-token";
        try {
            UserInfo userInfo = new UserInfo(); //Here we would retrieve the user from the database
            userInfo.setUsername( "zira" );
            userInfo.setUserRole( Secured.Role.user );
            token = AuthenticationService.generateToken( userInfo );
        } catch (IOException e) {
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
        }
        return Response.ok( token ).build();
    }

}

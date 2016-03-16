package services;

import annotations.Secured;
import authentication.AuthenticationService;
import model.data.beans.UserInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by david on 10/3/16.
 */
@Path( "token" )
public class TokenREST {

    @GET
    @Path( "user" )
    @Produces( MediaType.TEXT_PLAIN )
    public Response userToken() {
        String token = token( "zira", Secured.Role.user );
        if( token == null ) {
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
        }
        return Response.ok( token ).build();
    }

    @GET
    @Path( "admin" )
    @Produces( MediaType.TEXT_PLAIN )
    public Response adminToken() {
        String token = token( "david", Secured.Role.admin );
        if( token == null ) {
            return Response.status( Response.Status.INTERNAL_SERVER_ERROR ).build();
        }
        return Response.ok( token ).build();
    }

    private static String token( String username, Secured.Role role ) {
        String token = null;
        try {
            UserInfo userInfo = new UserInfo(); //Here we would retrieve the user from the database
            userInfo.setUsername( username );
            userInfo.setUserRole( role );
            token = AuthenticationService.generateToken( userInfo );
        } catch (IOException e) {}
        return token;
    }

}

package services;

import authentication.AuthenticationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by david on 10/3/16.
 */
@Path( "login" )
public class LoginREST {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces( MediaType.TEXT_PLAIN )
    public Response login( @FormParam( "username" ) String username,
                           @FormParam( "password" ) String password ) {
        System.out.println( "username: " + username );
        System.out.println( "password:" + password );
        String token = AuthenticationService.login( username, password );
        System.out.println( "token: " + token );
        if( token == null ) {
            return Response.status( Response.Status.UNAUTHORIZED ).build();
        }
        return Response.ok( token ).build();
    }

    /*@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces( MediaType.TEXT_PLAIN )
    public Response login( @FormParam( "credentials" ) Credentials credentials ) {
        System.out.println( "username: " + credentials.getUsername() );
        System.out.println( "password:" + credentials.getPassword() );
        String token = AuthenticationService.login( credentials.getUsername(), credentials.getUsername() );
        if( token == null ) {
            return Response.status( Response.Status.UNAUTHORIZED ).build();
        }
        return Response.ok( token ).build();
    }*/

}

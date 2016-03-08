package filters;

import annotations.AuthenticationRequired;
import authentication.AuthenticationService;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by david on 8/3/16.
 */
@AuthenticationRequired
public class FilterAuthentication implements ContainerRequestFilter {

    public static final String AUTHENTICATION_HEADER = "Authorization";

    @Override
    public void filter( ContainerRequestContext containerRequestContext ) throws WebApplicationException {
        /*int num = 2;
        int res = 3;
        if( num != res ) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED );
        }*/
        String authToken = containerRequestContext.getHeaderString( AUTHENTICATION_HEADER );
        System.out.println( "------------------------" );
        System.out.println( "Auth token: " + authToken );
        System.out.println( "------------------------" );
        try {
            AuthenticationService.authenticate( authToken );
        } catch (IOException e) {
            e.printStackTrace();
        }
        if( authToken == null || !authToken.equals( "david" )  ) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED );
        }
    }
}
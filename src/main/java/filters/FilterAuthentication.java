package filters;

import annotations.Secured;
import authentication.AuthenticationService;
import org.glassfish.jersey.server.ExtendedUriInfo;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by david on 8/3/16.
 */
@Secured
public class FilterAuthentication implements ContainerRequestFilter {

    public static final String AUTHENTICATION_HEADER = "Authorization";

    @Override
    public void filter( ContainerRequestContext containerRequestContext ) throws WebApplicationException {
        //We get the Role from the method which invoked the Filter
        ExtendedUriInfo extendedUriInfo = (ExtendedUriInfo) containerRequestContext.getUriInfo();
        Secured.Role role = extendedUriInfo.getMatchedResourceMethod().getInvocable().getHandlingMethod().getAnnotation( Secured.class ).role();

        //We get the token sent in the header
        String authToken = containerRequestContext.getHeaderString( AUTHENTICATION_HEADER );
        System.out.println( authToken );

        try {
            if( authToken == null || !AuthenticationService.checkAuthentication( authToken, role ) ) {
                throw new WebApplicationException(Response.Status.UNAUTHORIZED );
            }
        }
        catch (IOException e) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED );
        }

    }
}
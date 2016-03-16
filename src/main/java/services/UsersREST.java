package services;

import annotations.Secured;
import database.UsersDataBaseManager;
import model.data.ConversionUtils;
import model.data.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by david on 10/3/16.
 */
@Path( "users" )
public class UsersREST {

    @Context
    private UriInfo uriInfo;

    public UsersREST() {
        super();
    }

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    @Secured( role = Secured.Role.admin )
    public Response getUsers() {
        List<User> users = UsersDataBaseManager.getUsers();
        User[] usersArray = ConversionUtils.fromListObjectToArray( users );
        return Response.ok( usersArray ).build();
    }

}

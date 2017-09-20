package net.qiujuer.web.italker.push.service;

import net.qiujuer.web.italker.push.beam.db.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


//127.0.0.1/api/account/...
@Path("/account")
public class AccountService {

     @GET
    @Path("/login")
    public String get(){

        return "get the login";
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User post(){
        User user = new User();
//        user.setName("he");
//        user.setSex(2);
        return user;
    }

}
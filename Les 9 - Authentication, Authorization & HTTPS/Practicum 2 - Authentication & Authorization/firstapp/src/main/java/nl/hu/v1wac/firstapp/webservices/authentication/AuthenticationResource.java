package nl.hu.v1wac.firstapp.webservices.authentication;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.v1wac.firstapp.persistence.UserPostgresDaoImpl;
import nl.hu.v1wac.firstapp.persistence.interfaces.UserDao;

import java.security.Key;
import javax.ws.rs.Path;
import java.util.Calendar;

import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class AuthenticationResource {
    final static public Key key = MacProvider.generateKey();
    private UserDao dao = new UserPostgresDaoImpl();


    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("email") String username,
                                     @FormParam("wachtwoord") String password) {
        try {
            System.out.println("trying to login with name: " + username);
            System.out.println("trying to login with password: " + username);

  
            UserPostgresDaoImpl dao = new UserPostgresDaoImpl();
            String role = dao.findRoleForUser(username, password);
            if (role == null) {
                throw new IllegalArgumentException("No user found!");
            }
    
            Calendar expiration = Calendar.getInstance();
            expiration.add(Calendar.MINUTE, 30);
            String token = Jwts.builder()
                    .setSubject(username)
                    .claim("role", role)
                    .setExpiration(expiration.getTime())
                    .signWith(SignatureAlgorithm.HS512, key)
                    .compact();
     
            return Response.ok(token).build();
        } catch (JwtException | IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    

   


}
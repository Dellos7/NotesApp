package authentication;

import annotations.Secured;
import io.jsonwebtoken.*;
import model.data.beans.UserInfo;
import sun.misc.BASE64Decoder;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Key;

/**
 * Created by david on 8/3/16.
 */
public class AuthenticationService {

    //Should be a really secure password or key
    private static final String securePassword = "superSecureKey";
    //The key which is used in the Claims to set the role
    private static final String ROLE_CLAIM = "role";

    public static boolean checkAuthentication( String token, Secured.Role role ) throws IOException {
        byte[] decodedPassword = getDecodedPassword();
        try {
            //We check the token --> we also check if the role is the correct one
            Jws<Claims> tokenInfo = Jwts.parser().require( ROLE_CLAIM, role.getRole() ).setSigningKey( decodedPassword ).parseClaimsJws(token);
            //We extract the user info from the token
            //String username = tokenInfo.getBody().getSubject();
            //Secured.Role userRole = Secured.Role.valueOf( (String) tokenInfo.getBody().get( ROLE_CLAIM ) );
        }
        catch( SignatureException | IncorrectClaimException e) {
            System.out.println( e.getMessage() );
            return false;
        }
        return true;
    }

    public static String login( String username, String encodedPassword ) {
        UserInfo userInfo = new UserInfo(); //retrieve user info from bd using username and encodedPassword
        String token = null;
        if( userInfo.isAuthenticated() ) {
            try {
                token = generateToken( userInfo );
            } catch (IOException e) {}
        }
        return token;
    }

    public static String generateToken( UserInfo userInfo ) throws IOException {
        //Token creation
        byte[] decodedPassword = getDecodedPassword();
        Key key = new SecretKeySpec( decodedPassword, 0, decodedPassword.length, "DES" );
        //setSubject --> Username
        //in this case we are adding a claim which identifies a role
        String username = userInfo.getUsername();
        Secured.Role userRole = userInfo.getUserRole();
        String generatedToken = Jwts.builder().setSubject( username ).claim( ROLE_CLAIM, userRole.getRole() ).signWith( SignatureAlgorithm.HS512, key ).compact();
        return generatedToken;
    }

    /**
     * Converts Secure Password into Base 64 byte array
     * @return
     * @throws IOException
     */
    private static byte[] getDecodedPassword() throws IOException {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte [] decodedKey = base64Decoder.decodeBuffer( securePassword );
        return decodedKey;
    }

}

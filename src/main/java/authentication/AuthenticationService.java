package authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import sun.misc.BASE64Decoder;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Key;

/**
 * Created by david on 8/3/16.
 */
public class AuthenticationService {

    //Should be a really secure password or key
    private static final String securePassword = "superSecureKey";

    public static boolean authenticate( String token ) throws IOException {
        //Token creation
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte [] encodedKey = base64Decoder.decodeBuffer( securePassword );
        Key key = new SecretKeySpec( encodedKey, 0, encodedKey.length, "DES" );
        //setSubject --> Username
        String s = Jwts.builder().setSubject( token ).signWith( SignatureAlgorithm.HS512, key ).compact();
        System.out.println( "-------------" );
        System.out.println( "Token: " +  s );
        System.out.println( "-------------" );
        return true;

        //Token verify
        /*try {

            Jwts.parser().setSigningKey(key).parseClaimsJws(compactJwt);

            //OK, we can trust this JWT

        } catch (SignatureException e) {

            //don't trust the JWT!
        }*/
    }

}

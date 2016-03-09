package annotations;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by david on 9/3/16.
 */
@NameBinding
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Secured {

    //It is necessary for them to be lower case
    public enum Role {
        user( "user" ),
        admin( "admin" );

        String roleString;

        private Role( String roleString ) {
            this.roleString = roleString;
        }

        public String getRole() {
            return this.roleString;
        }
    }

    Role role() default Role.user;

}
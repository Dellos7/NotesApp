package model.data;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by david on 7/2/16.
 */
public class ConversionUtils {

    /*public static <T> T[] fromListObjectToArray( List<T> objectList ) {
        T[] objectArray = new T[ objectList.size() ];
        objectArray = objectList.toArray( objectArray );
        return objectArray;
    }*/

    public static <T> T[] fromListObjectToArray( final List<T> obj ) {
        if (obj == null || obj.isEmpty()) {
            return null;
        }
        final T t = obj.get(0);
        final T[] res = (T[]) Array.newInstance(t.getClass(), obj.size());
        for (int i = 0; i < obj.size(); i++) {
            res[i] = obj.get(i);
        }
        return res;
    }

}

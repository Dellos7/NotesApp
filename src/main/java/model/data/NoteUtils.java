package model.data;

import java.util.List;

/**
 * Created by david on 7/2/16.
 */
public class NoteUtils {

    public static Note[] fromListObjectToArray( List<Note> notesListObject ) {
        Note[] notesArray = new Note[ notesListObject.size() ];
        notesArray = notesListObject.toArray( notesArray );
        return notesArray;
    }

}

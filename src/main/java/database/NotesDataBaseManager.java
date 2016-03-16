package database;

import dao.NoteDao;
import model.data.Note;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * Created by david on 7/2/16.
 */
public class NotesDataBaseManager {

    public static NoteDao noteDao;

    public static void createDao( String persistenceUnit ) {
        noteDao = new NoteDao( persistenceUnit );
    }

    public static List<Note> getNotes() {
        return noteDao.getNotes();
    }

    public static Note getNote( Long id ) {
        return noteDao.getNote( id );
    }

    public static void newNote( Note note ) {
        noteDao.newNote( note );
    }

    public static void deleteNote( Note note ) {
        noteDao.deleteNote( note );
    }

    public static boolean deleteNote( Long id ) {
       return  noteDao.deleteNote( id );
    }
}

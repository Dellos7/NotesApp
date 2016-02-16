package database;

import dao.NoteDao;
import model.data.Note;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * Created by david on 7/2/16.
 */
public class DataBaseManager {

    public static final String LOCAL_BD = "local";
    public static final String HEROKU_BD = "heroku";
    public static NoteDao noteDao = new NoteDao( DataBaseManager.LOCAL_BD ) ;

    private DataBaseManager() {}

    public static void setDataBase( String dbPersistenceUnit ) {
        noteDao = new NoteDao( dbPersistenceUnit );
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

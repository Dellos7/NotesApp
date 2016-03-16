package dao;

import model.data.Note;

import javax.persistence.*;
import java.util.List;

/**
 * Created by david on 7/2/16.
 */
public class NoteDao {

    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    public NoteDao( String persistenceUnit ) {
        super();
        emf = Persistence.createEntityManagerFactory( persistenceUnit );
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    public List<Note> getNotes() {
        Query query = em.createNamedQuery( Note.RETRIEVE_ALL );
        List<Note> notes = query.getResultList();
        return notes;
    }

    public Note getNote( Long id ) {
        Note note = null;
        Query query = em.createNamedQuery( Note.RETRIEVE_BY_ID );
        query.setParameter( "id", id );
        List<Note> notesList = query.getResultList();
        if( !notesList.isEmpty() ) {
            return notesList.get( 0 );
        }
        return note;
    }

    public void newNote( Note note ) {
        tx.begin();
        em.persist( note );
        tx.commit();
    }

    public void deleteNote( Note note ) {
        tx.begin();
        em.remove( note );
        tx.commit();
    }

    public boolean deleteNote( Long id ) {
        Note note = getNote( id );
        if( note != null ) {
            deleteNote( note );
            return true;
        }
        return false;
    }


}

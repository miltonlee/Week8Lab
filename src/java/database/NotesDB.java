package database;

import models.Note;
import java.text.ParseException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class NotesDB {

    public int insert(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {

            trans.begin();
            em.persist(note);
            trans.commit();

        } catch (Exception ex) {
            trans.rollback();

        } finally {
            em.close();
        }
        return 1;
    }

    public int update(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {

            trans.begin();
            em.merge(note);
            trans.commit();
            trans.rollback();
            em.close();

        } catch (Exception ex) {
            trans.rollback();

        } finally {
            em.close();
        }
        return 1;
    }

    public List<Note> getAll() throws NotesDBException, ParseException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

      try {
            List<Note> notes = em.createNamedQuery("Note.findAll", Note.class).getResultList();
            return notes;                
        } finally {
            em.close();
        }

       
    }

    public Note getNote(int noteId) throws NotesDBException, ParseException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
     

        try {

         Note note = em.find(Note.class, noteId);
         return note;

        }  finally {
            em.close();
        }

    }

    public int delete(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {

            trans.begin();
            em.remove(em.merge(note));
            trans.commit();
            trans.rollback();
            em.close();

        } catch (Exception ex) {
            trans.rollback();

        } finally {
            em.close();
        }
        return 1;
    }
}

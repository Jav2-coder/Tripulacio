package net.xaviersala.Notes;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NotesUnit");
        EntityManager e = emf.createEntityManager();

        List<Nota> notes = Arrays.asList(
            new Nota("Base de dades", 10),
            new Nota("Programació", 9)
            );
        Alumne alumne = new Alumne();
        alumne.setNom("Frederic");
        alumne.setNotes(notes);

        e.getTransaction().begin();
        e.persist(alumne);
        e.getTransaction().commit();

        e.close();

    }
}

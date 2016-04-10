package net.javierjimenez.Tripulacio;

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Tripulants");
        EntityManager e = emf.createEntityManager();

        

        /*e.getTransaction().begin();
        e.persist();
        e.getTransaction().commit();

        e.close();*/

    }
}

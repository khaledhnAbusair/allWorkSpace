/**
 * 
 */
package jpa.firstsample;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author PSLPT 147
 *
 */
public class InsertTest {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa.firstsample");
		EntityManager em = emf.createEntityManager();

		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());

		Location entity = new Location();
		entity.setId(10389);// same ID in database
		entity.setLatitude(35.5);
		entity.setLongitude(34.3);
		entity.setName("Khlaed new location");
		entity.setDescription("this is the location updated by khaled");
		entity.setActiveFlag(ActiveFlag.ACTIVE);
		em.getTransaction().begin();

		// save to database/ insert
		entity = em.merge(entity);

		// entity = em.find(Location.class, 8787);
		// // em.persist(entity);
		// em.remove(entity);

		em.getTransaction().commit();

		System.out.println("done");
	}
}

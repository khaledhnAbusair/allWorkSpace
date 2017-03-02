/**
 * 
 */
package jpa.firstsample;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * @author PSLPT 147
 *
 */
public class QueryTest {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa.firstsample");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("select obj from LocationEntity as obj where obj.id >= 200");
		List<?> list = query.getResultList();
		for (Object object : list) {
			Location location = (Location) object;
			System.out.println(location.getId() + ": " + location.getName());
		}

		query = em.createQuery("select l.id,l.name from LocationEntity as l");
		list = query.getResultList();
		for (Object object : list) {
			Object[] fields = (Object[]) object;
			System.out.println(Arrays.toString(fields));
		}

		System.out.println("done");
	}
}

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 */

/**
 * @author PSLPT 147
 *
 */
public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/beans.xml");
		// UserRepository repository = context.getBean(UserRepository.class);
		//
		// User user = new User();
		// user.setEmail("mohammad.s.abdellatif@gmail.com");
		// user.setPassword("IHate@Khaled");
		// repository.save(user);
		System.out.println("done");
	}
}

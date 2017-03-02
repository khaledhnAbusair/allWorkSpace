import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;

import com.progressoft.jip.dao.Employee;
import com.progressoft.jip.dao.EmployeeDao;

public class DynamicProxyTest {
	public static void main(String[] args) {
		ClassLoader loader = DynamicProxyTest.class.getClassLoader();
		Object object = Proxy.newProxyInstance(loader, new Class[] { EmployeeDao.class, Comparable.class },
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Thread.currentThread().getStackTrace();
						System.out.println("==================");
						System.out.println(method.getName());
						System.out.println("arguments: " + Arrays.toString(args));
						if (method.getName().equals("listAll")) {
							return new ArrayList<>();
						}
						return null;
					}
				});
		EmployeeDao dao = (EmployeeDao) object;
		Class<? extends Object> class1 = object.getClass();
		System.out.println(Proxy.isProxyClass(class1));
		System.out.println(class1.getName());
		dao.create(new Employee());
		dao.delete(new Employee());
		dao.listAll();
		System.out.println("done");
		
	}
}

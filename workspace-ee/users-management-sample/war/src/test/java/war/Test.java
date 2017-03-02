package war;

public class Test {
	public static void main(String[] args) {
		String url = "/static/bootstrap/css/bootstrap.min.css";
		System.out.println(url.matches("/static/[\\w\\W]*"));
	}
}

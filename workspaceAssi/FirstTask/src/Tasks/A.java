package Tasks;

public interface A {

	public void test();

	public void play(String a);

	default void speed() {
		System.out.println("Hello");
	};
}

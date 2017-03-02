package jdbc.settings;

public class Test {

	public static void main(String[] args) {
		Setting setting = Setting.getSetting();
		System.out.println(setting.getDBDriver());
	}

}

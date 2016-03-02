package ma.junit.test.JUnitTest_3;

public class App {

	private String appName;

	private String appVersion;

	private int incrementVersion;

	public App() {
	}

	public App(String appName, String appVersion, int incrementVersion) {
		super();
		this.appName = appName;
		this.appVersion = appVersion;
		this.incrementVersion = incrementVersion;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public int getIncrementVersion() {
		return incrementVersion;
	}

	public void setIncrementVersion(int incrementVersion) {
		this.incrementVersion = incrementVersion;
	}

	// METHODS
	public String findAppByName(String appName) {
		return "New App Name";
	}

}

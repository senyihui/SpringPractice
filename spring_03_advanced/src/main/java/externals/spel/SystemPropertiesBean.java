
package externals.spel;

public class SystemPropertiesBean {

	private String javaVersion;
	private String javaHome;

	public SystemPropertiesBean(String javaVersion, String javaHome) {
		this.javaVersion = javaVersion;
		this.javaHome = javaHome;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public String getJavaHome() {
		return javaHome;
	}

	@Override
	public String toString() {
		return "SystemPropertiesBean [javaVersion=" + javaVersion + ", javaHome=" + javaHome + "]";
	}
	
}

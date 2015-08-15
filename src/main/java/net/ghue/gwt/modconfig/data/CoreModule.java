package net.ghue.gwt.modconfig.data;

public enum CoreModule {
	HTTP("com.google.gwt.http.HTTP", "HTTP"), JSON("com.google.gwt.json.JSON",
			"JSON"), JUNIT("com.google.gwt.junit.JUnit", "Junit"), USER("com.google.gwt.user.User", "User"), XML("com.google.gwt.xml.XML", "XML");

	public static CoreModule fromName(String name) {
		for (CoreModule coreModule : values()) {
			if (coreModule.getName().equals(name)) {
				return coreModule;
			}
		}
		return null;
	}

	private final String name;
	private final String path;

	private CoreModule(String path, String name) {
		this.path = path;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}
}
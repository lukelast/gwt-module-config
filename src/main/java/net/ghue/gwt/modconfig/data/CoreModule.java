package net.ghue.gwt.modconfig.data;

public enum CoreModule {
	HTTP("com.google.gwt.http.HTTP", "HTTP"), JSON("com.google.gwt.json.JSON", "JSON"), JUNIT(
			"com.google.gwt.junit.JUnit",
			"Junit"), USER("com.google.gwt.user.User", "User"), XML("com.google.gwt.xml.XML", "XML");

	public static CoreModule fromDisplayText(String text) {
		for (CoreModule coreModule : values()) {
			if (coreModule.getDisplayText().equals(text)) {
				return coreModule;
			}
		}
		return null;
	}

	public static CoreModule fromPath(String path) {
		for (CoreModule coreModule : values()) {
			if (coreModule.getPath().equals(path)) {
				return coreModule;
			}
		}
		return null;
	}

	private final String displayText;
	private final String path;

	private CoreModule(String path, String displayText) {
		this.path = path;
		this.displayText = displayText;
	}

	public String getDisplayText() {
		return displayText;
	}

	public String getPath() {
		return path;
	}
}
package net.ghue.gwt.modconfig.data;

public enum UserAgent {

	GECKO("gecko1_8"), IE10("ie10"), IE8("ie8"), IE9("ie9"), SAFARI("safari");

	public static UserAgent parse(String value) {
		value = value.trim().toLowerCase();
		for (UserAgent ua : UserAgent.values()) {
			if (ua.getValue().equals(value)) {
				return ua;
			}
		}
		return null;
	}

	private final String value;

	private UserAgent(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}

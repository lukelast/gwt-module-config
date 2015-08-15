package net.ghue.gwt.modconfig.data;

public enum EmulatedStackRecordOption {
	NONE(""), LINE_NUMBERS("compiler.emulatedStack.recordLineNumbers"), FILE_NAMES(
			"compiler.emulatedStack.recordFileNames");

	private EmulatedStackRecordOption(String name) {
		this.name = name;
	}

	private final String name;

	public String getName() {
		return name;
	}
}
package net.ghue.gwt.modconfig.data;

public enum EmulatedStackRecordOption {
	FILE_NAMES(
			"compiler.emulatedStack.recordFileNames"), LINE_NUMBERS("compiler.emulatedStack.recordLineNumbers"), NONE("");

	private final String name;

	private EmulatedStackRecordOption(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
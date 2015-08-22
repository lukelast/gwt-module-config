package net.ghue.gwt.modconfig.data;

public enum StackMode {
	EMULATED, NATIVE, STRIP;

	public StackMode parse(String text) {
		return StackMode.valueOf(text.toUpperCase());
	};

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
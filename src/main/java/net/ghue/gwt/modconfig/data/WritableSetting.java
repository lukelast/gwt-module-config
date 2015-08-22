package net.ghue.gwt.modconfig.data;

public interface WritableSetting {
	void parseValue(String text);

	void setComment(String comment);
}

package net.ghue.gwt.modconfig.data;

public interface PrintableSetting {
	String getComment();

	String getValueAsString();

	boolean shouldPrint();
}

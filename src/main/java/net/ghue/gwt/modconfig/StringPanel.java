package net.ghue.gwt.modconfig;

import gwt.material.design.client.ui.MaterialTextBox;

public final class StringPanel extends Panel {

	private final MaterialTextBox value = new MaterialTextBox();

	public StringPanel() {
		this.addContent(this.value);
	}

	public String getValue() {
		return this.value.getText();
	}

	public void setValue(String value) {
		this.value.setText(value);
	}

	public void setPlaceholder(String value) {
		this.value.setPlaceholder(value);
	}
}
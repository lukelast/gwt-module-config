package net.ghue.gwt.modconfig.ui;

import com.google.gwt.user.client.TakesValue;

import gwt.material.design.client.ui.MaterialTextBox;

public final class StringPanel extends Panel implements TakesValue<String> {

	private final MaterialTextBox value = new MaterialTextBox();

	public StringPanel() {
		this.addContent(this.value);
	}

	@Override
	public String getValue() {
		return this.value.getText();
	}

	public void setPlaceholder(String value) {
		this.value.setPlaceholder(value);
	}

	@Override
	public void setValue(String value) {
		this.value.setText(value);
	}
}
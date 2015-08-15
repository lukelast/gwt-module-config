package net.ghue.gwt.modconfig.ui;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

import gwt.material.design.client.ui.MaterialCheckBox;

public final class BooleanPanel extends Panel implements HasValue<Boolean> {

	private final MaterialCheckBox value = new MaterialCheckBox();

	public BooleanPanel() {
		this.addContent(this.value);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {
		return this.value.addValueChangeHandler(handler);
	}

	@Override
	public Boolean getValue() {
		return this.value.getValue();
	}

	public void setName(String value) {
		this.value.setText(value);
	}

	@Override
	public void setValue(Boolean value) {
		this.value.setValue(value);
	}

	@Override
	public void setValue(Boolean value, boolean fireEvents) {
		this.value.setValue(value, fireEvents);
	}
}
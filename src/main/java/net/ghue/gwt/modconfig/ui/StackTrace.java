package net.ghue.gwt.modconfig.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialRadioButton;

public class StackTrace implements IsWidget {

	public interface Binder extends UiBinder<Widget, StackTrace> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	@UiField
	MaterialRadioButton stackModeNative;
	@UiField
	MaterialRadioButton stackModeEmulated;
	@UiField
	MaterialRadioButton stackModeStrip;

	@UiField
	MaterialCheckBox stackModeLines;
	@UiField
	MaterialCheckBox stackModeNames;

	private final Widget root = BINDER.createAndBindUi(this);

	@Override
	public Widget asWidget() {
		return root;
	}

	@UiHandler("stackModeNames")
	void onStackModeNames(ClickEvent event) {
		final boolean namesChecked = stackModeNames.getValue();

		stackModeLines.setValue(true);
		stackModeLines.setEnabled(!namesChecked);
	}
}
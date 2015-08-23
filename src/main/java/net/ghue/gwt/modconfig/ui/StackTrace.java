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
import net.ghue.gwt.modconfig.data.EmulatedStackRecordOption;
import net.ghue.gwt.modconfig.data.GwtModuleDataModel;
import net.ghue.gwt.modconfig.data.StackMode;

public class StackTrace implements IsWidget {

	public interface Binder extends UiBinder<Panel, StackTrace> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	private final Panel root = BINDER.createAndBindUi(this);
	@UiField
	MaterialRadioButton stackModeEmulated;
	@UiField
	MaterialCheckBox stackModeLines;
	@UiField
	MaterialCheckBox stackModeNames;
	@UiField
	MaterialRadioButton stackModeNative;

	@UiField
	MaterialRadioButton stackModeStrip;

	@Override
	public Widget asWidget() {
		return root;
	}

	public void from(GwtModuleDataModel data) {
		root.setComment(data.getStackMode().getComment());
		switch (data.getStackMode().getValue()) {
		case NATIVE:
			stackModeNative.setValue(true);
			break;
		case EMULATED:
			stackModeEmulated.setValue(true);
			break;
		case STRIP:
			stackModeStrip.setValue(true);
			break;
		}

		switch (data.getStackEmulationOption()) {
		case FILE_NAMES:
			stackModeNames.setValue(true);
			stackModeLines.setValue(true);
			stackModeLines.setEnabled(false);
			break;
		case LINE_NUMBERS:
			stackModeLines.setValue(true);
			stackModeLines.setEnabled(true);
			stackModeNames.setValue(false);
			break;
		case NONE:
			stackModeLines.setValue(false);
			stackModeLines.setEnabled(true);
			stackModeNames.setValue(false);
			break;

		}

	}

	@UiHandler("stackModeNames")
	void onStackModeNames(ClickEvent event) {
		final boolean namesChecked = stackModeNames.getValue();

		stackModeLines.setValue(true);
		stackModeLines.setEnabled(!namesChecked);
	}

	public void to(GwtModuleDataModel data) {
		data.getStackMode().setComment(root.getComment());
		if (stackModeNative.getValue()) {
			data.getStackMode().setValue(StackMode.NATIVE);
		} else if (stackModeEmulated.getValue()) {
			data.getStackMode().setValue(StackMode.EMULATED);
		} else if (stackModeStrip.getValue()) {
			data.getStackMode().setValue(StackMode.STRIP);
		}
		if (stackModeNames.getValue()) {
			data.setStackEmulationOption(EmulatedStackRecordOption.FILE_NAMES);
		} else if (stackModeLines.getValue()) {
			data.setStackEmulationOption(EmulatedStackRecordOption.LINE_NUMBERS);
		} else {
			data.setStackEmulationOption(EmulatedStackRecordOption.NONE);
		}
	}
}
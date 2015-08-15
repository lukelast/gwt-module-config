package net.ghue.gwt.modconfig.ui;

import java.util.EnumSet;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialListBox;
import net.ghue.gwt.modconfig.data.CoreModule;
import net.ghue.gwt.modconfig.data.GwtModuleDataModel;
import net.ghue.gwt.modconfig.data.UserAgent;

@Singleton
public class HomeViewImpl implements HomeView {

	public interface Binder extends UiBinder<Widget, HomeViewImpl> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(HomeViewImpl.class.getSimpleName());

	@UiField
	BooleanPanel collapseProperties;

	@UiField
	StringPanel entryPoint;

	private final Timer exportTimer = new Timer() {
		@Override
		public void run() {
			pushUItoXml();
		}
	};

	@UiField
	Panel cssResourceStyle;

	@UiField
	MaterialListBox cssResourceStyleValue;

	@UiField
	StringPanel moduleName;

	@SuppressWarnings("unused")
	private Presenter presenter;

	private final Widget root = BINDER.createAndBindUi(this);
	@UiField
	Panel userAgent;
	@UiField
	MaterialCheckBox userAgentGecko1_8;
	@UiField
	MaterialCheckBox userAgentIe10;
	@UiField
	MaterialCheckBox userAgentIe8;
	@UiField
	MaterialCheckBox userAgentIe9;

	@UiField
	MaterialCheckBox userAgentSafari;

	@UiField
	TextArea xml;

	@UiField
	StackTrace stackTrace;

	@UiField
	Panel coreModules;

	@Override
	public Widget asWidget() {
		return root;
	}

	private void importXml(String xml) {
		pushModelToUI(new GwtModuleDataModel().parseXml(xml));
	}

	@Inject
	private void init() {
		createCoreModules(EnumSet.noneOf(CoreModule.class));

		xml.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				importXml(xml.getText());
			}
		});

		Event.addNativePreviewHandler(new NativePreviewHandler() {

			@Override
			public void onPreviewNativeEvent(NativePreviewEvent event) {
				if (event.getTypeInt() == Event.ONKEYPRESS || event.getTypeInt() == Event.ONCLICK) {
					EventTarget target = event.getNativeEvent().getEventTarget();
					if (TextAreaElement.is(target)) {

					} else {
						exportTimer.cancel();
						exportTimer.schedule(500);
					}
				}
			}
		});
	}

	@UiHandler("github")
	void onGitHubClick(ClickEvent event) {
		Window.open("https://github.com/lukelast/gwt-module-config", "_blank", "");
	}

	private void pushModelToUI(GwtModuleDataModel data) {
		data.getModuleName().to(moduleName);
		data.getEntryPoint().to(entryPoint);
		data.getCollapseAllProperties().to(collapseProperties);

		userAgent.setComment(data.getUserAgentComment());
		userAgentIe8.setValue(false);
		userAgentIe9.setValue(false);
		userAgentIe10.setValue(false);
		userAgentGecko1_8.setValue(false);
		userAgentSafari.setValue(false);
		for (UserAgent ua : data.getUserAgents()) {
			switch (ua) {
			case IE8:
				userAgentIe8.setValue(true);
				break;
			case IE9:
				userAgentIe9.setValue(true);
				break;
			case IE10:
				userAgentIe10.setValue(true);
				break;
			case GECKO:
				userAgentGecko1_8.setValue(true);
				break;
			case SAFARI:
				userAgentSafari.setValue(true);
				break;
			}
		}

		for (int i = 0; i < cssResourceStyleValue.getItemCount(); i++) {
			if (data.getCssResourceStyle().getValue().equals(cssResourceStyleValue.getValue(i))) {
				cssResourceStyleValue.setSelectedIndex(i);
			}
		}
		// This hack is required to force the changed value selection to show.
		String value = cssResourceStyleValue.getValue(0);
		String text = cssResourceStyleValue.getItemText(0);
		cssResourceStyleValue.removeItem(0);
		cssResourceStyleValue.insertItem(text, value, 0);
		cssResourceStyle.setComment(data.getCssResourceStyle().getComment());

		createCoreModules(data.getCoreModules().getValue());
	}

	private void createCoreModules(EnumSet<CoreModule> enabledModules) {
		coreModules.clearContents();
		for (CoreModule coreModule : CoreModule.values()) {
			MaterialCheckBox check = new MaterialCheckBox();
			check.setText(coreModule.getName());
			check.setValue(enabledModules.contains(coreModule));
			coreModules.addContent(check);
		}
	}

	private void pushUItoXml() {
		GwtModuleDataModel data = new GwtModuleDataModel();
		data.getModuleName().from(moduleName);
		data.getEntryPoint().from(entryPoint);
		data.getCollapseAllProperties().from(collapseProperties);

		data.setUserAgentComment(userAgent.getComment());
		if (userAgentIe8.getValue()) {
			data.getUserAgents().add(UserAgent.IE8);
		}
		if (userAgentIe9.getValue()) {
			data.getUserAgents().add(UserAgent.IE9);
		}
		if (userAgentIe10.getValue()) {
			data.getUserAgents().add(UserAgent.IE10);
		}
		if (userAgentGecko1_8.getValue()) {
			data.getUserAgents().add(UserAgent.GECKO);
		}
		if (userAgentSafari.getValue()) {
			data.getUserAgents().add(UserAgent.SAFARI);
		}

		data.getCssResourceStyle().setComment(cssResourceStyle.getComment());
		data.getCssResourceStyle().setValue(cssResourceStyleValue.getSelectedValue());

		data.getCoreModules().setComment(coreModules.getComment());
		data.getCoreModules().getValue().clear();
		for (Widget widget : coreModules.getContents()) {
			if (widget instanceof MaterialCheckBox) {
				MaterialCheckBox check = (MaterialCheckBox) widget;
				if (check.getValue()) {
					data.getCoreModules().getValue().add(CoreModule.fromName(check.getText()));
				}
			}
		}

		xml.setText(data.toXml());
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
package net.ghue.gwt.modconfig;

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

import gwt.material.design.client.ui.MaterialTextBox;
import net.ghue.gwt.modconfig.data.GwtModuleModel;

@Singleton
public class HomeViewImpl implements HomeView {

	public interface Binder extends UiBinder<Widget, HomeViewImpl> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	private static final Logger LOG = Logger.getLogger(HomeViewImpl.class.getSimpleName());

	@UiField
	StringPanel entryPoint;

	@UiField
	Panel moduleName;
	@UiField
	MaterialTextBox moduleNameValue;

	private Presenter presenter;

	private final Widget root = BINDER.createAndBindUi(this);

	@UiField
	TextArea xml;

	private final Timer exportTimer = new Timer() {

		@Override
		public void run() {
			pushUItoXml();
		}
	};

	@Override
	public Widget asWidget() {
		return root;
	}

	private void importXml(String xml) {

		GwtModuleModel mod = new GwtModuleModel();
		mod.parseXml(xml);

		pushModelToUI(mod);
	}

	@Inject
	private void init() {
		xml.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				importXml(xml.getText());
			}
		});

		Event.addNativePreviewHandler(new NativePreviewHandler() {

			@Override
			public void onPreviewNativeEvent(NativePreviewEvent event) {
				if (event.getTypeInt() == Event.ONKEYPRESS) {
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

	private void pushModelToUI(GwtModuleModel mod) {
		moduleNameValue.setText(mod.getModuleName().getValue());
		moduleName.setComment(mod.getModuleName().getComment());
		entryPoint.setValue(mod.getEntryPoint().getValue());
		entryPoint.setComment(mod.getEntryPoint().getComment());
	}

	private void pushUItoXml() {
		GwtModuleModel mod = new GwtModuleModel();
		mod.getModuleName().setValue(moduleNameValue.getText());
		mod.getModuleName().setComment(moduleName.getComment());
		mod.getEntryPoint().setValue(entryPoint.getValue());
		mod.getEntryPoint().setComment(entryPoint.getComment());

		xml.setText(mod.toXml());
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
package net.ghue.gwt.modconfig;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class HomeViewImpl implements HomeView {

	public interface Binder extends UiBinder<Widget, HomeViewImpl> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	private static final Logger LOG = Logger.getLogger(HomeViewImpl.class.getSimpleName());

	@UiField
	TextBox entryPoint;

	@UiField
	TextBox moduleName;

	private Presenter presenter;

	private final Widget root = BINDER.createAndBindUi(this);

	@UiField
	TextArea xml;

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

	}

	@UiHandler("moduleName")
	void onModuleNameChanged(ChangeEvent event) {
		pushUItoXml();
	}

	private void pushModelToUI(GwtModuleModel mod) {
		moduleName.setText(mod.getModuleName());
		entryPoint.setText(mod.getEntryPoint());
	}

	private void pushUItoXml() {
		GwtModuleModel mod = new GwtModuleModel();
		mod.setModuleName(moduleName.getText());
		mod.setEntryPoint(entryPoint.getText());

		xml.setText(mod.toXml());
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
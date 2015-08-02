package net.ghue.gwt.modconfig;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class HomeViewImpl implements HomeView {

	public interface Binder extends UiBinder<Widget, HomeViewImpl> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	private final Widget w = binder.createAndBindUi(this);

	private Presenter presenter;

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public Widget asWidget() {
		return w;
	}
}
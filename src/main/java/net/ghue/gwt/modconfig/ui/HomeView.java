package net.ghue.gwt.modconfig.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.ImplementedBy;

@ImplementedBy(HomeViewImpl.class)
public interface HomeView extends IsWidget {

	public interface Presenter {

	}

	void setPresenter(Presenter presenter);
}
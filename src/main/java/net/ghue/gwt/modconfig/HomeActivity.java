package net.ghue.gwt.modconfig;

import com.google.inject.Inject;

public class HomeActivity implements HomeView.Presenter {

	private final HomeView view;

	@Inject
	HomeActivity(HomeView view) {
		this.view = view;
		this.view.setPresenter(this);
	}
}
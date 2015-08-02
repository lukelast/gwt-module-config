package net.ghue.gwt.modconfig;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class HomeActivity extends AbstractActivity implements HomeView.Presenter {

	private final HomeView view;

	@Inject
	HomeActivity(HomeView view) {
		this.view = view;
	}

	@Override
	public void start(AcceptsOneWidget panel, com.google.gwt.event.shared.EventBus eventBus) {
		view.setPresenter(this);
		panel.setWidget(view);
	}
}
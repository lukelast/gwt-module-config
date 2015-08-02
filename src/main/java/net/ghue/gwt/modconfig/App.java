package net.ghue.gwt.modconfig;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class App implements EntryPoint {

	@Override
	public void onModuleLoad() {
		AppGinjector injector = GWT.create(AppGinjector.class);

		SimplePanel mainContainer = new SimplePanel();
		MainActivityMapper mainMapper = injector.mainActivityMapper();
		ActivityManager mainManager = new ActivityManager(mainMapper, injector.eventBus());
		mainManager.setDisplay(mainContainer);

		RootPanel.get().add(mainContainer);

		injector.placeHistoryHandler().handleCurrentHistory();
	}
}
package net.ghue.gwt.modconfig;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class App implements EntryPoint {
	@Override
	public void onModuleLoad() {
		AppGinjector injector = GWT.create(AppGinjector.class);
		RootPanel.get().add(injector.getHomeView());
	}
}
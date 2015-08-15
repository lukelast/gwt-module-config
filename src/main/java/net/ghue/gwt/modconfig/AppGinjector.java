package net.ghue.gwt.modconfig;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.web.bindery.event.shared.EventBus;

import net.ghue.gwt.modconfig.ui.HomeView;

@GinModules(AppGinModule.class)
public interface AppGinjector extends Ginjector {

	EventBus eventBus();

	HomeView getHomeView();
}
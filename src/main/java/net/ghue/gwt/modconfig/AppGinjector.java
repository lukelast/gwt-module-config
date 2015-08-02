package net.ghue.gwt.modconfig;

import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.web.bindery.event.shared.EventBus;

@GinModules(AppGinModule.class)
public interface AppGinjector extends Ginjector {

	EventBus eventBus();

	PlaceHistoryHandler placeHistoryHandler();

	MainActivityMapper mainActivityMapper();
}

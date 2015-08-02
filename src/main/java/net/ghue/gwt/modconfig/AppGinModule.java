package net.ghue.gwt.modconfig;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryHandler.DefaultHistorian;
import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class AppGinModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class);
		bind(SimpleEventBus.class).in(Singleton.class);

		bind(PlaceHistoryMapper.class).to(AppPlaceHistoryMapper.class);
		// For best UX at login time (preserving place), use an Html5History:
		// https://gist.github.com/1883821
		bind(Historian.class).to(DefaultHistorian.class);

		install(new GinFactoryModuleBuilder().build(MainActivityMapper.Factory.class));
	}

	private Place getDefaultPlace() {
		return new HomePlace();
	}

	@Provides
	@Singleton
	PlaceController providePlaceController(EventBus eventBus) {
		return new PlaceController(eventBus);
	}

	@Provides
	@Singleton
	PlaceHistoryHandler providePlaceHistoryHandler(PlaceHistoryMapper mapper, Historian historian,
			PlaceController controller, EventBus eventBus) {
		PlaceHistoryHandler handler = new PlaceHistoryHandler(mapper, historian);
		handler.register(controller, eventBus, getDefaultPlace());
		return handler;
	}

	@Provides
	Scheduler provideScheduler() {
		return Scheduler.get();
	}

}
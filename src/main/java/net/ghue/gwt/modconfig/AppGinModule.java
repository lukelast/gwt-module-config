package net.ghue.gwt.modconfig;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class AppGinModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class);
		bind(SimpleEventBus.class).in(Singleton.class);
	}

	@Provides
	Scheduler provideScheduler() {
		return Scheduler.get();
	}
}
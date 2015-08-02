package net.ghue.gwt.modconfig;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

public class MainActivityMapper implements ActivityMapper {

	private static final Logger logger = Logger.getLogger(MainActivityMapper.class.getName());

	public interface Factory {
		HomeActivity homeActivity();
	}

	private final Factory factory;

	@Inject
	MainActivityMapper(Factory factory) {
		this.factory = factory;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof HomePlace) {
			return factory.homeActivity();
		}

		logger.severe("Unhandled place type: " + place.getClass().getName());
		return null;
	}
}
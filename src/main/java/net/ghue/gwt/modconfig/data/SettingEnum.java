package net.ghue.gwt.modconfig.data;

import java.util.Objects;

public class SettingEnum<T extends Enum<T>> extends Setting<T> {

	SettingEnum(T defaultValue) {
		super(Objects.requireNonNull(defaultValue));
	}

	@Override
	public void parseValue(String text) {
		try {
			Enum<T> typedEnum = this.getValue();
			@SuppressWarnings("unchecked")
			T value = (T) Enum.valueOf(typedEnum.getClass(), text.toUpperCase());
			this.setValue(value);
		} catch (RuntimeException ex) {
			// Ignore.
		}
	}
}

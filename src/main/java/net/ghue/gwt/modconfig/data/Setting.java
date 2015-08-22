package net.ghue.gwt.modconfig.data;

import com.google.gwt.user.client.TakesValue;

public class Setting<T> implements TakesValue<T>, TakesComment, PrintableSetting, WritableSetting {

	public static <T> Setting<T> create(T defaultValue) {
		return new Setting<T>(defaultValue);
	}

	private String comment = "";

	private final T defaultValue;
	private T value;

	Setting(T defaultValue) {
		this.defaultValue = defaultValue;
		this.setValue(this.defaultValue);
	}

	public <S extends TakesValue<T> & TakesComment> void from(S obj) {
		this.setComment(obj.getComment());
		this.setValue(obj.getValue());
	}

	@Override
	public String getComment() {
		return (comment == null) ? "" : comment;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public String getValueAsString() {
		return getValue().toString();
	}

	public boolean hasComment() {
		return !getComment().isEmpty();
	}

	public boolean isDefaultValue() {
		return this.defaultValue.equals(this.getValue());
	}

	@SuppressWarnings("unchecked")
	public void parseValue(String text) {
		if (text != null) {
			if (this.value instanceof String) {
				this.value = (T) text;
			} else if (this.value instanceof Boolean) {
				this.value = (T) Boolean.valueOf(text);
			}
		}
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment.trim();
	}

	@Override
	public void setValue(T value) {
		if (value != null) {
			this.value = value;
		}
	}

	@Override
	public boolean shouldPrint() {
		return hasComment() || !isDefaultValue();
	}

	public <S extends TakesValue<T> & TakesComment> void to(S obj) {
		obj.setComment(this.getComment());
		obj.setValue(this.getValue());
	}

	public Setting<T> withComment(String comment) {
		setComment(comment);
		return this;
	}

	public Setting<T> withValue(T value) {
		setValue(value);
		return this;
	}

}
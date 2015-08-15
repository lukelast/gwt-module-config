package net.ghue.gwt.modconfig.data;

import com.google.gwt.user.client.TakesValue;

public final class CommentedValue<T> implements TakesValue<T>, TakesComment {

	public static <T> CommentedValue<T> create(T defaultValue) {
		return new CommentedValue<T>(defaultValue);
	}

	public CommentedValue(T defaultValue) {
		this.defaultValue = defaultValue;
		this.setValue(this.defaultValue);
	}

	private String comment = "";
	private T value;
	private final T defaultValue;

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

	public boolean hasComment() {
		return !getComment().isEmpty();
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

	public boolean isDefaultValue() {
		return this.defaultValue.equals(this.getValue());
	}

	public <S extends TakesValue<T> & TakesComment> void to(S obj) {
		obj.setComment(this.getComment());
		obj.setValue(this.getValue());
	}

	public CommentedValue<T> withComment(String comment) {
		setComment(comment);
		return this;
	}

	public CommentedValue<T> withValue(T value) {
		setValue(value);
		return this;
	}

}
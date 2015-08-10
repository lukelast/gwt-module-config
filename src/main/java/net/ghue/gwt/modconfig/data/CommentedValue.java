package net.ghue.gwt.modconfig.data;

public final class CommentedValue<T> {

	public static <T> CommentedValue<T> create(T defaultValue) {
		return new CommentedValue<T>().setValue(defaultValue);
	}

	private T value;
	private String comment = "";

	public T getValue() {
		return value;
	}

	public CommentedValue<T> setValue(T value) {
		this.value = value;
		return this;
	}

	public String getComment() {
		return (comment == null) ? "" : comment;
	}

	public CommentedValue<T> setComment(String comment) {
		this.comment = comment;
		return this;
	}

	public boolean hasComment() {
		return !getComment().isEmpty();
	}
}
package net.ghue.gwt.modconfig.data;

import net.ghue.gwt.modconfig.XmlConverter;

public final class GwtModuleModel {

	private final CommentedValue<String> entryPoint = CommentedValue.create("");

	private final CommentedValue<String> moduleName = CommentedValue.create("");

	public GwtModuleModel parseXml(String xml) {
		XmlConverter.fromXml(this, xml);
		return this;
	}

	public String toXml() {
		return XmlConverter.toXml(this);
	}

	public CommentedValue<String> getEntryPoint() {
		return entryPoint;
	}

	public CommentedValue<String> getModuleName() {
		return moduleName;
	}

}
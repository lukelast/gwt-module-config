package net.ghue.gwt.modconfig.data;

import java.util.EnumSet;

import net.ghue.gwt.modconfig.xml.XmlConverter;

public final class GwtModuleDataModel {

	private final CommentedValue<Boolean> collapseAllProperties = CommentedValue.create(false);
	private final CommentedValue<EnumSet<CoreModule>> coreModules = CommentedValue
			.create(EnumSet.noneOf(CoreModule.class));
	private final CommentedValue<String> CssResourceStyle = CommentedValue.create("obfuscated");
	private final CommentedValue<String> entryPoint = CommentedValue.create("");
	private final CommentedValue<String> moduleName = CommentedValue.create("");
	private EmulatedStackRecordOption stackEmulationOption = EmulatedStackRecordOption.NONE;
	private final CommentedValue<StackMode> stackMode = CommentedValue.create(StackMode.NATIVE);
	private String userAgentComment = "";
	private final EnumSet<UserAgent> userAgents = EnumSet.noneOf(UserAgent.class);

	public CommentedValue<Boolean> getCollapseAllProperties() {
		return collapseAllProperties;
	}

	public CommentedValue<EnumSet<CoreModule>> getCoreModules() {
		return coreModules;
	}

	public CommentedValue<String> getCssResourceStyle() {
		return CssResourceStyle;
	}

	public CommentedValue<String> getEntryPoint() {
		return entryPoint;
	}

	public CommentedValue<String> getModuleName() {
		return moduleName;
	}

	public EmulatedStackRecordOption getStackEmulationOption() {
		return stackEmulationOption;
	}

	public CommentedValue<StackMode> getStackMode() {
		return stackMode;
	}

	public String getUserAgentComment() {
		return userAgentComment;
	}

	public EnumSet<UserAgent> getUserAgents() {
		return userAgents;
	}

	public GwtModuleDataModel parseXml(String xml) {
		XmlConverter.fromXml(this, xml);
		return this;
	}

	public void setStackEmulationOption(EmulatedStackRecordOption stackEmulationOption) {
		this.stackEmulationOption = stackEmulationOption;
	}

	public void setUserAgentComment(String userAgentComment) {
		this.userAgentComment = userAgentComment.trim();
	}

	public String toXml() {
		return XmlConverter.toXml(this);
	}

}
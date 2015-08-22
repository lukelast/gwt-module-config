package net.ghue.gwt.modconfig.data;

import java.util.EnumSet;

import net.ghue.gwt.modconfig.xml.XmlConverter;

public final class GwtModuleDataModel {

	private final Setting<Boolean> collapseAllProperties = Setting.create(false);
	private final Setting<EnumSet<CoreModule>> coreModules = Setting.create(EnumSet.noneOf(CoreModule.class));
	private final Setting<String> CssResourceStyle = Setting.create("obfuscated");
	private final Setting<String> entryPoint = Setting.create("");
	private final Setting<String> moduleName = Setting.create("");
	private final Setting<Boolean> rpcSerializeFinalFields = Setting.create(false);
	private final Setting<Boolean> rpcSuppressFinalFieldWarnings = Setting.create(false);
	private EmulatedStackRecordOption stackEmulationOption = EmulatedStackRecordOption.NONE;
	private final SettingEnum<StackMode> stackMode = new SettingEnum<>(StackMode.NATIVE);
	private final SettingUserAgents userAgents = new SettingUserAgents();

	public Setting<Boolean> getCollapseAllProperties() {
		return collapseAllProperties;
	}

	public Setting<EnumSet<CoreModule>> getCoreModules() {
		return coreModules;
	}

	public Setting<String> getCssResourceStyle() {
		return CssResourceStyle;
	}

	public Setting<String> getEntryPoint() {
		return entryPoint;
	}

	public Setting<String> getModuleName() {
		return moduleName;
	}

	public Setting<Boolean> getRpcSerializeFinalFields() {
		return rpcSerializeFinalFields;
	}

	public Setting<Boolean> getRpcSuppressFinalFieldWarnings() {
		return rpcSuppressFinalFieldWarnings;
	}

	public EmulatedStackRecordOption getStackEmulationOption() {
		return stackEmulationOption;
	}

	public Setting<StackMode> getStackMode() {
		return stackMode;
	}

	public Setting<EnumSet<UserAgent>> getUserAgents() {
		return userAgents;
	}

	public GwtModuleDataModel parseXml(String xml) {
		XmlConverter.fromXml(this, xml);
		return this;
	}

	public void setStackEmulationOption(EmulatedStackRecordOption stackEmulationOption) {
		this.stackEmulationOption = stackEmulationOption;
	}

	public String toXml() {
		return XmlConverter.toXml(this);
	}

}
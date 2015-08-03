package net.ghue.gwt.modconfig;

public final class GwtModuleModel {

	private String entryPoint = "";

	private String moduleName = "";

	public String getEntryPoint() {
		return entryPoint;
	}
	public String getModuleName() {
		return moduleName;
	}

	public GwtModuleModel parseXml(String xml) {
		XmlConverter.fromXml(this, xml);
		return this;
	}

	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String toXml() {
		return XmlConverter.toXml(this);
	}
}
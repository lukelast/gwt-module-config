package net.ghue.gwt.modconfig.xml;

import net.ghue.gwt.modconfig.data.GwtModuleDataModel;

public final class XmlConverter {

	public static void fromXml(GwtModuleDataModel mod, String xml) {
		new XmlParser(xml).parse(mod);
	}

	public static String toXml(GwtModuleDataModel model) {
		return new XmlGenerator(model).generateString();
	}
}
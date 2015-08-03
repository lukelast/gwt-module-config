package net.ghue.gwt.modconfig;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.xml.client.DOMException;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

final class XmlConverter {
	private static final String ATTR_ENTRY_POINT_CLASS = "class";
	private static final String ATTR_RENAME_TO = "rename-to";
	private static final String EL_ENTRY_POINT = "entry-point";
	private static final String EL_MODULE = "module";
	private static final Logger LOG = Logger.getLogger(XmlConverter.class.getSimpleName());
	private static final char NEWLINE = '\n';

	public static void fromXml(GwtModuleModel mod, String xml) {
		try {

			// parse the XML document into a DOM
			Document doc = XMLParser.parse(xml);

			mod.setModuleName(doc.getDocumentElement().getAttribute(ATTR_RENAME_TO));

			NodeList entryPoints = doc.getElementsByTagName(EL_ENTRY_POINT);
			if (entryPoints.getLength() == 1) {
				mod.setEntryPoint(
						entryPoints.item(0).getAttributes().getNamedItem(ATTR_ENTRY_POINT_CLASS).getNodeValue());
			}

		} catch (DOMException ex) {
			LOG.log(Level.SEVERE, "XML Parsing", ex);
		}
	}

	public static String toXml(GwtModuleModel model) {

		Document doc = XMLParser.createDocument();

		Element module = doc.createElement(EL_MODULE);
		doc.appendChild(module);

		if (!model.getModuleName().isEmpty()) {
			module.setAttribute(ATTR_RENAME_TO, model.getModuleName());
		}

		if (!model.getEntryPoint().isEmpty()) {
			Element ep = doc.createElement(EL_ENTRY_POINT);
			ep.setAttribute(ATTR_ENTRY_POINT_CLASS, model.getEntryPoint());
			module.appendChild(ep);
		}

		StringBuilder sb = new StringBuilder(1024);

		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append(NEWLINE);
		sb.append("<!DOCTYPE module PUBLIC \"-//Google Inc.//DTD Google Web Toolkit 2.7.0//EN\"").append(NEWLINE);
		sb.append("\"http://gwtproject.org/doctype/2.7.0/gwt-module.dtd\">").append(NEWLINE);

		sb.append(doc.toString());

		return sb.toString();
	}
}
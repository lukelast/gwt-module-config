package net.ghue.gwt.modconfig.xml;

import static net.ghue.gwt.modconfig.xml.Constants.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import com.google.gwt.xml.client.impl.DOMParseException;

import net.ghue.gwt.modconfig.data.WritableSetting;

/**
 * Wrap an XML {@link Document} and provide low level access to parsing
 * elements.
 * 
 * @author Luke Last
 *
 */
final class DocReader {
	private static Iterable<Element> iterateElements(NodeList nodes) {
		List<Element> nodeList = new ArrayList<>(nodes.getLength());
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node instanceof Element) {
				nodeList.add((Element) node);
			}
		}
		return nodeList;
	}
	private final Document doc;

	/**
	 * Module element.
	 */
	private final Element root;

	public DocReader(String xml) throws DOMParseException {
		doc = XMLParser.parse(xml);
		doc.normalize();
		root = doc.getDocumentElement();
	}

	void copyAndRemoveComment(WritableSetting dest, Node node) {
		dest.setComment(findAndRemoveComment(node));
	}

	String findAndRemoveComment(Node node) {
		node = node.getPreviousSibling();
		if (node != null) {
			// Skip over whitespace text.
			while (node.getNodeType() == Node.TEXT_NODE && node.getPreviousSibling() != null) {
				node = node.getPreviousSibling();
			}
			if (node.getNodeType() == Node.COMMENT_NODE) {
				node.getParentNode().removeChild(node);
				return node.getNodeValue();
			}
		}
		return ""; // No comment found.
	}

	Iterable<Element> findElements(String element) {
		return iterateElements(root.getElementsByTagName(element));
	}

	Iterable<Element> findElements(String elementName, String name) {
		Iterable<Element> iterable = findElements(elementName);
		for (Iterator<Element> iterator = iterable.iterator(); iterator.hasNext();) {
			if (!name.equalsIgnoreCase(iterator.next().getAttribute(ATTR_NAME))) {
				iterator.remove();
			}
		}
		return iterable;
	}

	Element getModuleElement() {
		return this.root;
	}

	String getUnusedElements() {
		root.normalize();
		return root.getChildNodes().toString();
	}

	void parseConfigurationProperty(String name, WritableSetting setting) {
		for (Element element : findElements(EL_SET_CONFIG_PROPERTY, name)) {
			setting.parseValue(element.getAttribute(ATTR_VALUE));
			copyAndRemoveComment(setting, element);
			remove(element);
		}
	}

	void parseProperty(String name, WritableSetting setting) {
		for (Element element : findElements(EL_SET_PROPERTY, name)) {
			copyAndRemoveComment(setting, element);
			setting.parseValue(element.getAttribute(ATTR_VALUE));
			remove(element);
		}
	}

	void remove(Node node) {
		root.removeChild(node);
	}
}

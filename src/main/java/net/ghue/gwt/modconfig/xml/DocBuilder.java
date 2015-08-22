package net.ghue.gwt.modconfig.xml;

import static net.ghue.gwt.modconfig.xml.Constants.*;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.XMLParser;

import net.ghue.gwt.modconfig.data.PrintableSetting;

final class DocBuilder {

	final class ElementBuilder {
		private final Element element;

		public ElementBuilder(String elementName) {
			this.element = doc.createElement(elementName);
		}

		ElementBuilder attr(String attrKey, String value) {
			if (notEmpty(attrKey) && notEmpty(value)) {
				element.setAttribute(attrKey, value);
			}
			return this;
		}

		void build() {
			appendNextLine(element);
		}

		ElementBuilder name(String name) {
			return attr(ATTR_NAME, name);
		}

		ElementBuilder value(PrintableSetting value) {
			return attr(ATTR_VALUE, value.getValueAsString());
		}

		ElementBuilder value(String value) {
			return attr(ATTR_VALUE, value);
		}
	}

	private static boolean notEmpty(String str) {
		return str != null && !str.isEmpty();
	}

	private final Document doc = XMLParser.createDocument();
	private int indentLevel = 0;
	/**
	 * The document root element.
	 */
	private final Element module;

	public DocBuilder() {
		module = doc.createElement(EL_MODULE);
		doc.appendChild(module);
		indent();
	}

	DocBuilder appendComment(PrintableSetting comment) {
		return appendComment(comment.getComment());
	}

	DocBuilder appendComment(String comment) {
		if (notEmpty(comment)) {
			appendNextLine(doc.createComment(comment));
		}
		return this;
	}

	DocBuilder appendElement(String elementName) {
		appendNextLine(doc.createElement(elementName));
		return this;
	}

	void appendNewlineIndent() {
		final StringBuilder sb = new StringBuilder();
		sb.append(NEWLINE);
		for (int i = 0; i < indentLevel; i++) {
			sb.append("  ");
		}
		module.appendChild(doc.createTextNode(sb.toString()));
	}

	void appendNextLine(Node node) {
		appendNewlineIndent();
		module.appendChild(node);
	}

	void deindent() {
		indentLevel = Math.max(0, indentLevel - 1);
	}

	ElementBuilder element(String elementName) {
		return new ElementBuilder(elementName);
	}

	void indent() {
		indentLevel++;
	}

	ElementBuilder setConfigProperty() {
		return new ElementBuilder(EL_SET_CONFIG_PROPERTY);
	}

	void setModuleAttr(String attrName, String attrValue) {
		if (notEmpty(attrName) && notEmpty(attrValue)) {
			module.setAttribute(attrName, attrValue);
		}
	}

	ElementBuilder setProperty() {
		return new ElementBuilder(EL_SET_PROPERTY);
	}

	@Override
	public String toString() {
		deindent();
		appendNewlineIndent(); // Final newline.
		return doc.toString();
	}

}

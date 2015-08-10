package net.ghue.gwt.modconfig;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialTitle;

public class Panel extends Composite {

	public interface Binder extends UiBinder<Widget, Panel> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(Panel.class.getSimpleName());

	@UiField
	MaterialTextBox comment;
	private Widget content;
	@UiField
	MaterialColumn contentColumn;
	private final Widget root = BINDER.createAndBindUi(this);
	@UiField
	MaterialTitle title;

	@UiChild(tagname = "content")
	public void addContent(Widget item) {
		this.content = item;
		this.contentColumn.add(item);
	}

	public Panel() {
		initWidget(root);
	}

	public String getComment() {
		return comment.getText();
	}

	public Widget getContent() {
		return content;
	}

	public void setComment(String comment) {
		this.comment.setText(comment);
	}

	public void setDescription(String description) {
		this.title.setDescription(description);
	}

	@Override
	public void setTitle(String title) {
		this.title.setTitle(title);
	}

}
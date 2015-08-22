package net.ghue.gwt.modconfig.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialTitle;
import net.ghue.gwt.modconfig.data.TakesComment;

public class Panel extends Composite implements TakesComment {

	public interface Binder extends UiBinder<Widget, Panel> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(Panel.class.getSimpleName());

	@UiField
	MaterialTextBox comment;
	@UiField
	MaterialColumn contentColumn;
	@UiField
	MaterialLink link;
	private final Widget root = BINDER.createAndBindUi(this);
	@UiField
	MaterialTitle title;

	public Panel() {
		initWidget(root);
	}

	@UiChild(tagname = "content")
	public void addContent(Widget item) {
		this.contentColumn.add(item);
	}

	public void clearContents() {
		this.contentColumn.clear();
	}

	@Override
	public String getComment() {
		return comment.getText();
	}

	public List<Widget> getContents() {
		List<Widget> widgets = new ArrayList<>();
		for (int i = 0; i < contentColumn.getWidgetCount(); i++) {
			widgets.add(contentColumn.getWidget(i));
		}
		return widgets;
	}

	@Override
	public void setComment(String comment) {
		this.comment.setText(comment);
	}

	public void setDescription(String description) {
		this.title.setDescription(description);
	}

	public void setLink(String url) {
		link.setHref(url);
		link.setVisible(true);
	}

	@Override
	public void setTitle(String title) {
		this.title.setTitle(title);
	}

}
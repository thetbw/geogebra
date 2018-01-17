package org.geogebra.web.web.gui.dialog.image;

import org.geogebra.common.GeoGebraConstants.Versions;
import org.geogebra.common.euclidian.EuclidianConstants;
import org.geogebra.common.kernel.ModeSetter;
import org.geogebra.common.main.Localization;
import org.geogebra.web.html5.Browser;
import org.geogebra.web.html5.main.AppW;
import org.geogebra.web.web.gui.dialog.DialogBoxW;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * dialog to ask user for webcam permission
 * 
 * @author Alicia
 *
 */
public class WebcamPermissionDialog extends DialogBoxW implements ClickHandler {
	private AppW app1;
	private FlowPanel mainPanel;
	private FlowPanel buttonPanel;
	private Button cancelBtn;
	private Label text;
	private WebcamInputDialog webcamInputDialog;

	/**
	 * @param app
	 *            application
	 */
	public WebcamPermissionDialog(AppW app) {
		super(app.getPanel(), app);
		this.app1 = app;
		initGUI();
	}

	private void initGUI() {
		mainPanel = new FlowPanel();

		text = new Label();
		cancelBtn = new Button("");
		cancelBtn.addClickHandler(this);
		buttonPanel = new FlowPanel();
		buttonPanel.setStyleName("DialogButtonPanel");
		buttonPanel.add(cancelBtn);

		mainPanel.add(text);
		mainPanel.add(buttonPanel);
		add(mainPanel);
		addStyleName("GeoGebraPopup");
		addStyleName("mowPermissionDialog");
	}

	/**
	 * set button labels and dialog title
	 */
	public void setLabels() {
		Localization loc = app1.getLocalization();
		getCaption().setText(
				/* loc.getMenu("Camera") */"Allow GeoGebra to access your Camera?");
		String message;
		if (app1.getVersion() == Versions.WEB_FOR_DESKTOP) {
			message = "";
		} else if (Browser.isFirefox()) {
			message = loc.getMenu("Webcam.Firefox");
		} else if (Browser.isEdge()) {
			message = loc.getMenu("Webcam.Edge");
		} else {
			message = loc.getMenu("Webcam.Chrome");
		}
		text.setText(message);
		cancelBtn.setText(loc.getMenu("Cancel"));
	}

	public void onClick(ClickEvent event) {
		Object source = event.getSource();
		if (source == cancelBtn) {
			cancel();
		}
	}

	/**
	 * init webcam dialog and start video
	 */
	public void initWebcamInputDialog() {
		if (webcamInputDialog == null) {
		webcamInputDialog = new WebcamInputDialog((AppW) app, this);
		} else {
			webcamInputDialog.startVideo();
		}
	}

	@Override
	public void center() {
		super.center();
		setLabels();
	}

	private void cancel() {
		hide();
		webcamInputDialog.hide();
		app1.getGuiManager().setMode(EuclidianConstants.MODE_MOVE,
				ModeSetter.TOOLBAR);
	}
}

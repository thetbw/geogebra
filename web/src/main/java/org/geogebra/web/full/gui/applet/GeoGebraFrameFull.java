package org.geogebra.web.full.gui.applet;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import org.geogebra.common.euclidian.EuclidianConstants;
import org.geogebra.common.gui.AccessibilityGroup;
import org.geogebra.common.javax.swing.SwingConstants;
import org.geogebra.common.main.App;
import org.geogebra.common.main.App.InputPosition;
import org.geogebra.web.full.css.MaterialDesignResources;
import org.geogebra.web.full.gui.GuiManagerW;
import org.geogebra.web.full.gui.MyHeaderPanel;
import org.geogebra.web.full.gui.app.GGWMenuBar;
import org.geogebra.web.full.gui.app.GGWToolBar;
import org.geogebra.web.full.gui.app.ShowKeyboardButton;
import org.geogebra.web.full.gui.applet.panel.PanelTransitioner;
import org.geogebra.web.full.gui.browser.BrowseGUI;
import org.geogebra.web.full.gui.keyboard.KeyboardManager;
import org.geogebra.web.full.gui.laf.GLookAndFeel;
import org.geogebra.web.full.gui.layout.DockGlassPaneW;
import org.geogebra.web.full.gui.layout.DockManagerW;
import org.geogebra.web.full.gui.layout.DockPanelW;
import org.geogebra.web.full.gui.layout.panels.AlgebraPanelInterface;
import org.geogebra.web.full.gui.layout.panels.EuclidianDockPanelW;
import org.geogebra.web.full.gui.pagecontrolpanel.PageListPanel;
import org.geogebra.web.full.gui.toolbar.mow.ToolbarMow;
import org.geogebra.web.full.gui.toolbarpanel.ToolbarPanel;
import org.geogebra.web.full.gui.util.VirtualKeyboardGUI;
import org.geogebra.web.full.gui.view.algebra.AlgebraViewW;
import org.geogebra.web.full.gui.view.algebra.RetexKeyboardListener;
import org.geogebra.web.full.main.AppWFull;
import org.geogebra.web.full.main.GDevice;
import org.geogebra.web.full.main.HeaderResizer;
import org.geogebra.web.full.main.NullHeaderResizer;
import org.geogebra.web.html5.gui.FastClickHandler;
import org.geogebra.web.html5.gui.GeoGebraFrameW;
import org.geogebra.web.html5.gui.laf.GLookAndFeelI;
import org.geogebra.web.html5.gui.tooltip.ToolTipManagerW;
import org.geogebra.web.html5.gui.util.CancelEventTimer;
import org.geogebra.web.html5.gui.util.MathKeyboardListener;
import org.geogebra.web.html5.gui.view.button.StandardButton;
import org.geogebra.web.html5.gui.zoompanel.FocusableWidget;
import org.geogebra.web.html5.main.AppW;
import org.geogebra.web.html5.main.JsEval;
import org.geogebra.web.html5.util.ArticleElement;
import org.geogebra.web.html5.util.ArticleElementInterface;
import org.geogebra.web.html5.util.CopyPasteW;
import org.geogebra.web.html5.util.Dom;
import org.geogebra.web.html5.util.debug.LoggerW;
import org.geogebra.web.html5.util.keyboard.VirtualKeyboardW;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Frame for applets with GUI
 *
 */
public class GeoGebraFrameFull
		extends GeoGebraFrameW implements NativePreviewHandler, FrameWithHeaderAndKeyboard,
		FastClickHandler, KeyUpHandler {

	private AppletFactory factory;
	private DockGlassPaneW glass;
	private GGWToolBar ggwToolBar = null;
	private GGWMenuBar ggwMenuBar;
	private KeyboardState keyboardState;
	private final SimplePanel kbButtonSpace = new SimplePanel();
	private GDevice device;
	private boolean keyboardShowing = false;
	private ShowKeyboardButton showKeyboardButton;
	private int keyboardHeight;
	private ToolbarMow toolbarMow;
	private PageListPanel pageListPanel;
	private PanelTransitioner panelTransitioner;
	private HeaderResizer headerResizer;

	public GeoGebraFrameFull(GLookAndFeelI laf, ArticleElementInterface articleElement) {
		super(laf, articleElement);
	}

	/**
	 * @param factory
	 *            factory for applets (2D or 3D)
	 * @param laf
	 *            look and feel
	 * @param device
	 *            browser/tablet; if left null, defaults to browser
	 * @param articleElement
	 *            article with parameters
	 */
	public GeoGebraFrameFull(AppletFactory factory, GLookAndFeelI laf,
							 GDevice device, ArticleElementInterface articleElement) {
		super(laf, articleElement);
		this.device = device;
		this.factory = factory;
		panelTransitioner = new PanelTransitioner(this);
		kbButtonSpace.addStyleName("kbButtonSpace");
		this.add(kbButtonSpace);
		headerResizer = NullHeaderResizer.get();
		Event.addNativePreviewHandler(this);
	}

	@Override
	protected AppW createApplication(ArticleElementInterface article,
			GLookAndFeelI laf) {
		AppW application = factory.getApplet(article, this, laf, this.device);
		if (!app.isApplet()) {
			CopyPasteW.installCutCopyPaste(application, RootPanel.getBodyElement());
		} else {
			CopyPasteW.installCutCopyPaste(application, getElement());
		}

		if (app != null) {
			kbButtonSpace.addStyleName("kbButtonSpace");
			this.add(kbButtonSpace);
		}

		if (app != null && app.isUnbundled()) {
			addStyleName("newToolbar");
		}

		this.glass = new DockGlassPaneW();
		this.add(glass);
		headerResizer = getApp().getActivity()
				.getHeaderResizer(application.getAppletFrame());
		return application;
	}

	/**
	 * Main entry points called by geogebra.web.full.Web.startGeoGebra()
	 *
	 * @param geoGebraMobileTags
	 *            list of &lt;article&gt; elements of the web page
	 * @param factory
	 *            applet factory
	 * @param laf
	 *            look and feel
	 * @param device
	 *            browser/tablet; if left null, defaults to browser
	 */
	public static void main(ArrayList<ArticleElement> geoGebraMobileTags,
			AppletFactory factory, GLookAndFeel laf, GDevice device) {

		for (final ArticleElement articleElement : geoGebraMobileTags) {
			final GeoGebraFrameFull inst = new GeoGebraFrameFull(factory, laf,
					device, articleElement);
			LoggerW.startLogger(articleElement);
			inst.createSplash();
			RootPanel.get(articleElement.getId()).add(inst);
		}
	}

	/**
	 * @param el
	 *            html element to render into
	 * @param factory
	 *            applet factory
	 * @param laf
	 *            look and feel
	 * @param clb
	 *            call this after rendering
	 */
	public static void renderArticleElement(Element el, AppletFactory factory,
			GLookAndFeel laf, JavaScriptObject clb) {

		GeoGebraFrameW.renderArticleElementWithFrame(el, new GeoGebraFrameFull(
				factory, laf, null, ArticleElement.as(el)),
				clb);
	}

	/**
	 * @return glass pane for view moving
	 */
	public DockGlassPaneW getGlassPane() {
		return this.glass;
	}

	/**
	 * Attach glass pane to frame
	 */
	public void attachGlass() {
		if (this.glass != null) {
			this.add(glass);
		}
	}

	/**
	 * @param panel Shows this full-screen panel.
	 */
	public void showPanel(MyHeaderPanel panel) {
		panelTransitioner.showPanel(panel);
	}

	@Override
	public void hidePanel(MyHeaderPanel panel) {
		panelTransitioner.hidePanel(panel);
	}

	@Override
	public void setSize(int width, int height) {
		MyHeaderPanel currentPanel = panelTransitioner.getCurrentPanel();
		if (currentPanel != null) {
			currentPanel.setPixelSize(width, height);
			currentPanel.resizeTo(width, height);
		} else {
			super.setSize(width, height);
			app.adjustViews(true, height > width
					|| getGuiManager().isVerticalSplit(false));
		}
	}

	@Override
	public void updateHeaderSize() {
		headerResizer.resizeHeader();
	}

	@Override
	public void doShowKeyBoard(final boolean show,
			MathKeyboardListener textField) {
		if (keyboardState == KeyboardState.ANIMATING_IN
				|| keyboardState == KeyboardState.ANIMATING_OUT) {
			return;
		}

		if (this.isKeyboardShowing() == show) {
			getKeyboardManager().setOnScreenKeyboardTextField(textField);
			return;
		}

		if (!show) {
			getKeyboardManager().onScreenEditingEnded();
		}

		app.getEuclidianView1().setKeepCenter(false);
		if (show) {
			showZoomPanel(false);
			keyboardState = KeyboardState.ANIMATING_IN;
			app.hideMenu();
			app.persistWidthAndHeight();
			ToolTipManagerW.hideAllToolTips();
			addKeyboard(textField, true);
			if (app.isPortrait()) {
				getGuiManager().getLayout().getDockManager()
						.adjustViews(true);
			}
		} else {
			showZoomPanel(true);
			keyboardState = KeyboardState.ANIMATING_OUT;
			app.persistWidthAndHeight();
			showKeyboardButton(textField);
			removeKeyboard();
			keyboardState = KeyboardState.HIDDEN;
		}

		// this.mainPanel.add(this.dockPanel);

		Timer timer = new Timer() {
			@Override
			public void run() {

				scrollToInputField();

			}
		};
		timer.schedule(0);
	}

	private void removeKeyboard() {
		final VirtualKeyboardGUI keyBoard = getOnScreenKeyboard(null);
		this.setKeyboardShowing(false);

		ToolbarPanel toolbarPanel = getGuiManager()
				.getUnbundledToolbar();
		if (toolbarPanel != null) {
			toolbarPanel.updateMoveButton();
		}
		app.updateSplitPanelHeight();

		keyboardHeight = 0;
		keyBoard.remove(new Runnable() {
			@Override
			public void run() {
				keyBoard.resetKeyboardState();
				getApp().centerAndResizeViews();
			}
		});
	}

	/**
	 * Show keyboard and connect it to textField
	 *
	 * @param textField
	 *            keyboard listener
	 * @param animated
	 *            whether to animate the keyboard in
	 */
	private void addKeyboard(final MathKeyboardListener textField, boolean animated) {
		final VirtualKeyboardGUI keyboard = getOnScreenKeyboard(textField);
		if (keyboard == null) {
			return;
		}

		this.setKeyboardShowing(true);

		updateMoreButton(keyboard, textField);

		ToolbarPanel toolbarPanel = getGuiManager()
				.getUnbundledToolbar();
		if (toolbarPanel != null) {
			toolbarPanel.hideMoveFloatingButton();
		}

		keyboard.prepareShow(animated);
		if (!app.isWhiteboardActive()) {
			app.addAsAutoHidePartnerForPopups(keyboard.asWidget().getElement());
		}
		CancelEventTimer.keyboardSetVisible();
		getApp().getKeyboardManager().addKeyboard(this);
		Runnable callback = new Runnable() {

			@Override
			public void run() {
				// this is async, maybe we canceled the keyboard
				if (!isKeyboardShowing()) {
					remove(keyboard);
					return;
				}
				final boolean showPerspectivesPopup = getApp()
						.isPerspectivesPopupVisible();
				onKeyboardAdded(keyboard);
				if (showPerspectivesPopup) {
					getApp().showPerspectivesPopup();
				}
				if (!getApp().isWhiteboardActive()) {
					if (textField != null) {
						textField.setFocus(true);
					}
				}
			}
		};
		if (animated) {
			keyboard.afterShown(callback);
		} else {
			callback.run();
		}
	}

	private void updateMoreButton(VirtualKeyboardW keyboard, MathKeyboardListener textField) {
		if (shouldShowMoreButtonFor(textField)) {
			keyboard.showMoreButton();
		} else {
			keyboard.hideMoreButton();
		}
	}

	private boolean shouldShowMoreButtonFor(MathKeyboardListener textField) {
		boolean acceptsCommandInserts =
				textField instanceof RetexKeyboardListener
						&& ((RetexKeyboardListener) textField).acceptsCommandInserts();
		return textField == null || acceptsCommandInserts;
	}

	/**
	 * Callback for keyboard; takes care of resizing
	 *
	 * @param keyBoard
	 *            keyboard
	 */
	private void onKeyboardAdded(final VirtualKeyboardGUI keyBoard) {
		KeyboardManager keyboardManager = getApp().getKeyboardManager();
		if (keyboardManager.shouldDetach()) {
			keyboardHeight = 0;
		} else {
			keyboardHeight = keyboardManager
					.estimateKeyboardHeight();
		}

		app.updateSplitPanelHeight();

		app.updateViewSizes();
		keyboardManager.addKeyboard(this);
		keyBoard.setVisible(true);
		app.centerAndResizeViews();
		keyboardState = KeyboardState.SHOWN;
	}

	/**
	 * Scroll to the input-field, if the input-field is in the algebraView.
	 */
	private void scrollToInputField() {
		if (app.showAlgebraInput()
				&& app.getInputPosition() == InputPosition.algebraView) {
			AlgebraPanelInterface dp = (AlgebraPanelInterface) (app
					.getGuiManager()
					.getLayout().getDockManager().getPanel(App.VIEW_ALGEBRA));

			dp.scrollToActiveItem();
		}
	}

	private void showZoomPanel(boolean show) {
		if (app.isPortrait()) {
			return;
		}

		EuclidianDockPanelW dp = (EuclidianDockPanelW) (getGuiManager()
				.getLayout().getDockManager().getPanel(App.VIEW_EUCLIDIAN));
		if (show) {
			dp.showZoomPanel();
		} else {
			dp.hideZoomPanel();
		}

	}

	@Override
	public boolean showKeyBoard(boolean show, MathKeyboardListener textField,
			boolean forceShow) {
		if (forceShow && isKeyboardWantedFromCookie()) {
			doShowKeyBoard(show, textField);
			return true;
		}

		return keyBoardNeeded(show && isKeyboardWantedFromCookie(), textField);
	}

	@Override
	public boolean keyBoardNeeded(boolean show,
			MathKeyboardListener textField) {
		if (this.keyboardState == KeyboardState.ANIMATING_IN) {
			return true;
		}
		if (this.keyboardState == KeyboardState.ANIMATING_OUT) {
			return false;
		}

		if (app.isUnbundled() && !app.isWhiteboardActive()
				&& getGuiManager()
						.getUnbundledToolbar() != null
				&& !getGuiManager().getUnbundledToolbar()
						.isOpen()) {
			return false;
		}

		if (app.getLAF().isTablet()
				|| isKeyboardShowing()
									// showing, we don't have
									// to handle the showKeyboardButton
				|| getKeyboardManager().shouldKeyboardBeShown()
				|| keyboardNeededForGraphicsTools()) {
			doShowKeyBoard(show, textField);
			showKeyboardButton(textField);
			return true;
		}

		showKeyboardButton(textField);
		return false;
	}

	private boolean keyboardNeededForGraphicsTools() {
		return app.isApplet() && app.isShowToolbar()
				&& app.getActiveEuclidianView()
				.getEuclidianController()
						.modeNeedsKeyboard();
	}

	/**
	 * @param show
	 *            whether to show keyboard button
	 */
	public void showKeyboardButton(boolean show) {
		if (showKeyboardButton == null) {
			if (show) {
				DockManagerW dm = getGuiManager().getLayout()
						.getDockManager();
				DockPanelW dockPanelKB = dm.getPanelForKeyboard();

				if (dockPanelKB != null) {
					showKeyboardButton = new ShowKeyboardButton(this, dm,
							(AppWFull) app);
				}
			}
		}

		if (showKeyboardButton != null) {
			add(showKeyboardButton);
			showKeyboardButton.setVisible(show);
		}
	}

	private void showKeyboardButton(final MathKeyboardListener textField) {
		if (appNeedsKeyboard()) {
			Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
				@Override
				public void execute() {
					showKeyboardButton(isButtonNeeded(textField));
				}
			});
		}
	}

	private boolean isButtonNeeded(MathKeyboardListener textField) {
		MathKeyboardListener keyboardListener = getGuiManager().getKeyboardListener();
		if (app.getGuiManager().hasSpreadsheetView() || app.isUnbundled()) {
			return keyboardListener != null;
		}

		return app.isKeyboardNeeded()
				&& (textField != null && textField.hasFocus()
				|| keyboardListener != null && keyboardListener.hasFocus());
	}

	private boolean appNeedsKeyboard() {
		if (app.showAlgebraInput()
				&& app.getInputPosition() == InputPosition.algebraView
				&& app.showView(App.VIEW_ALGEBRA)) {
			return true;
		}

		return getGuiManager().getLayout().getDockManager()
				.getPanelForKeyboard() != null;
	}

	private GuiManagerW getGuiManager() {
		return (GuiManagerW) app.getGuiManager();
	}

	@Override
	public void refreshKeyboard() {
		if (isKeyboardShowing()) {
			final VirtualKeyboardW keyBoard = getOnScreenKeyboard(null);
			if (app.isKeyboardNeeded()) {
				ensureKeyboardDeferred();
				add(keyBoard);
			} else {
				removeKeyboard();
			}
		} else {
			if (app != null && app.isKeyboardNeeded() && appNeedsKeyboard()
					&& isKeyboardWantedFromCookie()) {
				if (!app.isStartedWithFile()
						&& !app.getArticleElement().preventFocus()) {
					if (getKeyboardManager()
							.isKeyboardClosedByUser()) {
						ensureKeyboardEditing();
						return;
					}
					setKeyboardShowing(true);
					app.invokeLater(new Runnable() {

						@Override
						public void run() {
							if (getApp().isWhiteboardActive()) {
								return;
							}
							getApp().persistWidthAndHeight();
							addKeyboard(null, false);
							ensureKeyboardDeferred();

						}
					});
				} else {
					showKeyboardButton(null);
					getOnScreenKeyboard(null).showOnFocus();
					app.adjustScreen(true);
				}

			} else if (app != null && app.isKeyboardNeeded()) {
				if (!isKeyboardWantedFromCookie()) {
					showKeyboardButton(null);
				} else {
					showKeyboardButton(true);
				}
			}
		}
	}

	private KeyboardManager getKeyboardManager() {
		return getApp().getKeyboardManager();
	}

	private VirtualKeyboardGUI getOnScreenKeyboard(
			MathKeyboardListener textField) {
		return getApp().getKeyboardManager().getOnScreenKeyboard(textField,
				this);
	}

	/**
	 * Schedule keyboard editing in 500ms
	 */
	private void ensureKeyboardDeferred() {
		new Timer() {

			@Override
			public void run() {
				if (getApp().getGuiManager().hasAlgebraView()) {
					AlgebraViewW av = getApp()
							.getAlgebraView();
					// av.clearActiveItem();
					av.setDefaultUserWidth();
				}

				ensureKeyboardEditing();
			}

		}.schedule(500);
	}

	/**
	 * Make sure keyboard is editing
	 */
	private void ensureKeyboardEditing() {
		GuiManagerW guiManager = getGuiManager();
		DockManagerW dm = guiManager.getLayout()
				.getDockManager();
		MathKeyboardListener ml = guiManager
				.getKeyboardListener(dm.getPanelForKeyboard());
		dm.setFocusedPanel(dm.getPanelForKeyboard());

		getKeyboardManager().setOnScreenKeyboardTextField(ml);

		if (ml != null) {
			ml.setFocus(true);
			ml.ensureEditing();
		}
	}

	@Override
	public boolean isKeyboardShowing() {
		return this.keyboardShowing;
	}

	@Override
	public void showKeyboardOnFocus() {
		if (app != null) {
			getOnScreenKeyboard(null).showOnFocus();
		}
	}

	@Override
	public void updateKeyboardHeight() {
		KeyboardManager keyboardManager = getApp().getKeyboardManager();
		if (isKeyboardShowing() && !keyboardManager.shouldDetach()) {
			int newHeight = keyboardManager
					.estimateKeyboardHeight();

			if (newHeight > 0) {
				app.updateSplitPanelHeight();
				keyboardHeight = newHeight;
				app.updateCenterPanelAndViews();
				add(getOnScreenKeyboard(null));
			}
		}
	}

	@Override
	public double getKeyboardHeight() {
		return isKeyboardShowing() ? keyboardHeight : 0;
	}

	private static boolean isKeyboardWantedFromCookie() {
		String wanted = Cookies.getCookie("GeoGebraKeyboardWanted");
		return !"false".equals(wanted);
	}

	/**
	 * Adds menu; if toolbar is missing also add it
	 *
	 * @param app1
	 *            application
	 */
	public void attachMenubar(AppW app1) {
		if (app1.isUnbundled() || app1.isWhiteboardActive()) {
			return;
		}
		if (ggwToolBar == null) {
			ggwToolBar = new GGWToolBar();
			ggwToolBar.init(app1);
			insert(ggwToolBar, 0);
		}
		ggwToolBar.attachMenubar();
	}

	/**
	 * Adds toolbar
	 *
	 * @param app1
	 *            application
	 */
	public void attachToolbar(AppW app1) {
		if (app1.isWhiteboardActive()) {
			attachToolbarMow(app1);

			if (app1.getVendorSettings().isMainMenuExternal()) {
				app1.getGuiManager().menuToGlobalHeader();
			} else {
				attachMowMainMenu(app1);
			}
			app1.getGuiManager().initShareActionInGlobalHeader();
			initPageControlPanel(app1);
			return;
		}

		if (app1.isUnbundled()) {
			// do not attach old toolbar
			return;
		}
		// reusing old toolbar is probably a good decision
		if (ggwToolBar == null) {
			ggwToolBar = new GGWToolBar();
			ggwToolBar.init(app1);
		} else {
			ggwToolBar.updateClassname(app1.getToolbarPosition());
		}

		if (app1.getToolbarPosition() == SwingConstants.SOUTH) {
			add(ggwToolBar);
		} else {
			insert(ggwToolBar, 0);
		}
	}

	private void attachMowMainMenu(final AppW app) {
		StandardButton openMenuButton = new StandardButton(
				MaterialDesignResources.INSTANCE.menu_black_whiteBorder(), null,
				24, app);

		openMenuButton.addFastClickHandler(this);
		openMenuButton.addDomHandler(this, KeyUpEvent.getType());

		openMenuButton.addStyleName("mowOpenMenuButton");
		new FocusableWidget(AccessibilityGroup.MENU, null, openMenuButton).attachTo(app);
		add(openMenuButton);
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			app.toggleMenu();
		}
	}

	private void attachToolbarMow(AppW app) {
		initToolbarMowIfNull(app);
		if (app.getToolbarPosition() == SwingConstants.SOUTH) {
			add(toolbarMow);
		} else {
			insert(toolbarMow, 0);
		}
		add(toolbarMow.getUndoRedoButtons());
		add(toolbarMow.getPageControlButton());
	}

	private void initToolbarMowIfNull(AppW app) {
		if (toolbarMow == null) {
			toolbarMow = new ToolbarMow(app);
		}
	}

	/**
	 * @return MOW toolbar
	 */
	public ToolbarMow getToolbarMow() {
		return toolbarMow;
	}

	/**
	 * If the toolbarMow is null then initializes it.
	 * @param app Needed for the initialization.
	 * @return toolbarMow
	 */
	@Nonnull
	public ToolbarMow getToolbarMowSafe(AppW app) {
		initToolbarMowIfNull(app);
		return toolbarMow;
	}

	@Override
	public GGWToolBar getToolbar() {
		return ggwToolBar;
	}

	@Override
	public void setMenuHeight(boolean linearInputbar) {
		// TODO in app mode we need to change menu height when inputbar is
		// visible
	}

	/**
	 * @param app1
	 *            application
	 * @return menubar
	 */
	public GGWMenuBar getMenuBar(AppW app1) {
		if (ggwMenuBar == null) {
			ggwMenuBar = new GGWMenuBar();
			((GuiManagerW) app1.getGuiManager()).setGgwMenubar(ggwMenuBar);
		}
		return ggwMenuBar;
	}

	/**
	 * Close all popups and if event was not from menu, also close menu
	 *
	 * @param event
	 *            browser event
	 */
	private void closePopupsAndMaybeMenu(NativeEvent event) {
		if (app.isMenuShowing()
				&& !Dom.eventTargetsElement(event, ggwMenuBar.getElement())
				&& !Dom.eventTargetsElement(event, getToolbarMenuElement())
				&& !getGlassPane().isDragInProgress()
				&& !app.isUnbundled() && panelTransitioner.getCurrentPanel() == null) {
			app.toggleMenu();
		}
	}

	private Element getToolbarMenuElement() {
		return getToolbar() == null ? null
				: getToolbar().getOpenMenuButtonElement();
	}

	@Override
	public void onBrowserEvent(Event event) {
		if (app == null || !app.getUseFullGui()) {
			return;
		}
		final int eventType = DOM.eventGetType(event);
		if (eventType == Event.ONMOUSEDOWN || eventType == Event.ONTOUCHSTART) {
			closePopupsAndMaybeMenu(event);
		}
	}

	/**
	 * Can be called to handle the back button event.
	 */
	public void onBackPressed() {
		if (isSubPanelOpen() && app != null) {
			GuiManagerW guiManager = getGuiManager();
			hidePanel((BrowseGUI) guiManager.getBrowseView());
		}
	}

	private boolean isSubPanelOpen() {
		return panelTransitioner.getCurrentPanel() != null;
	}

	/**
	 * Actions performed when menu button is pressed
	 */
	private void onMenuButtonPressed() {
		app.getActiveEuclidianView().getEuclidianController()
				.widgetsToBackground();
		app.hideKeyboard();
		app.closePopups();
		app.toggleMenu();
		if (app.isWhiteboardActive()) {
			pageListPanel.close();
		}
	}

	/**
	 * Update undo/redo in MOW toolbar
	 */
	public void updateUndoRedoMOW() {
		if (toolbarMow == null) {
			return;
		}
		toolbarMow.updateUndoRedoActions();
	}

	/**
	 * deselect drag button
	 */
	public void deselectDragBtn() {
		if (getApp().getZoomPanelMow() != null
				&& app.getMode() == EuclidianConstants.MODE_TRANSLATEVIEW) {
			getApp().getZoomPanelMow().deselectDragBtn();
		}
	}

	/**
	 * @param mode
	 *            new mode for MOW toolbar
	 */
	public void setToorbarMowMode(int mode) {
		if (toolbarMow == null) {
			return;
		}
		toolbarMow.setMode(mode);
	}

	private void setKeyboardShowing(boolean keyboardShowing) {
		this.keyboardShowing = keyboardShowing;
	}

	/**
	 * Create page control panel if needed
	 *
	 * @param app1
	 *            app
	 */
	@Override
	public void initPageControlPanel(AppW app1) {
		if (!app1.isWhiteboardActive()) {
			return;
		}
		if (pageListPanel == null) {
			pageListPanel = new PageListPanel(app1);
		}
	}

	/**
	 *
	 * @return pageControlPanel
	 */
	public PageListPanel getPageControlPanel() {
		return pageListPanel;
	}

	@Override
	public void onPreviewNativeEvent(NativePreviewEvent event) {
		if (event.getTypeInt() == Event.ONMOUSEDOWN
				|| event.getTypeInt() == Event.ONTOUCHSTART) {

			JavaScriptObject js = event.getNativeEvent().getEventTarget();
			JsEval.callNativeJavaScript("hideAppPicker", js);
		}
	}

	@Override
	public final void onHeaderVisible() {
		ToolbarPanel toolbar = getApp().getGuiManager()
				.getUnbundledToolbar();
		if (app.isPortrait() && toolbar != null && toolbar.isClosed()) {
			toolbar.doCloseInPortrait();
		}
	}

	@Override
	public void onPanelHidden() {
		if (app.getArticleElement().getDataParamFitToScreen()) {
			setSize(Window.getClientWidth(), computeHeight());
		} else {
			app.updateViewSizes();
		}
	}

	@Override
	protected int getSmallScreenHeaderHeight() {
		if (isExternalHeaderHidden()) {
			return 0;
		}
		return headerResizer.getSmallScreenHeight();
	}

	@Override
	public void onClick(Widget source) {
		onMenuButtonPressed();
		if (getApp().isWhiteboardActive()) {
			deselectDragBtn();
		}
	}

	@Override
	public AppWFull getApp() {
		return (AppWFull) super.getApp();
	}
}

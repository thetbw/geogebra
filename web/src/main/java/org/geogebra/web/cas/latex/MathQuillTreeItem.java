package org.geogebra.web.cas.latex;

import org.geogebra.common.euclidian.event.PointerEventType;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.StringTemplate;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.main.Feature;
import org.geogebra.common.util.AsyncOperation;
import org.geogebra.web.html5.gui.util.CancelEventTimer;
import org.geogebra.web.html5.gui.util.ClickStartHandler;
import org.geogebra.web.web.gui.view.algebra.EquationEditorListener;
import org.geogebra.web.web.gui.view.algebra.RadioTreeItem;
import org.geogebra.web.web.gui.view.algebra.ScrollableSuggestionDisplay;

import com.google.gwt.dom.client.Element;

public class MathQuillTreeItem extends RadioTreeItem
		implements EquationEditorListener {

	public MathQuillTreeItem(GeoElement geo0) {
		super(geo0);
	}

	public MathQuillTreeItem(Kernel kernel) {
		super(kernel);
	}

	/**
	 * Creates the specific tree item due to the type of the geo element.
	 * 
	 * @param geo0
	 *            the geo element which is the item for.
	 * @return The appropriate RadioTreeItem descendant.
	 */
	public static RadioTreeItem create(GeoElement geo0) {
		if (geo0.isMatrix()) {
			return new MatrixTreeItem(geo0);
		} else if (geo0.isGeoCurveCartesian()) {
			return new ParCurveTreeItem(geo0);
		} else if (geo0.isGeoFunctionConditional()) {
			return new CondFunctionTreeItem(geo0);
		}
		return new MathQuillTreeItem(geo0);
	}

	@Override
	public RadioTreeItem copy() {
		return new MathQuillTreeItem(geo);
	}

	/**
	 * This method can be used to invoke a keydown event on MathQuillGGB, e.g.
	 * key=8,alt=false,ctrl=false,shift=false will trigger a Backspace event
	 * 
	 * @param key
	 *            keyCode of the event, which is the same as "event.which", used
	 *            at keydown
	 * @param alt
	 *            boolean
	 * @param ctrl
	 *            boolean
	 * @param shift
	 *            boolean
	 */
	@Override
	public void keydown(int key, boolean alt, boolean ctrl, boolean shift) {
		if (isMinMaxPanelVisible()) {
			return;
		}
		if (commonEditingCheck()) {
			MathQuillHelper.triggerKeydown(this, latexItem.getElement(), key,
					alt, ctrl, shift);
		}
	}

	/**
	 * This method should be used to invoke a keypress on MathQuillGGB, e.g.
	 * keypress(47, false, false, false); will trigger a '/' press event... This
	 * method should be used instead of "keydown" in case we are interested in
	 * the Character meaning of the key (to be entered in a textarea) instead of
	 * the Controller meaning of the key.
	 * 
	 * @param character
	 *            charCode of the event, which is the same as "event.which",
	 *            used at keypress
	 * @param alt
	 *            boolean maybe not useful
	 * @param ctrl
	 *            boolean maybe not useful
	 * @param shift
	 *            boolean maybe not useful
	 */
	@Override
	public void keypress(char character, boolean alt, boolean ctrl,
			boolean shift, boolean more) {
		if (isMinMaxPanelVisible()) {
			return;
		}

		if (commonEditingCheck()) {
			MathQuillHelper.triggerKeypress(this, latexItem.getElement(),
					character, alt, ctrl, shift, more);
		}
	}

	/**
	 * This method can be used to invoke a keyup event on MathQuillGGB, e.g.
	 * key=13,alt=false,ctrl=false,shift=false will trigger a Enter event
	 * 
	 * @param key
	 *            keyCode of the event, which is the same as "event.which", used
	 *            at keyup
	 * @param alt
	 *            boolean
	 * @param ctrl
	 *            boolean
	 * @param shift
	 *            boolean
	 */
	@Override
	public final void keyup(int key, boolean alt, boolean ctrl, boolean shift) {
		if (isMinMaxPanelVisible()) {
			return;
		}

		if (commonEditingCheck()) {
			MathQuillHelper.triggerKeyUp(latexItem.getElement(), key, alt, ctrl,
					shift);
		}
	}

	@Override
	public final Element getLaTeXElement() {
		return latexItem.getElement();
	}

	@Override
	public void updatePosition(ScrollableSuggestionDisplay sug) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean resetAfterEnter() {
		return true;
	}

	@Override
	public String getLaTeX() {
		// TODO atm needed for CAS only
		return null;
	}

	@Override
	public boolean isForCAS() {
		return false;
	}

	@Override
	public void scrollCursorIntoView() {
		if (latexItem != null) {
			MathQuillHelper.scrollCursorIntoView(this, latexItem.getElement(),
					isInputTreeItem());
		}
	}

	@Override
	public void setFocus(boolean b, boolean sv) {
		MathQuillHelper.focusEquationMathQuillGGB(latexItem, b);
	}

	@Override
	public void insertString(String text) {
		// even worse
		// for (int i = 0; i < text.length(); i++)
		// geogebra.html5.main.DrawEquationWeb.writeLatexInPlaceOfCurrentWord(
		// seMayLatex, "" + text.charAt(i), "", false);

		MathQuillHelper.writeLatexInPlaceOfCurrentWord(this,
				latexItem.getElement(), text, "", false);
	}

	@Override
	public void cancelEditing() {
		if (isInputTreeItem()) {
			return;
		}
		// if (LaTeX) {
		MathQuillHelper.endEditingEquationMathQuillGGB(this, latexItem);
		// if (c != null) {
		// LayoutUtil.replace(ihtml, c, latexItem);
		// // ihtml.getElement().replaceChild(c.getCanvasElement(),
		// // latexItem.getElement());
		// }
		//
		// if (!latex && getPlainTextItem() != null) {
		// LayoutUtil.replace(ihtml, getPlainTextItem(), latexItem);
		// //
		// this.ihtml.getElement().replaceChild(getPlainTextItem().getElement(),
		// // latexItem.getElement());
		// }
		//
		doUpdate();
	}

	/**
	 * Starts the equation editor for the item.
	 * 
	 * @param substituteNumbers
	 *            Sets that variables must be substituted or not
	 * @return
	 */
	@Override
	protected boolean startEditing(boolean substituteNumbers) {
		String text = getTextForEditing(substituteNumbers,
				StringTemplate.latexTemplateMQedit);
		if (text == null) {
			return false;
		}
		if (errorLabel != null) {
			errorLabel.setText("");
		}
		if (isDefinitionAndValue()) {
			editLatexMQ(text);
		} else {
			renderLatexEdit(text);
		}

		MathQuillHelper.editEquationMathQuillGGB(this, latexItem, false);

		app.getGuiManager().setOnScreenKeyboardTextField(this);
		CancelEventTimer.keyboardSetVisible();
		ClickStartHandler.init(main, new ClickStartHandler(false, false) {
			@Override
			public void onClickStart(int x, int y,
					final PointerEventType type) {
				app.getGuiManager()
						.setOnScreenKeyboardTextField(MathQuillTreeItem.this);
				// prevent that keyboard is closed on clicks (changing
				// cursor position)
				CancelEventTimer.keyboardSetVisible();
			}
		});

		if (app.has(Feature.AV_INPUT_BUTTON_COVER)) {
			ihtml.insert(getClearInputButton(), 1);
			buttonPanel.setVisible(false);
		}

		return true;
	}

	public void onEnter(final boolean keepFocus) {
		if (!editing) {
			return;
		}
		stopEditing(getText(), new AsyncOperation<GeoElement>() {

			@Override
			public void callback(GeoElement obj) {
				if (keepFocus) {
					MathQuillHelper.stornoFormulaMathQuillGGB(
							MathQuillTreeItem.this, latexItem.getElement());
				}

			}
		});
	}

	@Override
	public String getText() {
		return getEditorValue(false);
	}

	/**
	 * @param latexValue
	 *            true for latex output, false for plain text
	 * @return editor content
	 */
	protected String getEditorValue(boolean latexValue) {
		if (latexItem == null)
			return "";

		String ret = MathQuillHelper
				.getActualEditedValue(latexItem.getElement(), latexValue);

		if (ret == null)
			return "";

		return ret;
	}

}

package org.geogebra.common.euclidian.smallscreen;

import org.geogebra.common.euclidian.EuclidianView;
import org.geogebra.common.kernel.geos.GeoNumeric;
import org.geogebra.common.util.debug.Log;

/**
 * Adjusts slider position on file load
 */
public class AdjustSlider extends AdjustWidget {
	private GeoNumeric number;
	private boolean horizontal;

	static final int MARGIN_X = 15;
	static final int MARGIN_Y = 15;

	/**
	 * @param num
	 *            slider
	 * @param view
	 *            view
	 */
	public AdjustSlider(GeoNumeric num, EuclidianView view) {
		super(view);

		this.number = num;

		x = number.getSliderX();
		origX = number.getOrigSliderX();

		y = number.getSliderY();
		origY = number.getOrigSliderY();

		width = number.getSliderWidth();
		origWidth = number.getOrigSliderWidth() == null ? 0
				: number.getOrigSliderWidth().doubleValue();

		horizontal = number.isSliderHorizontal();

	}

	@Override
	public boolean isOnScreen() {
		return horizontal ? isHSliderOnScreen() : isVSliderOnScreen();
	}

	private boolean isHSliderOnScreen() {
		if (origX == null) {
			return true;
		}

			if (x == origX && origX + origWidth < view.getWidth()
				&& origWidth == width && y == origY) {
				return true;
			}
		return false;
	}

	private boolean isVSliderOnScreen() {
		if (origY == null) {
			Log.debug("VSlider " + number.getLabelSimple() + " is ON screen");
			return true;
		}

		if (x == origX && origX < view.getWidth() && y == origY
				&& origY - origWidth > 0 
				&& origWidth == width) {
			Log.debug("VSlider " + number.getLabelSimple() + " is ON screen");
			return true;
		}
		Log.debug("VSlider " + number.getLabelSimple() + " is OFF screen");
		return false;
	}

	@Override
	public void apply() {
		if (isOnScreen()) {
			return;
		}

		double ratio = horizontal ? ratioX : ratioY;

		if (ratio > 1) {
			return;
		}

		x = Math.round(origX * ratioX);
		y = Math.round(origY * ratioY);
		if (horizontal) {
			adjustToRight();
		} else {
			adjustToTop();
		}


		if (width > view.getViewWidth() || width != origWidth) {
			width = Math.round(origWidth * ratio);
			number.setSliderWidth(width);
		}
		number.setSliderLocation(x, y, true);
	}

	protected void setDefaultRatio() {
		ratioX = 1;
		ratioY = 1;
	}
	private void adjustToRight() {
		if (x + width > view.getViewWidth()) {
			x = view.getViewWidth() - width - MARGIN_X;
		}

		int maxY = view.getViewHeight() - AdjustSlider.MARGIN_Y;
		if (y > maxY) {
			y = maxY;
		}

	}

	private void adjustToTop() {
		if (y - width < 0) {
			y = width + MARGIN_Y;
		}

		if (x + width > view.getViewWidth()) {
			x = view.getViewWidth() - width - MARGIN_X;
		}

	}
	/**
	 * Makes a slider onScreen with no scaling enabled.
	 * 
	 * @param num
	 *            The geo represents the slider.
	 * @param view
	 *            The view to adjust the slider on.
	 */
	public static void ensureOnScreen(GeoNumeric num, EuclidianView view) {
		AdjustSlider adjust = new AdjustSlider(num, view);
		adjust.setDefaultRatio();
		adjust.apply();
		
	}
}

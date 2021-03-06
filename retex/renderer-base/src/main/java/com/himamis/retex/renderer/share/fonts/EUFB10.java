/* EUFB10.java
 * =========================================================================
 * This file is part of the JLaTeXMath Library - http://forge.scilab.org/jlatexmath
 *
 * Copyright (C) 2018 DENIZET Calixte
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * A copy of the GNU General Public License can be found in the file
 * LICENSE.txt provided with the source distribution of this program (see
 * the META-INF directory in the source jar). This license can also be
 * found on the GNU website at http://www.gnu.org/licenses/gpl.html.
 *
 * If you did not receive a copy of the GNU General Public License along
 * with this program, contact the lead developer, or write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 *
 * Linking this library statically or dynamically with other modules
 * is making a combined work based on this library. Thus, the terms
 * and conditions of the GNU General Public License cover the whole
 * combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce
 * an executable, regardless of the license terms of these independent
 * modules, and to copy and distribute the resulting executable under terms
 * of your choice, provided that you also meet, for each linked independent
 * module, the terms and conditions of the license of that module.
 * An independent module is a module which is not derived from or based
 * on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obliged to do so.
 * If you do not wish to do so, delete this exception statement from your
 * version.
 *
 */

package com.himamis.retex.renderer.share.fonts;

import com.himamis.retex.renderer.share.FontInfo;

final class EUFB10 extends FontInfo {

	EUFB10(final String ttfPath) {
		super(0, ttfPath, 0.475342, 0.333333, 1.0, '\u0000');
	}

	@Override
	protected final void initMetrics() {
		setInfo('\u00A2', // char code: 162
				new double[] { 0.58743, 0.629288, 0., 0. }, // metrics: width,
															// height, depth,
															// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u00A3', // char code: 163
				new double[] { 0.393534, 0.691406, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u00A4', // char code: 164
				new double[] { 0.387474, 0.629288, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u00A5', // char code: 165
				new double[] { 0.592532, 0.691406, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u00A6', // char code: 166
				new double[] { 0.393214, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u00A7', // char code: 167
				new double[] { 0.397679, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u00A9', // char code: 169
				new double[] { 0.980644, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u00AA', // char code: 170
				new double[] { 0.726793, 0.475342, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u00B5', // char code: 181
				new double[] { 0.253532, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u00B6', // char code: 182
				new double[] { 0.253532, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0021', // char code: 33
				new double[] { 0.349205, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0026', // char code: 38
				new double[] { 0.871259, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\'', // char code: 39
				new double[] { 0.250343, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0028', // char code: 40
				new double[] { 0.45891, 0.749474, 0.249825, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0029', // char code: 41
				new double[] { 0.45891, 0.749474, 0.249825, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u002A', // char code: 42
				new double[] { 0.327838, 0.629288, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u002B', // char code: 43
				new double[] { 0.892944, 0.582834, 0.083185, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u002C', // char code: 44
				new double[] { 0.327838, 0.108032, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u002D', // char code: 45
				new double[] { 0.892944, 0.582834, 0.083185, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u002E', // char code: 46
				new double[] { 0.327838, 0.108032, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u002F', // char code: 47
				new double[] { 0.592532, 0.749474, 0.249825, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0030', // char code: 48
				new double[] { 0.592532, 0.475342, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0031', // char code: 49
				new double[] { 0.592532, 0.475342, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0032', // char code: 50
				new double[] { 0.592532, 0.475342, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0033', // char code: 51
				new double[] { 0.592532, 0.475342, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0034', // char code: 52
				new double[] { 0.592532, 0.475342, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0035', // char code: 53
				new double[] { 0.592532, 0.475342, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0036', // char code: 54
				new double[] { 0.592532, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0037', // char code: 55
				new double[] { 0.592532, 0.475342, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0038', // char code: 56
				new double[] { 0.592532, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0039', // char code: 57
				new double[] { 0.592532, 0.475342, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u003A', // char code: 58
				new double[] { 0.255127, 0.475342, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u003B', // char code: 59
				new double[] { 0.255127, 0.475342, 0.126038, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u003D', // char code: 61
				new double[] { 0.58169, 0.475342, 0., 0. }, // metrics: width,
															// height, depth,
															// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u003F', // char code: 63
				new double[] { 0.427976, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0041', // char code: 65
				new double[] { 0.847341, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0042', // char code: 66
				new double[] { 1.043788, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0043', // char code: 67
				new double[] { 0.723285, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0044', // char code: 68
				new double[] { 0.98192, 0.691406, 0., 0. }, // metrics: width,
															// height, depth,
															// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0045', // char code: 69
				new double[] { 0.782602, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0046', // char code: 70
				new double[] { 0.72169, 0.691406, 0.126038, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0047', // char code: 71
				new double[] { 0.927386, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0048', // char code: 72
				new double[] { 0.85053, 0.691406, 0.063019, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0049', // char code: 73
				new double[] { 0.654719, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u004A', // char code: 74
				new double[] { 0.652168, 0.691406, 0.126038, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u004B', // char code: 75
				new double[] { 0.789299, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u004C', // char code: 76
				new double[] { 0.786428, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u004D', // char code: 77
				new double[] { 1.239279, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u004E', // char code: 78
				new double[] { 0.982557, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u004F', // char code: 79
				new double[] { 0.976499, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0050', // char code: 80
				new double[] { 0.977137, 0.691406, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0051', // char code: 81
				new double[] { 0.976499, 0.691406, 0.037811, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0052', // char code: 82
				new double[] { 0.977774, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0053', // char code: 83
				new double[] { 0.978412, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0054', // char code: 84
				new double[] { 0.789937, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0055', // char code: 85
				new double[] { 0.85053, 0.691406, 0., 0. }, // metrics: width,
															// height, depth,
															// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0056', // char code: 86
				new double[] { 0.981601, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0057', // char code: 87
				new double[] { 1.235133, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0058', // char code: 88
				new double[] { 0.849254, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0059', // char code: 89
				new double[] { 0.983515, 0.691406, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u005A', // char code: 90
				new double[] { 0.710848, 0.691406, 0.126038, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u005B', // char code: 91
				new double[] { 0.2567215, 0.749474, 0.249825, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u005D', // char code: 93
				new double[] { 0.2567215, 0.749474, 0.249825, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u005E', // char code: 94
				new double[] { 0.589981, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0061', // char code: 97
				new double[] { 0.602737, 0.475342, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0062', // char code: 98
				new double[] { 0.589981, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0063', // char code: 99
				new double[] { 0.463694, 0.475342, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0064', // char code: 100
				new double[] { 0.588705, 0.629288, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0065', // char code: 101
				new double[] { 0.471985, 0.475342, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0066', // char code: 102
				new double[] { 0.388431, 0.691406, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0067', // char code: 103
				new double[] { 0.594765, 0.475342, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0068', // char code: 104
				new double[] { 0.615494, 0.691406, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0069', // char code: 105
				new double[] { 0.3313465, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u006A', // char code: 106
				new double[] { 0.331665, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u006B', // char code: 107
				new double[] { 0.464012, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u006C', // char code: 108
				new double[] { 0.336767, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u006D', // char code: 109
				new double[] { 0.921328, 0.475342, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u006E', // char code: 110
				new double[] { 0.653763, 0.475342, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u006F', // char code: 111
				new double[] { 0.609116, 0.475342, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0070', // char code: 112
				new double[] { 0.603694, 0.526655, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0071', // char code: 113
				new double[] { 0.595721, 0.475342, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0072', // char code: 114
				new double[] { 0.459547, 0.475342, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0073', // char code: 115
				new double[] { 0.522692, 0.475342, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0074', // char code: 116
				new double[] { 0.393214, 0.629288, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0075', // char code: 117
				new double[] { 0.588705, 0.475342, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0076', // char code: 118
				new double[] { 0.604332, 0.526655, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0077', // char code: 119
				new double[] { 0.9175005, 0.526655, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0078', // char code: 120
				new double[] { 0.45891, 0.475342, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u0079', // char code: 121
				new double[] { 0.589025, 0.475342, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u007A', // char code: 122
				new double[] { 0.461142, 0.475342, 0.189056, 0. }, // metrics:
																	// width,
																	// height,
																	// depth,
																	// italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u007D', // char code: 125
				new double[] { 0.253532, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
		setInfo('\u00C4', // char code: 196
				new double[] { 0.542145, 0.691406, 0., 0. }, // metrics: width,
																// height,
																// depth, italic
				null, // ligatures
				null, // kern codes
				null, // kern values
				null, '\0', // next larger
				null); // extension: top, mid, rep, bot
	}
}

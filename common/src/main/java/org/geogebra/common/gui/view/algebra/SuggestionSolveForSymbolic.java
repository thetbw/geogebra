package org.geogebra.common.gui.view.algebra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.geogebra.common.kernel.algos.Algos;
import org.geogebra.common.kernel.arithmetic.Equation;
import org.geogebra.common.kernel.arithmetic.ExpressionValue;
import org.geogebra.common.kernel.arithmetic.Inspecting;
import org.geogebra.common.kernel.arithmetic.SymbolicMode;
import org.geogebra.common.kernel.geos.GeoDummyVariable;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoSymbolic;
import org.geogebra.common.kernel.kernelND.GeoElementND;
import org.geogebra.common.scientific.LabelController;

public class SuggestionSolveForSymbolic extends SuggestionSolve {

	private static final int EQUATION_LIMIT = 4;
	private final List<GeoElementND> geos;
	private final String[] vars;
	private LabelController labelController = new LabelController();
	private SuggestionSolveForSymbolic(List<GeoElementND> geos, String[] vars) {
		super();
		this.geos = geos;
		this.vars = vars;
	}
	private SuggestionSolveForSymbolic(GeoElementND geo) {
		super();
		this.geos = Collections.singletonList(geo);
		this.vars = null;
	}

	@Override
	protected void runCommands(GeoElementND geo) {
		labelGeosIfNeeded();
		String command = getCommandText();
		geo.getKernel().getAlgebraProcessor().processAlgebraCommand(
				command, false);
	}

	private void labelGeosIfNeeded() {
		for (int i = geos.size() - 1; i >= 0; i--) {
			labelIfNeeded(geos.get(i));
		}
	}

	private void labelIfNeeded(GeoElementND geo) {
		if (!geo.isAlgebraLabelVisible()) {
			labelController.showLabel((GeoElement) geo);
		}
	}

	private String getCommandText() {
		StringBuilder sb = new StringBuilder();
		String varList = getVariableList();
		sb.append("Solve[");
		sb.append(getGeoList());
		if (!varList.isEmpty()) {
			sb.append(", ");
			sb.append(varList);
		}
		sb.append("]");
		return sb.toString();
	}

	private String getVariableList() {
		if (vars == null || vars.length == 0) {
			return "";
		} else if (vars.length == 1) {
			return vars[0];
		}

		StringBuilder sb = new StringBuilder();
		sb.append("{");
		int lastIdx = vars.length - 1;
		for (int i=0; i < lastIdx;i++) {
			sb.append(vars[i]);
			sb.append(", ");
		}
		sb.append(vars[lastIdx]);
		sb.append("}");
		return sb.toString();
	}

	private String getGeoList() {
		if (geos.size() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		int lastIdx = geos.size() - 1;
		for (int i=0; i < lastIdx;i++) {
			sb.append(geos.get(i).getLabelSimple());
			sb.append(", ");
		}
		sb.append(geos.get(lastIdx).getLabelSimple());
		sb.append("}");
		return sb.toString();
	}

	public static boolean isValid(GeoElementND geo) {
		return  isEquation(geo);
	}

	public static Suggestion get(GeoElement geo) {
		if (!isValid(geo)) {
			return null;
		}

		GeoSymbolic symbolic = (GeoSymbolic)geo;
		String[] vars = getVariables(symbolic);
		if (isAlgebraEquation(symbolic)) {
			if (vars.length == 1) {
				return new SuggestionSolveForSymbolic(geo);
			} else {
				return getMulti(symbolic);
			}
		}

		return null;

	}

	private static String[] getVariables(GeoElementND geo) {
		if (!isValid(geo)) {
			return new String[0];
		}

		HashSet<GeoElement> varSet = ((GeoSymbolic)geo).getValue().getVariables(SymbolicMode.SYMBOLIC);
		List<String> varStrings = new ArrayList<>();
		if (varSet != null) {
			for (GeoElement var : varSet) {
				String varName = (var instanceof GeoDummyVariable) ? ((GeoDummyVariable)var).getVarName()
						: var.getLabelSimple();
				if (!varStrings.contains(varName)) {
					varStrings.add(varName);
				}
			}
		}
		return varStrings.toArray(new String[0]);
	}

	private static boolean isAlgebraEquation(GeoElementND geo) {
		return  (isEquation(geo) && geo.getParentAlgorithm() == null
				|| geo.getParentAlgorithm().getClassName() == Algos.Expression);
	}

	private static boolean isEquation(GeoElementND geo) {
		if (!(geo instanceof GeoSymbolic)) {
			return false;
		}
		return ((GeoSymbolic)geo).getValue().unwrap() instanceof Equation;
	}


	private static Suggestion getMulti(GeoElement geo) {
		String[] vars = getVariables(geo);
		List<GeoElementND> geos = new ArrayList<>();
		GeoElementND prev = geo;
		while (prev != null && geos.size() < EQUATION_LIMIT) {
			geos.add(prev);
			prev  = isValid(prev) ? getPrevious(prev, vars)
					:null;

	}
		if (geos.size() == 1 && vars.length == 2) {
			return null;
		}
		return new SuggestionSolveForSymbolic(geos, vars);
	}

	private static GeoElementND getPrevious(final GeoElementND geo, final String[] vars) {
		return geo.getConstruction().getPrevious(geo,
				new Inspecting() {

					@Override
					public boolean check(ExpressionValue var) {
						return isAlgebraEquation((GeoElement) var)
								&& subset(getVariables((GeoSymbolic)var), vars)
								&& !SuggestionSolve.checkDependentAlgo((GeoElement) var,
								SINGLE_SOLVE, null);
					}
				});
	}
}
package org.geogebra.common.main;

public enum Feature {
	ALL_LANGUAGES,

	LOCALSTORAGE_FILES,

	TUBE_BETA,

	EXERCISES,

	TOOL_EDITOR,

	IMPLICIT_SURFACES,

	LOG_AXES,

	HIT_PARAMETRIC_SURFACE,

	PARAMETRIC_SURFACE_IS_REGION,

	ACRA,

	ANALYTICS,

	/** GGB-334, TRAC-3401 */
	ADJUST_WIDGETS,

	MOW_PEN_EVENTS,

	/** SolveQuartic in CAS GGB-1635 */
	SOLVE_QUARTIC,

	/** MOW-88 */
	MOW_DIRECT_FORMULA_CONVERSION,

	SURFACE_2D,

	/** MOB-1293 */
	SELECT_TOOL_NEW_BEHAVIOUR,

	/** MOB-1319 */
	MOB_NOTIFICATION_BAR_TRIGGERS_EXAM_ALERT_IOS_11,

	/** GGB-2215 */
	ARIA_CONTEXT_MENU,

	/** GGB-2144 */
	COMMAND_HOLES,

	/** MOB-1537 */
	MOB_PREVIEW_WHEN_EDITING,

	/** AND-887 and IGR-732 */
	MOB_PROPERTY_SORT_BY,

	/** MOW-189 */
	MOW_TEXT_TOOL,

	/** GGB-2255 */
	GEOMETRIC_DISCOVERY,

	/** GGB-2542 */
	SYMBOLIC_AV,

	/** G3D-42 */
	G3D_AR_REGULAR_TOOLS,

	/** G3D-95*/
 	G3D_AR_ROTATE_3D_VIEW_TOOL,

	/** G3D-97*/
	G3D_AR_TRANSLATE_3D_VIEW_TOOL,

	/** automatically add NDG conditions in locus equations */
	LOCUSEQU_AUTO_NDG,

	/** unify prove commands */
	PROVE_UNIFY,

	/** Csilla Master */
	SPEECH_RECOGNITION,

	/** G3D-160 */
	G3D_AR_TARGET,

	/** G3D-170 */
	G3D_IMPROVE_SOLID_TOOLS,

	/** G3D-151 */
	G3D_IOS_NATIVE_AR,

	/** G3D-66 */
	G3D_IMPROVE_AUTOMATIC_ROTATION,

	/** G3D-266 */
	G3D_ARMotionEvent,

	/** G3D-272 */
	G3D_NEW_SURFACE_FUNCTIONS_COLORS,

	/** G3D-277 */
	G3D_AV_UPDATES_RELEVANT_GEOS_AND_PROPERTIES,

	/** MOW-679 */
	MOW_PEN_IS_LOCUS,
	
	/** APPS-284 */
	COMMAND_FILTERING,

    /** G3D-28 */
    G3D_AR_LABELS_POSITION
}
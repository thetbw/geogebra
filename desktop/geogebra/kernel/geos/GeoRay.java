/* 
GeoGebra - Dynamic Mathematics for Everyone
http://www.geogebra.org

This file is part of GeoGebra.

This program is free software; you can redistribute it and/or modify it 
under the terms of the GNU General Public License as published by 
the Free Software Foundation.

*/

package geogebra.kernel.geos;

import geogebra.common.kernel.AbstractConstruction;
import geogebra.common.kernel.AbstractKernel;
import geogebra.common.kernel.PathMover;
import geogebra.common.kernel.PathMoverGeneric;
import geogebra.common.kernel.PathParameter;
import geogebra.common.kernel.Matrix.Coords;
import geogebra.common.kernel.algos.AlgoDirection;
import geogebra.common.kernel.algos.AlgoElement;
import geogebra.common.kernel.geos.GeoClass;
import geogebra.common.kernel.geos.GeoElement;
import geogebra.common.kernel.geos.GeoLine;
import geogebra.common.kernel.geos.GeoPoint2;
import geogebra.common.kernel.geos.GeoVec3D;
import geogebra.common.kernel.geos.GeoVector;
import geogebra.common.kernel.geos.LimitedPath;
import geogebra.common.kernel.kernelND.GeoPointND;
import geogebra.common.kernel.kernelND.GeoRayND;
import geogebra.common.kernel.AbstractKernel;
import geogebra.common.kernel.TransformInterface;
import geogebra.kernel.algos.AlgoConicPartCircumcircle;
import geogebra.kernel.algos.AlgoJoinPointsRay;
import geogebra.kernel.algos.AlgoRayPointVector;
import geogebra.kernel.algos.AlgoTranslate;


/**
 * @author Markus Hohenwarter
 */
final public class GeoRay extends GeoLine implements LimitedPath, GeoRayND {
	
	private boolean allowOutlyingIntersections = false;
	private boolean keepTypeOnGeometricTransform = true;
	
	/**
	 * Creates ray with start point A.
	 * @param c construction
	 * @param A start point
	 */
	public GeoRay(AbstractConstruction c, GeoPoint2 A) {
		super(c);		
		setStartPoint(A);
	}
	
	public GeoRay(AbstractConstruction c) {
		super(c);
	}

	public String getClassName() {	
		return "GeoRay";
 	}
	
	 protected String getTypeString() {
		return "Ray";
	}

	public GeoClass getGeoClassType() {
		return GeoClass.RAY;
	}

	 
	/**
	 * the copy of a ray is an independent line
	 *
	public GeoElement copy() {
		return new GeoLine(this); 
	}*/
	 
	
	public GeoElement copyInternal(AbstractConstruction cons) {
		GeoRay ray = new GeoRay(cons, (GeoPoint2) startPoint.copyInternal(cons));
		ray.set(this);
		return ray;
	}
	
	public void set(GeoElement geo) {
		super.set(geo);	
		if (!geo.isGeoRay()) return;
		
		GeoRay ray = (GeoRay) geo;		
		keepTypeOnGeometricTransform = ray.keepTypeOnGeometricTransform; 
										
		startPoint.set((GeoElement) ray.startPoint);		
	}
	
	public void set(GeoPoint2 s, GeoVec3D direction) {
		super.set(direction);
		setStartPoint(s);
	}
	
	public void setVisualStyle(GeoElement geo) {
		super.setVisualStyle(geo);
		
		if (geo.isGeoRay()) { 
			GeoRay ray = (GeoRay) geo;
			allowOutlyingIntersections = ray.allowOutlyingIntersections;
		}
	}
	
	/* 
	 * Path interface
	 */	 
	public void pointChanged(GeoPointND P) {
		super.pointChanged(P);
		
		// ensure that the point doesn't get outside the ray
		// i.e. ensure 0 <= t 
		PathParameter pp = P.getPathParameter();
		if (pp.t < 0.0) {
			P.setCoords2D(startPoint.x, startPoint.y,startPoint.z);
			P.updateCoordsFrom2D(false,null);
			pp.t = 0.0;
		} 
	}

	public void pathChanged(GeoPointND PI) {
		
		GeoPoint2 P = (GeoPoint2) PI;
		
		PathParameter pp = P.getPathParameter();
		if (pp.t < 0.0) {
			pp.t = 0;
		} 		
		
		// calc point for given parameter
		P.x = startPoint.inhomX + pp.t * y;
		P.y = startPoint.inhomY - pp.t * x;
		P.z = 1.0;		
	}
	
	public boolean allowOutlyingIntersections() {
		return allowOutlyingIntersections;
	}
	
	public void setAllowOutlyingIntersections(boolean flag) {
		allowOutlyingIntersections = flag;		
	}
	
	public boolean keepsTypeOnGeometricTransform() {		
		return keepTypeOnGeometricTransform;
	}

	public void setKeepTypeOnGeometricTransform(boolean flag) {
		keepTypeOnGeometricTransform = flag;
	}
	
	final public boolean isLimitedPath() {
		return true;
	}
	
    public boolean isIntersectionPointIncident(GeoPoint2 p, double eps) {
    	if (allowOutlyingIntersections)
			return isOnFullLine(p, eps);
		else
			return isOnPath(p, eps);
    }
      	
	/**
	 * Returns the smallest possible parameter value for this
	 * path (may be Double.NEGATIVE_INFINITY)
	 * @return smallest possible parameter
	 */
	public double getMinParameter() {
		return 0;
	}
	
	/**
	 * Returns the largest possible parameter value for this
	 * path (may be Double.POSITIVE_INFINITY)
	 * @return largest possible parameter
	 */
	public double getMaxParameter() {
		return Double.POSITIVE_INFINITY;
	}
	
	public PathMover createPathMover() {
		return new PathMoverGeneric(this);
	}
	
	/**
     * returns all class-specific xml tags for saveXML
     */
	protected void getXMLtags(StringBuilder sb) {
        super.getXMLtags(sb);
		
        // allowOutlyingIntersections
        sb.append("\t<outlyingIntersections val=\"");
        sb.append(allowOutlyingIntersections);
        sb.append("\"/>\n");
        
        // keepTypeOnGeometricTransform
        sb.append("\t<keepTypeOnTransform val=\"");
        sb.append(keepTypeOnGeometricTransform);
        sb.append("\"/>\n");
 
    }

   
    /**
     * Creates a new ray using a geometric transform.
     * @param t transform
     */

	public GeoElement [] createTransformedObject(TransformInterface t,String label) {	
		AlgoElement algoParent = keepTypeOnGeometricTransform ?
				getParentAlgorithm() : null;				
		
		// CREATE RAY
		if (algoParent instanceof AlgoJoinPointsRay) {	
			//	transform points
			AlgoJoinPointsRay algo = (AlgoJoinPointsRay) algoParent;
			GeoPointND [] points = {algo.getP(), algo.getQ()};
			points = t.transformPoints(points);	
			if(t.isAffine()){
				GeoElement ray = (GeoElement) kernel.RayND(label, points[0], points[1]);
				ray.setVisualStyleForTransformations(this);
				GeoElement [] geos = {ray, (GeoElement) points[0], (GeoElement) points[1]};
			return geos;
			}
			else {
				GeoPoint2 inf = new GeoPoint2(cons);
				inf.setCoords(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
				inf = (GeoPoint2)t.doTransform(inf);
				AlgoConicPartCircumcircle ae = new AlgoConicPartCircumcircle( cons, TransformInterface.transformedGeoLabel(this),
			    		(GeoPoint2) points[0], (GeoPoint2) points[1],inf,GeoConicPart.CONIC_PART_ARC);
				cons.removeFromAlgorithmList(ae);
				GeoElement arc = (GeoElement)ae.getConicPart();//GeoConicPart 				
				arc.setVisualStyleForTransformations(this);
				GeoElement [] geos = {arc, (GeoElement) points[0], (GeoElement) points[1]};
				return geos;		
			}
		}
		else if (algoParent instanceof AlgoRayPointVector) {			
			// transform startpoint
			GeoPointND [] points = {getStartPoint()};
			points = t.transformPoints(points);					
						
			boolean oldSuppressLabelCreation = cons.isSuppressLabelsActive();
			cons.setSuppressLabelCreation(true);
			AlgoDirection ad = new AlgoDirection( cons,this);
			cons.removeFromAlgorithmList(ad);
			GeoVector direction = ad.getVector();
			if(t.isAffine()) {
				
				direction = (GeoVector)t.doTransform(direction);
				cons.setSuppressLabelCreation(oldSuppressLabelCreation);
				
				// ray through transformed point with direction of transformed line
				GeoElement ray = (GeoRay)kernel.Ray(label, (GeoPoint2) points[0], direction);
				ray.setVisualStyleForTransformations(this);
				GeoElement [] geos = new GeoElement[] {ray, (GeoElement) points[0]};
				return geos;
			}
				AlgoTranslate at = new AlgoTranslate( cons,getStartPoint(),direction);
				cons.removeFromAlgorithmList(at);
				GeoPoint2 thirdPoint = (GeoPoint2) at.getResult();
				GeoPoint2 inf = new GeoPoint2(cons);
				inf.setCoords(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
						
				GeoPointND [] points2 = new GeoPointND[] {thirdPoint,inf};
				points2 = t.transformPoints(points2);
				cons.setSuppressLabelCreation(oldSuppressLabelCreation);
				AlgoConicPartCircumcircle ae = new AlgoConicPartCircumcircle(cons, TransformInterface.transformedGeoLabel(this),
			    		(GeoPoint2) points[0], (GeoPoint2) points2[0], (GeoPoint2) points2[1],GeoConicPart.CONIC_PART_ARC);
				GeoElement arc = (GeoElement)ae.getConicPart();//GeoConicPart 				
				arc.setVisualStyleForTransformations(this);
				GeoElement [] geos = {arc, (GeoElement) points[0]};
				return geos;		
						
			
			
							
			
		} else {
			//	create LINE	
			GeoElement transformedLine = t.getTransformedLine(this);
			transformedLine.setLabel(label);
			GeoElement [] ret = { transformedLine };
			return ret;
		}	
	}		
	
	public boolean isGeoRay() {
		return true;
	}
    // Michael Borcherds 2008-04-30
	final public boolean isEqual(GeoElement geo) {
		// return false if it's a different type, otherwise check direction and start point
		if (!geo.isGeoRay()) return false;
		
		return isSameDirection((GeoLine)geo) && ((GeoRay)geo).getStartPoint().isEqual(getStartPoint());

	}
	
	
	
    public boolean isOnPath(Coords Pnd, double eps) {    	
    	Coords P2d = Pnd.getCoordsIn2DView();
    	if  (!super.isOnPath(P2d, eps))
    		return false;
    	
    	return respectLimitedPath(P2d, eps);
	   	
    }
    
    public boolean respectLimitedPath(Coords Pnd, double eps) {    	
    	Coords P2d = Pnd.getCoordsIn2DView();
    	PathParameter pp = getTempPathParameter();
    	doPointChanged(P2d,pp);
    	double t = pp.getT();

    	return  t >= -eps;   	
    }
    
    public boolean isAllEndpointsLabelsSet() {
		return startPoint.isLabelSet();		
	} 
    
    public GeoPoint2 getInnerPoint(){    	
    	
    	double nx = startPoint.x+y;
    	double ny = startPoint.y-x;
    	GeoPoint2 ret = new GeoPoint2(cons);
    	ret.setCoords(nx, ny, 1);
    	if(!isOnPath(ret, AbstractKernel.EPSILON)){
    		nx = startPoint.x-y;
        	ny = startPoint.y+x;
        	ret.setCoords(nx, ny, 1);
    	}
    	return ret;
    }
 
	
}

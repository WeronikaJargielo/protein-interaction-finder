package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Atom;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class MathHelper {

    public static Vector3d calculateVector(Point3d begin, Point3d end) {
        return new Vector3d(end.x-begin.x, end.y-begin.y, end.z-begin.z);
    }

    public static Vector3d calculateVector(Atom begin, Atom end) {
        return new Vector3d( end.getX()-begin.getX(),
                            end.getY()-begin.getY(),
                            end.getZ()-begin.getZ());
    }

    public static Vector3d calculateCrossProduct(Vector3d firstVec, Vector3d secondVec) {
        Vector3d crossProd = new Vector3d();
        crossProd.cross(firstVec, secondVec);

        return crossProd;
    }

    public static double round(double number) {
        return (double) Math.round(number*100)/100;
    }
}

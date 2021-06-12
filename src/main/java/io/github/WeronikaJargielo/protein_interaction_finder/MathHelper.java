package io.github.WeronikaJargielo.protein_interaction_finder;

import org.biojava.nbio.structure.Atom;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

final class MathHelper {

    private static final double radiansToDegreesCoefficient = (180/Math.PI);

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

    public static double radiansToDegrees(final double angleInRadians) {
        return angleInRadians*radiansToDegreesCoefficient;
    }

    public static double angle(Vector3d firstVec, Vector3d secondVec) {
        return radiansToDegrees( Math.acos((firstVec.x*secondVec.x +
                                            firstVec.y*secondVec.y +
                                            firstVec.z*secondVec.z) / (firstVec.length() * secondVec.length())) );
    }

    public static double angle(Atom firstAtom, Atom secondAtom, Atom thirdAtom) {
        final Vector3d secondFirstVec = calculateVector(secondAtom, firstAtom);
        final Vector3d secondThirdVec = calculateVector(secondAtom, thirdAtom);

        return MathHelper.angle(secondFirstVec, secondThirdVec);
    }

}

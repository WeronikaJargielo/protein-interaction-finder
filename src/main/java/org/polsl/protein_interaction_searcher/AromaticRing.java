package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;
import org.biojava.nbio.structure.Group;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.util.Arrays;
import java.util.List;


public class AromaticRing {
    private final Atom[] atoms;
    private final Group aminoAcid;
    private final Atom ringCentroid;

    public AromaticRing(List<Atom> atoms) {
        this.atoms = new Atom[atoms.size()];
        atoms.toArray(this.atoms);

        this.aminoAcid = this.atoms[0].getGroup();
        this.ringCentroid = this.calculateCentroid();
    }

    public Atom[] getAtoms() {
        return atoms;
    }

    public Group getAminoAcid() {
        return aminoAcid;
    }

    public Atom calculateCentroid() {
        return Calc.getCentroid(atoms);
    }

    public Vector3d calculateNormalVector() {
        final int indA = 0; final int indB = 1; final int indC = 2;
        final Vector3d BA = MathHelper.calculateVector(atoms[indB], atoms[indA]);
        final Vector3d BC = MathHelper.calculateVector(atoms[indB], atoms[indC]);

        return MathHelper.calculateCrossProduct(BA, BC);
    }

    public double calculatePolarAngleOfAtom(Atom atom) {
        final Vector3d normalVec = this.calculateNormalVector();
        final Vector3d centroidAtomVec = MathHelper.calculateVector(this.calculateCentroid(), atom);

        return normalVec.angle(centroidAtomVec)*(180/Math.PI);
    }

    public double calculateElevationAngleOfAtom(Atom atom) {
        return Math.abs(90 - this.calculatePolarAngleOfAtom(atom));
    }

    public double calculateEquatorialAngleOfAtom(Atom atom) throws Exception {
        final Vector3d centroidToRingAtomVec = MathHelper.calculateVector(this.ringCentroid, this.getRingAtomForEquatorialAngle());
        final Point3d atomProjection = this.calculateAtomProjectionOnRing(atom);
        final Vector3d centroidAtomProjectionVec = MathHelper.calculateVector(this.ringCentroid.getCoordsAsPoint3d(), atomProjection);

        return centroidToRingAtomVec.angle(centroidAtomProjectionVec)*(180/Math.PI);
    }

    public Point3d calculateAtomProjectionOnRing(Atom atom) {
        final Vector3d normalVec = this.calculateNormalVector();
        final Atom ringAtom = atoms[0];

        // Plane equation: Ax + By + Cz + D = 0 => D = -(Ax + By + Cz)
        final double DCoefficient = -(normalVec.dot(new Vector3d(ringAtom.getCoordsAsPoint3d())));

        final double t = - (DCoefficient + normalVec.dot(new Vector3d(atom.getCoordsAsPoint3d())))/normalVec.lengthSquared();

        return new Point3d(normalVec.x*t + atom.getX(),
                          normalVec.y*t + atom.getY(),
                          normalVec.z*t + atom.getZ());
    }

    private Atom getRingAtomForEquatorialAngle() throws Exception {
        final String desiredAtom;

        switch (AminoAcidAbbreviations.valueOf(aminoAcid.getPDBName())) {
            case TYR:
            case PHE:
                desiredAtom = "CZ";
                break;
            case TRP:
                desiredAtom = this.atoms.length == 5 ? "NE1" : "CZ";
                break;
            default:
                throw new Exception("This amino acid does not have any aromatic rings.");
        }

        return Arrays.stream(atoms)
                     .filter(atom -> atom.getName().equals(desiredAtom))
                     .findFirst()
                     .orElse(null);
    }
}

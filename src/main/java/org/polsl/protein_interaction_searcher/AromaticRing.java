package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;
import org.biojava.nbio.structure.Group;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.util.Arrays;
import java.util.List;


public class AromaticRing {
    private final Atom[] atoms;
    private final Group aminoAcid;
    private final Atom ringCentroid;
    private final Vector3d normalVector;

    public AromaticRing(List<Atom> atoms) {
        this.atoms = new Atom[atoms.size()];
        atoms.toArray(this.atoms);

        this.aminoAcid = this.atoms[0].getGroup();
        this.ringCentroid = this.calculateCentroid();
        this.normalVector = this.calculateNormalVector();
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
        final String atomNameForBeginVec;
        final String atomNameForEndVec;

        switch (AminoAcidAbbreviations.valueOf(aminoAcid.getPDBName())) {
            case TYR:
            case PHE:
                atomNameForBeginVec = "CD1"; atomNameForEndVec = "CG";
                break;
            case TRP:
                if (this.atoms.length == 5) {
                    atomNameForBeginVec = "CD1"; atomNameForEndVec = "CG";
                } else {
                    atomNameForBeginVec = "CE2"; atomNameForEndVec = "CD2";
                }
                break;
            default:
                return null;
        }

        final Atom atomForBeginVec = this.findAtomInRingAtoms(atomNameForBeginVec);
        final Atom atomForEndVec = this.findAtomInRingAtoms(atomNameForEndVec);

        final Vector3d beginVec = MathHelper.calculateVector(this.ringCentroid, atomForBeginVec);
        final Vector3d endVec = MathHelper.calculateVector(this.ringCentroid, atomForEndVec);

        return MathHelper.calculateCrossProduct(beginVec, endVec);
    }

    public double calculatePolarAngleOfAtom(Atom atom) {
        final Vector3d centroidAtomVec = MathHelper.calculateVector(this.calculateCentroid(), atom);

        return MathHelper.radiansToDegrees(this.normalVector.angle(centroidAtomVec));
    }

    public double calculateElevationAngleOfAtom(Atom atom) {
        return Math.abs(90 - this.calculatePolarAngleOfAtom(atom));
    }

    public double calculateEquatorialAngleOfAtom(Atom atom) {
        final Vector3d centroidToRingAtomVec = MathHelper.calculateVector(this.ringCentroid, this.getRingAtomForEquatorialAngle());
        final Point3d atomProjection = this.calculateAtomProjectionOnRing(atom);
        final Vector3d centroidAtomProjectionVec = MathHelper.calculateVector(this.ringCentroid.getCoordsAsPoint3d(), atomProjection);

        final double cosine = (centroidToRingAtomVec.dot(centroidAtomProjectionVec)) / (centroidToRingAtomVec.length()*centroidAtomProjectionVec.length());
        final double sine = MathHelper.calculateCrossProduct(this.normalVector, centroidToRingAtomVec).dot(centroidAtomProjectionVec) / (centroidToRingAtomVec.length()*centroidAtomProjectionVec.length());

        final double equatorialAngle;
        if (sine >= 0) {
            equatorialAngle = Math.acos(cosine);
        } else {
            equatorialAngle = 2*Math.PI - Math.acos(cosine);
        }
        return MathHelper.radiansToDegrees(equatorialAngle);
    }

    public Point3d calculateAtomProjectionOnRing(Atom atom) {
        final Atom anyRingAtom = atoms[0];

        // Plane equation: Ax + By + Cz + D = 0 => D = -(Ax + By + Cz)
        final double DCoefficient = -(this.normalVector.dot(new Vector3d(anyRingAtom.getCoordsAsPoint3d())));

        final double t = - (DCoefficient + this.normalVector.dot(new Vector3d(atom.getCoordsAsPoint3d()))) / this.normalVector.lengthSquared();

        return new Point3d(this.normalVector.x*t + atom.getX(),
                          this.normalVector.y*t + atom.getY(),
                          this.normalVector.z*t + atom.getZ());
    }

    private Atom getRingAtomForEquatorialAngle() {
        final String desiredAtom;

        switch (AminoAcidAbbreviations.valueOf(aminoAcid.getPDBName())) {
            case TYR:
            case PHE:
                desiredAtom = "CZ";
                break;
            case TRP:
                desiredAtom = this.atoms.length == 5 ? "NE1" : "CZ2";
                break;
            default:
                return null;
        }
       return findAtomInRingAtoms(desiredAtom);
    }

    private Atom findAtomInRingAtoms(String pdbAtomName) {
        return Arrays.stream(atoms)
                .filter(atom -> atom.getName().equals(pdbAtomName))
                .findFirst()
                .orElse(null);
    }
}

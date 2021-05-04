package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;
import org.biojava.nbio.structure.Group;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import java.util.Arrays;
import java.util.List;


public final class AromaticRing {
    private final Atom[] atoms;
    private final Group aminoAcid;
    private final Atom ringCentroid;
    private final Vector3d normalVector;

    public AromaticRing(List<Atom> atoms) {
        this.atoms = new Atom[atoms.size()];
        atoms.toArray(this.atoms);

        this.aminoAcid = this.atoms[0].getGroup();
        this.ringCentroid = Calc.getCentroid(this.atoms);
        this.normalVector = this.calculateNormalVector();
        this.normalVector.normalize();
    }

    public Atom[] getAtoms() {
        return atoms;
    }

    public Group getAminoAcid() {
        return aminoAcid;
    }

    public Atom getRingCentroid() {
        return ringCentroid;
    }

    public Vector3d getNormalVector() {
        return normalVector;
    }

    private Vector3d calculateNormalVector() {
        final String atomNameForBeginVec;
        final String atomNameForEndVec;

        switch (AminoAcidAbbreviations.valueOf(aminoAcid.getPDBName())) {
            case PHE:
            case TYR:
                atomNameForBeginVec = "CD1"; atomNameForEndVec = "CG";
                break;
            case TRP:
                if (this.atoms.length == 5) {
                    atomNameForBeginVec = "CG"; atomNameForEndVec = "CD1";
                } else {
                    atomNameForBeginVec = "CE3"; atomNameForEndVec = "CD2";
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
        final Vector3d centroidAtomVec = MathHelper.calculateVector(this.ringCentroid, atom);

        return MathHelper.radiansToDegrees(this.normalVector.angle(centroidAtomVec));
    }

    public double calculateAzimuthalAngleOfAtom(Atom atom) {
        double azimuthalAngle = this.calculateEquatorialAngleOfAtom(atom);

        final AminoAcidAbbreviations aminoAcidAbbr = AminoAcidAbbreviations.valueOf(aminoAcid.getPDBName());
        if (aminoAcidAbbr == AminoAcidAbbreviations.PHE || aminoAcidAbbr == AminoAcidAbbreviations.TYR) {
            azimuthalAngle = (azimuthalAngle >= 180.0) ? azimuthalAngle - 180.0 : azimuthalAngle + 180.0;
        }

        return azimuthalAngle;
    }

    public double calculateElevationAngleOfAtom(Atom atom) {
        return Math.abs(90 - this.calculatePolarAngleOfAtom(atom));
    }

    public double calculateEquatorialAngleOfAtom(Atom atom) {
        final Vector3d centroidToRingAtomVec = MathHelper.calculateVector(this.ringCentroid, this.getRingAtomForEquatorialAngle());
        final Point3d atomProjection = this.calculateAtomProjectionOnRing(atom);
        final Vector3d centroidToAtomProjectionVec = MathHelper.calculateVector(this.ringCentroid.getCoordsAsPoint3d(), atomProjection);

        final double cosine = (centroidToRingAtomVec.dot(centroidToAtomProjectionVec)) / (centroidToRingAtomVec.length()*centroidToAtomProjectionVec.length());
        final double sine = MathHelper.calculateCrossProduct(this.normalVector, centroidToRingAtomVec)
                                      .dot(centroidToAtomProjectionVec) / (centroidToRingAtomVec.length()*centroidToAtomProjectionVec.length());

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
            case PHE:
            case TYR:
                desiredAtom = "CZ";
                break;
            case TRP:
                desiredAtom = (this.atoms.length == 5) ? "NE1" : "CZ2";
                break;
            default:
                return null;
        }
        return this.findAtomInRingAtoms(desiredAtom);
    }

    private Atom findAtomInRingAtoms(String pdbAtomName) {
        return Arrays.stream(atoms)
                .filter(atom -> atom.getName().equals(pdbAtomName))
                .findFirst()
                .orElse(null);
    }
}

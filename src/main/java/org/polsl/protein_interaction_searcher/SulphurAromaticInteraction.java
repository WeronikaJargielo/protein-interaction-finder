package org.polsl.protein_interaction_searcher;

public class SulphurAromaticInteraction {

    final private AminoAcid aromaticAminoAcid;
    final private AminoAcid sulphurAminoAcid;
    final private double dist;
    final private double elevationAngle;
    final private double equatorialAngle;

    public SulphurAromaticInteraction(AminoAcid aromaticAminoAcid, AminoAcid sulphurAminoAcid, double dist, double elevationAngle, double equatorialAngle) {
        this.aromaticAminoAcid = aromaticAminoAcid;
        this.sulphurAminoAcid = sulphurAminoAcid;
        this.dist = dist;
        this.elevationAngle = elevationAngle;
        this.equatorialAngle = equatorialAngle;
    }

    public AminoAcid getAromaticAminoAcid() {
        return aromaticAminoAcid;
    }

    public AminoAcid getSulphurAminoAcid() {
        return sulphurAminoAcid;
    }

    public double getDist() {
        return dist;
    }

    public double getElevationAngle() {
        return elevationAngle;
    }

    public double getEquatorialAngle() {
        return equatorialAngle;
    }

    @Override
    public String toString() {
        return "" + aromaticAminoAcid + '\t' + sulphurAminoAcid + '\t' + MathHelper.round(dist) + '\t'
                  + MathHelper.round(elevationAngle) + '\t' + MathHelper.round(equatorialAngle);
    }
}

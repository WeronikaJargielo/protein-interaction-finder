package org.polsl.protein_interaction_searcher;

public class AminoAromaticInteraction {

    final private AminoAcid aromaticAminoAcid;
    final private AminoAcid aminoAminoAcid;
    final private double dist;
    final private double polarAngle;
    final private double equatorialAngle;

    public AminoAromaticInteraction(AminoAcid aromaticAminoAcid, AminoAcid aminoAminoAcid, double dist, double polarAngle, double equatorialAngle) {
        this.aromaticAminoAcid = aromaticAminoAcid;
        this.aminoAminoAcid = aminoAminoAcid;
        this.dist = dist;
        this.polarAngle = polarAngle;
        this.equatorialAngle = equatorialAngle;
    }

    public AminoAcid getAromaticAminoAcid() {
        return aromaticAminoAcid;
    }

    public AminoAcid getAminoAminoAcid() {
        return aminoAminoAcid;
    }

    public double getDist() {
        return dist;
    }

    public double getPolarAngle() {
        return polarAngle;
    }

    public double getEquatorialAngle() {
        return equatorialAngle;
    }

    @Override
    public String toString() {
        return "" + aromaticAminoAcid + '\t' +  aminoAminoAcid + '\t' + MathHelper.round(dist) + '\t'
                  + MathHelper.round(polarAngle) + '\t' + MathHelper.round(equatorialAngle);
    }
}

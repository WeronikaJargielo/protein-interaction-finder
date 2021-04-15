package org.polsl.protein_interaction_searcher;

public class AminoAromaticInteraction {

    private AminoAcid aromaticAminoAcid;
    private AminoAcid aminoAminoAcid;
    private double distance;
//    private double angle;

    public AminoAromaticInteraction(AminoAcid aromaticAminoAcid, AminoAcid aminoAminoAcid, double distance) {
        this.aromaticAminoAcid = aromaticAminoAcid;
        this.aminoAminoAcid = aminoAminoAcid;
        this.distance = distance;
    }

    public AminoAcid getAromaticAminoAcid() {
        return aromaticAminoAcid;
    }

    public AminoAcid getAminoAminoAcid() {
        return aminoAminoAcid;
    }

    public double getDistance() {
        return distance;
    }

//    public double getAngle() {
//        return angle;
//    }

    @Override
    public String toString() {
//        return "" + aromaticAminoAcid + '\t' + aminoAminoAcid + '\t' + distance + '\t' + angle;
        return "" + aromaticAminoAcid + '\t' +  aminoAminoAcid + '\t' + distance;
    }
}

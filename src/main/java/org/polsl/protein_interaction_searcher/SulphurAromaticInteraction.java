package org.polsl.protein_interaction_searcher;

public class SulphurAromaticInteraction {

    private AminoAcid aromaticAminoAcid;
    private AminoAcid sulphurAminoAcid;
    private double distance;

    public SulphurAromaticInteraction(AminoAcid aromaticAminoAcid, AminoAcid sulphurAminoAcid, double distance) {
        this.aromaticAminoAcid = aromaticAminoAcid;
        this.sulphurAminoAcid = sulphurAminoAcid;
        this.distance = distance;
    }

    public AminoAcid getAromaticAminoAcid() {
        return aromaticAminoAcid;
    }

    public AminoAcid getSulphurAminoAcid() {
        return sulphurAminoAcid;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "" + aromaticAminoAcid + '\t' + sulphurAminoAcid + '\t' + distance;
    }
}

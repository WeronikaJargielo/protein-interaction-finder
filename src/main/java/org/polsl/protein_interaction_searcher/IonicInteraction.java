package org.polsl.protein_interaction_searcher;

public class IonicInteraction {

    private AminoAcid anionicAminoAcid;
    private AminoAcid cationicAminoAcid;
    private double distance;

    public IonicInteraction(AminoAcid anionicAminoAcid, AminoAcid cationicAminoAcid, double distance) {
        this.anionicAminoAcid = anionicAminoAcid;
        this.cationicAminoAcid = cationicAminoAcid;
        this.distance = distance;
    }

    public AminoAcid getAnionicAminoAcid() {
        return anionicAminoAcid;
    }

    public AminoAcid getCationicAminoAcid() {
        return cationicAminoAcid;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "" + anionicAminoAcid + '\t' + cationicAminoAcid + '\t' + distance;
    }
}

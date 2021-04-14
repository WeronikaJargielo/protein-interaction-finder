package org.polsl.protein_interaction_searcher;

public class AromaticAromaticInteraction {
    private AminoAcid firstInteractee;
    private AminoAcid secondInteractee;
    private double distance;

    public AromaticAromaticInteraction(AminoAcid firstInteractee, AminoAcid secondInteractee, double distance) {
        this.firstInteractee = firstInteractee;
        this.secondInteractee = secondInteractee;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "" + firstInteractee + '\t'+ secondInteractee + '\t' + distance;
    }
}

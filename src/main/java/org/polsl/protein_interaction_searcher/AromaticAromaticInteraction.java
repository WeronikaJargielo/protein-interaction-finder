package org.polsl.protein_interaction_searcher;

public class AromaticAromaticInteraction {
    private AminoAcid firstInteractee;
    private AminoAcid secondInteractee;
    private double dist;
    private double phi;

    public AromaticAromaticInteraction(AminoAcid firstInteractee, AminoAcid secondInteractee, double dist, double phi) {
        this.firstInteractee = firstInteractee;
        this.secondInteractee = secondInteractee;
        this.dist = dist;
        this.phi = phi;
    }

    @Override
    public String toString() {
        return "" + firstInteractee + '\t'+ secondInteractee + '\t' + dist + '\t' + phi;
    }
}

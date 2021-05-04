package org.polsl.protein_interaction_searcher;

import java.util.Objects;

public final class HydrophobicInteraction {

    private final AminoAcid firstInteractee;
    private final AminoAcid secondInteractee;
    private final double distanceBtwCAs;

    public HydrophobicInteraction(AminoAcid firstInteractee, AminoAcid secondInteractee, double distanceBtwCAs) {
        this.firstInteractee = firstInteractee;
        this.secondInteractee = secondInteractee;
        this.distanceBtwCAs = distanceBtwCAs;
    }

    public AminoAcid getFirstInteractee() {
        return firstInteractee;
    }

    public AminoAcid getSecondInteractee() {
        return secondInteractee;
    }

    public double getDistanceBtwCAs() {
        return distanceBtwCAs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydrophobicInteraction that = (HydrophobicInteraction) o;
        return firstInteractee.equals(that.firstInteractee)
               && secondInteractee.equals(that.secondInteractee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstInteractee, secondInteractee);
    }

    @Override
    public String toString() {
        return "" + firstInteractee + '\t' + secondInteractee + '\t' +  MathHelper.round(distanceBtwCAs);
    }
}

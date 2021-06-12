package io.github.WeronikaJargielo.protein_interaction_finder;

import java.util.Objects;

/**
 * Class representing hydrophobic interaction.
 */
public final class HydrophobicInteraction {

    private final AminoAcid firstInteractee;
    private final AminoAcid secondInteractee;
    private final double distanceBtwCAs;

    /**
     * Instantiates new hydrophobic interaction.
     *
     * @param firstInteractee  Hydrophobic amino acid of first interaction participant.
     * @param secondInteractee Hydrophobic amino acid of second interaction participant.
     * @param distanceBtwCAs   Distance between CA atoms of interaction participants.
     */
    public HydrophobicInteraction(AminoAcid firstInteractee, AminoAcid secondInteractee, double distanceBtwCAs) {
        this.firstInteractee = firstInteractee;
        this.secondInteractee = secondInteractee;
        this.distanceBtwCAs = distanceBtwCAs;
    }

    /**
     * Returns amino acid of first interaction participant.
     *
     * @return Amino acid of first interaction participant.
     */
    public AminoAcid getFirstInteractee() {
        return firstInteractee;
    }

    /**
     * Returns amino acid of second interaction participant.
     *
     * @return Amino acid of second interaction participant.
     */
    public AminoAcid getSecondInteractee() {
        return secondInteractee;
    }

    /**
     * Returns distance between CA atoms of interaction participants.
     *
     * @return Distance between CA atoms of interaction participants.
     */
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

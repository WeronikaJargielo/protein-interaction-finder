package io.github.WeronikaJargielo.protein_interaction_finder;

import java.util.Objects;

/**
 * Class representing aromatic-aromatic interaction.
 */
public final class AromaticAromaticInteraction {

    private final AminoAcid firstAromaticRing;
    private final AminoAcid secondAromaticRing;
    private final double distanceBtwRings;
    private final double angleBtwRings;

    /**
     * Instantiates new aromatic-aromatic interaction.
     * For parameters' full description see <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     *
     * @param firstAromaticRing  Aromatic amino acid of first interaction participant.
     * @param secondAromaticRing Aromatic amino acid of second interaction participant.
     * @param distanceBtwRings   Distance between aromatic rings' centroids.
     * @param angleBtwRings      Angle between aromatic rings' normal vectors.
     */
    public AromaticAromaticInteraction(AminoAcid firstAromaticRing, AminoAcid secondAromaticRing, double distanceBtwRings, double angleBtwRings) {
        this.firstAromaticRing = firstAromaticRing;
        this.secondAromaticRing = secondAromaticRing;
        this.distanceBtwRings = distanceBtwRings;
        this.angleBtwRings = angleBtwRings;
    }

    /**
     * Returns aromatic amino acid of first interaction participant.
     *
     * @return Aromatic amino acid of first interaction participant.
     */
    public AminoAcid getFirstAromaticRing() {
        return firstAromaticRing;
    }

    /**
     * Returns aromatic amino acid of second interaction participant.
     *
     * @return Aromatic amino acid of second interaction participant.
     */
    public AminoAcid getSecondAromaticRing() {
        return secondAromaticRing;
    }

    /**
     * Returns distance between aromatic rings' centroids.
     *
     * @return Distance between aromatic rings' centroids.
     */
    public double getDistanceBtwRings() {
        return distanceBtwRings;
    }

    /**
     * Returns angle between aromatic rings' normal vectors.
     *
     * @return Angle between aromatic rings' normal vectors.
     */
    public double getAngleBtwRings() {
        return angleBtwRings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AromaticAromaticInteraction that = (AromaticAromaticInteraction) o;
        return Double.compare(that.distanceBtwRings, distanceBtwRings) == 0
                              && Double.compare(that.angleBtwRings, angleBtwRings) == 0
                              && Objects.equals(firstAromaticRing, that.firstAromaticRing)
                              && Objects.equals(secondAromaticRing, that.secondAromaticRing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstAromaticRing, secondAromaticRing, distanceBtwRings, angleBtwRings);
    }

    @Override
    public String toString() {
        return "" + firstAromaticRing + '\t'+ secondAromaticRing + '\t'
                  + MathHelper.round(distanceBtwRings) + '\t' + MathHelper.round(angleBtwRings);
    }
}

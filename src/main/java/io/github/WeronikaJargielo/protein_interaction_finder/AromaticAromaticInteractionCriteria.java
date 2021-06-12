package io.github.WeronikaJargielo.protein_interaction_finder;

import java.util.Objects;

/**
 * Class representing criteria for identifying aromatic-aromatic interactions.
 * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
 */
public final class AromaticAromaticInteractionCriteria {

    final private double minDistanceBtwRings;
    final private double maxDistanceBtwRings;
    final private double minAngleBtwRings;
    final private double maxAngleBtwRings;


    /**
     * Instantiates new aromatic-aromatic interaction criteria using default boundary values.
     * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     */
    public AromaticAromaticInteractionCriteria() {
        this.minDistanceBtwRings = 0;
        this.maxDistanceBtwRings = 7;
        this.minAngleBtwRings = 0;
        this.maxAngleBtwRings = Double.MAX_VALUE;
    }

    /**
     * Instantiates new aromatic-aromatic interaction criteria using custom boundary values.
     * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     *
     * @param minDistanceBtwRings Minimum distance between aromatic rings' centroids.
     * @param maxDistanceBtwRings Maximum distance between aromatic rings' centroids.
     * @param minAngleBtwRings    Minimum angle between aromatic rings' normal vectors.
     * @param maxAngleBtwRings    Maximum angle between aromatic rings' normal vectors.
     */
    public AromaticAromaticInteractionCriteria(double minDistanceBtwRings, double maxDistanceBtwRings,
                                               double minAngleBtwRings, double maxAngleBtwRings) {

        this.minDistanceBtwRings = minDistanceBtwRings;
        this.maxDistanceBtwRings = maxDistanceBtwRings;
        this.minAngleBtwRings = minAngleBtwRings;
        this.maxAngleBtwRings = maxAngleBtwRings;
    }

    /**
     * Returns minimum distance between aromatic rings' centroids.
     *
     * @return Minimum distance between aromatic rings' centroids.
     */
    public double getMinDistanceBtwRings() {
        return minDistanceBtwRings;
    }

    /**
     * Returns maximum distance between aromatic rings' centroids.
     *
     * @return Maximum distance between aromatic rings' centroids.
     */
    public double getMaxDistanceBtwRings() {
        return maxDistanceBtwRings;
    }

    /**
     * Returns minimum angle between aromatic rings' normal vectors.
     *
     * @return Minimum angle between aromatic rings' normal vectors.
     */
    public double getMinAngleBtwRings() {
        return minAngleBtwRings;
    }

    /**
     * Returns maximum angle between aromatic rings' normal vectors.
     *
     * @return Maximum angle between aromatic rings' normal vectors.
     */
    public double getMaxAngleBtwRings() {
        return maxAngleBtwRings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AromaticAromaticInteractionCriteria that = (AromaticAromaticInteractionCriteria) o;
        return Double.compare(that.minDistanceBtwRings, minDistanceBtwRings) == 0
                              && Double.compare(that.maxDistanceBtwRings, maxDistanceBtwRings) == 0
                              && Double.compare(that.minAngleBtwRings, minAngleBtwRings) == 0
                              && Double.compare(that.maxAngleBtwRings, maxAngleBtwRings) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minDistanceBtwRings, maxDistanceBtwRings,
                            minAngleBtwRings, maxAngleBtwRings);
    }

    @Override
    public String toString() {
        return "AromaticAromaticInteractionCriteria: " +
                "minDistanceBtwRingAromatic = " + minDistanceBtwRings +
                ",\tmaxDistanceBtwRingAromatic = " + maxDistanceBtwRings +
                ",\tminAngleBtwRings = " + minAngleBtwRings +
                ",\tmaxAngleBtwRings = " + maxAngleBtwRings;
    }
}

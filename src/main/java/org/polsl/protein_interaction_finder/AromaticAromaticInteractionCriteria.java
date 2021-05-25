package org.polsl.protein_interaction_finder;

import java.util.Objects;

public final class AromaticAromaticInteractionCriteria {

    final private double minDistanceBtwRings;
    final private double maxDistanceBtwRings;
    final private double minAngleBtwRings;
    final private double maxAngleBtwRings;


    public AromaticAromaticInteractionCriteria() {
        this.minDistanceBtwRings = 0;
        this.maxDistanceBtwRings = 7;
        this.minAngleBtwRings = 0;
        this.maxAngleBtwRings = Double.MAX_VALUE;
    }

    public AromaticAromaticInteractionCriteria(double minDistanceBtwRings, double maxDistanceBtwRings,
                                               double minAngleBtwRings, double maxAngleBtwRings) {

        this.minDistanceBtwRings = minDistanceBtwRings;
        this.maxDistanceBtwRings = maxDistanceBtwRings;
        this.minAngleBtwRings = minAngleBtwRings;
        this.maxAngleBtwRings = maxAngleBtwRings;
    }

    public double getMinDistanceBtwRings() {
        return minDistanceBtwRings;
    }

    public double getMaxDistanceBtwRings() {
        return maxDistanceBtwRings;
    }

    public double getMinAngleBtwRings() {
        return minAngleBtwRings;
    }

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

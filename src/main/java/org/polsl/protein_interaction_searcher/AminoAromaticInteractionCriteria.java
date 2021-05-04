package org.polsl.protein_interaction_searcher;

import java.util.Objects;

public final class AminoAromaticInteractionCriteria {

    final private double minDistanceBtwCationRing;
    final private double maxDistanceBtwCationRing;
    final private double minPolarAngle;
    final private double maxPolarAngle;
    final private double minAzimuthalAngle;
    final private double maxAzimuthalAngle;


    public AminoAromaticInteractionCriteria() {
        this.minDistanceBtwCationRing = 0;
        this.maxDistanceBtwCationRing = 6;
        this.minPolarAngle = 0;
        this.maxPolarAngle = 180;
        this.minAzimuthalAngle = 0;
        this.maxAzimuthalAngle = 360;
    }

    public AminoAromaticInteractionCriteria(double minDistanceBtwCationRing, double maxDistanceBtwCationRing,
                                            double minPolarAngle, double maxPolarAngle,
                                            double minAzimuthalAngle, double maxAzimuthalAngle) {

        this.minDistanceBtwCationRing = minDistanceBtwCationRing;
        this.maxDistanceBtwCationRing = maxDistanceBtwCationRing;
        this.minPolarAngle = minPolarAngle;
        this.maxPolarAngle = maxPolarAngle;
        this.minAzimuthalAngle = minAzimuthalAngle;
        this.maxAzimuthalAngle = maxAzimuthalAngle;
    }

    public double getMinDistanceBtwCationRing() {
        return minDistanceBtwCationRing;
    }

    public double getMaxDistanceBtwCationRing() {
        return maxDistanceBtwCationRing;
    }

    public double getMinPolarAngle() {
        return minPolarAngle;
    }

    public double getMaxPolarAngle() {
        return maxPolarAngle;
    }

    public double getMinAzimuthalAngle() {
        return minAzimuthalAngle;
    }

    public double getMaxAzimuthalAngle() {
        return maxAzimuthalAngle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AminoAromaticInteractionCriteria that = (AminoAromaticInteractionCriteria) o;
        return Double.compare(that.minDistanceBtwCationRing, minDistanceBtwCationRing) == 0
                              && Double.compare(that.maxDistanceBtwCationRing, maxDistanceBtwCationRing) == 0
                              && Double.compare(that.minPolarAngle, minPolarAngle) == 0
                              && Double.compare(that.maxPolarAngle, maxPolarAngle) == 0
                              && Double.compare(that.minAzimuthalAngle, minAzimuthalAngle) == 0
                              && Double.compare(that.maxAzimuthalAngle, maxAzimuthalAngle) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minDistanceBtwCationRing, maxDistanceBtwCationRing,
                            minPolarAngle, maxPolarAngle,
                            minAzimuthalAngle, maxAzimuthalAngle);
    }

    @Override
    public String toString() {
        return "NitrogenAromaticInteractionCriteria: " +
                "minDistanceBtwCationRing = " + minDistanceBtwCationRing +
                ",\tmaxDistanceBtwCationRing = " + maxDistanceBtwCationRing +
                ",\tminPolarAngle = " + minPolarAngle +
                ",\tmaxPolarAngle = " + maxPolarAngle +
                ",\tminAzimuthalAngle = " + minAzimuthalAngle +
                ",\tmaxAzimuthalAngle = " + maxAzimuthalAngle;
    }
}

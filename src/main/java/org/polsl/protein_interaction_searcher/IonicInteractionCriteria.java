package org.polsl.protein_interaction_searcher;

import java.util.Objects;

public final class IonicInteractionCriteria {

    private final double minDistanceBtwCentroids;
    private final double maxDistanceBtwCentroids;

    public IonicInteractionCriteria() {
        this.minDistanceBtwCentroids = 0.0;
        this.maxDistanceBtwCentroids = 5.0;
    }

    public IonicInteractionCriteria(double minDistanceBtwCentroids, double maxDistanceBtwCentroids) {
        this.minDistanceBtwCentroids = minDistanceBtwCentroids;
        this.maxDistanceBtwCentroids = maxDistanceBtwCentroids;
    }

    public double getMinDistanceBtwCentroids() {
        return minDistanceBtwCentroids;
    }

    public double getMaxDistanceBtwCentroids() {
        return maxDistanceBtwCentroids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IonicInteractionCriteria that = (IonicInteractionCriteria) o;
        return Double.compare(that.minDistanceBtwCentroids, minDistanceBtwCentroids) == 0
               && Double.compare(that.maxDistanceBtwCentroids, maxDistanceBtwCentroids) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minDistanceBtwCentroids, maxDistanceBtwCentroids);
    }

    @Override
    public String toString() {
        return "IonicInteractionCriteria: " +
                "minDistanceBtwCentroids =" + minDistanceBtwCentroids +
                ",\tmaxDistanceBtwCentroids =" + maxDistanceBtwCentroids;
    }
}

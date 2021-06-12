package io.github.WeronikaJargielo.protein_interaction_finder;

import java.util.Objects;

/**
 * Class representing criteria for identifying ionic interactions.
 * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
 */
public final class IonicInteractionCriteria {

    private final double minDistanceBtwCentroids;
    private final double maxDistanceBtwCentroids;

    /**
     * Instantiates new ionic interaction criteria using default boundary values.
     * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     */
    public IonicInteractionCriteria() {
        this.minDistanceBtwCentroids = 0.0;
        this.maxDistanceBtwCentroids = 5.0;
    }

    /**
     * Instantiates new ionic interaction criteria using custom boundary values.
     * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     *
     * @param minDistanceBtwCentroids Minimum distance between charged groups' centroids of interaction participants.
     * @param maxDistanceBtwCentroids Maximum distance between charged groups' centroids of interaction participants.
     */
    public IonicInteractionCriteria(double minDistanceBtwCentroids, double maxDistanceBtwCentroids) {
        this.minDistanceBtwCentroids = minDistanceBtwCentroids;
        this.maxDistanceBtwCentroids = maxDistanceBtwCentroids;
    }

    /**
     * Returns minimum distance between charged groups' centroids of interaction participants.
     *
     * @return Minimum distance between charged groups' centroids of interaction participants.
     */
    public double getMinDistanceBtwCentroids() {
        return minDistanceBtwCentroids;
    }

    /**
     * Returns maximum distance between charged groups' centroids of interaction participants.
     *
     * @return Maximum distance between charged groups' centroids of interaction participants.
     */
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

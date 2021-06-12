package io.github.WeronikaJargielo.protein_interaction_finder;

import java.util.Objects;

/**
 * Class representing criteria for identifying amino-aromatic interactions.
 * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
 */
public final class AminoAromaticInteractionCriteria {

    final private double minDistanceBtwCationRing;
    final private double maxDistanceBtwCationRing;
    final private double minPolarAngle;
    final private double maxPolarAngle;
    final private double minAzimuthalAngle;
    final private double maxAzimuthalAngle;


    /**
     * Instantiates new amino-aromatic interaction criteria using default boundary values.
     * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     */
    public AminoAromaticInteractionCriteria() {
        this.minDistanceBtwCationRing = 3.4;
        this.maxDistanceBtwCationRing = 6;
        this.minPolarAngle = 0;
        this.maxPolarAngle = Double.MAX_VALUE;
        this.minAzimuthalAngle = 0;
        this.maxAzimuthalAngle = Double.MAX_VALUE;
    }

    /**
     * Instantiates new amino-aromatic interaction criteria using custom boundary values.
     * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     *
     * @param minDistanceBtwCationRing Minimum distance between aromatic ring centroid and amino group representative.
     * @param maxDistanceBtwCationRing Maximum distance between aromatic ring centroid and amino group representative.
     * @param minPolarAngle            Minimum polar angle for aromatic ring and amino group representative.
     * @param maxPolarAngle            Maximum polar angle for aromatic ring and amino group representative.
     * @param minAzimuthalAngle        Minimum azimuthal angle for aromatic ring and amino group representative.
     * @param maxAzimuthalAngle        Maximum azimuthal angle for aromatic ring and amino group representative.
     */
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

    /**
     * Returns minimum distance between aromatic ring centroid and amino group representative.
     *
     * @return Minimum distance between aromatic ring centroid and amino group representative.
     */
    public double getMinDistanceBtwCationRing() {
        return minDistanceBtwCationRing;
    }

    /**
     * Returns maximum distance between aromatic ring centroid and amino group representative.
     *
     * @return Maximum distance between aromatic ring centroid and amino group representative.
     */
    public double getMaxDistanceBtwCationRing() {
        return maxDistanceBtwCationRing;
    }

    /**
     * Returns minimum polar angle for aromatic ring and amino group representative.
     *
     * @return Minimum polar angle for aromatic ring and amino group representative.
     */
    public double getMinPolarAngle() {
        return minPolarAngle;
    }

    /**
     * Returns maximum polar angle for aromatic ring and amino group representative.
     *
     * @return Maximum polar angle for aromatic ring and amino group representative.
     */
    public double getMaxPolarAngle() {
        return maxPolarAngle;
    }

    /**
     * Returns minimum azimuthal angle for aromatic ring and amino group representative.
     *
     * @return Minimum azimuthal angle for aromatic ring and amino group representative.
     */
    public double getMinAzimuthalAngle() {
        return minAzimuthalAngle;
    }

    /**
     * Returns maximum azimuthal angle for aromatic ring and amino group representative.
     *
     * @return Maximum azimuthal angle for aromatic ring and amino group representative.
     */
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

package io.github.WeronikaJargielo.protein_interaction_finder;

import java.util.Objects;

/**
 * Class representing criteria for identifying sulphur-aromatic interactions.
 * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
 */
public final class SulphurAromaticInteractionCriteria {

    final private double minDistanceBtwRingSulphur;
    final private double maxDistanceBtwRingSulphur;
    final private double minElevationAngle;
    final private double maxElevationAngle;
    final private double minEquatorialAngle;
    final private double maxEquatorialAngle;


    /**
     * Instantiates new sulphur-aromatic interaction criteria using default boundary values.
     * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     */
    public SulphurAromaticInteractionCriteria() {
        this.minDistanceBtwRingSulphur = 0;
        this.maxDistanceBtwRingSulphur = 6;
        this.minElevationAngle = 0;
        this.maxElevationAngle = Double.MAX_VALUE;
        this.minEquatorialAngle = 0;
        this.maxEquatorialAngle = Double.MAX_VALUE;
    }

    /**
     * Instantiates new sulphur-aromatic interaction criteria using custom boundary values.
     * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     *
     * @param minDistanceBtwRingSulphur Minimum distance between aromatic ring centroid and sulphur atom.
     * @param maxDistanceBtwRingSulphur Maximum distance between aromatic ring centroid and sulphur atom.
     * @param minElevationAngle         Minimum elevation angle for aromatic ring and sulphur atom.
     * @param maxElevationAngle         Maximum elevation angle for aromatic ring and sulphur atom.
     * @param minEquatorialAngle        Minimum equatorial angle for aromatic ring and sulphur atom.
     * @param maxEquatorialAngle        Maximum equatorial angle for aromatic ring and sulphur atom.
     */
    public SulphurAromaticInteractionCriteria(double minDistanceBtwRingSulphur, double maxDistanceBtwRingSulphur,
                                              double minElevationAngle, double maxElevationAngle,
                                              double minEquatorialAngle, double maxEquatorialAngle) {

        this.minDistanceBtwRingSulphur = minDistanceBtwRingSulphur;
        this.maxDistanceBtwRingSulphur = maxDistanceBtwRingSulphur;
        this.minElevationAngle = minElevationAngle;
        this.maxElevationAngle = maxElevationAngle;
        this.minEquatorialAngle = minEquatorialAngle;
        this.maxEquatorialAngle = maxEquatorialAngle;
    }

    /**
     * Returns minimum distance between aromatic ring centroid and sulphur atom.
     *
     * @return Minimum distance between aromatic ring centroid and sulphur atom.
     */
    public double getMinDistanceBtwRingSulphur() {
        return minDistanceBtwRingSulphur;
    }

    /**
     * Returns maximum distance between aromatic ring centroid and sulphur atom.
     *
     * @return Maximum distance between aromatic ring centroid and sulphur atom.
     */
    public double getMaxDistanceBtwRingSulphur() {
        return maxDistanceBtwRingSulphur;
    }

    /**
     * Returns minimum elevation angle for aromatic ring and sulphur atom.
     *
     * @return Minimum elevation angle for aromatic ring and sulphur atom.
     */
    public double getMinElevationAngle() {
        return minElevationAngle;
    }

    /**
     * Returns maximum elevation angle for aromatic ring and sulphur atom.
     *
     * @return Maximum elevation angle for aromatic ring and sulphur atom.
     */
    public double getMaxElevationAngle() {
        return maxElevationAngle;
    }

    /**
     * Returns minimum equatorial angle for aromatic ring and sulphur atom.
     *
     * @return Minimum equatorial angle for aromatic ring and sulphur atom.
     */
    public double getMinEquatorialAngle() {
        return minEquatorialAngle;
    }

    /**
     * Returns maximum equatorial angle for aromatic ring and sulphur atom.
     *
     * @return Maximum equatorial angle for aromatic ring and sulphur atom.
     */
    public double getMaxEquatorialAngle() {
        return maxEquatorialAngle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SulphurAromaticInteractionCriteria that = (SulphurAromaticInteractionCriteria) o;
        return Double.compare(that.minDistanceBtwRingSulphur, minDistanceBtwRingSulphur) == 0
                              && Double.compare(that.maxDistanceBtwRingSulphur, maxDistanceBtwRingSulphur) == 0
                              && Double.compare(that.minElevationAngle, minElevationAngle) == 0
                              && Double.compare(that.maxElevationAngle, maxElevationAngle) == 0
                              && Double.compare(that.minEquatorialAngle, minEquatorialAngle) == 0
                              && Double.compare(that.maxEquatorialAngle, maxEquatorialAngle) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minDistanceBtwRingSulphur, maxDistanceBtwRingSulphur,
                            minElevationAngle, maxElevationAngle,
                            minEquatorialAngle, maxEquatorialAngle);
    }

    @Override
    public String toString() {
        return "SulphurAromaticInteractionCriteria: " +
                "minDistanceBtwRingSulphur = " + minDistanceBtwRingSulphur +
                ",\tmaxDistanceBtwRingSulphur = " + maxDistanceBtwRingSulphur +
                ",\tminElevationAngle = " + minElevationAngle +
                ",\tmaxElevationAngle = " + maxElevationAngle +
                ",\tminEquatorialAngle = " + minEquatorialAngle +
                ",\tmaxEquatorialAngle = " + maxEquatorialAngle;
    }
}

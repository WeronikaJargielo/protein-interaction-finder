package org.polsl.protein_interaction_searcher;

import java.util.Objects;

public final class SulphurAromaticInteractionCriteria {

    final private double minDistanceBtwRingSulphur;
    final private double maxDistanceBtwRingSulphur;
    final private double minElevationAngle;
    final private double maxElevationAngle;
    final private double minEquatorialAngle;
    final private double maxEquatorialAngle;


    public SulphurAromaticInteractionCriteria() {
        this.minDistanceBtwRingSulphur = 0;
        this.maxDistanceBtwRingSulphur = 6;
        this.minElevationAngle = 0;
        this.maxElevationAngle = 90;
        this.minEquatorialAngle = 0;
        this.maxEquatorialAngle = 360;
    }

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

    public double getMinDistanceBtwRingSulphur() {
        return minDistanceBtwRingSulphur;
    }

    public double getMaxDistanceBtwRingSulphur() {
        return maxDistanceBtwRingSulphur;
    }

    public double getMinElevationAngle() {
        return minElevationAngle;
    }

    public double getMaxElevationAngle() {
        return maxElevationAngle;
    }

    public double getMinEquatorialAngle() {
        return minEquatorialAngle;
    }

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

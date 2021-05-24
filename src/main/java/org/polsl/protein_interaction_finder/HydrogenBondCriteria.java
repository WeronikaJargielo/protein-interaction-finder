package org.polsl.protein_interaction_finder;

import java.util.Objects;

public final class HydrogenBondCriteria {

    final private double minDistanceDA;
    final private double maxDistanceDA;

    final private double minDistanceHA;
    final private double maxDistanceHA;

    final private double minAngleDHA;
    final private double maxAngleDHA;

    final private double minAngleHAAa;
    final private double maxAngleHAAa;

    final private double minAngleDAAa;
    final private double maxAngleDAAa;


    public HydrogenBondCriteria() {
        this.minDistanceDA = 0;
        this.maxDistanceDA = 3.9;
        this.minDistanceHA = 0;
        this.maxDistanceHA = 2.5;
        this.minAngleDHA = 90;
        this.maxAngleDHA = 180;
        this.minAngleHAAa = 90;
        this.maxAngleHAAa = 180;
        this.minAngleDAAa = 90;
        this.maxAngleDAAa = 180;
    }

    public HydrogenBondCriteria(double minDistanceDA, double maxDistanceDA, double minDistanceHA, double maxDistanceHA,
                                double minAngleDHA, double maxAngleDHA, double minAngleHAAa, double maxAngleHAAa, double minAngleDAAa, double maxAngleDAAa) {

        this.minDistanceDA = minDistanceDA;
        this.maxDistanceDA = maxDistanceDA;
        this.minDistanceHA = minDistanceHA;
        this.maxDistanceHA = maxDistanceHA;
        this.minAngleDHA = minAngleDHA;
        this.maxAngleDHA = maxAngleDHA;
        this.minAngleHAAa = minAngleHAAa;
        this.maxAngleHAAa = maxAngleHAAa;
        this.minAngleDAAa = minAngleDAAa;
        this.maxAngleDAAa = maxAngleDAAa;
    }

    public double getMinDistanceDA() {
        return minDistanceDA;
    }

    public double getMaxDistanceDA() {
        return maxDistanceDA;
    }

    public double getMinDistanceHA() {
        return minDistanceHA;
    }

    public double getMaxDistanceHA() {
        return maxDistanceHA;
    }

    public double getMinAngleDHA() {
        return minAngleDHA;
    }

    public double getMaxAngleDHA() {
        return maxAngleDHA;
    }

    public double getMinAngleHAAa() {
        return minAngleHAAa;
    }

    public double getMaxAngleHAAa() {
        return maxAngleHAAa;
    }

    public double getMinAngleDAAa() {
        return minAngleDAAa;
    }

    public double getMaxAngleDAAa() {
        return maxAngleDAAa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydrogenBondCriteria that = (HydrogenBondCriteria) o;
        return Double.compare(that.minDistanceDA, minDistanceDA) == 0
                              && Double.compare(that.maxDistanceDA, maxDistanceDA) == 0
                              && Double.compare(that.minDistanceHA, minDistanceHA) == 0
                              && Double.compare(that.maxDistanceHA, maxDistanceHA) == 0
                              && Double.compare(that.minAngleDHA, minAngleDHA) == 0
                              && Double.compare(that.maxAngleDHA, maxAngleDHA) == 0
                              && Double.compare(that.minAngleHAAa, minAngleHAAa) == 0
                              && Double.compare(that.maxAngleHAAa, maxAngleHAAa) == 0
                              && Double.compare(that.minAngleDAAa, minAngleDAAa) == 0
                              && Double.compare(that.maxAngleDAAa, maxAngleDAAa) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minDistanceDA, maxDistanceDA, minDistanceHA, maxDistanceHA,
                            minAngleDHA, maxAngleDHA, minAngleHAAa, maxAngleHAAa, minAngleDAAa, maxAngleDAAa);
    }

    @Override
    public String toString() {
        return "HydrogenBondCriteria: " +
                "minDistanceDA = " + minDistanceDA +
                ",\tmaxDistanceDA = " + maxDistanceDA +
                ",\tminDistanceHA = " + minDistanceHA +
                ",\tmaxDistanceHA = " + maxDistanceHA +
                ",\tminAngleDHA = " + minAngleDHA +
                ",\tmaxAngleDHA = " + maxAngleDHA +
                ",\tminAngleHAAa = " + minAngleHAAa +
                ",\tmaxAngleHAAa = " + maxAngleHAAa +
                ",\tminAngleDAAa = " + minAngleDAAa +
                ",\tmaxAngleDAAa = " + maxAngleDAAa;
    }
}

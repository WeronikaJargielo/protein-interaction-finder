package io.github.WeronikaJargielo.protein_interaction_finder;

import java.util.Objects;


/**
 * Class representing criteria for identifying hydrogen bonds.
 * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
 */
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

    /**
     * Instantiates new hydrogen bonds criteria using default boundary values.
     * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     */
    public HydrogenBondCriteria() {
        this.minDistanceDA = 0;
        this.maxDistanceDA = Double.MAX_VALUE;
        this.minDistanceHA = 0;
        this.maxDistanceHA = 2.5;
        this.minAngleDHA = 90;
        this.maxAngleDHA = 180;
        this.minAngleHAAa = 0;
        this.maxAngleHAAa = Double.MAX_VALUE;
        this.minAngleDAAa = 0;
        this.maxAngleDAAa = Double.MAX_VALUE;
    }

    /**
     * Instantiates new hydrogen bonds criteria using custom boundary values.
     * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     *
     * @param minDistanceDA Minimum distance between donor and acceptor.
     * @param maxDistanceDA Maximum distance between donor and acceptor.
     * @param minDistanceHA Minimum distance between hydrogen and acceptor.
     * @param maxDistanceHA Maximum distance between hydrogen and acceptor.
     * @param minAngleDHA   Minimum angle between donor, hydrogen and acceptor.
     * @param maxAngleDHA   Maximum angle between donor, hydrogen and acceptor.
     * @param minAngleHAAa  Minimum angle between hydrogen, acceptor and acceptor antecedent.
     * @param maxAngleHAAa  Maximum angle between hydrogen, acceptor and acceptor antecedent.
     * @param minAngleDAAa  Minimum angle between donor, acceptor and acceptor antecedent.
     * @param maxAngleDAAa  Maximum angle between donor, acceptor and acceptor antecedent.
     */
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

    /**
     * Returns minimum distance between donor and acceptor.
     *
     * @return Minimum distance between donor and acceptor.
     */
    public double getMinDistanceDA() {
        return minDistanceDA;
    }

    /**
     * Returns maximum distance between donor and acceptor.
     *
     * @return Maximum distance between donor and acceptor.
     */
    public double getMaxDistanceDA() {
        return maxDistanceDA;
    }

    /**
     * Returns minimum distance between hydrogen and acceptor.
     *
     * @return Minimum distance between hydrogen and acceptor.
     */
    public double getMinDistanceHA() {
        return minDistanceHA;
    }

    /**
     * Returns maximum distance between hydrogen and acceptor.
     *
     * @return Maximum distance between hydrogen and acceptor.
     */
    public double getMaxDistanceHA() {
        return maxDistanceHA;
    }

    /**
     * Returns minimum angle between donor, hydrogen and acceptor.
     *
     * @return Minimum angle between donor, hydrogen and acceptor.
     */
    public double getMinAngleDHA() {
        return minAngleDHA;
    }

    /**
     * Returns maximum angle between donor, hydrogen and acceptor.
     *
     * @return Maximum angle between donor, hydrogen and acceptor.
     */
    public double getMaxAngleDHA() {
        return maxAngleDHA;
    }

    /**
     * Returns minimum between hydrogen, acceptor and acceptor antecedent.
     *
     * @return Minimum between hydrogen, acceptor and acceptor antecedent.
     */
    public double getMinAngleHAAa() {
        return minAngleHAAa;
    }

    /**
     * Returns maximum between hydrogen, acceptor and acceptor antecedent.
     *
     * @return Maximum between hydrogen, acceptor and acceptor antecedent.
     */
    public double getMaxAngleHAAa() {
        return maxAngleHAAa;
    }

    /**
     * Returns minimum between donor, acceptor and acceptor antecedent.
     *
     * @return Minimum between donor, acceptor and acceptor antecedent.
     */
    public double getMinAngleDAAa() {
        return minAngleDAAa;
    }

    /**
     * Returns maximum between donor, acceptor and acceptor antecedent.
     *
     * @return Maximum between donor, acceptor and acceptor antecedent.
     */
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

package io.github.WeronikaJargielo.protein_interaction_finder;

import java.util.Objects;

/**
 * Class representing criteria for identifying disulphide bridges.
 * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
 */
public class DisulphideBridgeCriteria {

    private final double distanceBtwCAs = 6.5;
    private final double distanceBtwCBs = 4.5;

    private final double minDistanceBtwSulphurs;
    private final double maxDistanceBtwSulphurs;

    private final double minAbsDihAngleSS;
    private final double maxAbsDihAngleSS;

    private final double lowerRangeMinAbsDihAngle1;
    private final double lowerRangeMaxAbsDihAngle1;
    private final double upperRangeMinAbsDihAngle1;
    private final double upperRangeMaxAbsDihAngle1;

    private final double lowerRangeMinAbsDihAngle2;
    private final double lowerRangeMaxAbsDihAngle2;
    private final double upperRangeMinAbsDihAngle2;
    private final double upperRangeMaxAbsDihAngle2;

    /**
     * Instantiates new disulphide bridges criteria using default boundary values.
     * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     */
    public DisulphideBridgeCriteria() {
        this.minDistanceBtwSulphurs = 1.6;
        this.maxDistanceBtwSulphurs = 2.4;
        this.minAbsDihAngleSS = 60;
        this.maxAbsDihAngleSS = 120;
        this.lowerRangeMinAbsDihAngle1 = 30;
        this.lowerRangeMaxAbsDihAngle1 = 90;
        this.upperRangeMinAbsDihAngle1 = 150;
        this.upperRangeMaxAbsDihAngle1 = 180;
        this.lowerRangeMinAbsDihAngle2 = 30;
        this.lowerRangeMaxAbsDihAngle2 = 90;
        this.upperRangeMinAbsDihAngle2 = 150;
        this.upperRangeMaxAbsDihAngle2 = 180;
    }

    /**
     * Instantiates new disulphide bridges criteria using custom boundary values.
     * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     *
     * @param minDistanceBtwSulphurs    Minimum distance between sulphur atoms of participating cysteines.
     * @param maxDistanceBtwSulphurs    Maximum distance between sulphur atoms of participating cysteines.
     * @param minAbsDihAngleSS          Minimum dihedral angle between planes formed by CB1, S1, S2 and S1, S2, CB2 atoms (1 - first cysteine, 2 - second cysteine).
     * @param maxAbsDihAngleSS          Maximum dihedral angle between planes formed by CB1, S1, S2 and S1, S2, CB2 atoms (1 - first cysteine, 2 - second cysteine).
     * @param lowerRangeMinAbsDihAngle1 Minimum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of first cysteine in first range.
     * @param lowerRangeMaxAbsDihAngle1 Maximum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of first cysteine in first range.
     * @param upperRangeMinAbsDihAngle1 Minimum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of first cysteine in second range.
     * @param upperRangeMaxAbsDihAngle1 Maximum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of first cysteine in second range.
     * @param lowerRangeMinAbsDihAngle2 Minimum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of second cysteine in first range.
     * @param lowerRangeMaxAbsDihAngle2 Maximum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of second cysteine in first range.
     * @param upperRangeMinAbsDihAngle2 Minimum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of second cysteine in second range.
     * @param upperRangeMaxAbsDihAngle2 Maximum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of second cysteine in second range.
     */
    public DisulphideBridgeCriteria(double minDistanceBtwSulphurs, double maxDistanceBtwSulphurs, double minAbsDihAngleSS, double maxAbsDihAngleSS,
                                    double lowerRangeMinAbsDihAngle1, double lowerRangeMaxAbsDihAngle1, double upperRangeMinAbsDihAngle1, double upperRangeMaxAbsDihAngle1,
                                    double lowerRangeMinAbsDihAngle2, double lowerRangeMaxAbsDihAngle2, double upperRangeMinAbsDihAngle2, double upperRangeMaxAbsDihAngle2) {

        this.minDistanceBtwSulphurs = minDistanceBtwSulphurs;
        this.maxDistanceBtwSulphurs = maxDistanceBtwSulphurs;
        this.minAbsDihAngleSS = minAbsDihAngleSS;
        this.maxAbsDihAngleSS = maxAbsDihAngleSS;
        this.lowerRangeMinAbsDihAngle1 = lowerRangeMinAbsDihAngle1;
        this.lowerRangeMaxAbsDihAngle1 = lowerRangeMaxAbsDihAngle1;
        this.upperRangeMinAbsDihAngle1 = upperRangeMinAbsDihAngle1;
        this.upperRangeMaxAbsDihAngle1 = upperRangeMaxAbsDihAngle1;
        this.lowerRangeMinAbsDihAngle2 = lowerRangeMinAbsDihAngle2;
        this.lowerRangeMaxAbsDihAngle2 = lowerRangeMaxAbsDihAngle2;
        this.upperRangeMinAbsDihAngle2 = upperRangeMinAbsDihAngle2;
        this.upperRangeMaxAbsDihAngle2 = upperRangeMaxAbsDihAngle2;
    }

    /**
     * Returns maximum distance between CA atoms of participating cysteines.
     *
     * @return Maximum distance between CA atoms of participating cysteines.
     */
    public double getDistanceBtwCAs() {
        return distanceBtwCAs;
    }

    /**
     * Returns maximum distance between CB atoms of participating cysteines.
     *
     * @return Maximum distance between CB atoms of participating cysteines.
     */
    public double getDistanceBtwCBs() {
        return distanceBtwCBs;
    }

    /**
     * Returns minimum distance between sulphur atoms of participating cysteines.
     *
     * @return Minimum distance between sulphur atoms of participating cysteines.
     */
    public double getMinDistanceBtwSulphurs() {
        return minDistanceBtwSulphurs;
    }

    /**
     * Returns maximum distance between sulphur atoms of participating cysteines.
     *
     * @return Maximum distance between sulphur atoms of participating cysteines.
     */
    public double getMaxDistanceBtwSulphurs() {
        return maxDistanceBtwSulphurs;
    }

    /**
     * Returns minimum dihedral angle between planes formed by CB1, S1, S2 and S1, S2, CB2 atoms (1 - first cysteine, 2 - second cysteine).
     *
     * @return Minimum dihedral angle between planes formed by CB1, S1, S2 and S1, S2, CB2 atoms (1 - first cysteine, 2 - second cysteine).
     */
    public double getMinAbsDihAngleSS() {
        return minAbsDihAngleSS;
    }

    /**
     * Returns maximum dihedral angle between planes formed by CB1, S1, S2 and S1, S2, CB2 atoms (1 - first cysteine, 2 - second cysteine).
     *
     * @return Maximum dihedral angle between planes formed by CB1, S1, S2 and S1, S2, CB2 atoms (1 - first cysteine, 2 - second cysteine).
     */
    public double getMaxAbsDihAngleSS() {
        return maxAbsDihAngleSS;
    }

    /**
     * Returns minimum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of first cysteine in first range.
     *
     * @return Minimum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of first cysteine in first range.
     */
    public double getLowerRangeMinAbsDihAngle1() {
        return lowerRangeMinAbsDihAngle1;
    }

    /**
     * Returns maximum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of first cysteine in first range.
     *
     * @return Maximum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of first cysteine in first range.
     */
    public double getLowerRangeMaxAbsDihAngle1() {
        return lowerRangeMaxAbsDihAngle1;
    }

    /**
     * Returns minimum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of first cysteine in second range.
     *
     * @return Minimum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of first cysteine in second range.
     */
    public double getUpperRangeMinAbsDihAngle1() {
        return upperRangeMinAbsDihAngle1;
    }

    /**
     * Returns maximum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of first cysteine in second range.
     *
     * @return Maximum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of first cysteine in second range.
     */
    public double getUpperRangeMaxAbsDihAngle1() {
        return upperRangeMaxAbsDihAngle1;
    }

    /**
     * Returns minimum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of second cysteine in first range.
     *
     * @return Minimum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of second cysteine in first range.
     */
    public double getLowerRangeMinAbsDihAngle2() {
        return lowerRangeMinAbsDihAngle2;
    }

    /**
     * Returns maximum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of second cysteine in first range.
     *
     * @return Maximum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of second cysteine in first range.
     */
    public double getLowerRangeMaxAbsDihAngle2() {
        return lowerRangeMaxAbsDihAngle2;
    }

    /**
     * Returns minimum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of second cysteine in second range.
     *
     * @return Minimum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of second cysteine in second range.
     */
    public double getUpperRangeMinAbsDihAngle2() {
        return upperRangeMinAbsDihAngle2;
    }

    /**
     * Returns maximum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of second cysteine in second range.
     *
     * @return Maximum dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of second cysteine in second range.
     */
    public double getUpperRangeMaxAbsDihAngle2() {
        return upperRangeMaxAbsDihAngle2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisulphideBridgeCriteria that = (DisulphideBridgeCriteria) o;
        return Double.compare(that.distanceBtwCAs, distanceBtwCAs) == 0
                              && Double.compare(that.distanceBtwCBs, distanceBtwCBs) == 0
                              && Double.compare(that.minDistanceBtwSulphurs, minDistanceBtwSulphurs) == 0
                              && Double.compare(that.maxDistanceBtwSulphurs, maxDistanceBtwSulphurs) == 0
                              && Double.compare(that.minAbsDihAngleSS, minAbsDihAngleSS) == 0
                              && Double.compare(that.maxAbsDihAngleSS, maxAbsDihAngleSS) == 0
                              && Double.compare(that.lowerRangeMinAbsDihAngle1, lowerRangeMinAbsDihAngle1) == 0
                              && Double.compare(that.lowerRangeMaxAbsDihAngle1, lowerRangeMaxAbsDihAngle1) == 0
                              && Double.compare(that.upperRangeMinAbsDihAngle1, upperRangeMinAbsDihAngle1) == 0
                              && Double.compare(that.upperRangeMaxAbsDihAngle1, upperRangeMaxAbsDihAngle1) == 0
                              && Double.compare(that.lowerRangeMinAbsDihAngle2, lowerRangeMinAbsDihAngle2) == 0
                              && Double.compare(that.lowerRangeMaxAbsDihAngle2, lowerRangeMaxAbsDihAngle2) == 0
                              && Double.compare(that.upperRangeMinAbsDihAngle2, upperRangeMinAbsDihAngle2) == 0
                              && Double.compare(that.upperRangeMaxAbsDihAngle2, upperRangeMaxAbsDihAngle2) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(distanceBtwCAs, distanceBtwCBs, minDistanceBtwSulphurs, maxDistanceBtwSulphurs, minAbsDihAngleSS, maxAbsDihAngleSS,
                lowerRangeMinAbsDihAngle1, lowerRangeMaxAbsDihAngle1, upperRangeMinAbsDihAngle1, upperRangeMaxAbsDihAngle1,
                lowerRangeMinAbsDihAngle2, lowerRangeMaxAbsDihAngle2, upperRangeMinAbsDihAngle2, upperRangeMaxAbsDihAngle2);
    }

    @Override
    public String toString() {
        return "DisulphideBridgeCriteria:" +
                "distanceBtwCAs = " + distanceBtwCAs +
                ",\tdistanceBtwCBs = " + distanceBtwCBs +
                ",\tminDistanceBtwSulphurs = " + minDistanceBtwSulphurs +
                ",\tmaxDistanceBtwSulphurs = " + maxDistanceBtwSulphurs +
                ",\tminAbsDihAngleSS = " + minAbsDihAngleSS +
                ",\tmaxAbsDihAngleSS = " + maxAbsDihAngleSS +
                ",\tlowerRangeMinAbsDihAngle11 = " + lowerRangeMinAbsDihAngle1 +
                ",\tlowerRangeMaxAbsDihAngle11 = " + lowerRangeMaxAbsDihAngle1 +
                ",\tupperRangeMinAbsDihAngle11 = " + upperRangeMinAbsDihAngle1 +
                ",\tupperRangeMaxAbsDihAngle11 = " + upperRangeMaxAbsDihAngle1 +
                ",\tlowerRangeMinAbsDihAngle21 = " + lowerRangeMinAbsDihAngle2 +
                ",\tlowerRangeMaxAbsDihAngle21 = " + lowerRangeMaxAbsDihAngle2 +
                ",\tupperRangeMinAbsDihAngle21 = " + upperRangeMinAbsDihAngle2 +
                ",\tupperRangeMaxAbsDihAngle21 = " + upperRangeMaxAbsDihAngle2;

    }
}

package org.polsl.protein_interaction_searcher;

import java.util.Objects;

public class DisulphideBridgeCriteria {

    private final double distanceBtwCAs = 6.5;
    private final double distanceBtwCBs = 4.5;

    private final double minDistanceBtwSulphurs;
    private final double maxDistanceBtwSulphurs;

    private final double minAbsDihAngleSS;
    private final double maxAbsDihAngleSS;

    private final double lowerRangeMinAbsDihAngle11;
    private final double lowerRangeMaxAbsDihAngle11;
    private final double upperRangeMinAbsDihAngle11;
    private final double upperRangeMaxAbsDihAngle11;

    private final double lowerRangeMinAbsDihAngle21;
    private final double lowerRangeMaxAbsDihAngle21;
    private final double upperRangeMinAbsDihAngle21;
    private final double upperRangeMaxAbsDihAngle21;


    public DisulphideBridgeCriteria() {
        this.minDistanceBtwSulphurs = 1.6;
        this.maxDistanceBtwSulphurs = 2.4;
        this.minAbsDihAngleSS = 60;
        this.maxAbsDihAngleSS = 120;
        this.lowerRangeMinAbsDihAngle11 = 30;
        this.lowerRangeMaxAbsDihAngle11 = 90;
        this.upperRangeMinAbsDihAngle11 = 150;
        this.upperRangeMaxAbsDihAngle11 = 180;
        this.lowerRangeMinAbsDihAngle21 = 30;
        this.lowerRangeMaxAbsDihAngle21 = 90;
        this.upperRangeMinAbsDihAngle21 = 150;
        this.upperRangeMaxAbsDihAngle21 = 180;
    }

    public DisulphideBridgeCriteria(double minDistanceBtwSulphurs, double maxDistanceBtwSulphurs, double minAbsDihAngleSS, double maxAbsDihAngleSS,
                                    double lowerRangeMinAbsDihAngle11, double lowerRangeMaxAbsDihAngle11, double upperRangeMinAbsDihAngle11, double upperRangeMaxAbsDihAngle11,
                                    double lowerRangeMinAbsDihAngle21, double lowerRangeMaxAbsDihAngle21, double upperRangeMinAbsDihAngle21, double upperRangeMaxAbsDihAngle21) {

        this.minDistanceBtwSulphurs = minDistanceBtwSulphurs;
        this.maxDistanceBtwSulphurs = maxDistanceBtwSulphurs;
        this.minAbsDihAngleSS = minAbsDihAngleSS;
        this.maxAbsDihAngleSS = maxAbsDihAngleSS;
        this.lowerRangeMinAbsDihAngle11 = lowerRangeMinAbsDihAngle11;
        this.lowerRangeMaxAbsDihAngle11 = lowerRangeMaxAbsDihAngle11;
        this.upperRangeMinAbsDihAngle11 = upperRangeMinAbsDihAngle11;
        this.upperRangeMaxAbsDihAngle11 = upperRangeMaxAbsDihAngle11;
        this.lowerRangeMinAbsDihAngle21 = lowerRangeMinAbsDihAngle21;
        this.lowerRangeMaxAbsDihAngle21 = lowerRangeMaxAbsDihAngle21;
        this.upperRangeMinAbsDihAngle21 = upperRangeMinAbsDihAngle21;
        this.upperRangeMaxAbsDihAngle21 = upperRangeMaxAbsDihAngle21;
    }

    public double getDistanceBtwCAs() {
        return distanceBtwCAs;
    }

    public double getDistanceBtwCBs() {
        return distanceBtwCBs;
    }

    public double getMinDistanceBtwSulphurs() {
        return minDistanceBtwSulphurs;
    }

    public double getMaxDistanceBtwSulphurs() {
        return maxDistanceBtwSulphurs;
    }

    public double getMinAbsDihAngleSS() {
        return minAbsDihAngleSS;
    }

    public double getMaxAbsDihAngleSS() {
        return maxAbsDihAngleSS;
    }

    public double getLowerRangeMinAbsDihAngle11() {
        return lowerRangeMinAbsDihAngle11;
    }

    public double getLowerRangeMaxAbsDihAngle11() {
        return lowerRangeMaxAbsDihAngle11;
    }

    public double getUpperRangeMinAbsDihAngle11() {
        return upperRangeMinAbsDihAngle11;
    }

    public double getUpperRangeMaxAbsDihAngle11() {
        return upperRangeMaxAbsDihAngle11;
    }

    public double getLowerRangeMinAbsDihAngle21() {
        return lowerRangeMinAbsDihAngle21;
    }

    public double getLowerRangeMaxAbsDihAngle21() {
        return lowerRangeMaxAbsDihAngle21;
    }

    public double getUpperRangeMinAbsDihAngle21() {
        return upperRangeMinAbsDihAngle21;
    }

    public double getUpperRangeMaxAbsDihAngle21() {
        return upperRangeMaxAbsDihAngle21;
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
                              && Double.compare(that.lowerRangeMinAbsDihAngle11, lowerRangeMinAbsDihAngle11) == 0
                              && Double.compare(that.lowerRangeMaxAbsDihAngle11, lowerRangeMaxAbsDihAngle11) == 0
                              && Double.compare(that.upperRangeMinAbsDihAngle11, upperRangeMinAbsDihAngle11) == 0
                              && Double.compare(that.upperRangeMaxAbsDihAngle11, upperRangeMaxAbsDihAngle11) == 0
                              && Double.compare(that.lowerRangeMinAbsDihAngle21, lowerRangeMinAbsDihAngle21) == 0
                              && Double.compare(that.lowerRangeMaxAbsDihAngle21, lowerRangeMaxAbsDihAngle21) == 0
                              && Double.compare(that.upperRangeMinAbsDihAngle21, upperRangeMinAbsDihAngle21) == 0
                              && Double.compare(that.upperRangeMaxAbsDihAngle21, upperRangeMaxAbsDihAngle21) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(distanceBtwCAs, distanceBtwCBs, minDistanceBtwSulphurs, maxDistanceBtwSulphurs, minAbsDihAngleSS, maxAbsDihAngleSS,
                            lowerRangeMinAbsDihAngle11, lowerRangeMaxAbsDihAngle11, upperRangeMinAbsDihAngle11, upperRangeMaxAbsDihAngle11,
                            lowerRangeMinAbsDihAngle21, lowerRangeMaxAbsDihAngle21, upperRangeMinAbsDihAngle21, upperRangeMaxAbsDihAngle21);
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
                ",\tlowerRangeMinAbsDihAngle11 = " + lowerRangeMinAbsDihAngle11 +
                ",\tlowerRangeMaxAbsDihAngle11 = " + lowerRangeMaxAbsDihAngle11 +
                ",\tupperRangeMinAbsDihAngle11 = " + upperRangeMinAbsDihAngle11 +
                ",\tupperRangeMaxAbsDihAngle11 = " + upperRangeMaxAbsDihAngle11 +
                ",\tlowerRangeMinAbsDihAngle21 = " + lowerRangeMinAbsDihAngle21 +
                ",\tlowerRangeMaxAbsDihAngle21 = " + lowerRangeMaxAbsDihAngle21 +
                ",\tupperRangeMinAbsDihAngle21 = " + upperRangeMinAbsDihAngle21 +
                ",\tupperRangeMaxAbsDihAngle21 = " + upperRangeMaxAbsDihAngle21;

    }
}

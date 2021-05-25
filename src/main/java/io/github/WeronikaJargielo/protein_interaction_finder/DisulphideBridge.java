package io.github.WeronikaJargielo.protein_interaction_finder;

import java.util.Objects;

public class DisulphideBridge {
    private final AminoAcid firstInteractee;
    private final AminoAcid secondInteractee;
    private final double distanceBtwCAs;
    private final double distanceBtwCBs;
    private final double distanceBtwSS;
    private final double absDihAngleSS;
    private final double absDihAngle11;
    private final double absDihAngle21;


    public DisulphideBridge(AminoAcid firstInteractee, AminoAcid secondInteractee,
                            double distanceBtwCAs, double distanceBtwCBs, double distanceBtwSS,
                            double absDihAngleSS, double absDihAngle11, double absDihAngle21) {

        this.firstInteractee = firstInteractee;
        this.secondInteractee = secondInteractee;
        this.distanceBtwCAs = distanceBtwCAs;
        this.distanceBtwCBs = distanceBtwCBs;
        this.distanceBtwSS = distanceBtwSS;
        this.absDihAngleSS = absDihAngleSS;
        this.absDihAngle11 = absDihAngle11;
        this.absDihAngle21 = absDihAngle21;
    }

    public AminoAcid getFirstInteractee() {
        return firstInteractee;
    }

    public AminoAcid getSecondInteractee() {
        return secondInteractee;
    }

    public double getDistanceBtwCAs() {
        return distanceBtwCAs;
    }

    public double getDistanceBtwCBs() {
        return distanceBtwCBs;
    }

    public double getDistanceBtwSS() {
        return distanceBtwSS;
    }

    public double getAbsDihAngleSS() {
        return absDihAngleSS;
    }

    public double getAbsDihAngle11() {
        return absDihAngle11;
    }

    public double getAbsDihAngle21() {
        return absDihAngle21;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisulphideBridge that = (DisulphideBridge) o;
        return Double.compare(that.distanceBtwCAs, distanceBtwCAs) == 0
                && Double.compare(that.distanceBtwCBs, distanceBtwCBs) == 0
                && Double.compare(that.distanceBtwSS, distanceBtwSS) == 0
                && Double.compare(that.absDihAngleSS, absDihAngleSS) == 0
                && Double.compare(that.absDihAngle11, absDihAngle11) == 0
                && Double.compare(that.absDihAngle21, absDihAngle21) == 0
                && Objects.equals(firstInteractee, that.firstInteractee)
                && Objects.equals(secondInteractee, that.secondInteractee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstInteractee, secondInteractee,
                            distanceBtwCAs, distanceBtwCBs, distanceBtwSS,
                            absDihAngleSS, absDihAngle11, absDihAngle21);
    }

    @Override
    public String toString() {
        return "" + firstInteractee + "\t" + secondInteractee
                  + "\t" + MathHelper.round(distanceBtwCAs) + "\t" + MathHelper.round(distanceBtwCBs) + "\t" + MathHelper.round(distanceBtwSS)
                  + "\t" + MathHelper.round(absDihAngleSS) + "\t" + MathHelper.round(absDihAngle11) + "\t" + MathHelper.round(absDihAngle21);
    }
}

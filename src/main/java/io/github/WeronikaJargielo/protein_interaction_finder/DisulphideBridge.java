package io.github.WeronikaJargielo.protein_interaction_finder;

import java.util.Objects;

/**
 * Class representing disulphide bridge.
 */
public class DisulphideBridge {
    private final AminoAcid firstInteractee;
    private final AminoAcid secondInteractee;
    private final double distanceBtwCAs;
    private final double distanceBtwCBs;
    private final double distanceBtwSS;
    private final double absDihAngleSS;
    private final double absDihAngle1;
    private final double absDihAngle2;


    /**
     * Instantiates new disulphide bridge.
     * For parameters' full description see <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     *
     * @param firstInteractee  First cysteine.
     * @param secondInteractee Second cysteine.
     * @param distanceBtwCAs   Distance between CA atoms of participating cysteines.
     * @param distanceBtwCBs   Distance between CB atoms of participating cysteines.
     * @param distanceBtwSS    Distance between sulphur atoms of participating cysteines.
     * @param absDihAngleSS    Dihedral angle between planes formed by CB1, S1, S2 and S1, S2, CB2 atoms (1 - first cysteine, 2 - second cysteine).
     * @param absDihAngle1     Dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of first cysteine.
     * @param absDihAngle2     Dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of second cysteine.
     */
    public DisulphideBridge(AminoAcid firstInteractee, AminoAcid secondInteractee,
                            double distanceBtwCAs, double distanceBtwCBs, double distanceBtwSS,
                            double absDihAngleSS, double absDihAngle1, double absDihAngle2) {

        this.firstInteractee = firstInteractee;
        this.secondInteractee = secondInteractee;
        this.distanceBtwCAs = distanceBtwCAs;
        this.distanceBtwCBs = distanceBtwCBs;
        this.distanceBtwSS = distanceBtwSS;
        this.absDihAngleSS = absDihAngleSS;
        this.absDihAngle1 = absDihAngle1;
        this.absDihAngle2 = absDihAngle2;
    }

    /**
     * Returns first cysteine.
     *
     * @return First cysteine.
     */
    public AminoAcid getFirstInteractee() {
        return firstInteractee;
    }

    /**
     * Returns second cysteine.
     *
     * @return Second cysteine
     */
    public AminoAcid getSecondInteractee() {
        return secondInteractee;
    }

    /**
     * Returns distance between CA atoms of participating cysteines.
     *
     * @return Distance between CA atoms of participating cysteines.
     */
    public double getDistanceBtwCAs() {
        return distanceBtwCAs;
    }

    /**
     * Returns distance between CB atoms of participating cysteines.
     *
     * @return Distance between CB atoms of participating cysteines.
     */
    public double getDistanceBtwCBs() {
        return distanceBtwCBs;
    }

    /**
     * Returns distance between sulphur atoms of participating cysteines.
     *
     * @return Distance between sulphur atoms of participating cysteines.
     */
    public double getDistanceBtwSS() {
        return distanceBtwSS;
    }

    /**
     * Returns dihedral angle between planes formed by CB1, S1, S2 and S1, S2, CB2 atoms (1 - first cysteine, 2 - second cysteine).
     *
     * @return Dihedral angle between planes formed by CB1, S1, S2 and S1, S2, CB2 atoms (1 - first cysteine, 2 - second cysteine).
     */
    public double getAbsDihAngleSS() {
        return absDihAngleSS;
    }

    /**
     * Returns dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of first cysteine.
     *
     * @return Dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of first cysteine.
     */
    public double getAbsDihAngle1() {
        return absDihAngle1;
    }

    /**
     * Returns dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of second cysteine.
     *
     * @return Dihedral angle between planes formed by N, CA, CB and CA, CB, S atoms of second cysteine.
     */
    public double getAbsDihAngle2() {
        return absDihAngle2;
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
                && Double.compare(that.absDihAngle1, absDihAngle1) == 0
                && Double.compare(that.absDihAngle2, absDihAngle2) == 0
                && Objects.equals(firstInteractee, that.firstInteractee)
                && Objects.equals(secondInteractee, that.secondInteractee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstInteractee, secondInteractee,
                            distanceBtwCAs, distanceBtwCBs, distanceBtwSS,
                            absDihAngleSS, absDihAngle1, absDihAngle2);
    }

    @Override
    public String toString() {
        return "" + firstInteractee + "\t" + secondInteractee
                  + "\t" + MathHelper.round(distanceBtwCAs) + "\t" + MathHelper.round(distanceBtwCBs) + "\t" + MathHelper.round(distanceBtwSS)
                  + "\t" + MathHelper.round(absDihAngleSS) + "\t" + MathHelper.round(absDihAngle1) + "\t" + MathHelper.round(absDihAngle2);
    }
}

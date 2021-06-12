package io.github.WeronikaJargielo.protein_interaction_finder;

import org.biojava.nbio.structure.Atom;

import java.util.Objects;

/**
 * Class representing hydrogen bond.
 */
public final class HydrogenBond {

    private final AminoAcid donor;
    private final AminoAcid acceptor;

    private final Atom donorAtom;
    private final Atom hydrogenAtom;
    private final Atom acceptorAtom;
    private final Atom acceptorAntecedentAtom;

    private final double distanceHA;
    private final double distanceDA;
    private final double angleDHA;
    private final double angleHAAa;
    private final double angleDAAa;

    /**
     * Instantiates new hydrogen bond.
     * For parameters' full description see <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     *
     * @param donor                  Amino acid of participant with donor atom.
     * @param acceptor               Amino acid of participant with acceptor atom.
     * @param donorAtom              Hydrogen bond donor.
     * @param hydrogenAtom           Hydrogen atom covalently bound to donor atom.
     * @param acceptorAtom           Hydrogen bond acceptor.
     * @param acceptorAntecedentAtom Atom preceding hydrogen bond acceptor.
     * @param distanceHA             Distance between hydrogen and acceptor.
     * @param distanceDA             Distance between donor and acceptor.
     * @param angleDHA               Angle between donor, hydrogen and acceptor.
     * @param angleHAAa              Angle between hydrogen, acceptor and acceptor antecedent.
     * @param angleDAAa              Angle between donor, acceptor and acceptor antecedent.
     */
    public HydrogenBond(AminoAcid donor, AminoAcid acceptor, Atom donorAtom, Atom hydrogenAtom, Atom acceptorAtom, Atom acceptorAntecedentAtom,
                        double distanceHA, double distanceDA, double angleDHA, double angleHAAa, double angleDAAa) {
        
        this.donor = donor;
        this.acceptor = acceptor;
        this.donorAtom = donorAtom;
        this.hydrogenAtom = hydrogenAtom;
        this.acceptorAtom = acceptorAtom;
        this.acceptorAntecedentAtom = acceptorAntecedentAtom;
        this.distanceHA = distanceHA;
        this.distanceDA = distanceDA;
        this.angleDHA = angleDHA;
        this.angleHAAa = angleHAAa;
        this.angleDAAa = angleDAAa;
    }

    /**
     * Returns amino acid of participant with donor atom.
     *
     * @return Amino acid of participant with donor atom.
     */
    public AminoAcid getDonor() {
        return donor;
    }

    /**
     * Returns amino acid of participant with acceptor atom.
     *
     * @return Amino acid of participant with acceptor atom.
     */
    public AminoAcid getAcceptor() {
        return acceptor;
    }

    /**
     * Returns hydrogen bond donor.
     *
     * @return Hydrogen bond donor.
     */
    public Atom getDonorAtom() {
        return donorAtom;
    }

    /**
     * Returns hydrogen atom bound to donor.
     *
     * @return Hydrogen atom bound to donor.
     */
    public Atom getHydrogenAtom() {
        return hydrogenAtom;
    }

    /**
     * Returns hydrogen bond acceptor.
     *
     * @return Hydrogen bond acceptor.
     */
    public Atom getAcceptorAtom() {
        return acceptorAtom;
    }

    /**
     * Returns atom preceding hydrogen bond acceptor.
     *
     * @return Atom preceding hydrogen bond acceptor.
     */
    public Atom getAcceptorAntecedentAtom() {
        return acceptorAntecedentAtom;
    }

    /**
     * Returns distance between hydrogen and acceptor.
     *
     * @return Distance between hydrogen and acceptor.
     */
    public double getDistanceHA() {
        return distanceHA;
    }

    /**
     * Returns distance between donor and acceptor.
     *
     * @return Distance between donor and acceptor.
     */
    public double getDistanceDA() {
        return distanceDA;
    }

    /**
     * Returns angle between donor, hydrogen and acceptor.
     *
     * @return Angle between donor, hydrogen and acceptor.
     */
    public double getAngleDHA() {
        return angleDHA;
    }

    /**
     * Returns angle between hydrogen, acceptor and acceptor antecedent.
     *
     * @return Angle between hydrogen, acceptor and acceptor antecedent.
     */
    public double getAngleHAAa() {
        return angleHAAa;
    }

    /**
     * Returns angle between donor, acceptor and acceptor antecedent.
     *
     * @return Angle between donor, acceptor and acceptor antecedent.
     */
    public double getAngleDAAa() {
        return angleDAAa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydrogenBond that = (HydrogenBond) o;
        return Double.compare(that.distanceHA, distanceHA) == 0
                              && Double.compare(that.distanceDA, distanceDA) == 0
                              && Double.compare(that.angleDHA, angleDHA) == 0
                              && Double.compare(that.angleHAAa, angleHAAa) == 0
                              && Double.compare(that.angleDAAa, angleDAAa) == 0
                              && Objects.equals(donor, that.donor)
                              && Objects.equals(acceptor, that.acceptor)
                              && Objects.equals(donorAtom, that.donorAtom)
                              && Objects.equals(hydrogenAtom, that.hydrogenAtom)
                              && Objects.equals(acceptorAtom, that.acceptorAtom)
                              && Objects.equals(acceptorAntecedentAtom, that.acceptorAntecedentAtom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(donor, acceptor, donorAtom, hydrogenAtom, acceptorAtom, acceptorAntecedentAtom,
                distanceHA, distanceDA, angleDHA, angleHAAa, angleDAAa);
    }

    @Override
    public String toString() {
        return "" + '\t' + donor + '\t' + donorAtom.getName() + '\t' + hydrogenAtom.getName() + '\t'
                  + acceptor + '\t' + acceptorAtom.getName() + '\t' + acceptorAntecedentAtom.getName() + '\t'
                  + MathHelper.round(distanceDA) + '\t' +  MathHelper.round(distanceHA) + '\t'
                  + MathHelper.round(angleDHA) + '\t' +  MathHelper.round(angleHAAa) + '\t' +  MathHelper.round(angleDAAa);
    }
}

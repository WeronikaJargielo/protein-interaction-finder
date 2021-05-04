package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Atom;

import java.util.Objects;

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

    public AminoAcid getDonor() {
        return donor;
    }

    public AminoAcid getAcceptor() {
        return acceptor;
    }

    public Atom getDonorAtom() {
        return donorAtom;
    }

    public Atom getHydrogenAtom() {
        return hydrogenAtom;
    }

    public Atom getAcceptorAtom() {
        return acceptorAtom;
    }

    public Atom getAcceptorAntecedentAtom() {
        return acceptorAntecedentAtom;
    }

    public double getDistanceHA() {
        return distanceHA;
    }

    public double getDistanceDA() {
        return distanceDA;
    }

    public double getAngleDHA() {
        return angleDHA;
    }

    public double getAngleHAAa() {
        return angleHAAa;
    }

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

package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Atom;

final public class HydrogenBond {

    final private AminoAcid donor;
    final private AminoAcid acceptor;

    final private Atom donorAtom;
    final private Atom hydrogenAtom;
    final private Atom acceptorAtom;
    final private Atom acceptorAntecedentAtom;

    final private double distHA;
    final private double distDA;
    final private double angleDHA;
    final private double angleHAAa;
    final private double angleDAAa;

    public HydrogenBond(AminoAcid donor, AminoAcid acceptor, Atom donorAtom, Atom hydrogenAtom, Atom acceptorAtom, Atom acceptorAntecedentAtom, double distHA, double distDA, double angleDHA, double angleHAAa, double angleDAAa) {
        this.donor = donor;
        this.acceptor = acceptor;
        this.donorAtom = donorAtom;
        this.hydrogenAtom = hydrogenAtom;
        this.acceptorAtom = acceptorAtom;
        this.acceptorAntecedentAtom = acceptorAntecedentAtom;
        this.distHA = distHA;
        this.distDA = distDA;
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

    public double getDistHA() {
        return distHA;
    }

    public double getDistDA() {
        return distDA;
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
    public String toString() {
        return "" + '\t' + donor + '\t' + donorAtom.getName() + '\t' + hydrogenAtom.getName() + '\t' + acceptor + '\t' + acceptorAtom.getName() + '\t' + acceptorAntecedentAtom.getName() + '\t'
                  + MathHelper.round(distHA) + '\t' +  MathHelper.round(distDA) + '\t' +  MathHelper.round(angleDHA) + '\t' +  MathHelper.round(angleHAAa) + '\t' +  MathHelper.round(angleDAAa);
    }
}

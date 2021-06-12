package io.github.WeronikaJargielo.protein_interaction_finder;

import org.biojava.nbio.structure.Atom;

import java.util.Objects;

/**
 * Class representing amino-aromatic interaction.
 */
public final class AminoAromaticInteraction {

    final private AminoAcid aromaticAminoAcid;
    final private AminoAcid aminoAminoAcid;
    final private double distanceBtwCationRing;
    final private double polarAngle;
    final private double azimuthalAngle;

    /**
     * Instantiates new amino-aromatic interaction.
     * For parameters' full description see <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     *
     * @param aromaticAminoAcid     Amino acid of aromatic interaction participant.
     * @param aminoAminoAcid        Amino acid of interaction participant with amino group.
     * @param distanceBtwCationRing Distance between aromatic ring centroid and amino group representative.
     * @param polarAngle            Polar angle for aromatic ring and amino group representative.
     * @param azimuthalAngle        Azimuthal angle for aromatic ring and amino group representative.
     */
    public AminoAromaticInteraction(AminoAcid aromaticAminoAcid, AminoAcid aminoAminoAcid, double distanceBtwCationRing, double polarAngle, double azimuthalAngle) {
        this.aromaticAminoAcid = aromaticAminoAcid;
        this.aminoAminoAcid = aminoAminoAcid;
        this.distanceBtwCationRing = distanceBtwCationRing;
        this.polarAngle = polarAngle;
        this.azimuthalAngle = azimuthalAngle;
    }

    /**
     * Returns amino acid of aromatic interaction participant.
     *
     * @return Amino acid of aromatic interaction participant.
     */
    public AminoAcid getAromaticAminoAcid() {
        return aromaticAminoAcid;
    }

    /**
     * Returns amino acid of interaction participant with amino group.
     *
     * @return Amino acid of interaction participant with amino group.
     */
    public AminoAcid getAminoAminoAcid() {
        return aminoAminoAcid;
    }

    /**
     * Returns distance between aromatic ring centroid and amino group representative.
     *
     * @return Distance between aromatic ring centroid and amino group representative.
     */
    public double getDistanceBtwCationRing() {
        return distanceBtwCationRing;
    }

    /**
     * Returns polar angle for aromatic ring and amino group representative.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.AromaticRing#calculatePolarAngleOfAtom(Atom) 
     *
     * @return Polar angle for aromatic ring and amino group representative.
     */
    public double getPolarAngle() {
        return polarAngle;
    }

    /**
     * Returns azimuthal angle for aromatic ring and amino group representative.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.AromaticRing#calculateAzimuthalAngleOfAtom(Atom)
     *
     * @return Azimuthal angle for aromatic ring and amino group representative.
     */
    public double getAzimuthalAngle() {
        return azimuthalAngle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AminoAromaticInteraction that = (AminoAromaticInteraction) o;
        return Double.compare(that.distanceBtwCationRing, distanceBtwCationRing) == 0
                              && Double.compare(that.polarAngle, polarAngle) == 0
                              && Double.compare(that.azimuthalAngle, azimuthalAngle) == 0
                              && Objects.equals(aromaticAminoAcid, that.aromaticAminoAcid)
                              && Objects.equals(aminoAminoAcid, that.aminoAminoAcid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aromaticAminoAcid, aminoAminoAcid, distanceBtwCationRing, polarAngle, azimuthalAngle);
    }

    @Override
    public String toString() {
        return "" + aromaticAminoAcid + '\t' +  aminoAminoAcid + '\t' + MathHelper.round(distanceBtwCationRing) + '\t'
                  + MathHelper.round(polarAngle) + '\t' + MathHelper.round(azimuthalAngle);
    }
}

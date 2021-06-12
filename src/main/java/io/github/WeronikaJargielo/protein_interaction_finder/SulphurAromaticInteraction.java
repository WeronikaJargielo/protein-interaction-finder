package io.github.WeronikaJargielo.protein_interaction_finder;

import org.biojava.nbio.structure.Atom;

import java.util.Objects;

/**
 * Class representing sulphur-aromatic interaction.
 */
public final class SulphurAromaticInteraction {

    private final AminoAcid aromaticAminoAcid;
    private final AminoAcid sulphurAminoAcid;
    private final double distanceBtwRingSulphur;
    private final double elevationAngle;
    private final double equatorialAngle;

    /**
     * Instantiates new sulphur-aromatic interaction.
     * For parameters' full description see <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     *
     * @param aromaticAminoAcid      Amino acid of aromatic interaction participant.
     * @param sulphurAminoAcid       Amino acid of interaction participant with sulphur atom.
     * @param distanceBtwRingSulphur Distance between aromatic ring centroid and sulphur atom.
     * @param elevationAngle         Elevation angle for aromatic ring and sulphur atom.
     * @param equatorialAngle        Equatorial angle for aromatic ring and sulphur atom.
     */
    public SulphurAromaticInteraction(AminoAcid aromaticAminoAcid, AminoAcid sulphurAminoAcid, double distanceBtwRingSulphur, double elevationAngle, double equatorialAngle) {
        this.aromaticAminoAcid = aromaticAminoAcid;
        this.sulphurAminoAcid = sulphurAminoAcid;
        this.distanceBtwRingSulphur = distanceBtwRingSulphur;
        this.elevationAngle = elevationAngle;
        this.equatorialAngle = equatorialAngle;
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
     * Returns amino acid with sulphur atom.
     *
     * @return Amino acid with sulphur atom.
     */
    public AminoAcid getSulphurAminoAcid() {
        return sulphurAminoAcid;
    }

    /**
     * Returns distance between aromatic ring centroid and sulphur atom.
     *
     * @return Distance between aromatic ring centroid and sulphur atom.
     */
    public double getDistanceBtwRingSulphur() {
        return distanceBtwRingSulphur;
    }

    /**
     * Returns elevation angle for aromatic ring and sulphur atom.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.AromaticRing#calculateElevationAngleOfAtom(Atom)
     *
     * @return Elevation angle for aromatic ring and sulphur atom.
     */
    public double getElevationAngle() {
        return elevationAngle;
    }

    /**
     * Returns equatorial angle for aromatic ring and sulphur atom.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.AromaticRing#calculateEquatorialAngleOfAtom(Atom) 
     * 
     * @return Equatorial angle for aromatic ring and sulphur atom.
     */
    public double getEquatorialAngle() {
        return equatorialAngle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SulphurAromaticInteraction that = (SulphurAromaticInteraction) o;
        return Double.compare(that.distanceBtwRingSulphur, distanceBtwRingSulphur) == 0
                              && Double.compare(that.elevationAngle, elevationAngle) == 0
                              && Double.compare(that.equatorialAngle, equatorialAngle) == 0
                              && Objects.equals(aromaticAminoAcid, that.aromaticAminoAcid)
                              && Objects.equals(sulphurAminoAcid, that.sulphurAminoAcid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aromaticAminoAcid, sulphurAminoAcid, distanceBtwRingSulphur, elevationAngle, equatorialAngle);
    }

    @Override
    public String toString() {
        return "" + aromaticAminoAcid + '\t' + sulphurAminoAcid + '\t' + MathHelper.round(distanceBtwRingSulphur) + '\t'
                  + MathHelper.round(elevationAngle) + '\t' + MathHelper.round(equatorialAngle);
    }
}

package io.github.WeronikaJargielo.protein_interaction_finder;

import java.util.Objects;

/**
 * Class representing ionic interaction.
 */
public class IonicInteraction {

    private final AminoAcid anionicAminoAcid;
    private final AminoAcid cationicAminoAcid;
    private final double distanceBtwCentroids;

    /**
     * Instantiates new ionic interaction.
     *
     * @param anionicAminoAcid     Negatively charged amino acid of interaction participant.
     * @param cationicAminoAcid    Positively charged amino acid of interaction participant.
     * @param distanceBtwCentroids Distance between charged groups' centroids of interaction participants.
     *                             See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     */
    public IonicInteraction(AminoAcid anionicAminoAcid, AminoAcid cationicAminoAcid, double distanceBtwCentroids) {
        this.anionicAminoAcid = anionicAminoAcid;
        this.cationicAminoAcid = cationicAminoAcid;
        this.distanceBtwCentroids = distanceBtwCentroids;
    }

    /**
     * Returns negatively charged amino acid.
     *
     * @return Negatively charged amino acid.
     */
    public AminoAcid getAnionicAminoAcid() {
        return anionicAminoAcid;
    }

    /**
     * Returns positively charged amino acid.
     *
     * @return Positively charged amino acid.
     */
    public AminoAcid getCationicAminoAcid() {
        return cationicAminoAcid;
    }

    /**
     * Returns distance between charged groups' centroids of interaction participants.
     *
     * @return Distance between charged groups' centroids of interaction participants.
     */
    public double getDistanceBtwCentroids() {
        return distanceBtwCentroids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IonicInteraction that = (IonicInteraction) o;
        return Double.compare(that.distanceBtwCentroids, distanceBtwCentroids) == 0
                && Objects.equals(anionicAminoAcid, that.anionicAminoAcid)
                && Objects.equals(cationicAminoAcid, that.cationicAminoAcid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anionicAminoAcid, cationicAminoAcid, distanceBtwCentroids);
    }

    @Override
    public String toString() {
        return "" + anionicAminoAcid + '\t' + cationicAminoAcid + '\t' +  MathHelper.round(distanceBtwCentroids);
    }
}

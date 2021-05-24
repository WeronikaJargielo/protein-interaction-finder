package org.polsl.protein_interaction_finder;

import java.util.Objects;

public class IonicInteraction {

    private final AminoAcid anionicAminoAcid;
    private final AminoAcid cationicAminoAcid;
    private final double distanceBtwCentroids;

    public IonicInteraction(AminoAcid anionicAminoAcid, AminoAcid cationicAminoAcid, double distanceBtwCentroids) {
        this.anionicAminoAcid = anionicAminoAcid;
        this.cationicAminoAcid = cationicAminoAcid;
        this.distanceBtwCentroids = distanceBtwCentroids;
    }

    public AminoAcid getAnionicAminoAcid() {
        return anionicAminoAcid;
    }

    public AminoAcid getCationicAminoAcid() {
        return cationicAminoAcid;
    }

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

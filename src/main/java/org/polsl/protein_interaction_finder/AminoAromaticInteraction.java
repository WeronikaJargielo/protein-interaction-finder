package org.polsl.protein_interaction_finder;

import java.util.Objects;

public final class AminoAromaticInteraction {

    final private AminoAcid aromaticAminoAcid;
    final private AminoAcid aminoAminoAcid;
    final private double distanceBtwCationRing;
    final private double polarAngle;
    final private double azimuthalAngle;

    public AminoAromaticInteraction(AminoAcid aromaticAminoAcid, AminoAcid aminoAminoAcid, double distanceBtwCationRing, double polarAngle, double azimuthalAngle) {
        this.aromaticAminoAcid = aromaticAminoAcid;
        this.aminoAminoAcid = aminoAminoAcid;
        this.distanceBtwCationRing = distanceBtwCationRing;
        this.polarAngle = polarAngle;
        this.azimuthalAngle = azimuthalAngle;
    }

    public AminoAcid getAromaticAminoAcid() {
        return aromaticAminoAcid;
    }

    public AminoAcid getAminoAminoAcid() {
        return aminoAminoAcid;
    }

    public double getDistanceBtwCationRing() {
        return distanceBtwCationRing;
    }

    public double getPolarAngle() {
        return polarAngle;
    }

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

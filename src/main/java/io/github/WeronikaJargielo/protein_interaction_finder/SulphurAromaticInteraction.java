package io.github.WeronikaJargielo.protein_interaction_finder;

import java.util.Objects;

public final class SulphurAromaticInteraction {

    private final AminoAcid aromaticAminoAcid;
    private final AminoAcid sulphurAminoAcid;
    private final double distanceBtwRingSulphur;
    private final double elevationAngle;
    private final double equatorialAngle;

    public SulphurAromaticInteraction(AminoAcid aromaticAminoAcid, AminoAcid sulphurAminoAcid, double distanceBtwRingSulphur, double elevationAngle, double equatorialAngle) {
        this.aromaticAminoAcid = aromaticAminoAcid;
        this.sulphurAminoAcid = sulphurAminoAcid;
        this.distanceBtwRingSulphur = distanceBtwRingSulphur;
        this.elevationAngle = elevationAngle;
        this.equatorialAngle = equatorialAngle;
    }

    public AminoAcid getAromaticAminoAcid() {
        return aromaticAminoAcid;
    }

    public AminoAcid getSulphurAminoAcid() {
        return sulphurAminoAcid;
    }

    public double getDistanceBtwRingSulphur() {
        return distanceBtwRingSulphur;
    }

    public double getElevationAngle() {
        return elevationAngle;
    }

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

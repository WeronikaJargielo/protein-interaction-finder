package io.github.WeronikaJargielo.protein_interaction_finder;

import java.util.Objects;

public final class AromaticAromaticInteraction {

    private final AminoAcid firstAromaticRing;
    private final AminoAcid secondAromaticRing;
    private final double distanceBtwRings;
    private final double angleBtwRings;

    public AromaticAromaticInteraction(AminoAcid firstAromaticRing, AminoAcid secondAromaticRing, double distanceBtwRings, double angleBtwRings) {
        this.firstAromaticRing = firstAromaticRing;
        this.secondAromaticRing = secondAromaticRing;
        this.distanceBtwRings = distanceBtwRings;
        this.angleBtwRings = angleBtwRings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AromaticAromaticInteraction that = (AromaticAromaticInteraction) o;
        return Double.compare(that.distanceBtwRings, distanceBtwRings) == 0
                              && Double.compare(that.angleBtwRings, angleBtwRings) == 0
                              && Objects.equals(firstAromaticRing, that.firstAromaticRing)
                              && Objects.equals(secondAromaticRing, that.secondAromaticRing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstAromaticRing, secondAromaticRing, distanceBtwRings, angleBtwRings);
    }

    @Override
    public String toString() {
        return "" + firstAromaticRing + '\t'+ secondAromaticRing + '\t'
                  + MathHelper.round(distanceBtwRings) + '\t' + MathHelper.round(angleBtwRings);
    }
}

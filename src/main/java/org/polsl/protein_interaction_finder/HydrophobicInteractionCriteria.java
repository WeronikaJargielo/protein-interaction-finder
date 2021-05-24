package org.polsl.protein_interaction_finder;

import java.util.Objects;

public final class HydrophobicInteractionCriteria {

    private final double minDistanceCAs;
    private final double maxDistanceCAs;

    public HydrophobicInteractionCriteria() {
        this.minDistanceCAs = 0.0;
        this.maxDistanceCAs = 9.5;
    }

    public HydrophobicInteractionCriteria(double minDistanceCAs, double maxDistanceCAs) {
        this.minDistanceCAs = minDistanceCAs;
        this.maxDistanceCAs = maxDistanceCAs;
    }

    public double getMinDistanceCAs() {
        return minDistanceCAs;
    }

    public double getMaxDistanceCAs() {
        return maxDistanceCAs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydrophobicInteractionCriteria criteria = (HydrophobicInteractionCriteria) o;
        return Double.compare(criteria.minDistanceCAs, minDistanceCAs) == 0
               && Double.compare(criteria.maxDistanceCAs, maxDistanceCAs) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minDistanceCAs, maxDistanceCAs);
    }

    @Override
    public String toString() {
        return "HydrophobicInteractionCriteria: "+
                "minDistanceCAs = " + minDistanceCAs +
                ",\tmaxDistanceCAs = " + maxDistanceCAs;
    }
}

package io.github.WeronikaJargielo.protein_interaction_finder;

import java.util.Objects;

/**
 * Class representing criteria for identifying hydrophobic interactions.
 * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
 */
public final class HydrophobicInteractionCriteria {

    private final double minDistanceCAs;
    private final double maxDistanceCAs;

    /**
     * Instantiates new hydrophobic interaction criteria using default boundary values.
     * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     */
    public HydrophobicInteractionCriteria() {
        this.minDistanceCAs = 0.0;
        this.maxDistanceCAs = 9.5;
    }

    /**
     * Instantiates new hydrophobic interaction criteria using custom boundary values.
     * See <a href="https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/documentation/InteractionsDefinitions.pdf">here</a>.
     *
     * @param minDistanceCAs Minimum distance between CA atoms of interaction participants.
     * @param maxDistanceCAs Maximum distance between CA atoms of interaction participants.
     */
    public HydrophobicInteractionCriteria(double minDistanceCAs, double maxDistanceCAs) {
        this.minDistanceCAs = minDistanceCAs;
        this.maxDistanceCAs = maxDistanceCAs;
    }

    /**
     * Returns minimum distance between CA atoms of interaction participants.
     *
     * @return Minimum distance between CA atoms of interaction participants.
     */
    public double getMinDistanceCAs() {
        return minDistanceCAs;
    }

    /**
     * Returns maximum distance between CA atoms of interaction participants.
     *
     * @return Maximum distance between CA atoms of interaction participants.
     */
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

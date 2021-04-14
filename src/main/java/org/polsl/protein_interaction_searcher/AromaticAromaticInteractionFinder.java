package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Calc;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AromaticAromaticInteractionFinder {

    private PdbStructureParser pdbStructureParser;
    private final double distThresholdMin = 4.5;
    private final double distThresholdMax = 7;

    public AromaticAromaticInteractionFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

    public List<AromaticAromaticInteraction> findAromaticAromaticInteraction() {

        List<AromaticRing> aromaticRings = pdbStructureParser.getAromaticRings();
        ArrayList<AromaticAromaticInteraction> foundInteractions = new ArrayList<>();

        ListIterator<AromaticRing> aromaticRingItr = aromaticRings.listIterator();

        while (aromaticRingItr.hasNext()) {

            AromaticRing aromaticRing = aromaticRingItr.next();
            ListIterator<AromaticRing> remainingAromaticRingItr = aromaticRings.listIterator(aromaticRingItr.nextIndex());

            remainingAromaticRingItr.forEachRemaining((aromaRing) -> {
                double dist = Calc.getDistance(aromaticRing.calculateCentroid(), aromaRing.calculateCentroid());
                // TODO: Calculating dihedral angle between two planes created by interacting aromatic rings.
                if (dist > distThresholdMin && dist < distThresholdMax) {
                    foundInteractions.add(new AromaticAromaticInteraction(new AminoAcid(aromaticRing.getAminoAcid()), new AminoAcid(aromaRing.getAminoAcid()), dist));
                }
            });
        }

        return foundInteractions;
    }
}

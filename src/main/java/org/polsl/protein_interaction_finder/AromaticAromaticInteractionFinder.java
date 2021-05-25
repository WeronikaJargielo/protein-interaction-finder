package org.polsl.protein_interaction_finder;

import org.biojava.nbio.structure.Calc;

import java.util.ArrayList;
import java.util.List;

public final class AromaticAromaticInteractionFinder {

    private final PdbStructureParser pdbStructureParser;

    public AromaticAromaticInteractionFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

    public List<AromaticAromaticInteraction> findAromaticAromaticInteraction(AromaticAromaticInteractionCriteria criteria) {
        final List<AromaticRing> aromaticRings = pdbStructureParser.getAromaticRings();
        ArrayList<AromaticAromaticInteraction> foundAromaticAromaticInteractions = new ArrayList<>();

        final int aromaticRingsLen = aromaticRings.size();
        for (int i = 0; i < aromaticRingsLen; ++i) {
            for (int j = i + 1; j < aromaticRingsLen; ++j) {
                final AromaticAromaticInteraction aromaticAromaticInteraction = this.obtainAromaticAromaticInteraction(aromaticRings.get(i),
                                                                                                                       aromaticRings.get(j),
                                                                                                                       criteria);

                if (aromaticAromaticInteraction != null) {
                    foundAromaticAromaticInteractions.add(aromaticAromaticInteraction);
                }
            }
        }

        return foundAromaticAromaticInteractions;
    }

    private AromaticAromaticInteraction obtainAromaticAromaticInteraction(AromaticRing firstRing, AromaticRing secondRing,
                                                                          AromaticAromaticInteractionCriteria criteria) {
        // Check to eliminate TRP 5 to TRP 6 ring interactions.
        if (firstRing.getAminoAcid().equals(secondRing.getAminoAcid())) {
            return null;
        }

        final double distanceBtwRings = Calc.getDistance(firstRing.getRingCentroid(), secondRing.getRingCentroid());
        if ( ! (distanceBtwRings > criteria.getMinDistanceBtwRings() && distanceBtwRings < criteria.getMaxDistanceBtwRings()) ) {
            return null;
        }

        final double angleBtwRings = getAngleBetweenRings(firstRing, secondRing);
        if ( ! (angleBtwRings >= criteria.getMinAngleBtwRings() && angleBtwRings <= criteria.getMaxAngleBtwRings()) ) {
            return null;
        }

        return new AromaticAromaticInteraction(new AminoAcid(firstRing.getAminoAcid()),
                                               new AminoAcid(secondRing.getAminoAcid()),
                                               distanceBtwRings, angleBtwRings);
    }

    private double getAngleBetweenRings(AromaticRing firstRing, AromaticRing secondRing) {
        return MathHelper.radiansToDegrees(firstRing.getNormalVector().angle(secondRing.getNormalVector()));
    }
}

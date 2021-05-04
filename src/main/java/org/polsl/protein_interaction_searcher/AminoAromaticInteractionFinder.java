package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class AminoAromaticInteractionFinder {

    private final class Cation {
        public final String[] atoms;
        public final List<AminoAcidAbbreviations> aminoAcid;

        public Cation(String[] atoms, List<AminoAcidAbbreviations> aminoAcid) {
            this.atoms = atoms;
            this.aminoAcid = aminoAcid;
        }
    }

    private final PdbStructureParser pdbStructureParser;

    private final List<AminoAromaticInteractionFinder.Cation> desiredCations = Arrays.asList(new Cation(new String[] {"CZ"}, Arrays.asList(AminoAcidAbbreviations.ARG)),
                                                                                             new Cation(new String[] {"CE1"}, Arrays.asList(AminoAcidAbbreviations.HIS)),
                                                                                             new Cation(new String[] {"NZ"}, Arrays.asList(AminoAcidAbbreviations.LYS)));


    public AminoAromaticInteractionFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

    public List<AminoAromaticInteraction> findAminoAromaticInteractions(AminoAromaticInteractionCriteria criteria) {
        List<Atom> cations = new ArrayList<>();
        desiredCations.forEach(cation -> {
            cations.addAll(pdbStructureParser.getAtoms(cation.atoms, cation.aminoAcid));
        });

        List<AminoAromaticInteraction> foundAminoAromaticInteractions = new ArrayList<>();

        final ArrayList<AromaticRing> aromaticRings = pdbStructureParser.getAromaticRings();
        aromaticRings.forEach(aromaticRing -> {
            cations.forEach(cation -> {
                final AminoAromaticInteraction aminoAromaticInteraction = this.obtainAminoAromaticInteraction(cation, aromaticRing, criteria);

                if (aminoAromaticInteraction != null) {
                    foundAminoAromaticInteractions.add(aminoAromaticInteraction);
                }
            });
        });

        return foundAminoAromaticInteractions;
    }

    private AminoAromaticInteraction obtainAminoAromaticInteraction(Atom cation, AromaticRing aromaticRing, AminoAromaticInteractionCriteria criteria) {
        final double distanceBtwCationRing = Calc.getDistance(cation, aromaticRing.getRingCentroid());
        if ( ! (distanceBtwCationRing > criteria.getMinDistanceBtwCationRing() && distanceBtwCationRing <= criteria.getMaxDistanceBtwCationRing())) {
            return null;
        }

        final double polarAngle = aromaticRing.calculatePolarAngleOfAtom(cation);
        if ( ! (polarAngle >= criteria.getMinPolarAngle() && polarAngle <= criteria.getMaxPolarAngle())) {
            return null;
        }

        final double azimuthalAngle = aromaticRing.calculateAzimuthalAngleOfAtom(cation);
        if ( ! (azimuthalAngle >= criteria.getMinAzimuthalAngle() && azimuthalAngle <= criteria.getMaxAzimuthalAngle())) {
            return null;
        }

        return new AminoAromaticInteraction(new AminoAcid(aromaticRing.getAminoAcid()),
                                            new AminoAcid(cation.getGroup()),
                                            distanceBtwCationRing, polarAngle, azimuthalAngle);
    }
}

package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public final class AminoAromaticInteractionFinder {

    private final class Cation {
        public final String[] atoms;
        public final List<AminoAcidAbbreviations> aminoAcid;
        public final Function<AminoAromaticInteractionFinder.Cation, List<Atom>> cationFilter;
        public final Boolean specialTreatment;

        public Cation(String[] atoms, List<AminoAcidAbbreviations> aminoAcid) {
            this.atoms = atoms;
            this.aminoAcid = aminoAcid;
            this.cationFilter = null;
            this.specialTreatment = false;
        }

        public Cation(String[] atoms, List<AminoAcidAbbreviations> aminoAcid, Function<AminoAromaticInteractionFinder.Cation, List<Atom>> cationFilter) {
            this.atoms = atoms;
            this.aminoAcid = aminoAcid;
            this.cationFilter = cationFilter;
            this.specialTreatment = true;
        }
    }

    private PdbStructureParser pdbStructureParser;

    public AminoAromaticInteractionFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

    final private Function<AminoAromaticInteractionFinder.Cation, List<Atom>> getHisAtomsIfIsChargedFilter = (cation -> {
        List<Atom> histidineCationsAtoms = new ArrayList<>();
        final List<Atom> foundCE1s = pdbStructureParser.getAtoms(cation.atoms, cation.aminoAcid);
        if (foundCE1s.isEmpty()) { return histidineCationsAtoms; }

//      If histidine is charged it should have those two hydrogen atom: HD1 and HE2.
        final String[] hisHsAtoms = new String[]{"HD1" ,"HE2"};
        final List<Atom> foundHs = pdbStructureParser.getAtoms(hisHsAtoms, cation.aminoAcid);

        foundCE1s.forEach(CE1 -> {
            final Integer CE1SeqNum = CE1.getGroup().getResidueNumber().getSeqNum();
            final String CE1Chain = CE1.getGroup().getChain().getName();

            final long counterMatchingHs = foundHs.stream()
                                                  .filter(H -> CE1SeqNum.equals(H.getGroup().getResidueNumber().getSeqNum())
                                                               && CE1Chain.equals(H.getGroup().getChain().getName()) )
                                                  .count();

            if (counterMatchingHs == hisHsAtoms.length) {
                histidineCationsAtoms.add(CE1);
            }
        });
        return histidineCationsAtoms;
    });

    private final List<AminoAromaticInteractionFinder.Cation> desiredCations = Arrays.asList(new Cation(new String[] {"CZ"}, Arrays.asList(AminoAcidAbbreviations.ARG)),
                                                                                             new Cation(new String[] {"CE1"}, Arrays.asList(AminoAcidAbbreviations.HIS), getHisAtomsIfIsChargedFilter),
                                                                                             new Cation(new String[] {"NZ"}, Arrays.asList(AminoAcidAbbreviations.LYS)));



    public List<AminoAromaticInteraction> findAminoAromaticInteractions(AminoAromaticInteractionCriteria criteria) {
        List<Atom> cations = new ArrayList<>();
        desiredCations.forEach(cation -> {
           if (cation.specialTreatment) {
                cations.addAll(cation.cationFilter.apply(cation));
           } else {
                cations.addAll(pdbStructureParser.getAtoms(cation.atoms, cation.aminoAcid));
            }
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

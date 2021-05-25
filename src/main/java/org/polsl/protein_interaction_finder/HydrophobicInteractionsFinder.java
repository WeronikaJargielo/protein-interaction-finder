package org.polsl.protein_interaction_finder;

import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;

import java.util.*;


public final class HydrophobicInteractionsFinder {

    private final PdbStructureParser pdbStructureParser;
    private final List<AminoAcidAbbreviations> nonPolarAminoAcids = Arrays.asList(AminoAcidAbbreviations.ALA,
                                                                                  AminoAcidAbbreviations.CYS,
                                                                                  AminoAcidAbbreviations.GLY,
                                                                                  AminoAcidAbbreviations.ILE,
                                                                                  AminoAcidAbbreviations.LEU,
                                                                                  AminoAcidAbbreviations.MET,
                                                                                  AminoAcidAbbreviations.PHE,
                                                                                  AminoAcidAbbreviations.PRO,
                                                                                  AminoAcidAbbreviations.TRP,
                                                                                  AminoAcidAbbreviations.TYR,
                                                                                  AminoAcidAbbreviations.VAL
        );

    private final String[] desiredAtoms = new String[] {"CA"};

    public HydrophobicInteractionsFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

    List<HydrophobicInteraction> findHydrophobicInteractions(HydrophobicInteractionCriteria criteria) {
        ArrayList<Atom> CAsAtoms = pdbStructureParser.getAtoms(desiredAtoms, nonPolarAminoAcids);
        ArrayList<HydrophobicInteraction> foundHydrophobicInteractions = new ArrayList<>();

        final int CAsAtomsLen = CAsAtoms.size();
        for (int i = 0; i < CAsAtomsLen; ++i) {
            for (int j = i + 1; j < CAsAtomsLen; ++j) {
                final HydrophobicInteraction hydrophobicInteraction = this.obtainHydrophobicInteraction(CAsAtoms.get(i),
                                                                                                        CAsAtoms.get(j),
                                                                                                        criteria);

                if (hydrophobicInteraction != null) {
                    foundHydrophobicInteractions.add(hydrophobicInteraction);
                }
            }
        }
        return foundHydrophobicInteractions;
    }

    private HydrophobicInteraction obtainHydrophobicInteraction(Atom firstAtom, Atom secondAtom, HydrophobicInteractionCriteria criteria) {
        final double distCAs = Calc.getDistance(firstAtom, secondAtom);

        if (distCAs >= criteria.getMinDistanceCAs() && distCAs <= criteria.getMaxDistanceCAs()) {
            return new HydrophobicInteraction(new AminoAcid(firstAtom.getGroup()),
                                              new AminoAcid(secondAtom.getGroup()),
                                              distCAs);
        }

        return null;
    }

}

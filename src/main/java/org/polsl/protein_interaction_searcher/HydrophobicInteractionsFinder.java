package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;

import java.util.*;

public class HydrophobicInteractionsFinder {

    private final PdbStructureParser pdbStructureParser;
    private final List<AminoAcidAbbreviations> allowedAminoAcids = Arrays.asList(AminoAcidAbbreviations.GLY,
                                                                                 AminoAcidAbbreviations.ALA,
                                                                                 AminoAcidAbbreviations.VAL,
                                                                                 AminoAcidAbbreviations.LEU,
                                                                                 AminoAcidAbbreviations.ILE,
                                                                                 AminoAcidAbbreviations.MET,
                                                                                 AminoAcidAbbreviations.PRO,
                                                                                 AminoAcidAbbreviations.PHE,
                                                                                 AminoAcidAbbreviations.TRP
        );

    private final String[] desiredAtoms = new String[] {"CB"};

    public HydrophobicInteractionsFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

    List<HydrophobicInteraction> findHydrophobicInteractions() {
        ArrayList<Atom> cbAtoms =  pdbStructureParser.getAtoms(desiredAtoms, allowedAminoAcids);

        ArrayList<HydrophobicInteraction> foundInteractions = new ArrayList<>();

        ListIterator<Atom> atomItr = cbAtoms.listIterator();
        while(atomItr.hasNext()) {
            Atom atom = atomItr.next();

            ListIterator<Atom> remainingAtomsIt = cbAtoms.listIterator(atomItr.nextIndex());

            remainingAtomsIt.forEachRemaining((a) -> {
                double dist = Calc.getDistance(atom, a);
                if (dist <= 5) {
                    foundInteractions.add(new HydrophobicInteraction(new AminoAcid(atom.getGroup()), new AminoAcid(a.getGroup()), dist));
                }
            });
        }

        return foundInteractions;
    }
}

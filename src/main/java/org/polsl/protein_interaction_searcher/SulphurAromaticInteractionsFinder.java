package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Atom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SulphurAromaticInteractionsFinder {

    private PdbStructureParser pdbStructureParser;

    private final String[] desiredAtomsMethionine = new String[] {"SD"};
    private final String[] desiredAtomsCysteine = new String[] {"SG"};


    public SulphurAromaticInteractionsFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

    public List<SulphurAromaticInteraction> findSulphurAromaticInteractions() {
        LinkedList<Atom> atomsMethionine = pdbStructureParser.getAtoms(desiredAtomsMethionine, Arrays.asList(AminoAcidAbbreviations.MET));
        LinkedList<Atom> atomsCysteine = pdbStructureParser.getAtoms(desiredAtomsCysteine, Arrays.asList(AminoAcidAbbreviations.CYS));

        ArrayList<SulphurAromaticInteraction> list = new ArrayList<>();
        pdbStructureParser.getAromaticRings();
        return list;
    }
}

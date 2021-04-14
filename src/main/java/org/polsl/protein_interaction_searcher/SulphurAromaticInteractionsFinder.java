package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SulphurAromaticInteractionsFinder {

    private PdbStructureParser pdbStructureParser;

    private final String[] desiredAtomsMethionine = new String[] {"SD"};
    private final String[] desiredAtomsCysteine = new String[] {"SG"};


    public SulphurAromaticInteractionsFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

    public List<SulphurAromaticInteraction> findSulphurAromaticInteractions() {
        ArrayList<Atom> atomsMethionine = pdbStructureParser.getAtoms(desiredAtomsMethionine, Arrays.asList(AminoAcidAbbreviations.MET));
        ArrayList<Atom> atomsCysteine = pdbStructureParser.getAtoms(desiredAtomsCysteine, Arrays.asList(AminoAcidAbbreviations.CYS));

        List<Atom> sulphurAtoms = Stream.concat(atomsMethionine.stream(), atomsCysteine.stream()).collect(Collectors.toList());
        ArrayList<AromaticRing> aromaticRings = pdbStructureParser.getAromaticRings();

        double criticalDistFrom = 3;
        double criticalDistTo = 6;

        List<SulphurAromaticInteraction> foundInteractions = new ArrayList<>();
        aromaticRings.forEach((aromaticRing) -> {

            Atom ringCentroid = aromaticRing.calculateCentroid();

            sulphurAtoms.forEach((s -> {
                double dist = Calc.getDistance(ringCentroid, s);
                // TODO: Calculating elevation angle and equatorial angle.
                if (dist > criticalDistFrom && dist < criticalDistTo) {
                    foundInteractions.add(new SulphurAromaticInteraction(new AminoAcid(aromaticRing.getAminoAcid()), new AminoAcid(s.getGroup()), dist));

                }
            }));
        });

        return foundInteractions;
    }
}

package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AminoAromaticInteractionFinder {

    private PdbStructureParser pdbStructureParser;

    private final String[] desiredAtomsLysine = new String[] {"NZ"};
//    private final String[] desiredAtomsArginine = new String[] {"NH1, NH2"};
    private final String[] desiredAtomsArginine = new String[] {"CZ"};
//    private final String[] desiredAtomsHistydine = new String[] {"ND1, NE2"};
    private final String[] desiredAtomsHistydine = new String[] {"CE1"};

    public AminoAromaticInteractionFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

    public List<AminoAromaticInteraction> findAminoAromaticInteractions() {
        ArrayList<Atom> atomsLysine = pdbStructureParser.getAtoms(desiredAtomsLysine, Arrays.asList(AminoAcidAbbreviations.LYS));
        ArrayList<Atom> atomsArginine = pdbStructureParser.getAtoms(desiredAtomsArginine, Arrays.asList(AminoAcidAbbreviations.ARG));
        ArrayList<Atom> atomsHistydine = pdbStructureParser.getAtoms(desiredAtomsHistydine, Arrays.asList(AminoAcidAbbreviations.HIS));

        List<Atom> nitrogenAtoms = Stream.of(atomsLysine, atomsArginine, atomsHistydine).flatMap(Collection::stream).collect(Collectors.toList());

        ArrayList<AromaticRing> aromaticRings = pdbStructureParser.getAromaticRings();

        double criticalDistFrom = 3.4;
        double criticalDistTo = 6;

        List<AminoAromaticInteraction> foundInteractions = new ArrayList<>();
        aromaticRings.forEach((aromaticRing) -> {
            Atom ringCentroid = aromaticRing.calculateCentroid();

            nitrogenAtoms.forEach((n -> {
                final double dist = Calc.getDistance(ringCentroid, n);
                // TODO: Calculating the angle.
                final double polarAngle = aromaticRing.calculatePolarAngleOfAtom(n);
                final double equatorialAngle = aromaticRing.calculateEquatorialAngleOfAtom(n);
                    if (dist > criticalDistFrom && dist < criticalDistTo) {
                        foundInteractions.add(new AminoAromaticInteraction(new AminoAcid(aromaticRing.getAminoAcid()), new AminoAcid(n.getGroup()),
                                                                           dist, polarAngle, equatorialAngle));

                    }
            }));
        });
        return foundInteractions;
    }
}

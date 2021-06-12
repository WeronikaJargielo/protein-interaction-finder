package io.github.WeronikaJargielo.protein_interaction_finder;

import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class SulphurAromaticInteractionsFinder {

    private final PdbStructureParser pdbStructureParser;

    private final String[] desiredAtomsMethionine = new String[] {"SD"};
    private final String[] desiredAtomsCysteine = new String[] {"SG"};


    public SulphurAromaticInteractionsFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

    public List<SulphurAromaticInteraction> findSulphurAromaticInteractions(SulphurAromaticInteractionCriteria criteria) {
        final ArrayList<Atom> atomsMethionine = pdbStructureParser.getAtoms(desiredAtomsMethionine, Arrays.asList(AminoAcidAbbreviations.MET));
        final ArrayList<Atom> atomsCysteine = pdbStructureParser.getAtoms(desiredAtomsCysteine, Arrays.asList(AminoAcidAbbreviations.CYS));

        final List<Atom> sulphurAtoms = Stream.concat(atomsMethionine.stream(), atomsCysteine.stream()).collect(Collectors.toList());
        final ArrayList<AromaticRing> aromaticRings = pdbStructureParser.getAromaticRings();

        List<SulphurAromaticInteraction> foundSulphurAromaticInteractions = new ArrayList<>();
        aromaticRings.forEach((aromaticRing) -> {
            sulphurAtoms.forEach((sulphur -> {
                final SulphurAromaticInteraction sulphurAromaticInteraction = this.obtainSulphurAromaticInteraction(sulphur, aromaticRing, criteria);

                if (sulphurAromaticInteraction != null) {
                    foundSulphurAromaticInteractions.add(sulphurAromaticInteraction);
                }
            }));
        });
        return foundSulphurAromaticInteractions;
    }

    private SulphurAromaticInteraction obtainSulphurAromaticInteraction(Atom sulphur, AromaticRing ring, SulphurAromaticInteractionCriteria criteria) {
        final double distanceBtwSRing = Calc.getDistance(sulphur, ring.getRingCentroid());
        if ( ! (distanceBtwSRing >= criteria.getMinDistanceBtwRingSulphur() && distanceBtwSRing <= criteria.getMaxDistanceBtwRingSulphur()) ) {
            return null;
        }

        final double elevationAngle = ring.calculateElevationAngleOfAtom(sulphur);
        if ( ! (elevationAngle >= criteria.getMinElevationAngle() && elevationAngle <= criteria.getMaxElevationAngle()) ) {
            return null;
        }

        final double equatorialAngle =  ring.calculateEquatorialAngleOfAtom(sulphur);
        if ( ! (equatorialAngle >= criteria.getMinEquatorialAngle() && equatorialAngle <= criteria.getMaxEquatorialAngle()) ) {
            return null;
        }

        return new SulphurAromaticInteraction(ring.getAminoAcid(),
                                              new AminoAcid(sulphur.getGroup()),
                                              distanceBtwSRing, elevationAngle, equatorialAngle);
    }
}

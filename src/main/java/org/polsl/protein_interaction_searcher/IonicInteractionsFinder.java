package org.polsl.protein_interaction_searcher;

import com.google.common.collect.Lists;
import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class IonicInteractionsFinder {

    // Inner class representing ion (anionic and cationic) interactee which create ionic interaction.
    private final class IonInteractee {
        public final String[] atoms;
        public final List<AminoAcidAbbreviations> aminoAcid;

        public IonInteractee(String[] atoms, List<AminoAcidAbbreviations> aminoAcid) {
            this.atoms = atoms;
            this.aminoAcid = aminoAcid;
        }
    }

    private final PdbStructureParser pdbStructureParser;
    private final List<IonicInteractionsFinder.IonInteractee> desiredAtomsOfAnionicGroups = Arrays.asList(new IonInteractee(new String[]{"CG", "OD1", "OD2"}, Arrays.asList(AminoAcidAbbreviations.ASP)),
                                                                                                          new IonInteractee(new String[]{"CD", "OE1", "OE2"}, Arrays.asList(AminoAcidAbbreviations.GLU))
    );

    private final List<IonicInteractionsFinder.IonInteractee> desiredAtomsOfCationicGroups = Arrays.asList(new IonInteractee(new String[]{"NE", "CZ", "NH1", "NH2"}, Arrays.asList(AminoAcidAbbreviations.ARG)),
                                                                                                           new IonInteractee(new String[]{"NZ"}, Arrays.asList(AminoAcidAbbreviations.LYS)),
                                                                                                           new IonInteractee(new String[]{"CG", "ND1", "CD2", "CE1", "NE2"}, Arrays.asList(AminoAcidAbbreviations.HIS))
    );

    public IonicInteractionsFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }


    private List<Atom> getListOfGroupsCentroid(List<IonicInteractionsFinder.IonInteractee> desiredAtomsOfGroups){
        List<Atom> centroids = new ArrayList<>();
        desiredAtomsOfGroups.forEach(group -> {
            List<List<Atom>> atomsSingleAminoAcid = Lists.partition(pdbStructureParser.getAtoms(group.atoms, group.aminoAcid), group.atoms.length);

                atomsSingleAminoAcid.forEach(atomsPerAminoAcids -> {
                    Atom[] atomsAsArray = new Atom[atomsPerAminoAcids.size()];
                    final Atom centroid = Calc.getCentroid(atomsPerAminoAcids.toArray(atomsAsArray));
                    centroid.setGroup(atomsAsArray[0].getGroup());
                    centroids.add(centroid);
                });
        });
        return centroids;
    }
    public List<IonicInteraction> findIonicInteractions(IonicInteractionCriteria criteria) {
        final List<Atom> anionicCentroids = this.getListOfGroupsCentroid(desiredAtomsOfAnionicGroups);
        final List<Atom> cationicCentroids = this.getListOfGroupsCentroid(desiredAtomsOfCationicGroups);

        List<IonicInteraction> foundIonicInteractions = new ArrayList<>();
        anionicCentroids.forEach(anion -> {
            cationicCentroids.forEach(cation -> {
                final IonicInteraction ionicInteraction = this.obtainIonicInteraction(anion, cation, criteria);
                if (ionicInteraction != null) {
                    foundIonicInteractions.add(ionicInteraction);
                }
            });
        });

        return foundIonicInteractions;
    }

    private IonicInteraction obtainIonicInteraction(Atom anion, Atom cation, IonicInteractionCriteria criteria) {
        final double distanceBtwCentroids = Calc.getDistance(anion, cation);

        if (distanceBtwCentroids > criteria.getMinDistanceBtwCentroids()
            && distanceBtwCentroids <= criteria.getMaxDistanceBtwCentroids()) {

            return new IonicInteraction(new AminoAcid(anion.getGroup()),
                                        new AminoAcid(cation.getGroup()),
                                        distanceBtwCentroids);
        }
        return null;
    }
}

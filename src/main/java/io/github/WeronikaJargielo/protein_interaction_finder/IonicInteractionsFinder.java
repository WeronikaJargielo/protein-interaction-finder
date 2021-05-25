package io.github.WeronikaJargielo.protein_interaction_finder;

import com.google.common.collect.Lists;
import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public final class IonicInteractionsFinder {

    // Inner class representing ion (anionic and cationic) interactee which create ionic interaction.
    private final class IonInteractee {
        public final String[] atoms;
        public final List<AminoAcidAbbreviations> aminoAcid;
        public final Function<IonicInteractionsFinder.IonInteractee, List<Atom>> ionInteracteeFilter;
        public final Boolean specialTreatment;


        public IonInteractee(String[] atoms, List<AminoAcidAbbreviations> aminoAcid) {
            this.atoms = atoms;
            this.aminoAcid = aminoAcid;
            this.ionInteracteeFilter = null;
            this.specialTreatment = false;

        }

        public IonInteractee(String[] atoms, List<AminoAcidAbbreviations> aminoAcid, Function<IonInteractee, List<Atom>> ionInteracteeFilter) {
            this.atoms = atoms;
            this.aminoAcid = aminoAcid;
            this.ionInteracteeFilter = ionInteracteeFilter;
            this.specialTreatment = true;
        }
    }

    private PdbStructureParser pdbStructureParser;

    public IonicInteractionsFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

    final private Function<IonicInteractionsFinder.IonInteractee, List<Atom>> getHisCentroidIfIsChargedFilter = (ionInteractee -> {
        List<Atom> histidineCentroids = new ArrayList<>();
        final List<List<Atom>> foundHisAtoms = Lists.partition(pdbStructureParser.getAtoms(ionInteractee.atoms, ionInteractee.aminoAcid), ionInteractee.atoms.length);
        if (foundHisAtoms.isEmpty()) { return histidineCentroids; }

//      If histidine is charged it should have those two hydrogen atom: HD1 and HE2.
        final String[] hisHsAtoms = new String[]{"HD1" ,"HE2"};
        final List<Atom> foundHs = pdbStructureParser.getAtoms(hisHsAtoms, ionInteractee.aminoAcid);

        foundHisAtoms.forEach(his -> {
            final Atom anyAtom = his.get(0);
            final Integer hisSeqNum = anyAtom.getGroup().getResidueNumber().getSeqNum();
            final String hisChain = anyAtom.getGroup().getChain().getName();

            final long counterMatchingHs = foundHs.stream()
                                                  .filter(H -> hisSeqNum.equals(H.getGroup().getResidueNumber().getSeqNum())
                                                               && hisChain.equals(H.getGroup().getChain().getName()))
                                                  .count();

            if (counterMatchingHs == hisHsAtoms.length) {
                histidineCentroids.add(this.calculateCentroidOfGroupAtoms(his));
            }
        });
        return histidineCentroids;
    });

    private final List<IonicInteractionsFinder.IonInteractee> desiredAtomsOfAnionicGroups = Arrays.asList(new IonInteractee(new String[]{"CG", "OD1", "OD2"}, Arrays.asList(AminoAcidAbbreviations.ASP)),
                                                                                                          new IonInteractee(new String[]{"CD", "OE1", "OE2"}, Arrays.asList(AminoAcidAbbreviations.GLU))
    );

    private final List<IonicInteractionsFinder.IonInteractee> desiredAtomsOfCationicGroups = Arrays.asList(new IonInteractee(new String[]{"NE", "CZ", "NH1", "NH2"}, Arrays.asList(AminoAcidAbbreviations.ARG)),
                                                                                                           new IonInteractee(new String[]{"NZ"}, Arrays.asList(AminoAcidAbbreviations.LYS)),
                                                                                                           new IonInteractee(new String[]{"CG", "ND1", "CD2", "CE1", "NE2"}, Arrays.asList(AminoAcidAbbreviations.HIS), getHisCentroidIfIsChargedFilter)
    );


    private List<Atom> getListOfGroupsCentroids(List<IonicInteractionsFinder.IonInteractee> desiredAtomsOfGroups){
        List<Atom> centroids = new ArrayList<>();
        desiredAtomsOfGroups.forEach(ionInteractee -> {
            if (ionInteractee.specialTreatment) {
                centroids.addAll(ionInteractee.ionInteracteeFilter.apply(ionInteractee));
            } else {
                List<List<Atom>> atomsSingleAminoAcid = Lists.partition(pdbStructureParser.getAtoms(ionInteractee.atoms, ionInteractee.aminoAcid), ionInteractee.atoms.length);
                atomsSingleAminoAcid.forEach(atomsPerAminoAcids -> centroids.add(this.calculateCentroidOfGroupAtoms(atomsPerAminoAcids)));
            }
        });
        return centroids;
    }

    private Atom calculateCentroidOfGroupAtoms(List<Atom> foundGroupAtoms) {
        Atom[] atomsAsArray = new Atom[foundGroupAtoms.size()];
        Atom centroid = Calc.getCentroid(foundGroupAtoms.toArray(atomsAsArray));
        centroid.setGroup(atomsAsArray[0].getGroup());

        return centroid;
    }

    public List<IonicInteraction> findIonicInteractions(IonicInteractionCriteria criteria) {
        final List<Atom> anionicCentroids = this.getListOfGroupsCentroids(desiredAtomsOfAnionicGroups);
        final List<Atom> cationicCentroids = this.getListOfGroupsCentroids(desiredAtomsOfCationicGroups);

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

        if (distanceBtwCentroids >= criteria.getMinDistanceBtwCentroids()
            && distanceBtwCentroids <= criteria.getMaxDistanceBtwCentroids()) {

            return new IonicInteraction(new AminoAcid(anion.getGroup()),
                                        new AminoAcid(cation.getGroup()),
                                        distanceBtwCentroids);
        }
        return null;
    }
}

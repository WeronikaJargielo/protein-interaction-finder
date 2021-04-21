package org.polsl.protein_interaction_searcher;

import com.google.common.collect.Lists;
import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;
import org.biojava.nbio.structure.Group;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IonicInteractionFinder {

    private PdbStructureParser pdbStructureParser;
//    private final String[] desiredAtomsLysine = new String[] {"NZ"};
//    private final String[] desiredAtomsArginine = new String[] {"CZ", "NH1", "NH2"};
//    private final String[] desiredAtomsHistydine = new String[] {"CG", "ND1", "CE1", "NE2", "CD2"};

    private final String[] desiredAtomsLysine = new String[] {"NZ"};
    private final String[] desiredAtomsArginine = new String[] {"NE", "NH1", "NH2"};
    private final String[] desiredAtomsHistydine = new String[] {"CG", "ND1", "CE1", "NE2", "CD2"};

    private final String[] desiredAtomsGlutamicAcid = new String[] {"OE2"};
    private final String[] desiredAtomsAsparticAcid = new String[] {"OD2"};

    public IonicInteractionFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

    public List<IonicInteraction> findIonicInteractions() {
        ArrayList<Atom> atomsLysine = pdbStructureParser.getAtoms(desiredAtomsLysine, Arrays.asList(AminoAcidAbbreviations.LYS));
        ArrayList<Atom> atomsArginine = pdbStructureParser.getAtoms(desiredAtomsArginine, Arrays.asList(AminoAcidAbbreviations.ARG));
        ArrayList<Atom> atomsHistydine = pdbStructureParser.getAtoms(desiredAtomsHistydine, Arrays.asList(AminoAcidAbbreviations.HIS));

        ArrayList<Atom> atomsGlutamicAcid = pdbStructureParser.getAtoms(desiredAtomsGlutamicAcid, Arrays.asList(AminoAcidAbbreviations.GLU));
        ArrayList<Atom> atomsAsparticAcid = pdbStructureParser.getAtoms(desiredAtomsAsparticAcid, Arrays.asList(AminoAcidAbbreviations.ASP));

//        List<List<Atom>> arginines = Lists.partition(atomsArginine, desiredAtomsArginine.length);
//        List<Atom> arginineCentroids = new ArrayList<>();
//        arginines.forEach((arg) -> {
//            Atom centroid = Calc.getCentroid(arg.toArray(new Atom[arg.size()]));
//            centroid.setGroup(arg.get(0).getGroup());
//            arginineCentroids.add(centroid);
//        });

//        List<List<Atom>> histydines = Lists.partition(atomsHistydine, desiredAtomsHistydine.length);
//        List<Atom> histydineCentroids = new ArrayList<>();
//        histydines.forEach((his) -> {
//            Atom centroid = Calc.centerOfMass(his.toArray(new Atom[his.size()]));
//            centroid.setGroup(his.get(0).getGroup());
//            histydineCentroids.add(centroid);
//        });

        List<Atom> cations = Stream.of(atomsLysine, atomsArginine).flatMap(Collection::stream).collect(Collectors.toList());
//        List<Atom> cations = Stream.of(atomsLysine, atomsArginine, atomsHistydine).flatMap(Collection::stream).collect(Collectors.toList());
        List<Atom> anions = Stream.of(atomsGlutamicAcid, atomsAsparticAcid).flatMap(Collection::stream).collect(Collectors.toList());

        double distThreshold = 6;
        List<IonicInteraction> foundInteractions = new ArrayList<>();
        cations.forEach((cationAtom) -> {

            anions.forEach((anionAtom -> {
                double dist = Calc.getDistance(cationAtom, anionAtom);
                if (dist <= distThreshold) {
                    foundInteractions.add(new IonicInteraction(new AminoAcid(anionAtom.getGroup()), new AminoAcid(cationAtom.getGroup()), dist));
                }
            }));
        });
        return foundInteractions;
    }


//    public List<IonicInteraction> findIonicInteractions() {
//        ArrayList<Atom> atomsLysine = pdbStructureParser.getAtoms(desiredAtomsLysine, Arrays.asList(AminoAcidAbbreviations.LYS));
//        ArrayList<Atom> atomsArginine = pdbStructureParser.getAtoms(desiredAtomsArginine, Arrays.asList(AminoAcidAbbreviations.ARG));
//
//        ArrayList<Atom> centersOfMassArginine = new ArrayList<>();
//        List<List<Atom>> atomsArginineList = Lists.partition(atomsArginine, desiredAtomsArginine.length);
//        atomsArginineList.forEach((arginine) -> {
//            Atom centerOfMassArginine = Calc.centerOfMass(arginine.toArray(new Atom[arginine.size()]));
//            centerOfMassArginine.setGroup(arginine.get(0).getGroup());
//            centersOfMassArginine.add(centerOfMassArginine);
//        });
//        for (Atom interaction : centersOfMassArginine) { System.out.println(interaction.getGroup()); }
//
//        ArrayList<Atom> atomsHistydine = pdbStructureParser.getAtoms(desiredAtomsHistydine, Arrays.asList(AminoAcidAbbreviations.HIS));
//        List<List<Atom>> atomsHistydineList = Lists.partition(atomsHistydine, desiredAtomsHistydine.length);
//
//        ArrayList<Atom> centersOfMassHistydine = new ArrayList<>();
//        atomsHistydineList.forEach((histydine) -> {
//            Atom centerOfMassHistydine = Calc.centerOfMass(histydine.toArray(new Atom[histydine.size()]));
//            centerOfMassHistydine.setGroup(histydine.get(0).getGroup());
//            centersOfMassHistydine.add(centerOfMassHistydine);
//        });
//
//
//        ArrayList<Atom> atomsGlutamicAcid = pdbStructureParser.getAtoms(desiredAtomsGlutamicAcid, Arrays.asList(AminoAcidAbbreviations.GLU));
//        ArrayList<Atom> atomsAsparticAcid = pdbStructureParser.getAtoms(desiredAtomsAsparticAcid, Arrays.asList(AminoAcidAbbreviations.ASP));
//
//        List<Atom> cations = Stream.of(atomsLysine, centersOfMassArginine, centersOfMassHistydine).flatMap(Collection::stream).collect(Collectors.toList());
//        List<Atom> anions = Stream.of(atomsGlutamicAcid, atomsAsparticAcid).flatMap(Collection::stream).collect(Collectors.toList());
////        List<List<Atom>> anionLists = Lists.partition(anions, desiredAtomsGlutamicAcid.length);
//
//        double distThreshold = 6;
//
//        List<IonicInteraction> foundInteractions = new ArrayList<>();
////        anionLists.forEach((a) -> {
//        anions.forEach((a) -> {
////            Atom centerOfMass = Calc.centerOfMass(a.toArray(new Atom[a.size()]));
//            centersOfMassArginine.forEach((c -> {
//
////                double dist = Calc.getDistance(centerOfMass, c);
//                double dist = Calc.getDistance(a, c);
//                if (dist <= distThreshold) {
////                    foundInteractions.add(new IonicInteraction(new AminoAcid(a.get(0).getGroup()), new AminoAcid(c.getGroup()), dist));
//                    foundInteractions.add(new IonicInteraction(new AminoAcid(a.getGroup()), new AminoAcid(c.getGroup()), dist));
//                }
//            }));
//        });
//        return foundInteractions;
//    }

}

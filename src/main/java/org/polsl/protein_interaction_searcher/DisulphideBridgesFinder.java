package org.polsl.protein_interaction_searcher;

import com.google.common.collect.Lists;
import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;

import java.util.*;

public class DisulphideBridgesFinder {
    private PdbStructureParser pdbStructureParser;

    private final List<AminoAcidAbbreviations> allowedAminoAcids = Arrays.asList(AminoAcidAbbreviations.CYS);

    private final String[] desiredAtoms = new String[] {"N", "CA", "CB", "SG"};

    public DisulphideBridgesFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

    public List<DisulphideBridge> findDisulphideBridges() {
        ArrayList<Atom> atoms = pdbStructureParser.getAtoms(desiredAtoms, allowedAminoAcids);
        List<List<Atom>> cysteines = Lists.partition(atoms, desiredAtoms.length);

//        atoms.forEach((a) -> System.out.println(a.getGroup().getChain().getName()));

        ArrayList<DisulphideBridge> foundDisulphideBridges = new ArrayList<>();

        ListIterator<List<Atom>> cysteineItr = cysteines.listIterator();
        while(cysteineItr.hasNext()) {
            List<Atom> cys = cysteineItr.next();

            ListIterator<List<Atom>> remainingCysItr = cysteines.listIterator(cysteineItr.nextIndex());

            remainingCysItr.forEachRemaining((c) -> {
                DisulphideBridge disulphideBridge = getDisulphideBridge(cys.toArray(new Atom[0]), c.toArray(new Atom[0]));

                if (disulphideBridge != null) {
                    foundDisulphideBridges.add(disulphideBridge);
                }
            });

        }
        return foundDisulphideBridges;
    }

    private DisulphideBridge getDisulphideBridge(Atom[] firstCys, Atom[] secondCys) {
        final int indN = 0;
        final int indCA = 1;
        final int indCB = 2;
        final int indSG = 3;

        double distCAs = Calc.getDistance(firstCys[indCA], secondCys[indCA]);
        double distCBs = Calc.getDistance(firstCys[indCB], secondCys[indCB]);
        if ( !(distCAs <= 6.5 && distCBs <= 4.5) ) { return null; }

        double distSS = Calc.getDistance(firstCys[indSG], secondCys[indSG]);
        if ( !(distSS >= 1.6 && distSS <= 2.4) ) { return null; }

        double phiSS = Math.abs(Calc.torsionAngle(firstCys[indCB], firstCys[indSG], secondCys[indSG], secondCys[indCB]));
        if ( !(phiSS >= 60 && phiSS <= 120) ) { return null; }

        double phi11 = Math.abs(Calc.torsionAngle(firstCys[indN], firstCys[indCA], firstCys[indCB], firstCys[indSG]));
//        if ( ! ((phi11 >= 30 && phi11 <= 90) || (phi11 >= 150 && phi11 <= 180)) ) { return null; }

        double phi21 = Math.abs(Calc.torsionAngle(secondCys[indSG], secondCys[indCB], secondCys[indCA], secondCys[indN]));
//        if ( ! ((phi21 >= 30 && phi21 <= 90) || (phi21 >= 150 && phi21 <= 180)) ) { return null; }

        return new DisulphideBridge(new AminoAcid(firstCys[indSG].getGroup()), new AminoAcid(secondCys[indSG].getGroup()), distCAs, distCBs, distSS, phiSS, phi11, phi21);
    }

}

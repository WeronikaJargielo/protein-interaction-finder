package io.github.WeronikaJargielo.protein_interaction_finder;

import com.google.common.collect.Lists;
import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;

import java.util.*;
import java.util.stream.IntStream;

final class DisulphideBridgesFinder {
    private final PdbStructureParser pdbStructureParser;

    private final List<AminoAcidAbbreviations> allowedAminoAcids = Arrays.asList(AminoAcidAbbreviations.CYS);

    private final String[] desiredAtoms = new String[] {"N", "CA", "CB", "SG"};

    public DisulphideBridgesFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

    public List<DisulphideBridge> findDisulphideBridges(DisulphideBridgeCriteria criteria) {
        ArrayList<Atom> atoms = pdbStructureParser.getAtoms(desiredAtoms, allowedAminoAcids);
        List<List<Atom>> cysteines = Lists.partition(atoms, desiredAtoms.length);

        ArrayList<DisulphideBridge> foundDisulphideBridges = new ArrayList<>();

        final int cysteinesLen = cysteines.size();
        for (int i = 0; i < cysteinesLen; ++i) {
            for (int j = i + 1; j < cysteinesLen; ++j) {
                final DisulphideBridge disulphideBridge = obtainDisulphideBridge(cysteines.get(i), cysteines.get(j), criteria);

                if (disulphideBridge != null) {
                    foundDisulphideBridges.add(disulphideBridge);
                }
            }
        }
        return foundDisulphideBridges;
    }

    private DisulphideBridge obtainDisulphideBridge(List<Atom> firstCys, List<Atom> secondCys, DisulphideBridgeCriteria criteria) {
        final int indN = IntStream.range(0, firstCys.size())
                                  .filter(i -> "N".equals(firstCys.get(i).getName()))
                                  .findFirst()
                                  .orElse(-1);
        final int indCA = IntStream.range(0, firstCys.size())
                                   .filter(i -> "CA".equals(firstCys.get(i).getName()))
                                   .findFirst()
                                   .orElse(-1);
        final int indCB = IntStream.range(0, firstCys.size())
                                   .filter(i -> "CB".equals(firstCys.get(i).getName()))
                                   .findFirst()
                                   .orElse(-1);
        final int indSG = IntStream.range(0, firstCys.size())
                                   .filter(i -> "SG".equals(firstCys.get(i).getName()))
                                   .findFirst()
                                   .orElse(-1);

        if ( ! (firstCys.get(indCA).getName().equals("CA") || secondCys.get(indCA).getName().equals("CA")) ) {
            System.exit(-1);
        }

        if ( ! (firstCys.get(indCB).getName().equals("CB") || secondCys.get(indCB).getName().equals("CB")) ){
            System.exit(-1);
        }

        if ( ! (firstCys.get(indSG).getName().equals("SG") || secondCys.get(indSG).getName().equals("SG")) ) {
            System.exit(-1);
        }

        final double distanceBtwCAs = Calc.getDistance(firstCys.get(indCA), secondCys.get(indCA));
        final double distanceBtwCBs = Calc.getDistance(firstCys.get(indCB), secondCys.get(indCB));
        if ( ! (distanceBtwCAs <= criteria.getDistanceBtwCAs() && distanceBtwCBs <= criteria.getDistanceBtwCBs()) ) { return null; }

        final double distanceBtwSS = Calc.getDistance(firstCys.get(indSG), secondCys.get(indSG));
        if ( ! (distanceBtwSS >= criteria.getMinDistanceBtwSulphurs() && distanceBtwSS <= criteria.getMaxDistanceBtwSulphurs()) ) { return null; }

        final double absDihAngleSS = Math.abs(Calc.torsionAngle(firstCys.get(indCB), firstCys.get(indSG), secondCys.get(indSG), secondCys.get(indCB)));
        if ( ! (absDihAngleSS >= criteria.getMinAbsDihAngleSS() && absDihAngleSS <= criteria.getMaxAbsDihAngleSS()) ) { return null; }

        final double absDihAngle1 = Math.abs(Calc.torsionAngle(firstCys.get(indN), firstCys.get(indCA), firstCys.get(indCB), firstCys.get(indSG)));
        if ( ! ((absDihAngle1 >= criteria.getLowerRangeMinAbsDihAngle1() && absDihAngle1 <= criteria.getLowerRangeMaxAbsDihAngle1())
                 || (absDihAngle1 >= criteria.getUpperRangeMinAbsDihAngle1() && absDihAngle1 <= criteria.getUpperRangeMaxAbsDihAngle1())) ) { return null; }

        final double absDihAngle2 = Math.abs(Calc.torsionAngle(secondCys.get(indSG), secondCys.get(indCB), secondCys.get(indCA), secondCys.get(indN)));
        if ( ! ((absDihAngle2 >= criteria.getLowerRangeMinAbsDihAngle2() && absDihAngle2 <= criteria.getLowerRangeMaxAbsDihAngle2())
                || (absDihAngle2 >= criteria.getUpperRangeMinAbsDihAngle2() && absDihAngle2 <= criteria.getUpperRangeMaxAbsDihAngle2())) ) { return null; }

        return new DisulphideBridge(new AminoAcid(firstCys.get(indSG).getGroup()),
                                    new AminoAcid(secondCys.get(indSG).getGroup()),
                                    distanceBtwCAs, distanceBtwCBs, distanceBtwSS,
                                    absDihAngleSS, absDihAngle1, absDihAngle2);
    }

}

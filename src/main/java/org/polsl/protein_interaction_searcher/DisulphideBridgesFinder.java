package org.polsl.protein_interaction_searcher;

import com.google.common.collect.Lists;
import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;

import java.util.*;
import java.util.stream.IntStream;

public class DisulphideBridgesFinder {
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
        System.out.println(indN);
        System.out.println(indCA);
        System.out.println(indCB);
        System.out.println(indSG);

//      TODO: Sanity check - remove after development.
        if ( ! (firstCys.get(indN).getName().equals("N") || secondCys.get(indN).getName().equals("N")) ) {
            System.exit(-1);
        }

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

        final double absDihAngle11 = Math.abs(Calc.torsionAngle(firstCys.get(indN), firstCys.get(indCA), firstCys.get(indCB), firstCys.get(indSG)));
        if ( ! ((absDihAngle11 >= criteria.getLowerRangeMinAbsDihAngle11() && absDihAngle11 <= criteria.getLowerRangeMaxAbsDihAngle11())
                 || (absDihAngle11 >= criteria.getUpperRangeMinAbsDihAngle11() && absDihAngle11 <= criteria.getUpperRangeMaxAbsDihAngle11())) ) { return null; }

        final double absDihAngle21 = Math.abs(Calc.torsionAngle(secondCys.get(indSG), secondCys.get(indCB), secondCys.get(indCA), secondCys.get(indN)));
        if ( ! ((absDihAngle21 >= criteria.getLowerRangeMinAbsDihAngle21() && absDihAngle21 <= criteria.getLowerRangeMaxAbsDihAngle21())
                || (absDihAngle21 >= criteria.getUpperRangeMinAbsDihAngle21() && absDihAngle21 <= criteria.getUpperRangeMaxAbsDihAngle21())) ) { return null; }

        return new DisulphideBridge(new AminoAcid(firstCys.get(indSG).getGroup()),
                                    new AminoAcid(secondCys.get(indSG).getGroup()),
                                    distanceBtwCAs, distanceBtwCBs, distanceBtwSS,
                                    absDihAngleSS, absDihAngle11, absDihAngle21);
    }

}

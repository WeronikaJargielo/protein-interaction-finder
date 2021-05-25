package org.polsl.protein_interaction_finder;

public class Main {
    public static void main(String[] args) {
        String filename = "../pdbFiles/2bi6.pdb";
//        String filename = "../pdbFiles/4hhb.pdb";
//        String filename = "../pdbFiles/7jgy.pdb";
//        String filename = "../pdbFiles/6v5d.pdb";  // with histidines
//        String filename = "../pdbFiles/6vga.pdb";
//        String filename = "../pdbFiles/6hpj.pdb";
//        String filename = "../pdbFiles/6ujv.pdb";
//        String filename = "../pdbFiles/7kr0.pdb";  // Xray
        try {
            ProteinInteractionFinder finder = new ProteinInteractionFinder(filename);

            System.out.println("\nHydrophobic interactions found:\nPosition Residue Chain Position Residue Chain DistanceBtwCAs");
            for (Object interaction : finder.findHydrophobicInteractions()) { System.out.println(interaction); }

            System.out.println("\nIonic interactions found:\nPosition Residue Chain Position Residue Chain DistanceBtwCentroidsOfChargedGroups");
            for (Object interaction : finder.findIonicInteractions()) { System.out.println(interaction); }

//            DisulphideBridgeCriteria disulphideBridgeCriteria = new DisulphideBridgeCriteria(0, 2.2, 0, 360, 0, 360, 0, 360, 0, 360, 0, 360);
            System.out.println("\nDisulphide bridges found:\nPosition Residue Chain Position Residue Chain DistanceBtwCAs DistanceBtwCBs DistanceBtwSS AbsDihAngleSS AbsDihAngle11 AbsDihAngle21");
//            for (Object bridge : finder.findDisulphideBridges(disulphideBridgeCriteria)) { System.out.println(bridge); }
            for (Object bridge : finder.findDisulphideBridges()) { System.out.println(bridge); }

            System.out.println("\nSulphur-Aromatic interactions found:\nPosition Residue Chain Position Residue Chain DistanceBtwRingSulphur ElevationAngle EquatorialAngle");
            for (Object interaction : finder.findSulphurAromaticInteractions()) { System.out.println(interaction); }

            System.out.println("\nAromatic-Aromatic interactions found:\nPosition Residue Chain Position Residue Chain DistanceBtwRings AngleBtwRings");
            for (Object interaction : finder.findAromaticAromaticInteractions()) { System.out.println(interaction); }

            System.out.println("\nAmino-Aromatic interactions found:\nPosition Residue Chain Position Residue Chain DistanceBtwCationRing PolarAngle AzimuthalAngle");
            for (Object interaction : finder.findAminoAromaticInteractions()) { System.out.println(interaction); }

            System.out.println("\nMain Chain-Main Chain Hydrogen bonds found:\nDonor Acceptor\nPosition Residue Chain D H Position Residue Chain A Aa DistanceDA DistanceHA AngleDHA AngleHAAa AngleDAAa");
            for (Object interaction : finder.findMainMainHydrogenBonds()) { System.out.println(interaction); }

            System.out.println("\nMain Chain-Side Chain Hydrogen bonds found:\nDonor Acceptor\nPosition Residue Chain D H Position Residue Chain A Aa DistanceDA DistanceHA AngleDHA AngleHAAa AngleDAAa");
            for (Object interaction : finder.findMainSideHydrogenBonds()) { System.out.println(interaction); }

            System.out.println("\nSide Chain-Side Chain Hydrogen bonds found:\nDonor Acceptor\nPosition Residue Chain D H Position Residue Chain A Aa DistanceDA DistanceHA AngleDHA AngleHAAa AngleDAAa");
            for (Object interaction : finder.findSideSideHydrogenBonds()) { System.out.println(interaction); }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

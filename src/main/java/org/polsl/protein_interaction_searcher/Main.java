package org.polsl.protein_interaction_searcher;

public class Main {
    public static void main(String[] args) {
//        String filename = "C:\\Users\\user\\Desktop\\Studia\\Semestr_9\\mgr\\ProteinInteractionSearcher\\src\\proteins\\7jgy.pdb";
//        String filename = "C:\\Users\\user\\Desktop\\Studia\\Semestr_9\\mgr\\ProteinInteractionSearcher\\src\\proteins\\2bi6.pdb";
//        String filename = "C:\\Users\\user\\Desktop\\Studia\\Semestr_9\\mgr\\ProteinInteractionSearcher\\src\\proteins\\6vga.pdb";
//        String filename = "C:\\Users\\user\\Desktop\\Studia\\Semestr_9\\mgr\\ProteinInteractionSearcher\\src\\proteins\\6hpj.pdb";
//        String filename = "C:\\Users\\user\\Desktop\\Studia\\Semestr_9\\mgr\\ProteinInteractionSearcher\\src\\proteins\\6ujv.pdb";
//        String filename = "C:\\Users\\user\\Desktop\\Studia\\Semestr_9\\mgr\\ProteinInteractionSearcher\\src\\proteins\\6v5d.pdb";  // with histidines
        String filename = "C:\\Users\\user\\Desktop\\Studia\\Semestr_9\\mgr\\ProteinInteractionSearcher\\src\\proteins\\7kr0.pdb";  // Xray
        try {
            ProteinInteractionSearcher searcher = new ProteinInteractionSearcher(filename);

            System.out.println("Hydrophobic interactions found:\nPosition Residue Chain Position Residue Chain DistanceBtwCAs");
            for (Object interaction : searcher.findHydrophobicInteractions()) { System.out.println(interaction); }

            System.out.println("Ionic interactions found:\nPosition Residue Chain Position Residue Chain DistanceBtwCentroidsOfChargedGroups");
            for (Object interaction : searcher.findIonicInteractions()) { System.out.println(interaction); }

            System.out.println("\nDisulphide bridges found:\nPosition Residue Chain Position Residue Chain DistanceBtwCAs DistanceBtwCBs DistanceBtwSS AbsDihAngleSS AbsDihAngle11 AbsDihAngle21");
            for (Object bridge : searcher.findDisulphideBridges()) { System.out.println(bridge); }

            System.out.println("\nSulphur-Aromatic interactions found:\nPosition Residue Chain Position Residue Chain DistanceBtwRingSulphur ElevationAngle EquatorialAngle");
            for (Object interaction : searcher.findSulphurAromaticInteractions()) { System.out.println(interaction); }

            System.out.println("\nAromatic-Aromatic interactions found:\nPosition Residue Chain Position Residue Chain DistanceBtwRings AngleBtwRings");
            for (Object interaction : searcher.findAromaticAromaticInteractions()) { System.out.println(interaction); }

            System.out.println("\nAmino-Aromatic interactions found:\nPosition Residue Chain Position Residue Chain DistanceBtwCationRing PolarAngle AzimuthalAngle");
            for (Object interaction : searcher.findAminoAromaticInteractions()) { System.out.println(interaction); }

            System.out.println("\nMain Chain-Main Chain Hydrogen bonds found:\nDonor Acceptor\nPosition Residue Chain D H Position Residue Chain A Aa DistanceDA DistanceHA AngleDHA AngleHAAa AngleDAAa");
            for (Object interaction : searcher.findMainMainHydrogenBonds()) { System.out.println(interaction); }

            System.out.println("\nMain Chain-Side Chain Hydrogen bonds found:\nDonor Acceptor\nPosition Residue Chain D H Position Residue Chain A Aa DistanceDA DistanceHA AngleDHA AngleHAAa AngleDAAa");
            for (Object interaction : searcher.findMainSideHydrogenBonds()) { System.out.println(interaction); }

            System.out.println("\nSide Chain-Side Chain Hydrogen bonds found:\nDonor Acceptor\nPosition Residue Chain D H Position Residue Chain A Aa DistanceDA DistanceHA AngleDHA AngleHAAa AngleDAAa");
            for (Object interaction : searcher.findSideSideHydrogenBonds()) { System.out.println(interaction); }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

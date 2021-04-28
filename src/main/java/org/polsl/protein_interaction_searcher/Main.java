package org.polsl.protein_interaction_searcher;

public class Main {
    public static void main(String[] args) {
//        String filename = "C:\\Users\\user\\Desktop\\Studia\\Semestr_9\\mgr\\ProteinInteractionSearcher\\src\\proteins\\7jgy.pdb";
//        String filename = "C:\\Users\\user\\Desktop\\Studia\\Semestr_9\\mgr\\ProteinInteractionSearcher\\src\\proteins\\2bi6.pdb";
//        Str ing filename = "C:\\Users\\user\\Desktop\\Studia\\Semestr_9\\mgr\\ProteinInteractionSearcher\\src\\proteins\\6vga.pdb";
//        String filename = "C:\\Users\\user\\Desktop\\Studia\\Semestr_9\\mgr\\ProteinInteractionSearcher\\src\\proteins\\6hpj.pdb";
        String filename = "C:\\Users\\user\\Desktop\\Studia\\Semestr_9\\mgr\\ProteinInteractionSearcher\\src\\proteins\\6ujv.pdb";
        try {
            ProteinInteractionSearcher searcher = new ProteinInteractionSearcher(filename);
//            System.out.println("Hydrophobic interactions found:\nPosition Residue Chain Position Residue Chain Dist");
//            for (Object interaction : searcher.findHydrophobicInteractions()) { System.out.println(interaction); }
//
//            System.out.println("\nDisulphide bridges found:\nPosition Residue Chain Position Residue Chain DistCA DistCB DistSS PhiSS Phi11 Phi21");
//            for (Object bridge : searcher.findDisulphideBridges()) { System.out.println(bridge); }
//
//            System.out.println("\nSulphur-Aromatic interactions found:\nPosition Residue Chain Position Residue Chain Dist ElevationAngle EquatorialAngle");
//            for (Object interaction : searcher.findSulphurAromaticInteractions()) { System.out.println(interaction); }
//
//            System.out.println("\nAromatic-Aromatic interactions found:\nPosition Residue Chain Position Residue Chain Dist Phi");
//            for (Object interaction : searcher.findAromaticAromaticInteractions()) { System.out.println(interaction); }
//
//            System.out.println("\nAmino-Aromatic interactions found:\nPosition Residue Chain Position Residue Chain Dist PolarAngle EquatorialAngle");
//            for (Object interaction : searcher.findAminoAromaticInteractions()) { System.out.println(interaction); }

            System.out.println("\nMain Chain-Main Chain Hydrogen bonds found:\nDonor Acceptor\nPosition Residue Chain Position D H A Aa Residue Chain DistHA DistDA PhiDHA PhiHAAa PhiDAAa");
            for (Object interaction : searcher.findMainMainHydrogenBonds()) { System.out.println(interaction); }

            System.out.println("\nMain Chain-Side Chain Hydrogen bonds found:\nDonor Acceptor\nPosition Residue Chain Position D H A Aa Residue Chain DistHA DistDA PhiDHA PhiHAAa PhiDAAa");
            for (Object interaction : searcher.findMainSideHydrogenBonds()) { System.out.println(interaction); }

            System.out.println("\nSide Chain-Side Chain Hydrogen bonds found:\nDonor Acceptor\nPosition Residue Chain Position D H A Aa Residue Chain DistHA DistDA PhiDHA PhiHAAa PhiDAAa");
            for (Object interaction : searcher.findSideSideHydrogenBonds()) { System.out.println(interaction); }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

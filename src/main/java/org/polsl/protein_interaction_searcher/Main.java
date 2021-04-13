package org.polsl.protein_interaction_searcher;

public class Main {
    public static void main(String[] args) {
//        String filename = "C:\\Users\\user\\Desktop\\Studia\\Semestr_9\\mgr\\ProteinInteractionSearcher\\src\\proteins\\7jgy.pdb";
        String filename = "C:\\Users\\user\\Desktop\\Studia\\Semestr_9\\mgr\\ProteinInteractionSearcher\\src\\proteins\\2bi6.pdb";

        try {
            ProteinInteractionSearcher searcher = new ProteinInteractionSearcher(filename);
            System.out.println("Hydrophobic interactions found:\nPosition Residue Chain Position Residue Chain distance");
            for (Object interaction : searcher.findHydrophobicInteractions()) { System.out.println(interaction); }

            System.out.println("\nDisulphide bridges found:\nPosition Residue Chain Position Residue Chain distCA distCB distSS phiSS phi11 phi21");
            for (Object bridge : searcher.findDisulphideBridges()) { System.out.println(bridge); }


//            for (Object interaction : searcher.findSulphurAromaticInteractions()) { System.out.println(interaction); }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

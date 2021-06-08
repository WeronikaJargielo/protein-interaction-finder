package examples;

import java.util.List;

import io.github.WeronikaJargielo.protein_interaction_finder.*;

public class Main {

    public static void main (String[] args) {
        // Example PDB filename, path should be absolute or relative to directory from which Java executable is run.
        // You can download it e.g. from https://www.rcsb.org/structure/2BI6.
        final String filename = "2BI6.pdb";

        try {
            // To identify any interatomic interactions, first you need to create an instance of ProteinInteractionFinder
            // class with a path to the PDB file containing desired protein structure.
            ProteinInteractionFinder finder = new ProteinInteractionFinder(filename);

            // To identify e.g all hydrophobic interactions with default criteria, call findHydrophobicInteractions()
            // method on created ProteinInteractionFinder instance.
            List<HydrophobicInteraction> hydrophobicInteractions = finder.findHydrophobicInteractions();

            // For further analysis you can e.g. print all identified interactions of chosen type.
            printResults(hydrophobicInteractions,
                    "hydrophobic interactions",
                    "Position Residue Chain Position Residue Chain DistanceBtwCAs");


            // To identify all hydrophobic interactions with custom criteria, create an instance of
            // HydrophobicInteractionCriteria with desired boundary values.
            // E.g. for distance between CA atoms from 3 to 6 Angstrom:
            final double minDistance = 3.0;
            final double maxDistance= 6.0;
            HydrophobicInteractionCriteria customCriteria = new HydrophobicInteractionCriteria(minDistance, maxDistance);

            // Run the identification again and print results.
            List<HydrophobicInteraction> hydrophobicInteractionsCustomCriteria = finder.findHydrophobicInteractions(customCriteria);
            printResults(hydrophobicInteractionsCustomCriteria,
                    "hydrophobic interactions",
                    "Position Residue Chain Position Residue Chain DistanceBtwCAs");


            // Default criteria examples for every supported interaction type.
            // Each find method can also take one argument with custom criteria of appropriate type.

            List<DisulphideBridge> disulphideBridges = finder.findDisulphideBridges();
            printResults(disulphideBridges,
                    "disulphide bridges",
                    "Position Residue Chain Position Residue Chain DistanceBtwCAs DistanceBtwCBs DistanceBtwSS AbsDihAngleSS AbsDihAngle11 AbsDihAngle21");

            List<HydrogenBond> mainMainHydrogenBonds = finder.findMainMainHydrogenBonds();
            printResults(mainMainHydrogenBonds,
                    "main chain-main chain hydrogen bonds",
                    "Donor Acceptor\nPosition Residue Chain D H Position Residue Chain A Aa DistanceDA DistanceHA AngleDHA AngleHAAa AngleDAAa");

            List<HydrogenBond> mainSideHydrogenBonds = finder.findMainSideHydrogenBonds();
            printResults(mainSideHydrogenBonds,
                    "main chain-side chain hydrogen bonds",
                    "Donor Acceptor\nPosition Residue Chain D H Position Residue Chain A Aa DistanceDA DistanceHA AngleDHA AngleHAAa AngleDAAa");

            List<HydrogenBond> sideSideHydrogenBonds = finder.findSideSideHydrogenBonds();
            printResults(sideSideHydrogenBonds,
                    "side chain-side chain hydrogen bonds",
                    "Donor Acceptor\nPosition Residue Chain D H Position Residue Chain A Aa DistanceDA DistanceHA AngleDHA AngleHAAa AngleDAAa");

            List<IonicInteraction> ionicInteractions = finder.findIonicInteractions();
            printResults(ionicInteractions,
                    "ionic interactions",
                    "Position Residue Chain Position Residue Chain DistanceBtwCentroidsOfChargedGroups");

            List<AromaticAromaticInteraction> aromaticAromaticInteractions = finder.findAromaticAromaticInteractions();
            printResults(aromaticAromaticInteractions,
                    "aromatic-aromatic interactions",
                    "Position Residue Chain Position Residue Chain DistanceBtwRings AngleBtwRings");

            List<SulphurAromaticInteraction> sulphurAromaticInteractions = finder.findSulphurAromaticInteractions();
            printResults(sulphurAromaticInteractions,
                    "aromatic-sulphur interactions",
                    "Position Residue Chain Position Residue Chain DistanceBtwRingSulphur ElevationAngle EquatorialAngle");

            List<AminoAromaticInteraction> aminoAromaticInteractions = finder.findAminoAromaticInteractions();
            printResults(aminoAromaticInteractions,
                    "cation-PI Interactions",
                    "Position Residue Chain Position Residue Chain DistanceBtwCationRing PolarAngle AzimuthalAngle");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method for printing identification results.
    public static void printResults(List<? extends Object> interactionList, String interactionName, String dataHeader) {
        if (interactionList.size() > 0) {
            System.out.println("\nIntraprotein " + interactionName + " found:\n" + dataHeader);
            for (Object interaction : interactionList) {
                System.out.println(interaction);
            }
        } else {
            System.out.println("\nNo intraprotein " + interactionName + " found.");
        }
    }
}

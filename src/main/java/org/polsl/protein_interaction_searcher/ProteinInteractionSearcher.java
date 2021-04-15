package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.StructureException;

import java.io.IOException;
import java.util.List;

public final class ProteinInteractionSearcher {
    private final PdbStructureParser pdbStructureParser;

    public ProteinInteractionSearcher(String pdbFilename) throws IOException, StructureException {
        pdbStructureParser = new PdbStructureParser(pdbFilename);
    }

    public List<HydrogenBond> findHydrogenBonds() {
        return null;
    }

    public List<DisulphideBridge> findDisulphideBridges() {
        DisulphideBridgesFinder disulphideBridgesFinder = new DisulphideBridgesFinder(pdbStructureParser);
        return disulphideBridgesFinder.findDisulphideBridges();
    }

    public List<SulphurAromaticInteraction> findSulphurAromaticInteractions() {
        SulphurAromaticInteractionsFinder sulphurAromaticInteractionsFinder = new SulphurAromaticInteractionsFinder(pdbStructureParser);
        return sulphurAromaticInteractionsFinder.findSulphurAromaticInteractions();
    }

    public List<AminoAromaticInteraction> findAminoAromaticInteractions() {
        AminoAromaticInteractionFinder aminoAromaticInteractionFinder = new AminoAromaticInteractionFinder(pdbStructureParser);
        return aminoAromaticInteractionFinder.findAminoAromaticInteractions();
    }

    public List<AromaticAromaticInteraction> findAromaticAromaticInteractions() {
        AromaticAromaticInteractionFinder aromaticAromaticInteractionFinder = new AromaticAromaticInteractionFinder(pdbStructureParser);
        return aromaticAromaticInteractionFinder.findAromaticAromaticInteraction();
    }

    public List<IonicInteraction> findIonicInteractions() {
        return null;
    }

    public List<HydrophobicInteraction> findHydrophobicInteractions() {
        HydrophobicInteractionsFinder hydrophobicInteractionsFinder = new HydrophobicInteractionsFinder(pdbStructureParser);
        return hydrophobicInteractionsFinder.findHydrophobicInteractions();
    }

}

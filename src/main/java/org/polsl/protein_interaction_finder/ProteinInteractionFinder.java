package org.polsl.protein_interaction_finder;

import org.biojava.nbio.structure.StructureException;

import java.io.IOException;
import java.util.List;

public final class ProteinInteractionFinder {
    private final PdbStructureParser pdbStructureParser;

    public ProteinInteractionFinder(String pdbFilename) throws IOException, StructureException {
        pdbStructureParser = new PdbStructureParser(pdbFilename);
    }

    public List<HydrogenBond> findMainMainHydrogenBonds() {
        HydrogenBondFinder hydrogenBondFinder = new HydrogenBondFinder(pdbStructureParser);
        return hydrogenBondFinder.findMainMainHydrogenBonds();
    }

    public List<HydrogenBond> findSideSideHydrogenBonds() {
        HydrogenBondFinder hydrogenBondFinder = new HydrogenBondFinder(pdbStructureParser);
        return hydrogenBondFinder.findSideSideHydrogenBonds();
    }

    public List<HydrogenBond> findMainSideHydrogenBonds() {
        HydrogenBondFinder hydrogenBondFinder = new HydrogenBondFinder(pdbStructureParser);
        return hydrogenBondFinder.findMainSideHydrogenBonds();
    }

    public List<DisulphideBridge> findDisulphideBridges() {
        final DisulphideBridgeCriteria defaultCriteria = new DisulphideBridgeCriteria();
        DisulphideBridgesFinder disulphideBridgesFinder = new DisulphideBridgesFinder(pdbStructureParser);
        return disulphideBridgesFinder.findDisulphideBridges(defaultCriteria);
    }

    public List<DisulphideBridge> findDisulphideBridges(DisulphideBridgeCriteria userDefinedCriteria) {
        DisulphideBridgesFinder disulphideBridgesFinder = new DisulphideBridgesFinder(pdbStructureParser);
        return disulphideBridgesFinder.findDisulphideBridges(userDefinedCriteria);
    }

    public List<SulphurAromaticInteraction> findSulphurAromaticInteractions() {
        final SulphurAromaticInteractionCriteria defaultCriteria = new SulphurAromaticInteractionCriteria();
        SulphurAromaticInteractionsFinder sulphurAromaticInteractionsFinder = new SulphurAromaticInteractionsFinder(pdbStructureParser);
        return sulphurAromaticInteractionsFinder.findSulphurAromaticInteractions(defaultCriteria);
    }

    public List<SulphurAromaticInteraction> findSulphurAromaticInteractions(SulphurAromaticInteractionCriteria userDefinedCriteria) {
        SulphurAromaticInteractionsFinder sulphurAromaticInteractionsFinder = new SulphurAromaticInteractionsFinder(pdbStructureParser);
        return sulphurAromaticInteractionsFinder.findSulphurAromaticInteractions(userDefinedCriteria);
    }

    public List<AminoAromaticInteraction> findAminoAromaticInteractions() {
        AminoAromaticInteractionCriteria defaultCriteria = new AminoAromaticInteractionCriteria();
        AminoAromaticInteractionFinder aminoAromaticInteractionFinder = new AminoAromaticInteractionFinder(pdbStructureParser);
        return aminoAromaticInteractionFinder.findAminoAromaticInteractions(defaultCriteria);
    }

    public List<AminoAromaticInteraction> findAminoAromaticInteractions(AminoAromaticInteractionCriteria userDefinedCriteria) {
        AminoAromaticInteractionFinder aminoAromaticInteractionFinder = new AminoAromaticInteractionFinder(pdbStructureParser);
        return aminoAromaticInteractionFinder.findAminoAromaticInteractions(userDefinedCriteria);
    }

    public List<AromaticAromaticInteraction> findAromaticAromaticInteractions() {
        AromaticAromaticInteractionCriteria defaultCriteria = new AromaticAromaticInteractionCriteria();
        AromaticAromaticInteractionFinder aromaticAromaticInteractionFinder = new AromaticAromaticInteractionFinder(pdbStructureParser);
        return aromaticAromaticInteractionFinder.findAromaticAromaticInteraction(defaultCriteria);
    }

    public List<AromaticAromaticInteraction> findAromaticAromaticInteractions(AromaticAromaticInteractionCriteria userDefinedCriteria) {
        AromaticAromaticInteractionFinder aromaticAromaticInteractionFinder = new AromaticAromaticInteractionFinder(pdbStructureParser);
        return aromaticAromaticInteractionFinder.findAromaticAromaticInteraction(userDefinedCriteria);
    }

    public List<IonicInteraction> findIonicInteractions() {
        final IonicInteractionCriteria defaultCriteria = new IonicInteractionCriteria ();
        IonicInteractionsFinder ionicInteractionsFinder = new IonicInteractionsFinder(pdbStructureParser);
        return ionicInteractionsFinder.findIonicInteractions(defaultCriteria);
    }

    public List<IonicInteraction> findIonicInteractions(IonicInteractionCriteria userDefinedCriteria) {
        IonicInteractionsFinder ionicInteractionsFinder = new IonicInteractionsFinder(pdbStructureParser);
        return ionicInteractionsFinder.findIonicInteractions(userDefinedCriteria);
    }

    public List<HydrophobicInteraction> findHydrophobicInteractions() {
        final HydrophobicInteractionCriteria defaultCriteria = new HydrophobicInteractionCriteria();
        HydrophobicInteractionsFinder hydrophobicInteractionsFinder = new HydrophobicInteractionsFinder(pdbStructureParser);

        return hydrophobicInteractionsFinder.findHydrophobicInteractions(defaultCriteria);
    }

    public List<HydrophobicInteraction> findHydrophobicInteractions(HydrophobicInteractionCriteria userDefinedCriteria) {
        HydrophobicInteractionsFinder hydrophobicInteractionsFinder = new HydrophobicInteractionsFinder(pdbStructureParser);
        return hydrophobicInteractionsFinder.findHydrophobicInteractions(userDefinedCriteria);
    }

}

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
        final HydrogenBondCriteria defaultCriteria = new HydrogenBondCriteria();
        final HydrogenBondFinder hydrogenBondFinder = new HydrogenBondFinder(pdbStructureParser);
        return hydrogenBondFinder.findMainMainHydrogenBonds(defaultCriteria);
    }

    public List<HydrogenBond> findMainMainHydrogenBonds(HydrogenBondCriteria userDefinedCriteria) {
        final HydrogenBondFinder hydrogenBondFinder = new HydrogenBondFinder(pdbStructureParser);
        return hydrogenBondFinder.findMainMainHydrogenBonds(userDefinedCriteria);
    }

    public List<HydrogenBond> findSideSideHydrogenBonds() {
        final HydrogenBondCriteria defaultCriteria = new HydrogenBondCriteria();
        final HydrogenBondFinder hydrogenBondFinder = new HydrogenBondFinder(pdbStructureParser);
        return hydrogenBondFinder.findSideSideHydrogenBonds(defaultCriteria);
    }

    public List<HydrogenBond> findSideSideHydrogenBonds(HydrogenBondCriteria userDefinedCriteria) {
        final HydrogenBondFinder hydrogenBondFinder = new HydrogenBondFinder(pdbStructureParser);
        return hydrogenBondFinder.findSideSideHydrogenBonds(userDefinedCriteria);
    }

    public List<HydrogenBond> findMainSideHydrogenBonds() {
        final HydrogenBondCriteria defaultCriteria = new HydrogenBondCriteria();
        final HydrogenBondFinder hydrogenBondFinder = new HydrogenBondFinder(pdbStructureParser);
        return hydrogenBondFinder.findMainSideHydrogenBonds(defaultCriteria);
    }

    public List<HydrogenBond> findMainSideHydrogenBonds(HydrogenBondCriteria userDefinedCriteria) {
        final HydrogenBondFinder hydrogenBondFinder = new HydrogenBondFinder(pdbStructureParser);
        return hydrogenBondFinder.findMainSideHydrogenBonds(userDefinedCriteria);
    }

    public List<DisulphideBridge> findDisulphideBridges() {
        final DisulphideBridgeCriteria defaultCriteria = new DisulphideBridgeCriteria();
        final DisulphideBridgesFinder disulphideBridgesFinder = new DisulphideBridgesFinder(pdbStructureParser);
        return disulphideBridgesFinder.findDisulphideBridges(defaultCriteria);
    }

    public List<DisulphideBridge> findDisulphideBridges(DisulphideBridgeCriteria userDefinedCriteria) {
        final DisulphideBridgesFinder disulphideBridgesFinder = new DisulphideBridgesFinder(pdbStructureParser);
        return disulphideBridgesFinder.findDisulphideBridges(userDefinedCriteria);
    }

    public List<SulphurAromaticInteraction> findSulphurAromaticInteractions() {
        final SulphurAromaticInteractionCriteria defaultCriteria = new SulphurAromaticInteractionCriteria();
        final SulphurAromaticInteractionsFinder sulphurAromaticInteractionsFinder = new SulphurAromaticInteractionsFinder(pdbStructureParser);
        return sulphurAromaticInteractionsFinder.findSulphurAromaticInteractions(defaultCriteria);
    }

    public List<SulphurAromaticInteraction> findSulphurAromaticInteractions(SulphurAromaticInteractionCriteria userDefinedCriteria) {
        final SulphurAromaticInteractionsFinder sulphurAromaticInteractionsFinder = new SulphurAromaticInteractionsFinder(pdbStructureParser);
        return sulphurAromaticInteractionsFinder.findSulphurAromaticInteractions(userDefinedCriteria);
    }

    public List<AminoAromaticInteraction> findAminoAromaticInteractions() {
        final AminoAromaticInteractionCriteria defaultCriteria = new AminoAromaticInteractionCriteria();
        final AminoAromaticInteractionFinder aminoAromaticInteractionFinder = new AminoAromaticInteractionFinder(pdbStructureParser);
        return aminoAromaticInteractionFinder.findAminoAromaticInteractions(defaultCriteria);
    }

    public List<AminoAromaticInteraction> findAminoAromaticInteractions(AminoAromaticInteractionCriteria userDefinedCriteria) {
        final AminoAromaticInteractionFinder aminoAromaticInteractionFinder = new AminoAromaticInteractionFinder(pdbStructureParser);
        return aminoAromaticInteractionFinder.findAminoAromaticInteractions(userDefinedCriteria);
    }

    public List<AromaticAromaticInteraction> findAromaticAromaticInteractions() {
        final AromaticAromaticInteractionCriteria defaultCriteria = new AromaticAromaticInteractionCriteria();
        final AromaticAromaticInteractionFinder aromaticAromaticInteractionFinder = new AromaticAromaticInteractionFinder(pdbStructureParser);
        return aromaticAromaticInteractionFinder.findAromaticAromaticInteraction(defaultCriteria);
    }

    public List<AromaticAromaticInteraction> findAromaticAromaticInteractions(AromaticAromaticInteractionCriteria userDefinedCriteria) {
        final AromaticAromaticInteractionFinder aromaticAromaticInteractionFinder = new AromaticAromaticInteractionFinder(pdbStructureParser);
        return aromaticAromaticInteractionFinder.findAromaticAromaticInteraction(userDefinedCriteria);
    }

    public List<IonicInteraction> findIonicInteractions() {
        final IonicInteractionCriteria defaultCriteria = new IonicInteractionCriteria ();
        final IonicInteractionsFinder ionicInteractionsFinder = new IonicInteractionsFinder(pdbStructureParser);
        return ionicInteractionsFinder.findIonicInteractions(defaultCriteria);
    }

    public List<IonicInteraction> findIonicInteractions(IonicInteractionCriteria userDefinedCriteria) {
        final IonicInteractionsFinder ionicInteractionsFinder = new IonicInteractionsFinder(pdbStructureParser);
        return ionicInteractionsFinder.findIonicInteractions(userDefinedCriteria);
    }

    public List<HydrophobicInteraction> findHydrophobicInteractions() {
        final HydrophobicInteractionCriteria defaultCriteria = new HydrophobicInteractionCriteria();
        final HydrophobicInteractionsFinder hydrophobicInteractionsFinder = new HydrophobicInteractionsFinder(pdbStructureParser);
        return hydrophobicInteractionsFinder.findHydrophobicInteractions(defaultCriteria);
    }

    public List<HydrophobicInteraction> findHydrophobicInteractions(HydrophobicInteractionCriteria userDefinedCriteria) {
        final HydrophobicInteractionsFinder hydrophobicInteractionsFinder = new HydrophobicInteractionsFinder(pdbStructureParser);
        return hydrophobicInteractionsFinder.findHydrophobicInteractions(userDefinedCriteria);
    }

}

package io.github.WeronikaJargielo.protein_interaction_finder;

import org.biojava.nbio.structure.StructureException;

import java.io.IOException;
import java.util.List;

/**
 * Class for identifying all implemented interactions.
 */
public final class ProteinInteractionFinder {
    private final PdbStructureParser pdbStructureParser;

    /**
     * Instantiates new ProteinInteractionFinder for identifying interactions in protein stored in given PDB file.
     *
     * @param pdbFilename Path to protein PDB file.
     * @throws IOException        Probably given PDB file does not exist.
     * @throws StructureException Probably given PDB file is incorrectly formatted.
     */
    public ProteinInteractionFinder(String pdbFilename) throws IOException, StructureException {
        pdbStructureParser = new PdbStructureParser(pdbFilename);
    }

    /**
     * Returns PdbStructureParser created in constructor.
     *
     * @return PdbStructureParser created in constructor.
     */
    public PdbStructureParser getPdbStructureParser() {
        return pdbStructureParser;
    }

    /**
     * Identify main chain - main chain hydrogen bonds in protein.
     * Found bonds satisfy default criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.HydrogenBondCriteria
     *
     * @return List of main chain - main chain hydrogen bonds satisfying default criteria.
     */
    public List<HydrogenBond> findMainMainHydrogenBonds() {
        final HydrogenBondCriteria defaultCriteria = new HydrogenBondCriteria();
        final HydrogenBondFinder hydrogenBondFinder = new HydrogenBondFinder(pdbStructureParser);
        return hydrogenBondFinder.findMainMainHydrogenBonds(defaultCriteria);
    }

    /**
     * Identify main chain - main chain hydrogen bonds in protein.
     * Found bonds satisfy custom criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.HydrogenBondCriteria
     *
     * @param userDefinedCriteria Custom criteria that will be used for hydrogen bond identification.
     * @return List of main chain - main chain hydrogen bonds satisfying custom criteria.
     */
    public List<HydrogenBond> findMainMainHydrogenBonds(HydrogenBondCriteria userDefinedCriteria) {
        final HydrogenBondFinder hydrogenBondFinder = new HydrogenBondFinder(pdbStructureParser);
        return hydrogenBondFinder.findMainMainHydrogenBonds(userDefinedCriteria);
    }

    /**
     * Identify side chain - side chain hydrogen bonds in protein.
     * Found bonds satisfy default criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.HydrogenBondCriteria
     *
     * @return List of side chain - side chain hydrogen bonds satisfying default criteria.
     */
    public List<HydrogenBond> findSideSideHydrogenBonds() {
        final HydrogenBondCriteria defaultCriteria = new HydrogenBondCriteria();
        final HydrogenBondFinder hydrogenBondFinder = new HydrogenBondFinder(pdbStructureParser);
        return hydrogenBondFinder.findSideSideHydrogenBonds(defaultCriteria);
    }

    /**
     * Identify side chain - side chain hydrogen bonds in protein.
     * Found bonds satisfy custom criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.HydrogenBondCriteria
     *
     * @param userDefinedCriteria Custom criteria that will be used for hydrogen bond identification.
     * @return List of side chain - side chain hydrogen bonds satisfying custom criteria.
     */
    public List<HydrogenBond> findSideSideHydrogenBonds(HydrogenBondCriteria userDefinedCriteria) {
        final HydrogenBondFinder hydrogenBondFinder = new HydrogenBondFinder(pdbStructureParser);
        return hydrogenBondFinder.findSideSideHydrogenBonds(userDefinedCriteria);
    }

    /**
     * Identify main chain - side chain hydrogen bonds in protein.
     * Found bonds satisfy default criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.HydrogenBondCriteria
     *
     * @return List of main chain - side chain hydrogen bonds satisfying default criteria.
     */
    public List<HydrogenBond> findMainSideHydrogenBonds() {
        final HydrogenBondCriteria defaultCriteria = new HydrogenBondCriteria();
        final HydrogenBondFinder hydrogenBondFinder = new HydrogenBondFinder(pdbStructureParser);
        return hydrogenBondFinder.findMainSideHydrogenBonds(defaultCriteria);
    }

    /**
     * Identify main chain - side chain hydrogen bonds in protein.
     * Found bonds satisfy custom criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.HydrogenBondCriteria
     *
     * @param userDefinedCriteria Custom criteria that will be used for hydrogen bond identification.
     * @return List of main chain - side chain hydrogen bonds satisfying custom criteria.
     */
    public List<HydrogenBond> findMainSideHydrogenBonds(HydrogenBondCriteria userDefinedCriteria) {
        final HydrogenBondFinder hydrogenBondFinder = new HydrogenBondFinder(pdbStructureParser);
        return hydrogenBondFinder.findMainSideHydrogenBonds(userDefinedCriteria);
    }

    /**
     * Identify disulphide bridges in protein.
     * Found bridges satisfy default criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.DisulphideBridgeCriteria
     *
     * @return List of disulphide bridges satisfying default criteria.
     */
    public List<DisulphideBridge> findDisulphideBridges() {
        final DisulphideBridgeCriteria defaultCriteria = new DisulphideBridgeCriteria();
        final DisulphideBridgesFinder disulphideBridgesFinder = new DisulphideBridgesFinder(pdbStructureParser);
        return disulphideBridgesFinder.findDisulphideBridges(defaultCriteria);
    }

    /**
     * Identify disulphide bridges in protein.
     * Found bridges satisfy custom criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.DisulphideBridgeCriteria
     *
     * @param userDefinedCriteria Custom criteria that will be used for disulphide bridges identification.
     * @return List of disulphide bridges satisfying custom criteria.
     */
    public List<DisulphideBridge> findDisulphideBridges(DisulphideBridgeCriteria userDefinedCriteria) {
        final DisulphideBridgesFinder disulphideBridgesFinder = new DisulphideBridgesFinder(pdbStructureParser);
        return disulphideBridgesFinder.findDisulphideBridges(userDefinedCriteria);
    }

    /**
     * Identify sulphur-aromatic interactions in protein.
     * Found interactions satisfy default criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.SulphurAromaticInteractionCriteria
     *
     * @return List of sulphur-aromatic interactions satisfying default criteria.
     */
    public List<SulphurAromaticInteraction> findSulphurAromaticInteractions() {
        final SulphurAromaticInteractionCriteria defaultCriteria = new SulphurAromaticInteractionCriteria();
        final SulphurAromaticInteractionsFinder sulphurAromaticInteractionsFinder = new SulphurAromaticInteractionsFinder(pdbStructureParser);
        return sulphurAromaticInteractionsFinder.findSulphurAromaticInteractions(defaultCriteria);
    }

    /**
     * Identify sulphur-aromatic interactions in protein.
     * Found interactions satisfy custom criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.SulphurAromaticInteractionCriteria
     *
     * @param userDefinedCriteria Custom criteria that will be used for sulphur-aromatic interactions identification.
     * @return List of sulphur-aromatic interactions satisfying custom criteria.
     */
    public List<SulphurAromaticInteraction> findSulphurAromaticInteractions(SulphurAromaticInteractionCriteria userDefinedCriteria) {
        final SulphurAromaticInteractionsFinder sulphurAromaticInteractionsFinder = new SulphurAromaticInteractionsFinder(pdbStructureParser);
        return sulphurAromaticInteractionsFinder.findSulphurAromaticInteractions(userDefinedCriteria);
    }

    /**
     * Identify amino-aromatic interactions in protein.
     * Found interactions satisfy default criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.AminoAromaticInteractionCriteria
     *
     * @return List of amino-aromatic interactions satisfying default criteria.
     */
    public List<AminoAromaticInteraction> findAminoAromaticInteractions() {
        final AminoAromaticInteractionCriteria defaultCriteria = new AminoAromaticInteractionCriteria();
        final AminoAromaticInteractionFinder aminoAromaticInteractionFinder = new AminoAromaticInteractionFinder(pdbStructureParser);
        return aminoAromaticInteractionFinder.findAminoAromaticInteractions(defaultCriteria);
    }

    /**
     * Identify amino-aromatic interactions in protein.
     * Found interactions satisfy custom criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.AminoAromaticInteractionCriteria
     *
     * @param userDefinedCriteria Custom criteria that will be used for amino-aromatic interactions identification.
     * @return List of amino-aromatic interactions satisfying custom criteria.
     */
    public List<AminoAromaticInteraction> findAminoAromaticInteractions(AminoAromaticInteractionCriteria userDefinedCriteria) {
        final AminoAromaticInteractionFinder aminoAromaticInteractionFinder = new AminoAromaticInteractionFinder(pdbStructureParser);
        return aminoAromaticInteractionFinder.findAminoAromaticInteractions(userDefinedCriteria);
    }

    /**
     * Identify aromatic-aromatic interactions in protein.
     * Found interactions satisfy default criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.AromaticAromaticInteractionCriteria
     *
     * @return List of aromatic-aromatic interactions satisfying default criteria.
     */
    public List<AromaticAromaticInteraction> findAromaticAromaticInteractions() {
        final AromaticAromaticInteractionCriteria defaultCriteria = new AromaticAromaticInteractionCriteria();
        final AromaticAromaticInteractionFinder aromaticAromaticInteractionFinder = new AromaticAromaticInteractionFinder(pdbStructureParser);
        return aromaticAromaticInteractionFinder.findAromaticAromaticInteraction(defaultCriteria);
    }

    /**
     * Identify aromatic-aromatic interactions in protein.
     * Found interactions satisfy custom criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.AromaticAromaticInteractionCriteria
     *
     * @param userDefinedCriteria Custom criteria that will be used for aromatic-aromatic interactions identification.
     * @return List of aromatic-aromatic interactions satisfying custom criteria.
     */
    public List<AromaticAromaticInteraction> findAromaticAromaticInteractions(AromaticAromaticInteractionCriteria userDefinedCriteria) {
        final AromaticAromaticInteractionFinder aromaticAromaticInteractionFinder = new AromaticAromaticInteractionFinder(pdbStructureParser);
        return aromaticAromaticInteractionFinder.findAromaticAromaticInteraction(userDefinedCriteria);
    }

    /**
     * Identify ionic interactions in protein.
     * Found interactions satisfy default criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.IonicInteractionCriteria
     *
     * @return List of ionic interactions satisfying default criteria.
     */
    public List<IonicInteraction> findIonicInteractions() {
        final IonicInteractionCriteria defaultCriteria = new IonicInteractionCriteria ();
        final IonicInteractionsFinder ionicInteractionsFinder = new IonicInteractionsFinder(pdbStructureParser);
        return ionicInteractionsFinder.findIonicInteractions(defaultCriteria);
    }

    /**
     * Identify ionic interactions in protein.
     * Found interactions satisfy custom criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.IonicInteractionCriteria
     *
     * @param userDefinedCriteria Custom criteria that will be used for ionic interactions identification.
     * @return List of ionic interactions satisfying custom criteria.
     */
    public List<IonicInteraction> findIonicInteractions(IonicInteractionCriteria userDefinedCriteria) {
        final IonicInteractionsFinder ionicInteractionsFinder = new IonicInteractionsFinder(pdbStructureParser);
        return ionicInteractionsFinder.findIonicInteractions(userDefinedCriteria);
    }

    /**
     * Identify hydrophobic interactions in protein.
     * Found interactions satisfy default criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.HydrophobicInteractionCriteria
     *
     * @return List of hydrophobic interactions satisfying default criteria.
     */
    public List<HydrophobicInteraction> findHydrophobicInteractions() {
        final HydrophobicInteractionCriteria defaultCriteria = new HydrophobicInteractionCriteria();
        final HydrophobicInteractionsFinder hydrophobicInteractionsFinder = new HydrophobicInteractionsFinder(pdbStructureParser);
        return hydrophobicInteractionsFinder.findHydrophobicInteractions(defaultCriteria);
    }

    /**
     * Identify hydrophobic interactions in protein.
     * Found interactions satisfy custom criteria.
     * @see io.github.WeronikaJargielo.protein_interaction_finder.HydrophobicInteractionCriteria
     *
     * @param userDefinedCriteria Custom criteria that will be used for hydrophobic interactions identification.
     * @return List of hydrophobic interactions satisfying custom criteria.
     */
    public List<HydrophobicInteraction> findHydrophobicInteractions(HydrophobicInteractionCriteria userDefinedCriteria) {
        final HydrophobicInteractionsFinder hydrophobicInteractionsFinder = new HydrophobicInteractionsFinder(pdbStructureParser);
        return hydrophobicInteractionsFinder.findHydrophobicInteractions(userDefinedCriteria);
    }

}

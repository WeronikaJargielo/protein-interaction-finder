package io.github.WeronikaJargielo.protein_interaction_finder;

import com.google.common.collect.Lists;
import org.biojava.nbio.structure.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class PdbStructureParser {
    private final Structure proteinStructure;

    PdbStructureParser(String pdbFilename) throws IOException, StructureException {
        proteinStructure = readProteinStructure(pdbFilename);
    }

    public Structure getProteinStructure() {
        return proteinStructure;
    }

    private Structure readProteinStructure(String pdbFilename) throws IOException, StructureException {
        return StructureIO.getStructure(pdbFilename);
    }

    public ArrayList<Atom> getAtoms(String[] atomNames) {
        ArrayList<Atom> atoms = new ArrayList<>(Arrays.asList(StructureTools.getAtomArray(proteinStructure, atomNames)));

        // Filter unrecognizable amino acid abbreviations.
        atoms.removeIf((a) -> {
            final String aminoAcidName = a.getGroup().getPDBName();
            try {
                final AminoAcidAbbreviations aminoAcidAbbr = AminoAcidAbbreviations.valueOf(aminoAcidName);
            } catch (java.lang.IllegalArgumentException e) {
                return true;
            }
            return false;
        });

        return atoms;
    }

    public ArrayList<Atom> getAtoms(String[] atomNames, List<AminoAcidAbbreviations> allowedAminoAcids) {
        ArrayList<Atom> atoms = this.getAtoms(atomNames);

        atoms.removeIf((a) -> {
            final String aminoAcidName = a.getGroup().getPDBName();
            final AminoAcidAbbreviations aminoAcidAbbr = AminoAcidAbbreviations.valueOf(aminoAcidName);
            return ( ! allowedAminoAcids.contains(aminoAcidAbbr));
        });

        return atoms;
    }

    public ArrayList<AromaticRing> getAromaticRings() {

        final List<Atom> phenylalanineAtoms = getAtoms(new String[]{"CG", "CD1", "CD2", "CE1", "CE2", "CZ"}, Arrays.asList(AminoAcidAbbreviations.PHE));
        final List<Atom> tyrosineAtoms = getAtoms(new String[]{"CG", "CD1", "CD2", "CE1", "CE2", "CZ"}, Arrays.asList(AminoAcidAbbreviations.TYR));

        final List<Atom> tryptophanPyrroleRingAtoms = getAtoms(new String[]{"CG", "CD1", "CD2", "NE1", "CE2"}, Arrays.asList(AminoAcidAbbreviations.TRP));
        final List<Atom> tryptophanBenzeneRingAtoms = getAtoms(new String[]{"CD2", "CE2", "CE3", "CZ2", "CZ3", "CH2"}, Arrays.asList(AminoAcidAbbreviations.TRP));

        final int pyrroleRingLen = 5;
        final int benzeneRingLen = 6;

        final List<List<Atom>> phes = Lists.partition(phenylalanineAtoms, benzeneRingLen);
        final List<List<Atom>> tyrs = Lists.partition(tyrosineAtoms, benzeneRingLen);
        final List<List<Atom>> phesAndTyrs = Stream.concat(phes.stream(), tyrs.stream()).collect(Collectors.toList());

        final List<List<Atom>> tryPyrroles = Lists.partition(tryptophanPyrroleRingAtoms, pyrroleRingLen);
        final List<List<Atom>> tryBenzenes = Lists.partition(tryptophanBenzeneRingAtoms, benzeneRingLen);

        ArrayList <AromaticRing> aromaticRings = new ArrayList<>();

        phesAndTyrs.forEach(aromaticRingAtoms -> aromaticRings.add(new AromaticRing(aromaticRingAtoms)));
        tryPyrroles.forEach(aromaticRingAtoms -> aromaticRings.add(new AromaticRing(aromaticRingAtoms)));
        tryBenzenes.forEach(aromaticRingAtoms -> aromaticRings.add(new AromaticRing(aromaticRingAtoms)));

        return aromaticRings;
    }

}

package io.github.WeronikaJargielo.protein_interaction_finder;

import org.biojava.nbio.structure.Group;

import java.util.Objects;

/**
 * Class representing single amino acid in protein.
 * It contains its abbreviation, chain ID and position in that chain.
 */
public final class AminoAcid {
    private final AminoAcidAbbreviations abbreviation;
    private final String chainId;
    private final int position;

    /**
     * Instantiates new Amino acid.
     *
     * @param abbreviation Abbreviation of amino acid name.
     * @param chainId      Protein chain ID.
     * @param position     Position in given chain.
     */
    public AminoAcid(AminoAcidAbbreviations abbreviation, String chainId, int position) {
        this.position = position;
        this.abbreviation = abbreviation;
        this.chainId = chainId;
    }

    /**
     * Instantiates new Amino acid.
     *
     * @param aminoAcidGroup Group which can be obtained from BioJava's Atom.
     */
    public AminoAcid(Group aminoAcidGroup) {
        this.position = aminoAcidGroup.getResidueNumber().getSeqNum();
        this.abbreviation = AminoAcidAbbreviations.valueOf(aminoAcidGroup.getPDBName());
        this.chainId = aminoAcidGroup.getChain().getName();
    }

    /**
     * Returns amino acid abbreviation.
     *
     * @return Amino acid abbreviation.
     */
    public AminoAcidAbbreviations getAbbreviation() {
        return abbreviation;
    }

    /**
     * Returns chain ID of amino acid.
     *
     * @return Chain ID of amino acid.
     */
    public String getChainId() {
        return chainId;
    }

    /**
     * Returns amino acid position in protein chain.
     *
     * @return Amino acid position in protein chain.
     */
    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AminoAcid aminoAcid = (AminoAcid) o;
        return position == aminoAcid.position &&
                abbreviation == aminoAcid.abbreviation &&
                chainId.equals(aminoAcid.chainId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, abbreviation, chainId);
    }

    @Override
    public String toString() {
        return  "" + position + '\t' + abbreviation + '\t' + chainId;
    }


}

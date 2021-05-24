package org.polsl.protein_interaction_finder;

import org.biojava.nbio.structure.Group;

import java.util.Objects;

public final class AminoAcid {
    private final int position;
    private final AminoAcidAbbreviations abbreviation;
    private final String chain;

    public AminoAcid(int position, AminoAcidAbbreviations abbreviation, String chain) {
        this.position = position;
        this.abbreviation = abbreviation;
        this.chain = chain;
    }

    public AminoAcid(Group aminoAcidGroup) {
        this.position = aminoAcidGroup.getResidueNumber().getSeqNum();
        this.abbreviation = AminoAcidAbbreviations.valueOf(aminoAcidGroup.getPDBName());
        this.chain = aminoAcidGroup.getChain().getName();
    }

    public int getPosition() {
        return position;
    }

    public AminoAcidAbbreviations getAbbreviation() {
        return abbreviation;
    }

    public String getChain() {
        return chain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AminoAcid aminoAcid = (AminoAcid) o;
        return position == aminoAcid.position &&
                abbreviation == aminoAcid.abbreviation &&
                chain.equals(aminoAcid.chain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, abbreviation, chain);
    }

    @Override
    public String toString() {
        return  "" + position + '\t' + abbreviation + '\t' + chain;
    }


}

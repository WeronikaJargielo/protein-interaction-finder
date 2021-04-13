package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Group;

import java.util.Objects;

public class AminoAcid {
    private int position;
    private AminoAcidAbbreviations abbreviation;
    private String chain;

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

    public void setPosition(int position) {
        this.position = position;
    }

    public AminoAcidAbbreviations getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(AminoAcidAbbreviations abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
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

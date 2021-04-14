package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;
import org.biojava.nbio.structure.Group;

import java.util.List;


public class AromaticRing {
    private final Atom[] atoms;
    private final Group aminoAcid;


    public AromaticRing(List<Atom> atoms) {
        this.atoms = new Atom[atoms.size()];
        atoms.toArray(this.atoms);

        this.aminoAcid = this.atoms[0].getGroup();
    }

    public Atom[] getAtoms() {
        return atoms;
    }

    public Group getAminoAcid() {
        return aminoAcid;
    }

    public Atom calculateCentroid() {
        return Calc.getCentroid(atoms);
    }
}

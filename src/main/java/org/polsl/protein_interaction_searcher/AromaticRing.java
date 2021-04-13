package org.polsl.protein_interaction_searcher;

import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;

import java.util.List;


public class AromaticRing {
    private Atom[] atoms;

    public AromaticRing(List<Atom> atoms) {
        this.atoms = new Atom[atoms.size()];
        atoms.toArray(this.atoms);
    }

    public Atom calculateCentroid() {
        return Calc.getCentroid(atoms);
    }
}

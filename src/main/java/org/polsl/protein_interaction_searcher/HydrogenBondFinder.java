package org.polsl.protein_interaction_searcher;

import com.google.common.collect.Lists;
import org.biojava.nbio.structure.Atom;
import org.biojava.nbio.structure.Calc;

import java.util.*;
import java.util.function.Function;

public final class HydrogenBondFinder {

    // Inner class representing donors and acceptors which create hydrogen bond.
    final private class BondParticipant {
        public final String[] atoms;
        public final AminoAcidAbbreviations aminoAcid;
        public final Function<BondParticipant, List<Atom>> bondParticipantFilter;
        public final Boolean specialTreatment;

        public BondParticipant(String[] atoms, AminoAcidAbbreviations aminoAcid) {
            this.atoms = atoms;
            this.aminoAcid = aminoAcid;
            this.bondParticipantFilter = null;
            this.specialTreatment = false;
        }

        public BondParticipant(String[] atoms, AminoAcidAbbreviations aminoAcid, Function<BondParticipant, List<Atom>> bondParticipantFilter) {
            this.atoms = atoms;
            this.aminoAcid = aminoAcid;
            this.bondParticipantFilter = bondParticipantFilter;
            this.specialTreatment = true;
        }

    }

    final private int bondParticipantSize = 2;
    private PdbStructureParser pdbStructureParser;

    public HydrogenBondFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

//  Filter is essential due to the fact that the histidine is particularly difficult to examine. There are two scenarios:
//  1. On uncharged HIS the H atom may be located on either HIS ND1 or HIS NE2, so in this situation one nitrogen may only donate a proton,
//     in a hydrogen bond while the other nitrogen can only accept one.
//  2. Additionally, HIS could be charged with a hydrogen on each nitrogen, allowing both only to donate.
//  The important message here is that A single histidine side-chain nitrogen cannot simultaneously accept and donate a hydrogen bond.
    final private Function<BondParticipant, List<Atom>> acceptorHistidineFilter = (bondParticipant -> {
        List<Atom> histidineAcceptorsAtoms = new ArrayList<>();
        final String[] desiredAtomsPdbNames = bondParticipant.atoms;
        final List<AminoAcidAbbreviations> desiredAbbreviations = Arrays.asList(bondParticipant.aminoAcid);

        final List<Atom> foundNandCsAtoms = pdbStructureParser.getAtoms(desiredAtomsPdbNames, desiredAbbreviations);
        if (foundNandCsAtoms.isEmpty()) { return histidineAcceptorsAtoms; }

//      Assuming list in order: N, C, C.
        final List<List<Atom>> NCCTriplets = Lists.partition(foundNandCsAtoms, desiredAtomsPdbNames.length);

        final int indN = 0;
        String HPdbName = desiredAtomsPdbNames[indN];
        HPdbName.replace("N", "H");

        final List<Atom> foundHs = pdbStructureParser.getAtoms(new String[] {HPdbName}, desiredAbbreviations);

        NCCTriplets.forEach(triplet -> {
            final Integer tripletSeqNum = triplet.get(indN).getGroup().getResidueNumber().getSeqNum();
            final String tripletChain = triplet.get(indN).getGroup().getChain().getName();

            final Optional<Atom> matchingH = foundHs.stream()
                                                    .filter(H -> tripletSeqNum.equals(H.getGroup().getResidueNumber().getSeqNum())
                                                                 && tripletChain.equals(H.getGroup().getChain().getName()))
                                                    .findAny();

            if (matchingH.isEmpty()) {
                histidineAcceptorsAtoms.addAll(this.getHistidineAcceptorsPairs(foundNandCsAtoms));
            }
        });

        return histidineAcceptorsAtoms;
    });

    private List<Atom> getHistidineAcceptorsPairs(List<Atom> atoms) {
//      Assuming list in order: N, C, C.
        final int indN = 0;
        List<Atom> histidineAcceptorsPairs = new ArrayList<>();

        for (int indC = indN + 1; indC < atoms.size(); ++indC){
            histidineAcceptorsPairs.add(atoms.get(indN));
            histidineAcceptorsPairs.add(atoms.get(indC));
        }
        return histidineAcceptorsPairs;
    }

    final private List<BondParticipant> donorsDesiredAtomsMainChain = Arrays.asList(  new BondParticipant(new String[]{"N", "H"}, null),
//                                                                                    For amino acids at the N-terminus of protein's chain.
                                                                                      new BondParticipant(new String[]{"N", "H1"}, null),
                                                                                      new BondParticipant(new String[]{"N", "H2"}, null),
                                                                                      new BondParticipant(new String[]{"N", "H3"}, null));

    final private List<BondParticipant> acceptorsDesiredAtomsMainChain = Arrays.asList(new BondParticipant(new String[]{"C", "O"}, null),
//                                                                                     For amino acids at the C-terminus of protein's chain.
                                                                                       new BondParticipant(new String[]{"C", "OXT"}, null));


//                                                                                  new BondParticipant(new String[]{donor, hydrogen}, abbreviation of amino acid)
    final private List<BondParticipant> donorsDesiredAtomsSideChain = Arrays.asList(new BondParticipant(new String[]{"NE", "HE"}, AminoAcidAbbreviations.ARG),
                                                                                    new BondParticipant(new String[]{"NH1", "HH11"}, AminoAcidAbbreviations.ARG),
                                                                                    new BondParticipant(new String[]{"NH1", "HH12"}, AminoAcidAbbreviations.ARG),
                                                                                    new BondParticipant(new String[]{"NH2", "HH21"}, AminoAcidAbbreviations.ARG),
                                                                                    new BondParticipant(new String[]{"NH2", "HH22"}, AminoAcidAbbreviations.ARG),

                                                                                    new BondParticipant(new String[]{"ND2", "HD21"}, AminoAcidAbbreviations.ASN),
                                                                                    new BondParticipant(new String[]{"ND2", "HD22"}, AminoAcidAbbreviations.ASN),

                                                                                    new BondParticipant(new String[]{"SG", "HG"}, AminoAcidAbbreviations.CYS),

                                                                                    new BondParticipant(new String[]{"NE2", "HE21"}, AminoAcidAbbreviations.GLN),
                                                                                    new BondParticipant(new String[]{"NE2", "HE22"}, AminoAcidAbbreviations.GLN),

                                                                                    new BondParticipant(new String[]{"NE2", "HE2"}, AminoAcidAbbreviations.HIS),
                                                                                    new BondParticipant(new String[]{"ND1", "HD1"}, AminoAcidAbbreviations.HIS),

                                                                                    new BondParticipant(new String[]{"NZ", "HZ1"}, AminoAcidAbbreviations.LYS),
                                                                                    new BondParticipant(new String[]{"NZ", "HZ2"}, AminoAcidAbbreviations.LYS),
                                                                                    new BondParticipant(new String[]{"NZ", "HZ3"}, AminoAcidAbbreviations.LYS),

                                                                                    new BondParticipant(new String[]{"OG", "HG"}, AminoAcidAbbreviations.SER),

                                                                                    new BondParticipant(new String[]{"OG1", "HG1"}, AminoAcidAbbreviations.THR),

                                                                                    new BondParticipant(new String[]{"NE1", "HE1"}, AminoAcidAbbreviations.TRP),

                                                                                    new BondParticipant(new String[]{"OH", "HH"}, AminoAcidAbbreviations.TYR));



//                                                                                     new BondParticipant(new String[]{acceptor, acceptor antecedent}, abbreviation of amino acid)
    final private List<BondParticipant> acceptorsDesiredAtomsSideChain = Arrays.asList(new BondParticipant(new String[]{"OD1", "CG"}, AminoAcidAbbreviations.ASN),

                                                                                       new BondParticipant(new String[]{"OD1", "CG"}, AminoAcidAbbreviations.ASP),
                                                                                       new BondParticipant(new String[]{"OD2", "CG"}, AminoAcidAbbreviations.ASP),

                                                                                       new BondParticipant(new String[]{"SG", "CB"}, AminoAcidAbbreviations.CYS),

                                                                                       new BondParticipant(new String[]{"OE1", "CD"}, AminoAcidAbbreviations.GLN),

                                                                                       new BondParticipant(new String[]{"OE1", "CD"}, AminoAcidAbbreviations.GLU),
                                                                                       new BondParticipant(new String[]{"OE2", "CD"}, AminoAcidAbbreviations.GLU),

                                                                                       // TODO
                                                                                       new BondParticipant(new String[]{"ND1", "CG", "CE1"}, AminoAcidAbbreviations.HIS, acceptorHistidineFilter),
//                                                                                       new BondParticipant(new String[]{"ND1", "CG"}, AminoAcidAbbreviations.HIS, acceptorHistidineFilter),
                                                                                       new BondParticipant(new String[]{"NE2", "CD2", "CE1"}, AminoAcidAbbreviations.HIS, acceptorHistidineFilter),
//                                                                                       new BondParticipant(new String[]{"NE2", "CD2"}, AminoAcidAbbreviations.HIS, acceptorHistidineFilter),

                                                                                       new BondParticipant(new String[]{"SD", "CG"}, AminoAcidAbbreviations.MET),

                                                                                       new BondParticipant(new String[]{"OG", "CB"}, AminoAcidAbbreviations.SER),

                                                                                       new BondParticipant(new String[]{"OG1", "CB"}, AminoAcidAbbreviations.THR),

                                                                                       new BondParticipant(new String[]{"OH", "CZ"}, AminoAcidAbbreviations.TYR));



    private List<List<Atom>> getDonorsMainChain() {
        List<Atom> donorsMainChain = new ArrayList<>();
        donorsDesiredAtomsMainChain.forEach(bondParticipant -> donorsMainChain.addAll(pdbStructureParser.getAtoms(bondParticipant.atoms)));

        return Lists.partition(donorsMainChain, bondParticipantSize);
    }

    private List<List<Atom>> getAcceptorsMainChain() {
        List<Atom> acceptorsMainChain = new ArrayList<>();
        acceptorsDesiredAtomsMainChain.forEach(bondParticipant -> acceptorsMainChain.addAll(pdbStructureParser.getAtoms(bondParticipant.atoms)));

        return Lists.partition(acceptorsMainChain, bondParticipantSize);
    }

    private List<List<Atom>> getDonorsSideChain() {
        List<Atom> donorsSideChain = new ArrayList<>();
        donorsDesiredAtomsSideChain.forEach(bondParticipant -> donorsSideChain.addAll(pdbStructureParser.getAtoms(bondParticipant.atoms, Arrays.asList(bondParticipant.aminoAcid))));

        return Lists.partition(donorsSideChain, bondParticipantSize);
    }

    private List<List<Atom>> getAcceptorsSideChain() {
        List<Atom> acceptorsSideChain = new ArrayList<>();
        acceptorsDesiredAtomsSideChain.forEach(bondParticipant -> {
            if (bondParticipant.specialTreatment) {
                acceptorsSideChain.addAll(bondParticipant.bondParticipantFilter.apply(bondParticipant));
            } else {
                acceptorsSideChain.addAll(pdbStructureParser.getAtoms(bondParticipant.atoms, Arrays.asList(bondParticipant.aminoAcid)));
            }
        });

        return Lists.partition(acceptorsSideChain, bondParticipantSize);
    }

    private HydrogenBond obtainHydrogenBond(List<Atom> donor, List<Atom> acceptor) {
        final int indA = 0, indD = 0;
        final int indAa = 1, indH = 1;

        // TODO check if acceptor != donor
        // TODO add relaxed and strict versino of rules
        final double distHA = Calc.getDistance(donor.get(indH), acceptor.get(indA));
        final double distDA = Calc.getDistance(donor.get(indD), acceptor.get(indA));

        final double angleDHA = MathHelper.angle(donor.get(indD), donor.get(indH), acceptor.get(indA));
        final double angleHAAa = MathHelper.angle(donor.get(indH), acceptor.get(indA), acceptor.get(indAa));
        final double angleDAAa = MathHelper.angle(donor.get(indD), acceptor.get(indA), acceptor.get(indAa));

//        if (!(distDA < 3.9 && distHA < 2.5 && angleHAAa > 90 && angleDAAa > 90 && angleDHA > 90)) { // my criterions
        if (!(distDA < 3.9 && distHA < 2.5 && angleDHA > 90)) { // my criterions
////        if (!(distDA <= 3.5)) { // PIC criterion
            return null;
        }

        return new HydrogenBond(new AminoAcid(donor.get(indD).getGroup()), new AminoAcid(acceptor.get(indA).getGroup()),
                                donor.get(indD), donor.get(indH), acceptor.get(indA), acceptor.get(indAa),
                                distHA, distDA, angleDHA, angleHAAa, angleDAAa);
    }


    private List<HydrogenBond> findHydrogenBonds(List<List<Atom>> donors, List<List<Atom>> acceptors) {
        List<HydrogenBond> foundHydrogenBonds = new ArrayList<>();

        donors.forEach(donor -> {
            acceptors.forEach(acceptor -> {
                final HydrogenBond hydrogenBond = this.obtainHydrogenBond(donor, acceptor);

                if (hydrogenBond != null) {
                    foundHydrogenBonds.add(hydrogenBond);
                }
            });
        });
        return foundHydrogenBonds;
    }

    public List<HydrogenBond> findMainMainHydrogenBonds() {
       final List<List<Atom>> donors = this.getDonorsMainChain();
       final List<List<Atom>> acceptors = this.getAcceptorsMainChain();

        return this.findHydrogenBonds(donors, acceptors);
    }

    public List<HydrogenBond> findMainSideHydrogenBonds() {
        List<List<Atom>> donorsMainChain = this.getDonorsMainChain();
        List<List<Atom>> donorsSideChain = this.getDonorsSideChain();

        List<List<Atom>> acceptorsMainChain = this.getAcceptorsMainChain();
        List<List<Atom>> acceptorsSideChain = this.getAcceptorsSideChain();

        List<HydrogenBond> foundMainSideHydrogenBonds = this.findHydrogenBonds(donorsMainChain, acceptorsSideChain);
        foundMainSideHydrogenBonds.addAll(this.findHydrogenBonds(donorsSideChain, acceptorsMainChain));

        return foundMainSideHydrogenBonds;
    }

    public List<HydrogenBond> findSideSideHydrogenBonds() {
        final List<List<Atom>> donors = this.getDonorsSideChain();
        final List<List<Atom>> acceptors = this.getAcceptorsSideChain();

        return this.findHydrogenBonds(donors, acceptors);
    }

}

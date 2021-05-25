package org.polsl.protein_interaction_finder;

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
        public final  Boolean specialTreatment;

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

    private final int bondParticipantSize = 2;
    private PdbStructureParser pdbStructureParser;

    public HydrogenBondFinder(PdbStructureParser pdbStructureParser) {
        this.pdbStructureParser = pdbStructureParser;
    }

//  Filter is essential due to the fact that the histidine is particularly difficult to examine. There are two scenarios:
//  1. On uncharged HIS the H atom may be located on either HIS ND1 or HIS NE2, so in this situation one nitrogen may only donate a proton,
//     in a hydrogen bond while the other nitrogen can only accept one.
//  2. Additionally, HIS could be charged with a hydrogen on each nitrogen, allowing both only to donate.
//  The important message here is that a single histidine side-chain nitrogen cannot simultaneously accept and donate a hydrogen bond.
    private final Function<BondParticipant, List<Atom>> acceptorHistidineFilter = (bondParticipant -> {
        List<Atom> histidineAcceptorsAtoms = new ArrayList<>();
        final String[] desiredAtomsPdbNames = bondParticipant.atoms;
        final List<AminoAcidAbbreviations> desiredAbbreviations = Arrays.asList(bondParticipant.aminoAcid);

        final List<Atom> foundAtoms = pdbStructureParser.getAtoms(desiredAtomsPdbNames, desiredAbbreviations);
        if (foundAtoms.isEmpty()) { return histidineAcceptorsAtoms; }

        final List<List<Atom>> dublets = Lists.partition(foundAtoms, desiredAtomsPdbNames.length);

        final int indN = (foundAtoms.get(0).getName().charAt(0) == ('N')) ? 0 : 1;
        String HPdbName = desiredAtomsPdbNames[indN];
        HPdbName.replace("N", "H");

        final List<Atom> foundHs = pdbStructureParser.getAtoms(new String[] {HPdbName}, desiredAbbreviations);

        dublets.forEach(dublet -> {
//          TODO: Sanity check - remove after development.
            if (dublet.get(indN).getName().charAt(0) != ('N')) {
                System.exit(-1);
            }

            final Integer dubletSeqNum = dublet.get(indN).getGroup().getResidueNumber().getSeqNum();
            final String dubletChain = dublet.get(indN).getGroup().getChain().getName();

            final Optional<Atom> matchingH = foundHs.stream()
                                                    .filter(H -> dubletSeqNum.equals(H.getGroup().getResidueNumber().getSeqNum())
                                                                 && dubletChain.equals(H.getGroup().getChain().getName()))
                                                    .findAny();

            if (matchingH.isEmpty()) {
                histidineAcceptorsAtoms.addAll(foundAtoms);
            }
        });

        return histidineAcceptorsAtoms;
    });


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

                                                                                       new BondParticipant(new String[]{"ND1", "CG"}, AminoAcidAbbreviations.HIS, acceptorHistidineFilter),
                                                                                       new BondParticipant(new String[]{"NE2", "CD2"}, AminoAcidAbbreviations.HIS, acceptorHistidineFilter),

                                                                                       new BondParticipant(new String[]{"SD", "CG"}, AminoAcidAbbreviations.MET),

                                                                                       new BondParticipant(new String[]{"OG", "CB"}, AminoAcidAbbreviations.SER),

                                                                                       new BondParticipant(new String[]{"OG1", "CB"}, AminoAcidAbbreviations.THR),

                                                                                       new BondParticipant(new String[]{"OH", "CZ"}, AminoAcidAbbreviations.TYR));



    public List<HydrogenBond> findMainMainHydrogenBonds(HydrogenBondCriteria criteria) {
        final List<List<Atom>> donors = this.getDonorsMainChain();
        final List<List<Atom>> acceptors = this.getAcceptorsMainChain();

        return this.findHydrogenBonds(donors, acceptors, criteria);
    }

    public List<HydrogenBond> findMainSideHydrogenBonds(HydrogenBondCriteria criteria) {
        final List<List<Atom>> donorsMainChain = this.getDonorsMainChain();
        final List<List<Atom>> donorsSideChain = this.getDonorsSideChain();

        final List<List<Atom>> acceptorsMainChain = this.getAcceptorsMainChain();
        final List<List<Atom>> acceptorsSideChain = this.getAcceptorsSideChain();

        List<HydrogenBond> foundMainSideHydrogenBonds = this.findHydrogenBonds(donorsMainChain, acceptorsSideChain, criteria);
        foundMainSideHydrogenBonds.addAll(this.findHydrogenBonds(donorsSideChain, acceptorsMainChain, criteria));

        return foundMainSideHydrogenBonds;
    }

    public List<HydrogenBond> findSideSideHydrogenBonds(HydrogenBondCriteria criteria) {
        final List<List<Atom>> donors = this.getDonorsSideChain();
        final List<List<Atom>> acceptors = this.getAcceptorsSideChain();

        return this.findHydrogenBonds(donors, acceptors, criteria);
    }

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

    private HydrogenBond obtainHydrogenBond(List<Atom> donor, List<Atom> acceptor, HydrogenBondCriteria criteria) {
        final int indD = (donor.get(0).getName().charAt(0) == ('H')) ? 1 : 0;
        final int indH = (donor.get(0).getName().charAt(0) == ('H')) ? 0 : 1;

        final int indA = (acceptor.get(0).getName().charAt(0) == ('C')) ? 1 : 0;
        final int indAa = (acceptor.get(0).getName().charAt(0) == ('C')) ? 0 : 1;

//      Checking if donor atom is not equal to acceptor atom, to eliminate cases when one atom can be donor and acceptor at the same time.
        if ( (donor.get(indD).getPDBserial() == acceptor.get(indA).getPDBserial())
              && (donor.get(indD).getGroup().equals(acceptor.get(indA).getGroup())) ) {
            return null;
        }

        final double distanceDA = Calc.getDistance(donor.get(indD), acceptor.get(indA));
        if ( ! (distanceDA > criteria.getMinDistanceDA() && distanceDA < criteria.getMaxDistanceDA())) {
            return null;
        }

        final double distanceHA = Calc.getDistance(donor.get(indH), acceptor.get(indA));
        if ( ! (distanceHA > criteria.getMinDistanceHA() && distanceHA < criteria.getMaxDistanceHA()) ) {
            return null;
        }

        final double angleDHA = MathHelper.angle(donor.get(indD), donor.get(indH), acceptor.get(indA));
        if ( ! (angleDHA > criteria.getMinAngleDHA() && angleDHA < criteria.getMaxAngleDHA()) ) {
            return null;
        }

        final double angleHAAa = MathHelper.angle(donor.get(indH), acceptor.get(indA), acceptor.get(indAa));
        if ( ! (angleHAAa > criteria.getMinAngleHAAa() && angleHAAa < criteria.getMaxAngleHAAa()) ) {
            return null;
        }

        final double angleDAAa = MathHelper.angle(donor.get(indD), acceptor.get(indA), acceptor.get(indAa));
        if ( ! (angleDAAa > criteria.getMinAngleDAAa() && angleDAAa < criteria.getMaxAngleDAAa()) ) {
            return null;
        }

        return new HydrogenBond(new AminoAcid(donor.get(indD).getGroup()), new AminoAcid(acceptor.get(indA).getGroup()),
                                donor.get(indD), donor.get(indH), acceptor.get(indA), acceptor.get(indAa),
                                distanceHA, distanceDA, angleDHA, angleHAAa, angleDAAa);
    }

    private List<HydrogenBond> findHydrogenBonds(List<List<Atom>> donors, List<List<Atom>> acceptors, HydrogenBondCriteria criteria) {
        List<HydrogenBond> foundHydrogenBonds = new ArrayList<>();

        donors.forEach(donor -> {
            acceptors.forEach(acceptor -> {
                final HydrogenBond hydrogenBond = this.obtainHydrogenBond(donor, acceptor, criteria);

                if (hydrogenBond != null) {
                    foundHydrogenBonds.add(hydrogenBond);
                }
            });
        });
        return foundHydrogenBonds;
    }

}

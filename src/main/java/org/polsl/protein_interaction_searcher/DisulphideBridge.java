package org.polsl.protein_interaction_searcher;

public class DisulphideBridge {
    private AminoAcid firstInteractee;
    private AminoAcid secondInteractee;
    private double distCAs;
    private double distCBs;
    private double distSS;
    private double phiSS;
    private double phi11;
    private double phi21;;

    public DisulphideBridge(AminoAcid firstInteractee, AminoAcid secondInteractee, double distCAs, double distCBs, double distSS, double phiSS, double phi11, double phi21) {
        this.firstInteractee = firstInteractee;
        this.secondInteractee = secondInteractee;
        this.distCAs = distCAs;
        this.distCBs = distCBs;
        this.distSS = distSS;
        this.phiSS = phiSS;
        this.phi11 = phi11;
        this.phi21 = phi21;
    }

    public AminoAcid getFirstInteractee() {
        return firstInteractee;
    }

    public AminoAcid getSecondInteractee() {
        return secondInteractee;
    }

    public double getDistCAs() {
        return distCAs;
    }

    public double getDistCBs() {
        return distCBs;
    }

    public double getDistSS() {
        return distSS;
    }

    public double getPhiSS() {
        return phiSS;
    }

    public double getPhi11() {
        return phi11;
    }

    public double getPhi21() {
        return phi21;
    }

    @Override
    public String toString() {
        return "" + firstInteractee + "\t" + secondInteractee
                  + "\t" + distCAs + "\t" + distCBs + "\t" + distSS
                  + "\t" + phiSS + "\t" + phi11 + "\t" + phi21;
    }
}

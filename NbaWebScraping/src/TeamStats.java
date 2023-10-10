public class TeamStats {
    // Fields for team statistics
    private double GP, PTS, FGM, FGA, FGP, TPM, TPA, TPP, FTM, FTA, FTP, OR, DR, REB, AST, STL, BLK, TO, PF;
    // Field for the team name
    private String team;

    // Constructor to initialize the object with team name and stats
    public TeamStats(String name, double[] stats) {
        team = name;
        GP = stats[0];
        PTS = stats[1];
        FGM = stats[2];
        FGA = stats[3];
        FGP = stats[4];
        TPM = stats[5];
        TPA = stats[6];
        TPP = stats[7];
        FTM = stats[8];
        FTA = stats[9];
        FTP = stats[10];
        OR = stats[11];
        DR = stats[12];
        REB = stats[13];
        AST = stats[14];
        STL = stats[15];
        BLK = stats[16];
        TO = stats[17];
        PF = stats[18];
    }

    // Getter methods for retrieving individual statistics
    public double getGP() {
        return GP;
    }

    public double getPTS() {
        return PTS;
    }

    public double getFGM() {
        return FGM;
    }

    public double getFGA() {
        return FGA;
    }

    public double getFGP() {
        return FGP;
    }

    public double getTPM() {
        return TPM;
    }

    public double getTPA() {
        return TPA;
    }

    public double getTPP() {
        return TPP;
    }

    public double getFTM() {
        return FTM;
    }

    public double getFTA() {
        return FTA;
    }

    public double getFTP() {
        return FTP;
    }

    public double getOR() {
        return OR;
    }

    public double getDR() {
        return DR;
    }

    public double getREB() {
        return REB;
    }

    public double getAST() {
        return AST;
    }

    public double getSTL() {
        return STL;
    }

    public double getBLK() {
        return BLK;
    }

    public double getTO() {
        return TO;
    }

    public double getPF() {
        return PF;
    }

    // Getter method for retrieving the team name
    public String getName() {
        return team;
    }
}

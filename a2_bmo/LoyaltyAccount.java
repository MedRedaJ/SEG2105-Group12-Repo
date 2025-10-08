public class LoyaltyAccount {
    private String rewardsId;
    private int numOfPoints;

    public LoyaltyAccount(String rewardsId) {
        this.rewardsId =rewardsId;
        this.numOfPoints = 0;
    }

    public String getRewardsId() {
        return rewardsId;
    }

    public int getNumOfPoints() {
        return numOfPoints;
    }

    public void addPoints(int pts) {
        numOfPoints = numOfPoints + pts;
    }

    public void deductPoints(int pts) {
        numOfPoints = numOfPoints - pts;
    }
    
}



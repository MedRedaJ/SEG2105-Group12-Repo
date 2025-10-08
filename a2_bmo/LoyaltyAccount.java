public class LoyaltyAccount {
    private String rewardsId;
    private int numOfPoints;

    public LoyaltyAccount(String rewardsId) {
        this.rewardsId =rewardsId;
        this.numOfPoints = 0;
    }

    public String getRewarsdid () {
        return rewardsId;
    }

    public int getnumOfPoints(){
        return numOfPoints;
    }

    public void addPoints(int pts) {
        numOfPoints = numOfPoints + pts;
    }

    public void detuctPoints(int pts) {
        numOfPoints = numOfPoints - pts;
    }
    
}



import java.util.*;
import static java.util.Map.entry;

public class LoyaltySystem {
    private List<LoyaltyAccount> LoyaltyAccounts;
    private Map<String, Integer> REDEEMOPTIONS = Map.ofEntries(entry("Flight", 350), entry("Cinema", 50),
            entry("Clothing", 100));

    public LoyaltySystem(List<LoyaltyAccount> loyaltyAccounts) {
        LoyaltyAccounts = loyaltyAccounts;
    }

    public void addLoyaltyAccount(LoyaltyAccount new_account) {
        LoyaltyAccounts.add(new_account);
    }

    public void createRedemption(LoyaltyAccount accountForReward, String requestedReward) {

        if (REDEEMOPTIONS.containsKey(requestedReward)) {

            if (accountForReward.getnumOfPoints() >= REDEEMOPTIONS.get(requestedReward)) {
                accountForReward.detuctPoints(REDEEMOPTIONS.get(requestedReward));
                System.out.println("Reward Redeemed");
                System.out.println("Current Points: " + accountForReward.getnumOfPoints());
            } else {
                System.out.println("Not enough points!!!");
                System.out.println("Current Points: " + accountForReward.getnumOfPoints());

            }
        } else {
            System.out.println("Reward specified is not offered");
        }

    }

}

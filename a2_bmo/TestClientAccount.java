import java.util.*;

public class TestClientAccount {
    public static void main(String[] args) {
        System.out.println("Starting Client/Account tests...");

        // Create dates
        Calendar cal = Calendar.getInstance();
        cal.set(1990, Calendar.JANUARY, 1);
        Date dobAdult = cal.getTime();
        cal.set(2010, Calendar.MAY, 15);
        Date dobMinor = cal.getTime();

        // Create Clients
        Client guardian = new Client("G001", "Alice Guardian", "123 Main St", "555-1000", dobAdult) {};
        Client adult = new Client("C001", "Bob Adult", "456 Oak Ave", "555-2000", dobAdult) {};
        Minor minor = new Minor("M001", "Charlie Minor", "789 Pine Rd", "555-3000", dobMinor, guardian.getClientId());
        Student student = new Student("S001", "Dana Student", "101 Maple St", "555-4000", dobAdult, "UOttawa", "300411511");

        // Test setters/getters on Client
        adult.setAddress("999 New Address");
        adult.setName("Bob A. Adult");
        adult.setPhoneNumber("555-2222");
        System.out.println("Adult name: " + adult.getName());
        System.out.println("Adult address: " + adult.getAddress());
        System.out.println("Adult phone: " + adult.getPhoneNumber());

        // Create Accounts
        ChequingAccount cheq = new ChequingAccount(adult, "Daily Cheq", "Chequing", null);
        SavingsAccount sav = new SavingsAccount(adult, "Rainy Day", "Savings", 0.02f);
        InvestmentAccount inv = new InvestmentAccount(adult, "LongTerm", "Investment", "Aggressive", 1234.56);
        CreditCard cc = new CreditCard(adult, "Visa", "CreditCard", 500.0f, 0.19f, 700, new Date());
        LoanAccount loan = new LoanAccount(adult, "CarLoan", "Loan", 0.05f, new Date(), new Date());

        // Add accounts to client
        adult.addAccount(cheq);
        adult.addAccount(sav);
        adult.addAccount(inv);
        adult.addAccount(cc);
        adult.addAccount(loan);

        System.out.println("Adult has " + adult.getAccounts().size() + " accounts.");

        // Deposit and withdraw
        cheq.deposit(1000.0f);
        sav.deposit(2000.0f);
        System.out.println("Chequing balance after deposit: " + cheq.getBalance());
        System.out.println("Savings balance after deposit: " + sav.getBalance());

        cheq.withdraw(150.0f);
        System.out.println("Chequing balance after withdrawal: " + cheq.getBalance());

        // Test DebitCard linking and spend/refund
        DebitCard card = new DebitCard(cheq, 500.0f);
        card.addSavingsAccount(sav);
        System.out.println("Debit card number: " + card.getCardNumber());
        System.out.println("Debit card account balance (cheq + sav): " + card.getAccountBalance());

        // Spend using card
        card.spend(50.0f);
        System.out.println("Chequing balance after card spend: " + cheq.getBalance());

        // Refund
        card.refund(20.0f);
        System.out.println("Chequing balance after refund: " + cheq.getBalance());

        // Test specialized getters
        System.out.println("Investment quarterly profits: " + inv.getQuarterlyProfits());
        System.out.println("Savings interest rate: " + sav.getInterestRate());
        System.out.println("Credit card remaining balance: " + cc.getRemainingBalance());

        // Test Minor/Student fields
        System.out.println("Minor guardian id: " + minor.getGuardianId());
        System.out.println("Student school: " + student.getSchoolName());
        System.out.println("Student id: " + student.getStudentId());

    System.out.println("\n--- Loyalty system tests ---");

    // Loyalty account and system
    LoyaltyAccount loyalty = new LoyaltyAccount("RWD-1001");
    List<LoyaltyAccount> list = new ArrayList<>();
    list.add(loyalty);
    LoyaltySystem loyaltySystem = new LoyaltySystem(list);

    System.out.println("Initial loyalty points: " + loyalty.getNumOfPoints());

    // Add points and check
    loyalty.addPoints(120);
    System.out.println("Points after adding 120: " + loyalty.getNumOfPoints());

    // Try redeeming a valid but expensive reward (Flight needs 350)
    System.out.println("Attempting to redeem 'Flight' (350 pts) with only " + loyalty.getNumOfPoints() + " pts:");
    loyaltySystem.createRedemption(loyalty, "Flight");

    // Add more points then redeem a valid cheaper reward
    loyalty.addPoints(300);
    System.out.println("Points after adding 300: " + loyalty.getNumOfPoints());
    System.out.println("Attempting to redeem 'Flight' again:");
    loyaltySystem.createRedemption(loyalty, "Flight");

    // Attempt to redeem an invalid reward
    System.out.println("Attempting to redeem unknown reward 'Spaceship':");
    loyaltySystem.createRedemption(loyalty, "Spaceship");

    System.out.println("Loyalty tests completed.");
    }
}

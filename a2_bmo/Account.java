import java.util.Random;
import java.util.Date;

public abstract class Account{
    private int id;
    private String accountType;
    private float balance;
    private String nickname;
    private Client accountOwner;

    public Account(Client owner, String nickname, String type){
        this.accountOwner = owner;
        this.nickname = nickname;
        this.accountType = type;
        this.id = new Random().nextInt(99999999-10000000+1)+10000000;
    }

    public int getAccountID(){
        return id;
    }
    public String getAccountType(){
        return accountType;
    }
    public float getBalance(){
        return balance;
    }
    public Client getAccountOwner(){
        return accountOwner;
    }
    public String getNickname(){
        return nickname;
    }
    public void deposit(float amount){
        balance += amount;
    }
    public void withdraw(float amount){
        balance -= amount;
    }
}

class InvestmentAccount extends Account{
    private String portfolioType;
    private double quarterlyProfits;

    public InvestmentAccount(Client owner, String nickname, String type, String portfolioType, double quarterlyProfits){
        super(owner, nickname, type);
        this.portfolioType = portfolioType;
        this.quarterlyProfits = quarterlyProfits;
    }

    public String getPortfolioType(){
        return portfolioType;
    }
    public double getQuarterlyProfits(){
        return quarterlyProfits;
    }
}

class SavingsAccount extends Account{
    private float interestRate;

    public SavingsAccount(Client owner, String nickname, String type, float interestRate){
        super(owner, nickname, type);
        this.interestRate = interestRate;
    }

    public float getInterestRate(){
        return interestRate;
    }
}

class ChequingAccount extends Account{
    private DebitCard debitCard;

    public ChequingAccount(Client owner, String nickname, String type, DebitCard debitCard){
        super(owner, nickname, type);
        this.debitCard = debitCard;
    }

    public DebitCard getDebitCard(){
        return debitCard;
    }
}

class CreditCard extends Account{
    private float remainingBalance;
    private float interestRate;
    private int creditScore;
    private Date paymentDueDate;
    public CreditCard(Client owner, String nickname, String type, float remainingBalance, float interestRate, int creditScore, Date paymentDueDate){
        super(owner, nickname, type);
        this.remainingBalance = remainingBalance;
        this.interestRate = interestRate;
        this.creditScore = creditScore;
        this.paymentDueDate = paymentDueDate;
    }
    public float getRemainingBalance(){
        return remainingBalance;
    }
    public float getInterestRate(){
        return interestRate;
    }
    public int getCreditScore(){
        return creditScore;
    }
    public Date getPaymentDueDate(){
        return paymentDueDate;
    }

}

class LoanAccount extends Account{
    private float interestRate;
    private Date payoffDate;
    private Date nextPaymentDate;

    public LoanAccount(Client owner, String nickname, String type, float interestRate, Date payoffDate, Date nextPaymentDate){
        super(owner, nickname, type);
        this.interestRate = interestRate;
        this.payoffDate = payoffDate;
        this.nextPaymentDate = nextPaymentDate;
    }

    public float getInterestRate(){
        return interestRate;
    }
    public Date getPayoffDate(){
        return payoffDate;
    }
    public Date getNextPaymentDate(){
        return nextPaymentDate;
    }
}

class DebitCard{
    private long cardNumber;
    private Date expiryDate;
    private int cvv;
    private String nickname;
    private float dailyLimit;
    private ChequingAccount chequingAccount;
    private SavingsAccount savingsAccount;

    public DebitCard(ChequingAccount chequingAccount, float dailyLimit){
        // Generate a pseudo-random 16-digit card number safely using nextLong
        long part = Math.abs(new Random().nextLong());
        this.cardNumber = 1000000000000000L + (part % 9000000000000000L);
        this.expiryDate = new Date(System.currentTimeMillis() + 3L * 365 * 24 * 60 * 60 * 1000); // 3 years from now
        this.cvv = new Random().nextInt(900) + 100; // Generate a random 3-digit CVV
        this.chequingAccount = chequingAccount;
        this.dailyLimit = dailyLimit;
    }

    public Date getExpiryDate(){
        return expiryDate;
    }

    public int getCvv(){
        return cvv;
    }

    public long getCardNumber(){
        return cardNumber;
    }

    public Account getChequingAccount(){
        return chequingAccount;
    }
    public String getNickname(){
        return nickname;
    }

    public void addSavingsAccount(SavingsAccount account){
        if (this.savingsAccount != null) {
            System.out.println("This debit card is already linked to a savings account.");
            return;
        }
        this.savingsAccount = account;
    }
    public Account getSavingsAccount(){
        return savingsAccount;
    }
    public float getAccountBalance(){
        return chequingAccount.getBalance() + savingsAccount.getBalance();
    }
    public void spend(float amount){
        chequingAccount.withdraw(amount);
    }
    public void refund(float amount){
        chequingAccount.deposit(amount);
    }
}
import java.util.Random;
import java.util.Date;

public abstract class Account{
    private int id;
    private String accountType;
    private float balance;
    private String nickname;
    private Client accountOwner;

    public account(Client owner, String nickname, String type){
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

public class DebitCard{
    private int cardNumber;
    private Date expiryDate;
    private int cvv;
    private String nickname;
    private float dailyLimit;
    private ChequingAccount chequingAccount;
    private SavingsAccount savingsAccount;

    public DebitCard(Date expiryDate, int cvv, ChequingAccount chequingAccount, float dailyLimit){
        this.cardNumber = new Random().nextInt(9999999999999999-1000000000000000+1)+1000000000000000;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.chequingAccount = chequingAccount;
        this.dailyLimit = dailyLimit;
    }

    public Account getChequingAccount(){
        return chequingAccount;
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
    public int getCardNumber(){
        return cardNumber;
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
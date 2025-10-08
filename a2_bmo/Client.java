import java.util.*;

public abstract class Client {
    private String clientId;
    private String name;
    private String address;
    private String phoneNumber;
    private Date dob;
    private List<Account> accounts;

    public Client(String clientId, String name, String address, String phoneNumber, Date dob) {
        this.clientId = clientId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.accounts = new ArrayList<>();
    }

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDateOfBirth() {
        return dob;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getAddress() {
        return address;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}

class Minor extends Client {
    private String guardianId;

    public Minor(String clientId, String name, String address, String phoneNumber, Date dob, String guardianId) {
        super(clientId, name, address, phoneNumber, dob);
        this.guardianId = guardianId;
    }

    public String getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(String new_guardianID) {
        this.guardianId = new_guardianID;
    }

}

class Student extends Client {
    private String schoolName;
    private String studentId;

    public Student(String clientId, String name, String address, String phoneNumber, Date dob, String schoolName,
            String studentId) {
        super(clientId, name, address, phoneNumber, dob);
        this.schoolName = schoolName;
        this.studentId = studentId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    abstract class Adult extends Client {
        private String occupation;

        public Adult(String clientId, String name, String address, String phoneNumber, Date dob, String occupation) {
            super(clientId, name, address, phoneNumber, dob);
            this.occupation = occupation;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

    }

    class IndividualUser extends Adult {

        public IndividualUser(String clientId, String name, String address, String phoneNumber, Date dob,
                String occupation) {
            super(clientId, name, address, phoneNumber, dob, occupation);
        }

    }

    class Investor extends Adult {
        private InvestmentAccount investmentAccount;
        private float averageReturns;

        public Investor(String clientId, String name, String address, String phoneNumber, Date dob, String occupation,
                InvestmentAccount investmentAccount) {
            super(clientId, name, address, phoneNumber, dob, occupation);
            this.investmentAccount = investmentAccount;
        }

        public InvestmentAccount getInvestmentAccount() {
            return investmentAccount;
        }

        public void setAverageReturns(float averageReturns) {
            this.averageReturns = averageReturns;
        }

        public float getAverageReturns() {
            return averageReturns;
        }

        abstract class Business extends Adult {
            private String businessName;
            private List<String> employeeIds;

            public Business(String clientId, String name, String address, String phoneNumber, Date dob,
                    String occupation, String businessName) {
                super(clientId, name, address, phoneNumber, dob, occupation);
                this.businessName = businessName;
            }

            public void setEmployeeIds(List<String> employeeIds) {
                this.employeeIds = employeeIds;
            }

            public void setBusinessName(String businessName) {
                this.businessName = businessName;
            }

            public String getBusinessName() {
                return businessName;
            }

            public List<String> getEmployeeIds() {
                return employeeIds;
            }

            public void addEmployee(String newEmployeeId) {
                this.employeeIds.add(newEmployeeId);
            }

        }

        class SmallBusiness extends Business {

            public SmallBusiness(String clientId, String name, String address, String phoneNumber, Date dob,
                    String occupation, String businessName) {
                super(clientId, name, address, phoneNumber, dob, occupation, businessName);

            }
        }

        class LargeBusiness extends Business {

            public LargeBusiness(String clientId, String name, String address, String phoneNumber, Date dob,
                    String occupation, String businessName) {
                super(clientId, name, address, phoneNumber, dob, occupation, businessName);

            }
        }

    }

}

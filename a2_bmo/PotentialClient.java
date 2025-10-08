import java.util.*;

public class PotentialClient {
    private String name;
    private String address;
    private String phoneNumber;
    private Date dob;
    private String CurrentBank;
    
    public PotentialClient(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public void setCurrentBank(String currentBank) {
        CurrentBank = currentBank;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getDob() {
        return dob;
    }

    public String getCurrentBank() {
        return CurrentBank;
    }
    
}

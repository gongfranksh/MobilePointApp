package personal.wl.mobilepointapp.auth.ldap;

import java.io.Serializable;

public class User implements Serializable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getsAMAccountName() {
        return sAMAccountName;
    }

    public void setsAMAccountName(String sAMAccountName) {
        this.sAMAccountName = sAMAccountName;
    }

    public String getMailNickname() {
        return mailNickname;
    }

    public void setMailNickname(String mailNickname) {
        this.mailNickname = mailNickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSystemaccount() {
        return systemaccount;
    }

    public void setSystemaccount(String systemaccount) {
        this.systemaccount = systemaccount;
    }

    private String name;
    private String displayName;
    private String sAMAccountName;
    private String mailNickname;
    private String title;
    private String company;
    private String Department;
    private String mobile;
    private String mail;
    private String systemaccount;
    private String getUsername;


    private String sex;
    private float balance;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getGetUsername() {
        return getUsername;
    }

    public void setGetUsername(String getUsername) {
        this.getUsername = getUsername;
    }
}

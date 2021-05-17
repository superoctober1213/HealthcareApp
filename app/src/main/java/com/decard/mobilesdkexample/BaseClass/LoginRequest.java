package com.decard.mobilesdkexample.BaseClass;

public class LoginRequest extends BaseRequest {
    private String accountNo;            //用户名
    private String password;
    //密码
    private String OrganizationCode;

    public LoginRequest(String sAccountNo, String sOrganizationCode) {
        this.accountNo = sAccountNo;
        this.OrganizationCode = sOrganizationCode;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrganizationCode() {
        return OrganizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        OrganizationCode = organizationCode;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "accountNo='" + accountNo + '\'' +
                ", OrganizationCode='" + OrganizationCode + '\'' +
                '}';
    }

}


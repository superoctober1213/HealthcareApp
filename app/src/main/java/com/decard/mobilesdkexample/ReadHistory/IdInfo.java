package com.decard.mobilesdkexample.ReadHistory;

public class IdInfo {

    private String name;
    private String gender;
    private String birth;
    private String idNum;
    private String address;

    public IdInfo(String name, String gender, String birth, String idNum, String address) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.idNum = idNum;
        this.address = address;
    }

    public IdInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "IdInfo{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birth='" + birth + '\'' +
                ", idNum='" + idNum + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

package com.decard.mobilesdkexample.ReadHistory;

import com.decard.mobilesdkexample.BaseRequest;

public class GetCustomerInfoRequest extends BaseRequest {

    /// <summary>
    /// 用户Guid
    /// </summary>
    private String CustomerGuid;

    /// <summary>
    /// 用户名称
    /// </summary>
    private String CustomerName;

    /// <summary>
    /// 用户性别
    /// </summary>
    private String CustomerGender;

    /// <summary>
    /// 用户民族
    /// </summary>
    private String CustomerNation;

    /// <summary>
    /// 用户生日
    /// </summary>
    private String CustomerBirthday;

    /// <summary>
    /// 用户地址
    /// </summary>
    private String CustomerAddress;

    /// <summary>
    /// 身份证编码
    /// </summary>
    private String CardIdNo;

    /// <summary>
    /// 发证机构
    /// </summary>
    private String Department;

    /// <summary>
    /// 有效开始时间
    /// </summary>
    private String ExpireStartdate;

    /// <summary>
    /// 有效结束时间
    /// </summary>
    private String ExpireEnddate;

    /// <summary>
    /// 用户联系方式
    /// </summary>
    private String ContactTel;


    /// <summary>
    /// 证件照片
    /// </summary>
    private String PhoteImage;

    /// <summary>
    /// 创建时间
    /// </summary>
    private String CreatedOn;

    /// <summary>
    /// 创建人
    /// </summary>
    private String CreatedBy;

    /// <summary>
    /// 修改时间
    /// </summary>
    private String UpdatedOn;

    /// <summary>
    /// 修改人
    /// </summary>
    private String UpdatedBy;

    /// <summary>
    /// 备注
    /// </summary>
    private String Remark;

    public GetCustomerInfoRequest() {
    }

    public GetCustomerInfoRequest(String customerGuid, String customerName, String customerGender
            , String customerNation, String customerBirthday, String customerAddress,
                                  String cardIdNo, String department, String expireStartdate,
                                  String expireEnddate, String contactTel, String photeImage,
                                  String createdOn, String createdBy, String updatedOn,
                                  String updatedBy, String remark) {
        CustomerGuid = customerGuid;
        CustomerName = customerName;
        CustomerGender = customerGender;
        CustomerNation = customerNation;
        CustomerBirthday = customerBirthday;
        CustomerAddress = customerAddress;
        CardIdNo = cardIdNo;
        Department = department;
        ExpireStartdate = expireStartdate;
        ExpireEnddate = expireEnddate;
        ContactTel = contactTel;
        PhoteImage = photeImage;
        CreatedOn = createdOn;
        CreatedBy = createdBy;
        UpdatedOn = updatedOn;
        UpdatedBy = updatedBy;
        Remark = remark;
    }

    public String getCustomerGuid() {
        return CustomerGuid;
    }

    public void setCustomerGuid(String customerGuid) {
        CustomerGuid = customerGuid;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerGender() {
        return CustomerGender;
    }

    public void setCustomerGender(String customerGender) {
        CustomerGender = customerGender;
    }

    public String getCustomerNation() {
        return CustomerNation;
    }

    public void setCustomerNation(String customerNation) {
        CustomerNation = customerNation;
    }

    public String getCustomerBirthday() {
        return CustomerBirthday;
    }

    public void setCustomerBirthday(String customerBirthday) {
        CustomerBirthday = customerBirthday;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCardIdNo() {
        return CardIdNo;
    }

    public void setCardIdNo(String cardIdNo) {
        CardIdNo = cardIdNo;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getExpireStartdate() {
        return ExpireStartdate;
    }

    public void setExpireStartdate(String expireStartdate) {
        ExpireStartdate = expireStartdate;
    }

    public String getExpireEnddate() {
        return ExpireEnddate;
    }

    public void setExpireEnddate(String expireEnddate) {
        ExpireEnddate = expireEnddate;
    }

    public String getContactTel() {
        return ContactTel;
    }

    public void setContactTel(String contactTel) {
        ContactTel = contactTel;
    }

    public String getPhoteImage() {
        return PhoteImage;
    }

    public void setPhoteImage(String photeImage) {
        PhoteImage = photeImage;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getUpdatedOn() {
        return UpdatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        UpdatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        UpdatedBy = updatedBy;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    @Override
    public String toString() {
        return "GetCustomerInfoRequest{" +
                "CustomerGuid='" + CustomerGuid + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", CustomerGender='" + CustomerGender + '\'' +
                ", CustomerNation='" + CustomerNation + '\'' +
                ", CustomerBirthday='" + CustomerBirthday + '\'' +
                ", CustomerAddress='" + CustomerAddress + '\'' +
                ", CardIdNo='" + CardIdNo + '\'' +
                ", Department='" + Department + '\'' +
                ", ExpireStartdate='" + ExpireStartdate + '\'' +
                ", ExpireEnddate='" + ExpireEnddate + '\'' +
                ", ContactTel='" + ContactTel + '\'' +
                ", PhoteImage='" + PhoteImage + '\'' +
                ", CreatedOn='" + CreatedOn + '\'' +
                ", CreatedBy='" + CreatedBy + '\'' +
                ", UpdatedOn='" + UpdatedOn + '\'' +
                ", UpdatedBy='" + UpdatedBy + '\'' +
                ", Remark='" + Remark + '\'' +
                '}';
    }
}


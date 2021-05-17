package com.decard.mobilesdkexample.BaseClass;

public class CustomerQueryParamRequest extends BaseRequest {

    // <summary>
    /// 客户姓名
    /// </summary>
    private String CustomerName;

    /// <summary>
    /// 客户身份证号
    /// </summary>
    private String CustomerCardIdNo;

    /// <summary>
    /// 客户联系方式
    /// </summary>
    private String ContactTel;

    /// <summary>
    /// 创建时间
    /// </summary>
    private String CreateTime;

    /// <summary>
    /// 其他参数
    /// </summary>
    private String OthrtParam;

    public CustomerQueryParamRequest(String customerName, String customerCardIdNo,
                                     String contactTel, String createTime, String othrtParam) {
        CustomerName = customerName;
        CustomerCardIdNo = customerCardIdNo;
        ContactTel = contactTel;
        CreateTime = createTime;
        OthrtParam = othrtParam;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerCardIdNo() {
        return CustomerCardIdNo;
    }

    public void setCustomerCardIdNo(String customerCardIdNo) {
        CustomerCardIdNo = customerCardIdNo;
    }

    public String getContactTel() {
        return ContactTel;
    }

    public void setContactTel(String contactTel) {
        ContactTel = contactTel;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getOthrtParam() {
        return OthrtParam;
    }

    public void setOthrtParam(String othrtParam) {
        OthrtParam = othrtParam;
    }

    @Override
    public String toString() {
        return "CustomerQueryParamRequest{" +
                "CustomerName='" + CustomerName + '\'' +
                ", CustomerCardIdNo='" + CustomerCardIdNo + '\'' +
                ", ContactTel='" + ContactTel + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", OthrtParam='" + OthrtParam + '\'' +
                '}';
    }
}

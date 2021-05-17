package com.decard.mobilesdkexample.BaseClass;

public class GetCheckItemInfoResponse extends BaseResponse{

    /// <summary>
    /// 自动自增流水
    /// </summary>
    private int Ruid;

    /// <summary>
    /// 客户Guid
    /// </summary>
    private String CustomerGuid;

    /// <summary>
    /// 检测项
    /// </summary>
    private String CheckItem;

    /// <summary>
    /// 检测值
    /// </summary>
    private String CheckValue;

    /// <summary>
    /// 检测日期
    /// </summary>
    private String CheckDate;

    public int getRuid() {
        return Ruid;
    }

    public void setRuid(int ruid) {
        Ruid = ruid;
    }

    public String getCustomerGuid() {
        return CustomerGuid;
    }

    public void setCustomerGuid(String customerGuid) {
        CustomerGuid = customerGuid;
    }

    public String getCheckItem() {
        return CheckItem;
    }

    public void setCheckItem(String checkItem) {
        CheckItem = checkItem;
    }

    public String getCheckValue() {
        return CheckValue;
    }

    public void setCheckValue(String checkValue) {
        CheckValue = checkValue;
    }

    public String getCheckDate() {
        return CheckDate;
    }

    public void setCheckDate(String checkDate) {
        CheckDate = checkDate;
    }

    @Override
    public String toString() {
        return "GetCheckItemInfoResponse{" +
                "Ruid=" + Ruid +
                ", CustomerGuid='" + CustomerGuid + '\'' +
                ", CheckItem='" + CheckItem + '\'' +
                ", CheckValue='" + CheckValue + '\'' +
                ", CheckDate='" + CheckDate + '\'' +
                '}';
    }
}

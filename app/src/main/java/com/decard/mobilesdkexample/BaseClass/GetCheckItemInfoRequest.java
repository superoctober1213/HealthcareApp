package com.decard.mobilesdkexample.BaseClass;

public class GetCheckItemInfoRequest extends BaseRequest {

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

    private EnumCheckItem enumCheckItem;

    private enum EnumCheckItem {
        RHIResult,//反应充血值,
        Temperature,//体温,
        DiastolicPressure,//舒张压,
        SystolicPressure,//收缩压,
        FastingGlucose,//空腹血糖,
        PostprandialGlucose,//餐后血糖,
        HeartRate//心率值,
    }

    public GetCheckItemInfoRequest(int ruid, String customerGuid, EnumCheckItem enumCheckItem,
                                   String checkValue, String checkDate) {
        Ruid = ruid;
        CustomerGuid = customerGuid;
        CheckItem = enumCheckItem.toString();
        CheckValue = checkValue;
        CheckDate = checkDate;
    }

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

    public EnumCheckItem getEnumCheckItem() {
        return enumCheckItem;
    }

    public void setEnumCheckItem(EnumCheckItem enumCheckItem) {
        this.enumCheckItem = enumCheckItem;
    }

    @Override
    public String toString() {
        return "GetCheckItemInfoRequest{" +
                "Ruid=" + Ruid +
                ", CustomerGuid='" + CustomerGuid + '\'' +
                ", CheckItem='" + CheckItem + '\'' +
                ", CheckValue='" + CheckValue + '\'' +
                ", CheckDate='" + CheckDate + '\'' +
                ", enumCheckItem=" + enumCheckItem +
                '}';
    }
}

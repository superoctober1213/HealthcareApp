package com.decard.mobilesdkexample.BaseClass;

public class GetAccountInfoResponse extends BaseResponse {
    /// <summary>
    /// 用户Guid
    /// </summary>
    private String AccountGuid;

    /// <summary>
    /// 账户登录名
    /// </summary>
    private String AccountNO;

    /// <summary>
    /// 账户密码
    /// </summary>
    private String AccountPwd;

    /// <summary>
    /// 密码加盐
    /// </summary>
    private String SignSalt;

    /// <summary>
    /// 账户姓名
    /// </summary>
    private String AccountName;

    /// <summary>
    /// 父节点
    /// </summary>
    private String ParentGuid;

    /// <summary>
    /// 组织机构代码
    /// </summary>
    private String OrganizationCode;

    /// <summary>
    /// 联系电话
    /// </summary>
    private String ContactTel;

    /// <summary>
    /// 账户状态,0表示创建，1表示有效，2表示注销
    /// </summary>
    private String RecStatus;

    /// <summary>
    /// 创建时间
    /// </summary>
    private String CreatedOn;

    /// <summary>
    /// 修改时间
    /// </summary>
    private String UpdatedOn;

    /// <summary>
    /// 备注
    /// </summary>
    private String Remark;

    /// <summary>
    /// 创建人
    /// </summary>
    private String CreatedBy;

    /// <summary>
    /// 修改人ID
    /// </summary>
    private String UpdatedBy;

    /// <summary>
    /// 角色代码
    /// </summary>
    private String RoleCode;

    public String getAccountGuid() {
        return AccountGuid;
    }

    public String getAccountNO() {
        return AccountNO;
    }

    public void setAccountNO(String accountNO) {
        AccountNO = accountNO;
    }

    public String getAccountPwd() {
        return AccountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        AccountPwd = accountPwd;
    }

    public String getSignSalt() {
        return SignSalt;
    }

    public void setSignSalt(String signSalt) {
        SignSalt = signSalt;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getParentGuid() {
        return ParentGuid;
    }

    public void setParentGuid(String parentGuid) {
        ParentGuid = parentGuid;
    }

    public String getOrganizationCode() {
        return OrganizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        OrganizationCode = organizationCode;
    }

    public String getContactTel() {
        return ContactTel;
    }

    public void setContactTel(String contactTel) {
        ContactTel = contactTel;

    }

    public String getRecStatus() {
        return RecStatus;
    }

    public void setRecStatus(String recStatus) {
        RecStatus = recStatus;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public String getUpdatedOn() {
        return UpdatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        UpdatedOn = updatedOn;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        UpdatedBy = updatedBy;
    }

    public String getRoleCode() {
        return RoleCode;
    }

    public void setRoleCode(String roleCode) {
        RoleCode = roleCode;
    }

    public void setAccountGuid(String accountGuid) {
        AccountGuid = accountGuid;
    }


    ///// <summary>
    ///// 权限值数据对象
    ///// </summary>
    //public List<RolePrivilegeMappingInfo> RolePrivilegeMappingInfos
    //        {
    //        get;
    //        set;
    //        }


    @Override
    public String toString() {
        return "GetAccountInfoResponse{" +
                "AccountGuid='" + AccountGuid + '\'' +
                ", AccountNO='" + AccountNO + '\'' +
                ", AccountPwd='" + AccountPwd + '\'' +
                ", SignSalt='" + SignSalt + '\'' +
                ", AccountName='" + AccountName + '\'' +
                ", ParentGuid='" + ParentGuid + '\'' +
                ", OrganizationCode='" + OrganizationCode + '\'' +
                ", ContactTel='" + ContactTel + '\'' +
                ", RecStatus='" + RecStatus + '\'' +
                ", CreatedOn='" + CreatedOn + '\'' +
                ", UpdatedOn='" + UpdatedOn + '\'' +
                ", Remark='" + Remark + '\'' +
                ", CreatedBy='" + CreatedBy + '\'' +
                ", UpdatedBy='" + UpdatedBy + '\'' +
                ", RoleCode='" + RoleCode + '\'' +
                '}';
    }
}

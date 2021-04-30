package com.decard.mobilesdkexample.ReadHistory;

import com.decard.mobilesdkexample.BaseResponse;

public class GetPatientRecordResponse extends BaseResponse {

    /// <summary>
    /// 自动自增流水
    /// </summary>
    private int Record_id;

    /// <summary>
    /// 病人姓名
    /// </summary>
    private String PatientName;

    /// <summary>
    /// 设备ID
    /// </summary>
    private String DeviceID;

    /// <summary>
    /// 患者号
    /// </summary>
    private String PatientNum;

    /// <summary>
    /// 门诊号
    /// </summary>
    private String VisitNum;

    /// <summary>
    /// 性别
    /// </summary>
    private String Sex;

    /// <summary>
    /// 性别来源(0: 设备获取数据 1:微信获取数据)
    /// </summary>
    private int Sex_remark;

    /// <summary>
    /// 出生
    /// </summary>
    private String Birth;

    /// <summary>
    /// 出生来源(0: 设备获取数据 1:微信获取数据)
    /// </summary>
    private int Birth_remark;

    /// <summary>
    /// 身高
    /// </summary>
    private String Height;

    /// <summary>
    /// 身高来源(0: 设备获取数据 1:微信获取数据)
    /// </summary>
    private int Height_remark;

    /// <summary>
    /// 体重
    /// </summary>
    private String Weight;

    /// <summary>
    /// 体重来源(0: 设备获取数据 1:微信获取数据)
    /// </summary>
    private int Weight_remark;

    /// <summary>
    /// 腹围
    /// </summary>
    private String Abdominal;

    /// <summary>
    /// 腹围来源(0: 设备获取数据 1:微信获取数据)
    /// </summary>
    private int Abdominal_remark;

    /// <summary>
    /// 血压来源(0: 设备获取数据 1:微信获取数据)
    /// </summary>
    private int Blood_remark;

    /// <summary>
    /// 舒张压
    /// </summary>
    private String DBP;

    /// <summary>
    /// 收缩压
    /// </summary>
    private String SBP;

    /// <summary>
    /// 空腹血糖
    /// </summary>
    private String FastingGlucose;

    /// <summary>
    /// 空腹血糖来源(0: 设备获取数据 1:微信获取数据)
    /// </summary>
    private int FastingGlucoseRemark;

    /// <summary>
    /// 糖负荷
    /// </summary>
    private String PostprandialGlucose;

    /// <summary>
    /// 糖负荷来源(0: 设备获取数据 1:微信获取数据)
    /// </summary>
    private int PostprandialGlucoseRemark;

    /// <summary>
    /// 低密度
    /// </summary>
    private String LowDensityBlood;

    /// <summary>
    /// 低密度来源(0: 设备获取数据 1:微信获取数据)
    /// </summary>
    private int LowDensityBloodRemark;

    /// <summary>
    /// 总胆固醇
    /// </summary>
    private String TotalCholesterol;

    /// <summary>
    /// 总胆固醇来源(0: 设备获取数据 1:微信获取数据)
    /// </summary>
    private int TotalCholesterolRemark;

    /// <summary>
    /// 吸烟
    /// </summary>
    private String Smoker;

    /// <summary>
    /// 吸烟来源(0: 设备获取数据 1:微信获取数据)
    /// </summary>
    private int Smoker_remark;

    /// <summary>
    /// 间接性跛行
    /// </summary>
    private String Limp;

    /// <summary>
    /// 间接性跛行来源(0: 设备获取数据 1:微信获取数据)
    /// </summary>
    private int Limp_remark;

    /// <summary>
    /// 血管内皮功能指数
    /// </summary>
    private String EFI;

    /// <summary>
    /// 创建时间
    /// </summary>
    private String Create_time;

    /// <summary>
    /// 开始时间
    /// </summary>
    private String StartTime;

    /// <summary>
    /// 结束时间
    /// </summary>
    private String StopTime;

    /// <summary>
    /// 室温
    /// </summary>
    private String RoomTemp;

    /// <summary>
    /// 评分方式
    /// </summary>
    private String GradeMode;

    /// <summary>
    /// 胆固醇
    /// </summary>
    private String Cholesterol;

    /// <summary>
    /// 高密度脂蛋白胆固醇
    /// </summary>
    private String HDLCholesterol;

    /// <summary>
    /// 超敏C反应蛋白
    /// </summary>
    private String HSCRP;

    /// <summary>
    /// 心血管病史 心血管病史“是”“否”
    /// </summary>
    private String Cardiovascular;

    /// <summary>
    /// 家族心血管病史
    /// </summary>
    private boolean IsFCVDHistory;

    /// <summary>
    /// 欧洲风险区
    /// </summary>
    private boolean IsEurRiskRegion;

    /// <summary>
    /// 用户id
    /// </summary>
    private int User_id;

    /// <summary>
    /// 预留字默认为1
    /// </summary>
    private int Create_user;

    /// <summary>
    /// 患者电话
    /// </summary>
    private String PatientPhone;

}

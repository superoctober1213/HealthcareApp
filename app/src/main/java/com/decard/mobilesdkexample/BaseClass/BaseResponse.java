package com.decard.mobilesdkexample.BaseClass;

public class BaseResponse {
    /// <summary>
    /// 流水号
    /// </summary>
    private String sequence;

    /// <summary>
    /// 命令字
    /// </summary>
    private String command;

    /// <summary>
    /// 0=成功，大于0有错误
    /// </summary>
    private String ResultCode;

    /// <summary>
    /// 如果有错误返回错误信息，否则返回空
    /// </summary>
    private String ResultMessage;

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String resultCode) {
        ResultCode = resultCode;
    }

    public String getResultMessage() {
        return ResultMessage;
    }

    public void setResultMessage(String resultMessage) {
        ResultMessage = resultMessage;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "sequence='" + sequence + '\'' +
                ", command='" + command + '\'' +
                ", ResultCode='" + ResultCode + '\'' +
                ", ResultMessage='" + ResultMessage + '\'' +
                '}';
    }
}

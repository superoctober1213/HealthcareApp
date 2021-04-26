package com.decard.mobilesdkexample;

import java.util.UUID;

public class BaseRequest {

    private String sequence;
    private String command;
    private String ParamObj;

    public BaseRequest()
    {
        command = "Login";
        sequence = UUID.randomUUID().toString();
    }

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

    public String getParamObj() {
        return ParamObj;
    }

    public void setParamObj(String paramObj) {
        ParamObj = paramObj;
    }



    @Override
    public String toString() {
        return "BaseRequest{" +
                "sequence='" + sequence + '\'' +
                ", command='" + command + '\'' +
                ", ParamObj='" + ParamObj + '\'' +
                '}';
    }
}

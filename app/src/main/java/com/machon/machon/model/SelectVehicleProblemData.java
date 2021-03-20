package com.machon.machon.model;

public class SelectVehicleProblemData {

    String problemType;
    String problemId;

    public SelectVehicleProblemData(String problemType, String problemId) {
        this.problemType = problemType;
        this.problemId = problemId;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }
}

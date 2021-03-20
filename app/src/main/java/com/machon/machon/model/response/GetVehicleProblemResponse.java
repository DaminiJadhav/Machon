package com.machon.machon.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetVehicleProblemResponse {
    @SerializedName("Status")
    String Status;
    @SerializedName("Message")
    String Message;
    @SerializedName("VehicleType")
    List<GetVehicleProblemResponse.VehicleType> vehicleType;


    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public List<VehicleType> getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(List<VehicleType> vehicleType) {
        this.vehicleType = vehicleType;
    }

    public class VehicleType{
        @SerializedName("tblAssignProblemToUserDetails")
        List<Object> tblAssignProblemToUserHeaders;
        @SerializedName("ProblemId")
        String ProblemId;
        @SerializedName("Peoblem")
        String Peoblem;

        boolean isSelectProblem;

        public List<Object> getTblAssignProblemToUserHeaders() {
            return tblAssignProblemToUserHeaders;
        }

        public void setTblAssignProblemToUserHeaders(List<Object> tblAssignProblemToUserHeaders) {
            this.tblAssignProblemToUserHeaders = tblAssignProblemToUserHeaders;
        }

        public String getProblemId() {
            return ProblemId;
        }

        public void setProblemId(String problemId) {
            ProblemId = problemId;
        }

        public String getPeoblem() {
            return Peoblem;
        }

        public void setPeoblem(String peoblem) {
            Peoblem = peoblem;
        }

        public boolean isSelectProblem() {
            return isSelectProblem;
        }

        public void setSelectProblem(boolean selectProblem) {
            isSelectProblem = selectProblem;
        }
    }

}

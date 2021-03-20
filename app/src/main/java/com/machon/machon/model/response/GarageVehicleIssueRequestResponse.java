    package com.machon.machon.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GarageVehicleIssueRequestResponse {
    @SerializedName("Status")
    String Status;
    @SerializedName("Message")
    String Message;
    @SerializedName("UserGarageIssueRequest")
    List<UserGarageIssueRequest> userGarageIssueRequests;


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

    public List<UserGarageIssueRequest> getUserGarageIssueRequests() {
        return userGarageIssueRequests;
    }

    public void setUserGarageIssueRequests(List<UserGarageIssueRequest> userGarageIssueRequests) {
        this.userGarageIssueRequests = userGarageIssueRequests;
    }

    public class UserGarageIssueRequest{
        @SerializedName("AssignProblemToUserDetailList")
        List<AssignProblemToUserDetailList> userGarageIssueRequests;
        @SerializedName("AssignProblemToUserId")
        String AssignProblemToUserId;
        @SerializedName("UserId")
        String UserId;
        @SerializedName("UserLatitude")
        String UserLatitude;
        @SerializedName("UserLongitude")
        String UserLongitude;
        @SerializedName("CurrentDateTime")
        String CurrentDateTime;
        @SerializedName("UserAddress")
        String UserAddress;
        @SerializedName("VehicleNo")
        String VehicleNo;
        @SerializedName("VehicleTypeId")
        String VehicleTypeId;
        @SerializedName("AspNetUser")
        String AspNetUser;
        @SerializedName("tblVehicleType")
        String tblVehicleType;
        @SerializedName("Other")
        String Other;

        @SerializedName("Description")
        String Description;
        @SerializedName("FirstName")
        String FirstName;
        @SerializedName("LastName")
        String LastName;
        @SerializedName("VehicleType")
        String VehicleType;
        @SerializedName("MobNo")
        String MobNo;
        @SerializedName("ProblemId")
        String ProblemId;
        @SerializedName("Problem")
        String Problem;

        public List<AssignProblemToUserDetailList> getUserGarageIssueRequests() {
            return userGarageIssueRequests;
        }

        public void setUserGarageIssueRequests(List<AssignProblemToUserDetailList> userGarageIssueRequests) {
            this.userGarageIssueRequests = userGarageIssueRequests;
        }

        public String getAssignProblemToUserId() {
            return AssignProblemToUserId;
        }

        public void setAssignProblemToUserId(String assignProblemToUserId) {
            AssignProblemToUserId = assignProblemToUserId;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getUserLatitude() {
            return UserLatitude;
        }

        public void setUserLatitude(String userLatitude) {
            UserLatitude = userLatitude;
        }

        public String getUserLongitude() {
            return UserLongitude;
        }

        public void setUserLongitude(String userLongitude) {
            UserLongitude = userLongitude;
        }

        public String getCurrentDateTime() {
            return CurrentDateTime;
        }

        public void setCurrentDateTime(String currentDateTime) {
            CurrentDateTime = currentDateTime;
        }

        public String getUserAddress() {
            return UserAddress;
        }

        public void setUserAddress(String userAddress) {
            UserAddress = userAddress;
        }

        public String getVehicleNo() {
            return VehicleNo;
        }

        public void setVehicleNo(String vehicleNo) {
            VehicleNo = vehicleNo;
        }

        public String getVehicleTypeId() {
            return VehicleTypeId;
        }

        public void setVehicleTypeId(String vehicleTypeId) {
            VehicleTypeId = vehicleTypeId;
        }

        public String getAspNetUser() {
            return AspNetUser;
        }

        public void setAspNetUser(String aspNetUser) {
            AspNetUser = aspNetUser;
        }

        public String getTblVehicleType() {
            return tblVehicleType;
        }

        public void setTblVehicleType(String tblVehicleType) {
            this.tblVehicleType = tblVehicleType;
        }

        public String getOther() {
            return Other;
        }

        public void setOther(String other) {
            Other = other;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String firstName) {
            FirstName = firstName;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String lastName) {
            LastName = lastName;
        }

        public String getVehicleType() {
            return VehicleType;
        }

        public void setVehicleType(String vehicleType) {
            VehicleType = vehicleType;
        }

        public String getMobNo() {
            return MobNo;
        }

        public void setMobNo(String mobNo) {
            MobNo = mobNo;
        }

        public String getProblemId() {
            return ProblemId;
        }

        public void setProblemId(String problemId) {
            ProblemId = problemId;
        }

        public String getProblem() {
            return Problem;
        }

        public void setProblem(String problem) {
            Problem = problem;
        }
    }



    public class AssignProblemToUserDetailList{
        @SerializedName("tblAssignProblemToUserHeader")
        String tblAssignProblemToUserHeader;
        @SerializedName("tblProblemMaster")
        String tblProblemMaster;
        @SerializedName("AssignProblemToUserDetailId")
        String AssignProblemToUserDetailId;
        @SerializedName("AssignProblemToUserHeaderId")
        String AssignProblemToUserHeaderId;
        @SerializedName("ProblemId")
        String ProblemId;
        @SerializedName("Other")
        String Other;

        public String getTblAssignProblemToUserHeader() {
            return tblAssignProblemToUserHeader;
        }

        public void setTblAssignProblemToUserHeader(String tblAssignProblemToUserHeader) {
            this.tblAssignProblemToUserHeader = tblAssignProblemToUserHeader;
        }

        public String getTblProblemMaster() {
            return tblProblemMaster;
        }

        public void setTblProblemMaster(String tblProblemMaster) {
            this.tblProblemMaster = tblProblemMaster;
        }

        public String getAssignProblemToUserDetailId() {
            return AssignProblemToUserDetailId;
        }

        public void setAssignProblemToUserDetailId(String assignProblemToUserDetailId) {
            AssignProblemToUserDetailId = assignProblemToUserDetailId;
        }

        public String getAssignProblemToUserHeaderId() {
            return AssignProblemToUserHeaderId;
        }

        public void setAssignProblemToUserHeaderId(String assignProblemToUserHeaderId) {
            AssignProblemToUserHeaderId = assignProblemToUserHeaderId;
        }

        public String getProblemId() {
            return ProblemId;
        }

        public void setProblemId(String problemId) {
            ProblemId = problemId;
        }

        public String getOther() {
            return Other;
        }

        public void setOther(String other) {
            Other = other;
        }
    }


}

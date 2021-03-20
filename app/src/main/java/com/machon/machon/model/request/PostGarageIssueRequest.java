package com.machon.machon.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostGarageIssueRequest {
    @SerializedName("UserId")
    String UserId;
    @SerializedName("UserLatitude")
    String UserLatitude;
    @SerializedName("CurrentDateTime")
    String CurrentDateTime;
    @SerializedName("UserLongitude")
    String UserLongitude;
    @SerializedName("UserAddress")
    String UserAddress;
    @SerializedName("VehicleNo")
    String VehicleNo;
    @SerializedName("VehicleTypeId")
    String VehicleTypeId;
    @SerializedName("Description")
    String Description;
    @SerializedName("Other")
    String Other;
    @SerializedName("AssignProblemToUserDetailList")
    List<AssignProblemToUserDetailList> AssignProblemToUserDetailList;

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

    public String getCurrentDateTime() {
        return CurrentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        CurrentDateTime = currentDateTime;
    }

    public String getUserLongitude() {
        return UserLongitude;
    }

    public void setUserLongitude(String userLongitude) {
        UserLongitude = userLongitude;
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


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getOther() {
        return Other;
    }

    public void setOther(String other) {
        Other = other;
    }

    public List<PostGarageIssueRequest.AssignProblemToUserDetailList> getAssignProblemToUserDetailList() {
        return AssignProblemToUserDetailList;
    }

    public void setAssignProblemToUserDetailList(List<PostGarageIssueRequest.AssignProblemToUserDetailList> assignProblemToUserDetailList) {
        AssignProblemToUserDetailList = assignProblemToUserDetailList;
    }

    public static class AssignProblemToUserDetailList{
        @SerializedName("ProblemId")
        String ProblemId;
        @SerializedName("AssignProblemToUserHeaderId")
        String AssignProblemToUserHeaderId;

        public String getProblemId() {
            return ProblemId;
        }

        public void setProblemId(String problemId) {
            ProblemId = problemId;
        }

        public String getAssignProblemToUserHeaderId() {
            return AssignProblemToUserHeaderId;
        }

        public void setAssignProblemToUserHeaderId(String assignProblemToUserHeaderId) {
            AssignProblemToUserHeaderId = assignProblemToUserHeaderId;
        }


    }
}

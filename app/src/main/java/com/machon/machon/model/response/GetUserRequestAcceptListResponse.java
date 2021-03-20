package com.machon.machon.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUserRequestAcceptListResponse {
    @SerializedName("Status")
    String Status;
    @SerializedName("Message")
    String Message;
    @SerializedName("UserGarageIssueRequest")
    List<GetUserRequestAcceptListResponse.UserGarageIssueRequest> userGarageIssueRequests;

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
        @SerializedName("AssignProblemToUserId")
        String AssignProblemToUserId;
        @SerializedName("UserId")
        String UserId;
        @SerializedName("CurrentDateTime")
        String CurrentDateTime;
        @SerializedName("Description")
        String Description;
        @SerializedName("Status")
        boolean Status;
        @SerializedName("AssignProblemToUserDetailId")
        String AssignProblemToUserDetailId;
        @SerializedName("AssignProblemToUserHeaderId")
        String AssignProblemToUserHeaderId;
        @SerializedName("ProblemId")
        String ProblemId;
        @SerializedName("Other")
        String Other;
        @SerializedName("GarageRegistrationId")
        String GarageRegistrationId;
        @SerializedName("GarageName")
        String GarageName;
        @SerializedName("GarageOwnerName")
        String GarageOwnerName;
        @SerializedName("GarageOwnerMobNo")
        String GarageOwnerMobNo;
        @SerializedName("GarageOwnerEmail")
        String GarageOwnerEmail;
        @SerializedName("GarageAddress")
        String GarageAddress;
        @SerializedName("Password")
        String Password;
        @SerializedName("GarageLatitude")
        String GarageLatitude;
        @SerializedName("GarageLongitude")
        String GarageLongitude;

        @SerializedName("NoOfMechanics")
        String NoOfMechanics;
        @SerializedName("HeadMechanicName")
        String HeadMechanicName;
        @SerializedName("HeadMechanicMobNo")
        String HeadMechanicMobNo;
        @SerializedName("BankName")
        String BankName;
        @SerializedName("AccountNo")
        String AccountNo;
        @SerializedName("IFSCCode")
        String IFSCCode;
        @SerializedName("UPIPaymentId")
        String UPIPaymentId;
        @SerializedName("UPIPaymentNo")
        String UPIPaymentNo;
        @SerializedName("IsActive")
        String IsActive;
        @SerializedName("RequestId")
        String RequestId;
        @SerializedName("AssignProblemToUserDetailList")
        List<AssignProblemToUserDetailList> userGarageIssueRequests;

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

        public String getCurrentDateTime() {
            return CurrentDateTime;
        }

        public void setCurrentDateTime(String currentDateTime) {
            CurrentDateTime = currentDateTime;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public boolean isStatus() {
            return Status;
        }

        public void setStatus(boolean status) {
            Status = status;
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

        public String getGarageRegistrationId() {
            return GarageRegistrationId;
        }

        public void setGarageRegistrationId(String garageRegistrationId) {
            GarageRegistrationId = garageRegistrationId;
        }

        public String getGarageName() {
            return GarageName;
        }

        public void setGarageName(String garageName) {
            GarageName = garageName;
        }

        public String getGarageOwnerName() {
            return GarageOwnerName;
        }

        public void setGarageOwnerName(String garageOwnerName) {
            GarageOwnerName = garageOwnerName;
        }

        public String getGarageOwnerMobNo() {
            return GarageOwnerMobNo;
        }

        public void setGarageOwnerMobNo(String garageOwnerMobNo) {
            GarageOwnerMobNo = garageOwnerMobNo;
        }

        public String getGarageOwnerEmail() {
            return GarageOwnerEmail;
        }

        public void setGarageOwnerEmail(String garageOwnerEmail) {
            GarageOwnerEmail = garageOwnerEmail;
        }

        public String getGarageAddress() {
            return GarageAddress;
        }

        public void setGarageAddress(String garageAddress) {
            GarageAddress = garageAddress;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String password) {
            Password = password;
        }

        public String getGarageLatitude() {
            return GarageLatitude;
        }

        public void setGarageLatitude(String garageLatitude) {
            GarageLatitude = garageLatitude;
        }

        public String getGarageLongitude() {
            return GarageLongitude;
        }

        public void setGarageLongitude(String garageLongitude) {
            GarageLongitude = garageLongitude;
        }

        public String getNoOfMechanics() {
            return NoOfMechanics;
        }

        public void setNoOfMechanics(String noOfMechanics) {
            NoOfMechanics = noOfMechanics;
        }

        public String getHeadMechanicName() {
            return HeadMechanicName;
        }

        public void setHeadMechanicName(String headMechanicName) {
            HeadMechanicName = headMechanicName;
        }

        public String getHeadMechanicMobNo() {
            return HeadMechanicMobNo;
        }

        public void setHeadMechanicMobNo(String headMechanicMobNo) {
            HeadMechanicMobNo = headMechanicMobNo;
        }

        public String getBankName() {
            return BankName;
        }

        public void setBankName(String bankName) {
            BankName = bankName;
        }

        public String getAccountNo() {
            return AccountNo;
        }

        public void setAccountNo(String accountNo) {
            AccountNo = accountNo;
        }

        public String getIFSCCode() {
            return IFSCCode;
        }

        public void setIFSCCode(String IFSCCode) {
            this.IFSCCode = IFSCCode;
        }

        public String getUPIPaymentId() {
            return UPIPaymentId;
        }

        public void setUPIPaymentId(String UPIPaymentId) {
            this.UPIPaymentId = UPIPaymentId;
        }

        public String getUPIPaymentNo() {
            return UPIPaymentNo;
        }

        public void setUPIPaymentNo(String UPIPaymentNo) {
            this.UPIPaymentNo = UPIPaymentNo;
        }

        public String getIsActive() {
            return IsActive;
        }

        public void setIsActive(String isActive) {
            IsActive = isActive;
        }

        public String getRequestId() {
            return RequestId;
        }

        public void setRequestId(String requestId) {
            RequestId = requestId;
        }

        public List<AssignProblemToUserDetailList> getUserGarageIssueRequests() {
            return userGarageIssueRequests;
        }

        public void setUserGarageIssueRequests(List<AssignProblemToUserDetailList> userGarageIssueRequests) {
            this.userGarageIssueRequests = userGarageIssueRequests;
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

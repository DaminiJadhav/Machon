package com.machon.machon.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostGarageIssueResponse {
    @SerializedName("Status")
    String Status;
    @SerializedName("Message")
    String Message;
    @SerializedName("RequestId")
    String RequestId;

    @SerializedName("GarageList")
    List<GarageList> garageList;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String userId) {
        Status = userId;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }


    public List<GarageList> getGarageList() {
        return garageList;
    }

    public void setGarageList(List<GarageList> garageList) {
        this.garageList = garageList;
    }

    public class GarageList{
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
        double GarageLatitude;
        @SerializedName("GarageLongitude")
        double GarageLongitude;
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

        public String getGarageRegistrationId() {
            return GarageRegistrationId;
        }

        public void setGarageRegistrationId(String garageRegistrationId) {
            GarageRegistrationId = garageRegistrationId;
        }

        public double getGarageLatitude() {
            return GarageLatitude;
        }

        public void setGarageLatitude(double garageLatitude) {
            GarageLatitude = garageLatitude;
        }

        public double getGarageLongitude() {
            return GarageLongitude;
        }

        public void setGarageLongitude(double garageLongitude) {
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
    }
}

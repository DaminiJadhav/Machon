package com.machon.machon.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginMechanicResponse {
    @SerializedName("Status")
    String Status;
    @SerializedName("Message")
    String Message;
    @SerializedName("Garage")
    Garage garage;


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

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public class Garage {
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
        @SerializedName("IFSCCode")
        String IFSCCode;
        @SerializedName("UPIPaymentId")
        String UPIPaymentId;
        @SerializedName("UPIPaymentNo")
        String UPIPaymentNo;
        @SerializedName("IsActive")
        boolean IsActive;
        @SerializedName("RequestId")
        String RequestId;
        @SerializedName("GarageFCMTocken")
        String GarageFCMTocken;

        public String getGarageRegistrationId() {
            return GarageRegistrationId;
        }

        public void setGarageRegistrationId(String garageRegistrationId) {
            GarageRegistrationId = garageRegistrationId;
        }

        public String getGarageLatitude() {
            return GarageLatitude;
        }

        public String getGarageLongitude() {
            return GarageLongitude;
        }

        public String getHeadMechanicMobNo() {
            return HeadMechanicMobNo;
        }

        public void setGarageLatitude(String garageLatitude) {
            GarageLatitude = garageLatitude;
        }

        public void setGarageLongitude(String garageLongitude) {
            GarageLongitude = garageLongitude;
        }

        public void setHeadMechanicMobNo(String headMechanicMobNo) {
            HeadMechanicMobNo = headMechanicMobNo;
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



        public String getBankName() {
            return BankName;
        }

        public void setBankName(String bankName) {
            BankName = bankName;
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

        public boolean isActive() {
            return IsActive;
        }

        public void setActive(boolean active) {
            IsActive = active;
        }


        public String getRequestId() {
            return RequestId;
        }

        public void setRequestId(String requestId) {
            RequestId = requestId;
        }

        public String getGarageFCMTocken() {
            return GarageFCMTocken;
        }

        public void setGarageFCMTocken(String garageFCMTocken) {
            GarageFCMTocken = garageFCMTocken;
        }
    }
}

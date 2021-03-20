package com.machon.machon.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginResponse {
    @SerializedName("Status")
    String Status;
    @SerializedName("Message")
    String Message;
    @SerializedName("Response")
    Response response;

   public class Response {
        @SerializedName("AspNetUserClaims")
        ArrayList<AspNetUserClaims> aspNetUserClaimsList = new ArrayList<AspNetUserClaims>();
        @SerializedName("AspNetUserLogins")
        ArrayList<AspNetUserLogins> aspNetUserLoginsList = new ArrayList<AspNetUserLogins>();
        @SerializedName("AspNetRoles")
        ArrayList<AspNetRoles> aspNetRolesList = new ArrayList<AspNetRoles>();

        @SerializedName("Id")
        String id;
        @SerializedName("Email")
        String email;
        @SerializedName("EmailConfirmed")
        String emailConfirmed;
        @SerializedName("PasswordHash")
        String passwordHash;
        @SerializedName("SecurityStamp")
        String securityStamp;
        @SerializedName("PhoneNumber")
        String phoneNumber;
        @SerializedName("PhoneNumberConfirmed")
        String phoneNumberConfirmed;
        @SerializedName("TwoFactorEnabled")
        String twoFactorEnabled;
        @SerializedName("LockoutEndDateUtc")
        String lockoutEndDateUtc;
        @SerializedName("LockoutEnabled")
        String lockoutEnabled;
        @SerializedName("AccessFailedCount")
        String accessFailedCount;
        @SerializedName("UserName")
        String userName;
        @SerializedName("FirstName")
        String firstName;
        @SerializedName("MiddleName")
        String middleName;
        @SerializedName("LastName")
        String lastName;
        @SerializedName("MobNo")
        String mobNo;
        @SerializedName("DOB")
        String DOB;
       @SerializedName("UserFCMTocken")
       String UserFCMTocken;


        class AspNetRoles {
        }

        class AspNetUserLogins {
        }

        class AspNetUserClaims {
        }


        public ArrayList<AspNetUserClaims> getAspNetUserClaimsList() {
            return aspNetUserClaimsList;
        }

        public void setAspNetUserClaimsList(ArrayList<AspNetUserClaims> aspNetUserClaimsList) {
            this.aspNetUserClaimsList = aspNetUserClaimsList;
        }

        public ArrayList<AspNetUserLogins> getAspNetUserLoginsList() {
            return aspNetUserLoginsList;
        }

        public void setAspNetUserLoginsList(ArrayList<AspNetUserLogins> aspNetUserLoginsList) {
            this.aspNetUserLoginsList = aspNetUserLoginsList;
        }

        public ArrayList<AspNetRoles> getAspNetRolesList() {
            return aspNetRolesList;
        }

        public void setAspNetRolesList(ArrayList<AspNetRoles> aspNetRolesList) {
            this.aspNetRolesList = aspNetRolesList;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmailConfirmed() {
            return emailConfirmed;
        }

        public void setEmailConfirmed(String emailConfirmed) {
            this.emailConfirmed = emailConfirmed;
        }

        public String getPasswordHash() {
            return passwordHash;
        }

        public void setPasswordHash(String passwordHash) {
            this.passwordHash = passwordHash;
        }

        public String getSecurityStamp() {
            return securityStamp;
        }

        public void setSecurityStamp(String securityStamp) {
            this.securityStamp = securityStamp;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPhoneNumberConfirmed() {
            return phoneNumberConfirmed;
        }

        public void setPhoneNumberConfirmed(String phoneNumberConfirmed) {
            this.phoneNumberConfirmed = phoneNumberConfirmed;
        }

        public String getTwoFactorEnabled() {
            return twoFactorEnabled;
        }

        public void setTwoFactorEnabled(String twoFactorEnabled) {
            this.twoFactorEnabled = twoFactorEnabled;
        }

        public String getLockoutEndDateUtc() {
            return lockoutEndDateUtc;
        }

        public void setLockoutEndDateUtc(String lockoutEndDateUtc) {
            this.lockoutEndDateUtc = lockoutEndDateUtc;
        }

        public String getLockoutEnabled() {
            return lockoutEnabled;
        }

        public void setLockoutEnabled(String lockoutEnabled) {
            this.lockoutEnabled = lockoutEnabled;
        }

        public String getAccessFailedCount() {
            return accessFailedCount;
        }

        public void setAccessFailedCount(String accessFailedCount) {
            this.accessFailedCount = accessFailedCount;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMobNo() {
            return mobNo;
        }

        public void setMobNo(String mobNo) {
            this.mobNo = mobNo;
        }

        public String getDOB() {
            return DOB;
        }

        public void setDOB(String DOB) {
            this.DOB = DOB;
        }

       public String getUserFCMTocken() {
           return UserFCMTocken;
       }

       public void setUserFCMTocken(String userFCMTocken) {
           UserFCMTocken = userFCMTocken;
       }
   }

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

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }


}


/*

{
    "Status": 1,
    "Message": "Login Successfully",
    "Response": {
        "AspNetUserClaims": [],
        "AspNetUserLogins": [],
        "AspNetRoles": [],
        "Id": "57b62bce-7ce7-41ca-96c3-dd8f527c80e7",
        "Email": "damini@gmail.com",
        "EmailConfirmed": false,
        "PasswordHash": "9lAK4PMU19pc0PXm8p0rHRbioTAy/AbYxiHdgi+Z2vs=",
        "SecurityStamp": "D022BAA5F188604987DC965937B701BF",
        "PhoneNumber": null,
        "PhoneNumberConfirmed": true,
        "TwoFactorEnabled": false,
        "LockoutEndDateUtc": null,
        "LockoutEnabled": true,
        "AccessFailedCount": 0,
        "UserName": "9657431432",
        "FirstName": "Damini",
        "MiddleName": "Bharat",
        "LastName": "Jadhav",
        "MobNo": "9657431432",
        "DOB": null
    }
}



 */
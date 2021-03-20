package com.machon.machon.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetVehicleTypeResponse  {
    @SerializedName("Status")
    String Status;
    @SerializedName("Message")
    String Message;
    @SerializedName("VehicleType")
    List<VehicleType> vehicleType;

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

        @SerializedName("tblAssignProblemToUserHeaders")
        List<Object> tblAssignProblemToUserHeaders;
        @SerializedName("VehicleTypeId")
        String VehicleTypeId;
        @SerializedName("VehicleType")
        String VehicleType;

        boolean isGarageTypeSelect;

        public List<Object> getTblAssignProblemToUserHeaders() {
            return tblAssignProblemToUserHeaders;
        }

        public void setTblAssignProblemToUserHeaders(List<Object> tblAssignProblemToUserHeaders) {
            this.tblAssignProblemToUserHeaders = tblAssignProblemToUserHeaders;
        }

        public String getVehicleTypeId() {
            return VehicleTypeId;
        }

        public void setVehicleTypeId(String vehicleTypeId) {
            VehicleTypeId = vehicleTypeId;
        }

        public String getVehicleType() {
            return VehicleType;
        }

        public void setVehicleType(String vehicleType) {
            VehicleType = vehicleType;
        }


        public boolean isGarageTypeSelect() {
            return isGarageTypeSelect;
        }

        public void setGarageTypeSelect(boolean garageTypeSelect) {
            isGarageTypeSelect = garageTypeSelect;
        }
    }
}

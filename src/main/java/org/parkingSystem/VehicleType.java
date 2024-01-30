package org.parkingSystem;

public enum VehicleType {
    CAR, BIKE, TRUCK;

    public static VehicleType  getVehicle(String vehicleType) throws Exception{
        VehicleType type;
        if(vehicleType.equalsIgnoreCase("CAR")){
            type=VehicleType.CAR;
        } else if (vehicleType.equalsIgnoreCase("TRUCK")) {
            type=VehicleType.TRUCK;
        } else if (vehicleType.equalsIgnoreCase("BIKE")) {
            type=VehicleType.BIKE;
        }
        else {
            throw new Exception("Invalid Vehicle Type Provided");
        }
        return type;
    }
}

package org.parkingSystem.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class Vehicle {
    private VehicleType vehicleType;
    private String regNum;
    private String color;
    private static Map<String,Vehicle> vehicleMap = new HashMap<>();

    public Vehicle(VehicleType vehicleType, String regNum, String color) {
        this.vehicleType = vehicleType;
        this.regNum = regNum;
        this.color = color;
        vehicleMap.put(regNum,this);
    }

    public static Vehicle getVehicleByRegNum(String regNum) throws Exception {

        return vehicleMap.get(regNum);
    }
    public static void removeVehicleByRegNum(String regNum) throws Exception {
        if(vehicleMap.containsKey(regNum))
            vehicleMap.remove(regNum);
        else
            throw new Exception("No Such Vehicle Present with Registration number "+ regNum);
    }
}


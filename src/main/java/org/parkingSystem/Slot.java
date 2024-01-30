package org.parkingSystem;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Slot {
    private Integer slotNum;
    private boolean isOccupied;
    private Integer floorNum;

    private VehicleType VehicleType;

    private static int counter=0;
    Slot(VehicleType VehicleType,Integer floorNum){
        this.isOccupied=false;
        counter++;
        this.slotNum =counter;
        this.VehicleType=VehicleType;
        this.floorNum=floorNum;


    }
}

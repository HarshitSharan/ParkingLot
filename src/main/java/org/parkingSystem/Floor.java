package org.parkingSystem;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class Floor {
    private Integer floorNum;
    private Map<VehicleType, List<Slot>> slots;

    Floor(Integer floorNum,Integer slotsPerFloor) throws Exception {
        this.floorNum=floorNum;
        this.slots = new LinkedHashMap<>();
        addSlots(slotsPerFloor,floorNum);
    }
    public void addSlots(Integer slotsPerFloor,Integer floorNum) throws Exception{
        if(slotsPerFloor<4){
            throw new Exception("Slots Can't be 0 per floor");
        }
        slots.put(VehicleType.TRUCK,List.of(new Slot(VehicleType.TRUCK,floorNum)));
        slots.put(VehicleType.BIKE,List.of(new Slot(VehicleType.BIKE,floorNum),new Slot(VehicleType.BIKE,floorNum)));
        slots.put(VehicleType.CAR,new ArrayList<>());

        for(int i=0;i<slotsPerFloor-3;i++){
            slots.get(VehicleType.CAR).add(new Slot(VehicleType.CAR,floorNum));
        }
    }

    public List<Slot> getSlotListByVehicleType(VehicleType type){
        return this.slots.get(type);

    }

    public List<Slot> getFreeSlotByVehicleType(VehicleType type){
        return this.slots.get(type).stream().filter(var->!var.isOccupied()).collect(Collectors.toList());
    }
    public List<Slot> getOccupiedSlotByVehicleType(VehicleType type){
        return this.slots.get(type).stream().filter(var->var.isOccupied()).collect(Collectors.toList());
    }
}

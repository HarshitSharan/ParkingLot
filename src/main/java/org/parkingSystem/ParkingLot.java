package org.parkingSystem;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter @Getter
public class ParkingLot {
    private String parkingLotId;
    private List<Floor> floors;
    public ParkingLot (String parkingLotId,Integer floorCount,Integer slotPerFloor) throws Exception {
        this.parkingLotId = parkingLotId;
        this.floors = new ArrayList<>();
        for(int i=0;i<floorCount;i++){
            floors.add(new Floor(i,slotPerFloor));

        }
    }

    public Ticket parkVeichle(String veichleType,String regNum,String color) throws Exception {
        VehicleType type = VehicleType.getVehicle(veichleType);
        if(Vehicle.getVehicleByRegNum(regNum)!=null){
            throw new Exception("Vehicle with given Registration number already Present");
        }
        Vehicle vehicle = new Vehicle(type,regNum,color);
        Slot slot=getFirstVaccant(type);
        slot.setOccupied(true);
        Ticket ticket = new Ticket(this.parkingLotId, slot.getFloorNum(), slot.getSlotNum(),regNum);
        return ticket;
    }

    public Slot getFirstVaccant(VehicleType vehicleType) throws Exception {
        for(Floor var : this.floors){
            for(Slot slot : var.getSlotListByVehicleType(vehicleType)){
                if(!slot.isOccupied()){
                    return slot;
                }
            }
        }
        throw new Exception("Parking Lot Full");
    }

    public Vehicle unParkVehicle(String ticketId) throws Exception {
        Ticket ticket = Ticket.getTicketById(ticketId);
        if (ticket == null) {
            throw new Exception("Invalid Ticket");
        }
        Vehicle vehicle = Vehicle.getVehicleByRegNum(ticket.getRegNum());
        if(vehicle==null){
            throw  new Exception("This Ticket is Already Unparked");
        }
        List<Slot> slots = this.floors.get(ticket.getFloorNum()).getSlotListByVehicleType(vehicle.getVehicleType());
        boolean slotFound = false;
        for (Slot var : slots) {
            if (var.getSlotNum().equals(ticket.getSlotNum())) {
                var.setOccupied(false);
                slotFound = true;
                break;
            }
        }
        if (slotFound){
            Vehicle.removeVehicleByRegNum(ticket.getRegNum());
            return vehicle;
        }
        else{
            throw new Exception("Invalid Ticket");
        }
    }

    public List<List<Slot>> getFreeSlotsByVeichleType(VehicleType vehicleType){
        List <List<Slot>> freeSlots = new ArrayList<>();

        for (Floor floor: this.floors){
            freeSlots.add(floor.getFreeSlotByVehicleType(vehicleType));
        }
        return freeSlots;
    }

    public List<Integer> getFreeCountByVehicleType(VehicleType vehicleType){
        List<List<Slot>> freeSlots = getFreeSlotsByVeichleType(vehicleType);
        return freeSlots.stream().map(var -> var.size()).collect(Collectors.toList());
    }
    public List<List<Slot>> getOccupiedSlotsByVeichleType(VehicleType vehicleType){
        List <List<Slot>> occupiedSlots = new ArrayList<>();

        for (Floor floor: this.floors){
            occupiedSlots.add(floor.getOccupiedSlotByVehicleType(vehicleType));
        }
        return occupiedSlots;
    }
}

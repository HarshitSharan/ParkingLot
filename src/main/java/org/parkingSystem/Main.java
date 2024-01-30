package org.parkingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String query = br.readLine();
        ParkingLot parkingLot=null;
        while(!query.equalsIgnoreCase("exit")){
            String qry[] = query.split(" ");
            if(qry[0].equalsIgnoreCase("display")){
                qry[0]=qry[0]+" "+qry[1];
            }
            switch (qry[0].toLowerCase()){
                case "create_parking_lot":{
                    Integer floorNum = Integer.parseInt(qry[2]),slotCount = Integer.parseInt(qry[3]);
                    try {
                        parkingLot = new ParkingLot(qry[1],floorNum,slotCount);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.printf("Created parking lot with %d floors and %d slots per floor\n",floorNum,slotCount);
                    break;
                }



                case "park_vehicle":{
                    String regNum = qry[2],color=qry[3],type = qry[1];
                    if(parkingLot==null){
                        System.out.println("Please Initialize a parking lot first to begin");
                    }
                    else {
                        try {
                            Ticket ticket=parkingLot.parkVeichle(type,regNum,color);
                            System.out.println("Parked vehicle. Ticket ID: "+ticket.getTicketId());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                }


                case "unpark_vehicle":{
                    String ticketId = qry[1];
                    if(parkingLot==null){
                        System.out.println("Please Initialize a parking lot first to begin");
                    }
                    else {
                        try {
                            Vehicle vehicle=parkingLot.unParkVehicle(ticketId);
                            System.out.printf("Unparked vehicle with Registration Number: %s and Color: %s\n",vehicle.getRegNum(),vehicle.getColor());
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                }


                case "display free_count": {
                    String vehicleType = qry[2];
                    if(parkingLot==null){
                        System.out.println("Please Initialize a parking lot first to begin");
                    }
                    else {
                        try {
                            List<Integer> result = parkingLot.getFreeCountByVehicleType(VehicleType.getVehicle(vehicleType));
                            for(int i=0;i<result.size();i++){
                                System.out.printf("No. of free slots for %s on Floor %d: %d\n",vehicleType,i+1,result.get(i));
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                }

                case "display free_slots": {
                    String vehicleType = qry[2];
                    if (parkingLot == null) {
                        System.out.println("Please Initialize a parking lot first to begin");
                    } else {
                        try {
                            List<List<Slot>> result = parkingLot.getFreeSlotsByVeichleType(VehicleType.getVehicle(vehicleType));
                            for (int i = 0; i < result.size(); i++) {
                                System.out.printf("Free slots for %s on Floor %d:", vehicleType, i + 1);
                                result.get(i).stream().forEach(var -> System.out.printf("%d,", var.getSlotNum().intValue()));
                                System.out.println("");
                            }

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                }

                case "display occupied_slots":{
                    String vehicleType = qry[2];
                    if (parkingLot == null) {
                        System.out.println("Please Initialize a parking lot first to begin");
                    } else {
                        try {
                            List<List<Slot>> result = parkingLot.getOccupiedSlotsByVeichleType(VehicleType.getVehicle(vehicleType));
                            for (int i = 0; i < result.size(); i++) {
                                System.out.printf("Occupied slots for %s on Floor %d:", vehicleType, i + 1);
                                result.get(i).stream().forEach(var -> System.out.printf("%d,", var.getSlotNum()));
                                System.out.println("");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                }

                default:
                    System.out.println("Enter Valid Option No Matching option found");
            }

            query = br.readLine();
        }
    }
}
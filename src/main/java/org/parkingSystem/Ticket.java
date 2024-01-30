package org.parkingSystem;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

 @Setter@Getter
public class Ticket {
    private String parkingLotId;
    private Integer floorNum;
    private Integer SlotNum;
    private String regNum;
    private static Map<String,Ticket> tickets = new HashMap<>();
    public String getTicketId() {
        return parkingLotId+
                "_" + floorNum +
                "_" + SlotNum;
    }

    public Ticket(String parkingLotId, Integer floorNum, Integer slotNum, String regNum) {
        this.parkingLotId = parkingLotId;
        this.floorNum = floorNum;
        SlotNum = slotNum;
        this.regNum = regNum;
        tickets.put(this.getTicketId(),this);

    }

    public static Ticket getTicketById(String ticketId){
        return tickets.get(ticketId);
    }
}

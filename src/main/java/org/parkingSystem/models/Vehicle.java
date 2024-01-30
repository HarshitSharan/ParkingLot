package org.parkingSystem.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Veichle {
    private VeichleType veichleType;
    private String regNum;
    private String color;
}

package ru.itmo.dto;

import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CatDto {
    private String name;
    @Past
    private String birthday;
    private String breed;
    private String color;
    private int ownerId;
}

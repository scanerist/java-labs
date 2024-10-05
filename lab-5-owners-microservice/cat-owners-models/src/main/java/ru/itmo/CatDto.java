package ru.itmo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CatDto {

    public CatDto(int id, String name, LocalDate birthDate, String breed, CatColour colour, String ownerName, List<Integer> catFriends) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.colour = colour;
        this.ownerName = ownerName;
        this.catFriends = catFriends;
    }

    private int id;
    private String name;
    private LocalDate birthDate;
    private String breed;
    private CatColour colour;
    private String ownerName;
    private List<Integer> catFriends;


}

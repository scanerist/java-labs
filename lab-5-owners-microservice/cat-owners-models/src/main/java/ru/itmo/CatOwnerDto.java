package ru.itmo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class CatOwnerDto {

    public CatOwnerDto(String name, LocalDate birthDate, List<CatDto> ownedCats) {
        this.name = name;
        this.birthDate = birthDate;
        this.ownedCats = ownedCats;
    }

    private String name;
    private LocalDate birthDate;
    private List<CatDto> ownedCats;
}

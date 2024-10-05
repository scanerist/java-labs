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

    public CatOwnerDto(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.ownedCats = new ArrayList<>();
    }

    private String name;
    private LocalDate birthDate;
    private List<String> ownedCats;
}

package ru.itmo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "cat_owners")
@Getter
@NoArgsConstructor(force = true)
public class CatOwner {

    @Id
    private final String name;
    @Column(name = "birth_date")
    private final LocalDate birthDate;

    public CatOwner(String name, LocalDate birthDate) {

        this.name = name;
        this.birthDate = birthDate;
    }
}

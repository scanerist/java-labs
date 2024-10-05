package ru.itmo.models;

import jakarta.persistence.*;
import lombok.*;
import ru.itmo.CatColour;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "cats")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String breed;
    @Enumerated(EnumType.STRING)
    private CatColour colour;
    private String owner;
    @ManyToMany
    @JoinTable(
            name = "cat_friends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<Cat> catFriends;

    public Cat(String name, LocalDate birthDate, String breed, CatColour colour, String owner) {

        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.colour = colour;
        this.owner = owner;
        this.catFriends = new ArrayList<>();
    }

    public Cat(
            String name,
            LocalDate birthDate,
            String breed,
            CatColour colour,
            String owner,
            List<Cat> catFriends) {

        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.colour = colour;
        this.owner = owner;
        this.catFriends = catFriends;
    }
}

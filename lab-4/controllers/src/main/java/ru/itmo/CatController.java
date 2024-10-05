package ru.itmo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itmo.dto.CatDto;
import ru.itmo.models.Cat;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/cat")
public class CatController {
    @Autowired
    CatService catService;

    @PostMapping(path = "/add")
    public ResponseEntity<String> addCat(@RequestBody CatDto catDto) {
        catService.addCat(catDto);
        return ResponseEntity.ok("Saved");
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteCat (@RequestBody int id, Principal principal) {
        catService.deleteCat(id, principal.getName());
        return ResponseEntity.ok("Delete");
    }

    @GetMapping(path = "/getCat/byId/{id}")
    public ResponseEntity<Cat> getCatById (@PathVariable int id, Principal principal) {
        return ResponseEntity.ok(catService.getCatById(id, principal.getName()));
    }

    @GetMapping(path = "/getCat/byName/{name}")
    public ResponseEntity<List<Cat>> getCatByName (@PathVariable String name, Principal principal) {
        return ResponseEntity.ok(catService.getAllByName(name, principal.getName()));
    }

    @GetMapping(path = "/getCat/byBirthday/{birthday}")
    public ResponseEntity<List<Cat>> getCatByBirthday (@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday, Principal principal) {
        return ResponseEntity.ok(catService.getAllByBirthday(birthday, principal.getName()));
    }

    @GetMapping(path = "/getCat/byBreed/{breed}")
    public ResponseEntity<List<Cat>> getCatByBreed (@PathVariable String breed, Principal principal) {
        return ResponseEntity.ok(catService.getAllByBreed(breed, principal.getName()));
    }

    @GetMapping(path = "/getCat/byColor/{color}")
    public ResponseEntity<List<Cat>> getCatByColor (@PathVariable String color, Principal principal) {
        return ResponseEntity.ok(catService.getAllByColor(color, principal.getName()));
    }

    @GetMapping(path = "/getCat/byOwnerId/{ownerId}")
    public ResponseEntity<List<Cat>> getCatByOwnerId (@PathVariable int ownerId, Principal principal) {
        return ResponseEntity.ok(catService.getAllByOwnerId(ownerId, principal.getName()));
    }

    @DeleteMapping(path = "/deleteAll")
    public ResponseEntity<String> deleteAll(Principal principal) {
        catService.deleteAll(principal.getName());
        return ResponseEntity.ok("Delete");
    }

    @GetMapping(path = "/getAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Cat> getAll() {
        return catService.getAll();
    }
}
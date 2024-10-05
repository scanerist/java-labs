package ru.itmo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.dto.UserDto;
import ru.itmo.models.Owner;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    OwnerService ownerService;
    @Autowired
    UserService userService;
    @Autowired
    UserJpaService userJpaService;

    @PostMapping("/add")
    public ResponseEntity<String> addOwner (@RequestBody UserDto userDto) {
        ownerService.addOwner(userDto);
        userJpaService.addUser(userDto);
        return ResponseEntity.ok("Saved");
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteOwner (@RequestBody int id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.ok("Delete");
    }

    @GetMapping(path = "/getOwner/byId/{id}")
    public ResponseEntity<Owner> getOwnerById (@PathVariable int id) {
        return ResponseEntity.ok(ownerService.getOwnerById(id));
    }

    @GetMapping(path = "/getOwner/byName/{name}")
    public ResponseEntity<List<Owner>> getOwnerByName (@PathVariable String name) {
        return ResponseEntity.ok(ownerService.getAllByName(name));
    }

    @GetMapping(path = "/getOwner/byBirthday/{birthday}")
    public ResponseEntity<List<Owner>> getOwnerByName (@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday) {
        return ResponseEntity.ok(ownerService.getAllByBirthday(birthday));
    }

    @DeleteMapping(path = "/deleteAll")
    public ResponseEntity<String> deleteAll() {
        userJpaService.deleteAll();
        return ResponseEntity.ok("Delete all");

    }

    @GetMapping(path = "/getAll")
    public List<Owner> getAll() {
        List<Owner> t = ownerService.getAll();
        return t;
    }
}
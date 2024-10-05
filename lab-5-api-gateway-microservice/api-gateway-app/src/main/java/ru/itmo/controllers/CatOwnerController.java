package ru.itmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.clients.CatOwnerClient;
import ru.itmo.CatOwnerDto;
import ru.itmo.CatOwnerDtoExtended;
import ru.itmo.models.dtos.UserDetailsImpl;
import ru.itmo.services.UserService;

@RestController
@RequestMapping("/catOwners")
public class CatOwnerController {

    private CatOwnerClient catOwnerClient;
    private UserService userService;

    @Autowired
    public CatOwnerController(CatOwnerClient catOwnerClient, UserService userService) {
        this.catOwnerClient = catOwnerClient;
        this.userService = userService;
    }

    @PostMapping
    public CatOwnerDto saveCatOwner(@RequestBody CatOwnerDtoExtended dto) {

        userService.putUser(new UserDetailsImpl(dto.getOwnerDto().getName(), dto.getPassword(), dto.getRoles()));
        return catOwnerClient.saveCatOwner(dto.getOwnerDto());
    }

    @GetMapping("/{name}")
    public CatOwnerDto readCatOwner(@PathVariable("name") String name) {

        return catOwnerClient.findCatOwnerByName(name);
    }

    @PutMapping
    public CatOwnerDto putCatOwner(@RequestBody CatOwnerDto dto) {

        return catOwnerClient.updateCatOwner(dto);
    }

    @DeleteMapping("/{name}")
    public void deleteCateOwner(@PathVariable("name") String name) {

        catOwnerClient.deleteCatOwnerByName(name);
    }
}
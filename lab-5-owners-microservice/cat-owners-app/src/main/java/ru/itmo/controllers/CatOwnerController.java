package ru.itmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmo.CatOwnerDto;
import ru.itmo.services.CatOwnerService;

import java.util.List;

@RestController
@RequestMapping("/catOwners")
public class CatOwnerController {

    private CatOwnerService catOwnerService;

    @Autowired
    public CatOwnerController(CatOwnerService catOwnerService) {
        this.catOwnerService = catOwnerService;
    }

    @PostMapping
    public CatOwnerDto saveCatOwner(@RequestBody CatOwnerDto dto) {

        return catOwnerService.saveCatOwner(dto);
    }

    @GetMapping("/{name}")
    public CatOwnerDto readCatOwner(@PathVariable("name") String name) {

        return catOwnerService.readCatOwner(name);
    }

    @PutMapping
    public CatOwnerDto putCatOwner(@RequestBody CatOwnerDto dto) {

        return catOwnerService.updateCatOwner(dto);
    }

    @DeleteMapping("/{name}")
    public void deleteCateOwner(@PathVariable("name") String name) {

        var dto = new CatOwnerDto();
        dto.setName(name);
        catOwnerService.deleteCatOwner(dto);
    }
}

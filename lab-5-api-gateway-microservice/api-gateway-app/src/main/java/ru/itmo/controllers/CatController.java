package ru.itmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.clients.CatClient;
import ru.itmo.CatColour;
import ru.itmo.CatDto;
import ru.itmo.services.CurrentUserService;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {

    private CatClient catClient;
    private CurrentUserService currentUserService;

    @Autowired
    public CatController (CatClient catClient, CurrentUserService currentUserService) {
        this.catClient = catClient;
        this.currentUserService = currentUserService;
    }

    @PostMapping
    public Object saveCat(@RequestBody CatDto dto) {

        String requestSenderName = currentUserService.getCurrentUserName();

        return catClient.saveCat(dto, requestSenderName);
    }

    @GetMapping("/{id}")
    public CatDto readCat(@PathVariable("id") int id) {

        String requestSenderName = currentUserService.getCurrentUserName();

        return catClient.findCatById(id, requestSenderName);
    }

    @GetMapping
    public List<CatDto> findCats(
            @RequestParam(value = "name", required = false) String catName,
            @RequestParam(value = "colour", required = false) CatColour colour,
            @RequestParam(value = "breed", required = false) String breed) {

        String requestSenderName = currentUserService.getCurrentUserName();

        if (catName != null && colour == null && breed == null)
            return catClient.findCatsByOwnerNameAndName(requestSenderName, catName);
        if (catName == null && colour != null && breed == null)
            return catClient.findCatsByColour(colour, requestSenderName);
        if (catName == null && colour == null && breed != null)
            return catClient.findCatsByBreed(breed, requestSenderName);

        return catClient.findCatsByOwnerName(requestSenderName);
    }

    @PutMapping
    public CatDto putCat(@RequestBody CatDto dto) {

        String requestSenderName = currentUserService.getCurrentUserName();

        return catClient.updateCat(dto, requestSenderName);
    }

    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable("id") int id) {

        String requestSenderName = currentUserService.getCurrentUserName();

        catClient.deleteCatById(id, requestSenderName);
    }

    @PutMapping("/{id}/friend/{id2}")
    public void befriendCats(@PathVariable("id") int lId,
                                     @PathVariable("id2") int rId) {

        String requestSenderName = currentUserService.getCurrentUserName();

        catClient.befriendCats(lId, rId, requestSenderName);
    }
}
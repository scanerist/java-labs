package ru.itmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.itmo.CatBefriendDto;
import ru.itmo.CatColour;
import ru.itmo.CatDto;
import ru.itmo.CatDtoExtended;
import ru.itmo.services.CatService;
import ru.itmo.services.CatServiceResult;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {

    private CatService catService;

    @Autowired
    public CatController (CatService catService) {
        this.catService = catService;
    }

    @PostMapping
    public CatDto saveCat(@RequestParam(value = "request_sender") String requestSenderName,
                          @RequestBody CatDto dto) {

        var result = catService.saveCat(new CatDtoExtended(dto, requestSenderName));
        if (result instanceof CatServiceResult.Success) {
            return ((CatServiceResult.Success) result).dtos().get(0);
        } else if (result instanceof  CatServiceResult.AccessDenied) {
            return null;
        }
        return null;
    }

    @GetMapping("/{id}")
    public CatDto readCat(@RequestParam(value = "request_sender") String requestSenderName,
                          @PathVariable("id") int id) {

        var result = catService.readCat(id, requestSenderName);
        if (result instanceof CatServiceResult.Success) {
            return ((CatServiceResult.Success) result).dtos().get(0);
        } else if (result instanceof CatServiceResult.AccessDenied) {
            return null;
        }
        return null;
    }

    @GetMapping
    public List<CatDto> findCats(
            @RequestParam(value = "request_sender") String requestSenderName,
            @RequestParam(value = "name", required = false) String catName,
            @RequestParam(value = "colour", required = false) CatColour colour,
            @RequestParam(value = "breed", required = false) String breed) {
        CatServiceResult result = null;

        if (catName != null && colour == null && breed == null)
            result = catService.findCatsByOwnerNameAndName(requestSenderName, catName);
        if (catName == null && colour != null && breed == null)
            result = catService.findCatsByColourAndOwnerName(colour, requestSenderName);
        if (catName == null && colour == null && breed != null)
            result = catService.findCatsByBreedAndOwnerName(breed, requestSenderName);
        if (catName == null && colour == null && breed == null)
            result = catService.findCatsByOwnerName(requestSenderName);

        if (result instanceof CatServiceResult.Success) {
            return ((CatServiceResult.Success) result).dtos();
        } else if (result instanceof CatServiceResult.AccessDenied) {
            return null;
        }
        return null;
    }

    @PutMapping
    public CatDto putCat(@RequestParam(value = "request_sender") String requestSenderName,
                         @RequestBody CatDto dto) {

        var result = catService.updateCat(new CatDtoExtended(dto, requestSenderName));
        if (result instanceof CatServiceResult.Success) {
            return ((CatServiceResult.Success) result).dtos().get(0);
        } else if (result instanceof CatServiceResult.AccessDenied) {
            return null;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCat(@RequestParam(value = "request_sender") String requestSenderName,
                            @PathVariable("id") int id) {

        var dto = new CatDto();
        dto.setId(id);
        catService.deleteCat(new CatDtoExtended(dto, requestSenderName));
    }

    @PutMapping("/{id}/friend/{id2}")
    public List<CatDto> befriendCats(@RequestParam(value = "request_sender") String requestSenderName,
                               @PathVariable("id") int lId,
                               @PathVariable("id2") int rId) {

        var result = catService.befriendCats(new CatBefriendDto(lId, rId, requestSenderName));
        if (result instanceof CatServiceResult.Success) {
            return ((CatServiceResult.Success) result).dtos();
        } else if (result instanceof CatServiceResult.AccessDenied) {
            return null;
        }
        return null;
    }

    private RedirectView accessDeniedRedirect() {

        return new RedirectView("http://localhost:8080");
    }
}

package ru.itmo.console.scenarios;

import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.cats.CurrentCatService;
import ru.itmo.models.Cat;

import java.util.List;

public class ShowFriendsScenario implements Scenario {
    private CatService catService;
    private CurrentCatService currentCatService;

    public ShowFriendsScenario(CatService catService, CurrentCatService currentCatService) {
        this.catService = catService;
        this.currentCatService = currentCatService;
    }

    @Override
    public String name() {
        return "Show Friends";
    }

    @Override
    public void run() {
        List<Cat> friends = catService.getCatFriends(currentCatService.getCat());
        for(int i = 0 ; i < friends.size(); ++i) {
            System.out.println(i + ") " + friends.get(i));
        }
    }
}

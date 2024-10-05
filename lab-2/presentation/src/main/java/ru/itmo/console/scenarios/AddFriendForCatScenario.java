package ru.itmo.console.scenarios;

import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.cats.CurrentCatService;
import ru.itmo.models.Cat;

import java.util.List;
import java.util.Scanner;

public class AddFriendForCatScenario implements Scenario{
    private CurrentCatService currentCatService;
    private CatService catService;
    private Scanner scanner;

    public AddFriendForCatScenario(CurrentCatService currentCatService, CatService catService) {
        scanner = new Scanner(System.in);
        this.currentCatService = currentCatService;
        this.catService = catService;
    }

    @Override
    public String name() {
        return "Add Friend For cat";
    }

    @Override
    public void run() {
        System.out.println("Choose cat friend: ");
        List<Cat> cats = catService.getAllCats();
        for(int i = 0; i < cats.size(); ++i) {
            System.out.println(i + ") " + cats.get(i).getName());
        }
        int choose = scanner.nextInt();
        Cat friend = cats.get(choose);
        catService.addFriend(currentCatService.getCat(), friend);
    }
}

package ru.itmo.console.scenarios;

import ru.itmo.abstractions.CatRepository;
import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.cats.CurrentCatService;
import ru.itmo.models.Cat;

import java.util.List;
import java.util.Scanner;

public class ChooseCatScenario implements Scenario {
    Scanner scanner;
    private CatService catService;
    private CurrentCatService currentCatService;

    public ChooseCatScenario(CatService catService, CurrentCatService currentCatService) {
        scanner = new Scanner(System.in);
        this.currentCatService = currentCatService;
        this.catService = catService;
    }

    @Override
    public String name() {
        return "Choose cat ";
    }

    @Override
    public void run() {
        System.out.println("Choose your cat: ");
        List<Cat> cats = catService.getAllCats();
        for(int i = 0; i < cats.size(); ++i) {
            System.out.println(i + ") " + cats.get(i).getName());
        }
        int choose = scanner.nextInt();
        currentCatService.setCat(cats.get(choose));
    }
}

package ru.itmo.console.scenarios;

import ru.itmo.contracts.cats.CatService;
import ru.itmo.models.Cat;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class DeleteCatScenario implements Scenario{
    private CatService catService;
    Scanner scanner;

    public DeleteCatScenario(CatService catService, InputStream inputStream) {
        this.catService = catService;
        scanner = new Scanner(inputStream);
    }

    @Override
    public String name() {
        return "Delete cat";
    }

    @Override
    public void run() {
        List<Cat> cats = catService.getAllCats();
        System.out.println("Choose cat for delete: ");
        for(int i = 0; i < cats.size(); ++i) {
            System.out.println(i + ") " + cats.get(i));
        }
        int choose = scanner.nextInt();
        Cat cat = cats.get(choose);
        catService.deleteCat(cat);
    }
}

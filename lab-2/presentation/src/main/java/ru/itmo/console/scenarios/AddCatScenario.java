package ru.itmo.console.scenarios;

import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.owners.CurrentOwnerService;
import ru.itmo.models.Cat;
import ru.itmo.models.Colors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddCatScenario implements Scenario {
    private Scanner scanner;
    private CatService catService;
    private CurrentOwnerService currentOwnerService;

    public AddCatScenario(CatService catService, CurrentOwnerService currentOwnerService) {
        this.currentOwnerService = currentOwnerService;
        scanner = new Scanner(System.in);
        this.catService = catService;
    }

    @Override
    public String name() {
        return "Add cat";
    }

    @Override
    public void run() {
        System.out.println("Enter cat name:");
        String name = scanner.nextLine();
        System.out.println("Enter cat birth date:");
        System.out.println("Enter year: ");
        int year = scanner.nextInt();
        System.out.println("Enter month: ");
        int month = scanner.nextInt();
        System.out.println("Enter day: ");
        int day = scanner.nextInt();
        System.out.println("Enter cat breed:");
        scanner.nextLine();
        String breed = scanner.nextLine();
        System.out.println("Enter cat color:");
        Colors color = Colors.valueOf(scanner.nextLine());
        LocalDate birthDate = LocalDate.of(year, month, day);
        catService.addCat(name, birthDate, breed, color, currentOwnerService.getOwner());
    }
}

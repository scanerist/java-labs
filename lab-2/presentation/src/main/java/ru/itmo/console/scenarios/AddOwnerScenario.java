package ru.itmo.console.scenarios;

import ru.itmo.abstractions.OwnerRepository;
import ru.itmo.contracts.owners.OwnerService;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Scanner;

public class AddOwnerScenario implements Scenario{
    private final OwnerService ownerService;
    Scanner scanner;

    public AddOwnerScenario(OwnerService ownerService, InputStream source) {
        this.ownerService = ownerService;
        scanner = new Scanner(source);
    }

    @Override
    public String name() {
        return "Add owner";
    }

    @Override
    public void run() {
        System.out.println("Enter owner name: ");
        String ownerName = scanner.nextLine();
        System.out.println("Enter owner birthdate: ");
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();
        ownerService.addOwner(ownerName, LocalDate.of(year, month, day));
    }
}

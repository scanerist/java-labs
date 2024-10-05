package ru.itmo.console.scenarios;

import ru.itmo.contracts.owners.CurrentOwnerService;
import ru.itmo.contracts.owners.OwnerService;
import ru.itmo.models.Owner;

import java.util.List;
import java.util.Scanner;

public class ChooseOwnerScenario implements Scenario{
    Scanner scanner;
    private CurrentOwnerService currentOwnerService;
    private OwnerService ownerService;

    public ChooseOwnerScenario(CurrentOwnerService currentOwnerService, OwnerService ownerService) {
        scanner = new Scanner(System.in);
        this.currentOwnerService = currentOwnerService;
        this.ownerService = ownerService;
    }

    @Override
    public String name() {
        return "Choose owner";
    }

    @Override
    public void run() {
        List<Owner> owners = ownerService.getAllOwners();
        for (int i = 0; i < owners.size(); i++) {
            System.out.println(i + ". " + owners.get(i).getName());
        }
        int index = scanner.nextInt();
        currentOwnerService.setOwner(owners.get(index));
    }
}

package ru.itmo.console.scenarios;

import ru.itmo.contracts.owners.OwnerService;
import ru.itmo.models.Owner;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class DeleteOwnerScenario implements Scenario {
    Scanner scanner;
    private OwnerService ownerService;

    public DeleteOwnerScenario(OwnerService ownerService, InputStream inputStream) {
        this.ownerService = ownerService;
        scanner = new Scanner(inputStream);
    }

    @Override
    public String name() {
        return "Delete owner";
    }

    @Override
    public void run() {
        List<Owner> owners = ownerService.getAllOwners();
        for (int i = 0; i < owners.size(); ++i) {
            System.out.println(i + ") " + owners.get(i));
        }
        int choose = scanner.nextInt();
        ownerService.deleteOwner(owners.get(choose));
    }
}

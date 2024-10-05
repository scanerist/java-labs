package ru.itmo.console.scenarios;

import org.xml.sax.InputSource;
import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.cats.CurrentCatService;
import ru.itmo.models.Cat;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class RemoveFriendScenario implements Scenario{
    private CatService catService;
    private CurrentCatService currentCatService;
    private Scanner scanner;

    public RemoveFriendScenario(CatService catService, CurrentCatService currentCatService, InputStream inputStream) {
        this.catService = catService;
        this.currentCatService = currentCatService;
        scanner = new Scanner(inputStream);
    }

    @Override
    public String name() {
        return "Remove friend";
    }

    @Override
    public void run() {
        List<Cat> friends = catService.getCatFriends(currentCatService.getCat());
        if (friends.size() == 0) {
            System.out.println("You dont have friends");
            return;
        }
        System.out.println("Take a cat that is no longer your friend ");
        for(int i = 0; i < friends.size(); ++i) {
            System.out.println(i + ") " + friends.get(i));
        }
        int choose = scanner.nextInt();
        Cat notFriend = friends.get(choose);
        catService.deleteFriend(currentCatService.getCat(), notFriend);
    }
}

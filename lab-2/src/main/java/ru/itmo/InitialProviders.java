package ru.itmo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.itmo.abstractions.CatRepository;
import ru.itmo.abstractions.OwnerRepository;
import ru.itmo.console.providers.*;
import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.cats.CurrentCatService;
import ru.itmo.contracts.owners.CurrentOwnerService;
import ru.itmo.contracts.owners.OwnerService;
import ru.itmo.logic.cats.CatServiceImpl;
import ru.itmo.logic.cats.CurrentCatServiceImpl;
import ru.itmo.logic.owners.CurrentOwnerServiceImpl;
import ru.itmo.logic.owners.OwnerServiceImpl;
import ru.itmo.models.Cat;
import ru.itmo.models.Owner;
import ru.itmo.repositories.CatRepositoryImpl;
import ru.itmo.repositories.OwnerRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class InitialProviders {
    public static List<Provider> getProviders() {
        Configuration configuration = new Configuration().addAnnotatedClass(Cat.class).addAnnotatedClass(Owner.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        OwnerRepository ownerRepository = new OwnerRepositoryImpl(sessionFactory);
        CatRepository catRepository = new CatRepositoryImpl(sessionFactory);

        OwnerService ownerService = new OwnerServiceImpl(ownerRepository, catRepository);
        CatService catService = new CatServiceImpl(catRepository, ownerRepository);
        CurrentCatService currentCatService = new CurrentCatServiceImpl();
        CurrentOwnerService currentOwnerService = new CurrentOwnerServiceImpl();


        List<Provider> providers = new ArrayList<>();
        providers.add(new AddCatProvider(catService, currentOwnerService));
        providers.add(new AddOwnerProvider(ownerService, System.in));
        providers.add(new AddFriendForCatProvider(currentCatService, catService));

        providers.add(new ChooseCatProvider(catService, currentCatService));
        providers.add(new ChooseOwnerProvider(currentOwnerService, ownerService));

        providers.add(new ShowCatProvider(currentOwnerService, catService));
        providers.add(new ShowAllCatsProvider(currentOwnerService, currentCatService, catService));
        providers.add(new ShowFriendsProvider(currentCatService, catService));

        providers.add(new DeleteCatProvider(catService, System.in));
        providers.add(new DeleteOwnerProvider(ownerService, System.in));
        providers.add(new RemoveFriendProvider(catService, currentCatService, System.in));
        return providers;
    }
}

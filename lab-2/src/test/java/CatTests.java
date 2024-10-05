import org.junit.jupiter.api.Test;
import ru.itmo.abstractions.CatRepository;
import ru.itmo.abstractions.OwnerRepository;
import ru.itmo.contracts.cats.CatService;
import ru.itmo.contracts.owners.OwnerService;
import ru.itmo.logic.cats.CatServiceImpl;
import ru.itmo.logic.owners.OwnerServiceImpl;
import ru.itmo.models.Cat;
import ru.itmo.models.Colors;
import ru.itmo.models.Owner;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class CatTests {

    @Test
    public void shouldAddOwner() {
        var mockOwner = mock(OwnerRepository.class);
        var mockCat = mock(CatRepository.class);
        OwnerService ownerService = new OwnerServiceImpl(mockOwner, mockCat);
        assertNotNull(ownerService.addOwner("Leha", LocalDate.now()));
    }

    @Test
    public void shouldAddCat() {
        OwnerRepository mockOwner = mock(OwnerRepository.class);
        CatRepository mockCat = mock(CatRepository.class);
        CatService catService = new CatServiceImpl(mockCat, mockOwner);
        OwnerService ownerService = new OwnerServiceImpl(mockOwner, mockCat);
        ownerService.addOwner("Leha", LocalDate.now());
        when(mockOwner.getById(anyInt())).thenReturn(new Owner());
        assertNotNull(catService.addCat("le", LocalDate.now(), "re", Colors.RED, new Owner()));
    }

    @Test
    public void testGetCatById() {
        CatRepository mockCatRepository = mock(CatRepository.class);
        Cat cat = new Cat();
        cat.setName("Whiskers");
        when(mockCatRepository.getById(1)).thenReturn(cat);

        Cat actualCat = mockCatRepository.getById(1);
        assertEquals(cat, actualCat);
    }

    @Test
    public void testGetOwnerById() {
        OwnerRepository mockOwnerRepository = mock(OwnerRepository.class);
        Owner owner = new Owner();
        owner.setName("John");
        when(mockOwnerRepository.getById(1)).thenReturn(owner);

        Owner actualOwner = mockOwnerRepository.getById(1);

        assertEquals(owner, actualOwner);
    }
}

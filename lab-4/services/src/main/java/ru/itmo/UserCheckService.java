package ru.itmo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.exceptions.UserException;
import ru.itmo.models.Cat;
import ru.itmo.models.Owner;
import ru.itmo.models.Role;
import ru.itmo.models.User;

@Service
public class UserCheckService {
    @Autowired
    UserService userService;

    public void IsUserHaveAuthorityCat(String username, Cat cat) {
        User currentUser = userService.findByUsername(username);
        if (!currentUser.getRoles().contains(Role.RoleType.ROLE_USER)) {
            if (cat.getOwner().getId() != currentUser.getOwner().getId()) {
                throw UserException.OwnerHasNotAuthorityCat();
            }
        }
    }

    public boolean IsUserHaveAuthorityCatFilter(String username, Cat cat) {
        User currentUser = userService.findByUsername(username);
        if (!currentUser.getRoles().contains(Role.RoleType.ROLE_ADMIN)){
            return cat.getOwner().getId() == currentUser.getOwner().getId();
        }
        return true;
    }

    public Owner ownerOfUser(String username) {
        User currentUser = userService.findByUsername(username);
        return currentUser.getOwner();
    }

}
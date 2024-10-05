package ru.itmo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.dto.UserDto;
import ru.itmo.models.Owner;
import ru.itmo.models.Role;
import ru.itmo.models.User;
import ru.itmo.repositories.OwnerRepository;
import ru.itmo.repositories.RoleRepository;
import ru.itmo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserJpaService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    public User addUser(UserDto userDto) {
        Owner owner = ownerRepository.findDistinctByName(userDto.getUsername());
        List<Role> listRole = new ArrayList<>();
        Role userRole = roleRepository.findRoleByName(Role.RoleType.ROLE_USER);
        listRole.add(userRole);
        User user = new User(owner, owner.getName(), passwordEncoder.encode(userDto.getPassword()), listRole);
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}

package ru.itmo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.models.dtos.UserDetailsConverter;
import ru.itmo.repositories.UserRepositoryJpa;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryJpa userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserDetailsConverter converter;

    @Override
    public UserDetails putUser(UserDetails dto) {

        var user = converter.toModel(dto);
        user.setPassword(encoder.encode(user.getPassword()));

        return converter.toDto(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepository.getByOwner(username);
        if (user == null)
            throw new UsernameNotFoundException(username + "not found");

        return converter.toDto(user);
    }
}

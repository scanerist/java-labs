package ru.itmo.models.dtos;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itmo.models.entities.User;

@Component
public class UserDetailsConverter {

    public UserDetails toDto(User user) {

        return new UserDetailsImpl(user);
    }

    public User toModel(UserDetails dto) {

        return new User(
                dto.getUsername(),
                dto.getPassword(),
                dto.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    }
}

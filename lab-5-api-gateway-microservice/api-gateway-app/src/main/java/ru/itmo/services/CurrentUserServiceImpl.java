package ru.itmo.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {
    @Override
    public String getCurrentUserName() {

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

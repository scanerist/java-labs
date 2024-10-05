package ru.itmo.services;

import ru.itmo.CatDto;

import java.util.List;

public interface CatServiceResult {

    record Success(List<CatDto> dtos) implements CatServiceResult { };

    record AccessDenied() implements CatServiceResult { };
}

package ru.itmo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CatDtoExtended {

    private CatDto catDto;
    private String requestSenderName;
}

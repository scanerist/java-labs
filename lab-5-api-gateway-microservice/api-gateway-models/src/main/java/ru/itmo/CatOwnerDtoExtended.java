package ru.itmo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class CatOwnerDtoExtended {

    private CatOwnerDto ownerDto;
    private String password;
    private List<String> roles;

    public CatOwnerDtoExtended(CatOwnerDto ownerDto, String password, List<String> roles) {
        this.ownerDto = ownerDto;
        this.password = password;
        this.roles = roles;
    }
}

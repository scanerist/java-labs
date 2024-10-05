package ru.itmo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CatBefriendDto {

    private int lCatId;
    private int rCatId;
    private String requestSenderName;
}

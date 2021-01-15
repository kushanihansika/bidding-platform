package com.wonderSoft.bidding.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;
    private String name;
    private String role;

    public UserDTO(String name, String role) {
        this.name = name;
        this.role = role;
    }
}

package com.example.userservicejan24.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignupRestquestDto {

    private String name;
    private String email;
    private String password;
    private List<Long> roleIds;

}

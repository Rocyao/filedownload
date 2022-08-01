package com.demo.cloud.hystrixservice.userservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Getter
    public Long id;

    @Setter
    @Getter
    public String username;

    @Getter
    @Setter
    public String pass;
}

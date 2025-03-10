package com.waa.waaproject.dto;

import com.waa.waaproject.domain.Post;
import com.waa.waaproject.domain.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class UsersDto {
    long id;
    String name;
    String email;
    String password;
    List<Post> posts;
    List<Role> roles;
}

package com.waa.waaproject.dto;

import com.waa.waaproject.domain.Post;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    long id;
    String name;
    String email;
    List<Post> posts;
}

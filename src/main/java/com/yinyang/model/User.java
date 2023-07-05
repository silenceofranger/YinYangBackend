package com.yinyang.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    private String _id;
    private String name;
    private String password; // Encrypted
    private String email;
    private String phone;
    private String gender;  // TODO : MAKE IT ENUM
    private Integer age;
    private Double latitude;
    private Double longitude;
    private String city;
    private Map<String, String> imagesUrlWithIndex;
    private Set<String> matchedUserIds;
    private Set<String> likedUserIds;
    private Set<String> dislikedUserIds;
}

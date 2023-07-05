package com.yinyang.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImagesRequest {
    private String userId;
    private Map<String, String> imagesUrlWithIndex;
}

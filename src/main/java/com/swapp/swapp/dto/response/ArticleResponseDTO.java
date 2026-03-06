package com.swapp.swapp.dto.response;

import java.util.List;

public record ArticleResponseDTO(List<Integer> id, List<String> title, List<String> picture) {

}

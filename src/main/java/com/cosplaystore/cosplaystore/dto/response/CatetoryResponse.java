package com.cosplaystore.cosplaystore.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CatetoryResponse {
    private int id;
    private String name;
    private LocalDateTime updated_at;
    private LocalDateTime created_at;
}

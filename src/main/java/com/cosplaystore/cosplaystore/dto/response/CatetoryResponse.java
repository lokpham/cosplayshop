package com.cosplaystore.cosplaystore.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.cosplaystore.cosplaystore.model.Catetory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CatetoryResponse {
    private int id;
    private String name;
    private Catetory parent;
    private List<Catetory> children;
    private LocalDateTime updated_at;
    private LocalDateTime created_at;
}

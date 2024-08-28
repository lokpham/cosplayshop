package com.cosplaystore.cosplaystore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CatetoryRequest {

    @NotBlank
    @NotNull
    private String name;
    private int id_parent;
}

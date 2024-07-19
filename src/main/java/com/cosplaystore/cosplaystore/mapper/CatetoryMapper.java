package com.cosplaystore.cosplaystore.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cosplaystore.cosplaystore.dto.request.CatetoryRequest;
import com.cosplaystore.cosplaystore.dto.response.CatetoryResponse;
import com.cosplaystore.cosplaystore.model.Catetory;

@Mapper(componentModel = "spring")
public interface CatetoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "updated_at", ignore = true)
    @Mapping(target = "products", ignore = true)
    Catetory toCatetory(CatetoryRequest catetoryRequest);

    CatetoryResponse toCatoryResponse(Catetory catetory);

    List<CatetoryResponse> toListCatetoryResponses(List<Catetory> list);

}

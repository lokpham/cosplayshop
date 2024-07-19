package com.cosplaystore.cosplaystore.service;

import java.util.List;
import java.util.Optional;

import com.cosplaystore.cosplaystore.dto.request.CatetoryRequest;
import com.cosplaystore.cosplaystore.dto.response.CatetoryResponse;
import com.cosplaystore.cosplaystore.model.Catetory;

public interface CatetoryService {
    public Boolean catetoryExisted(String catetoryName);

    public Optional<Catetory> findCatetoryById(int id);

    public CatetoryResponse addCatetory(CatetoryRequest catetoryRequest);

    public Catetory updateCatetory(int id, CatetoryRequest catetoryRequest);

    public Optional<Catetory> catetoryExistedById(int id);

    public void deleteCatetory(int id);

    public List<Catetory> getAllCatetory();

    public Catetory getCatetory(int id);

}

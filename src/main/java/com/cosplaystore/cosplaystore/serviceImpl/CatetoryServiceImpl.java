package com.cosplaystore.cosplaystore.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosplaystore.cosplaystore.dto.request.CatetoryRequest;
import com.cosplaystore.cosplaystore.dto.response.CatetoryResponse;
import com.cosplaystore.cosplaystore.dto.response.Message;
import com.cosplaystore.cosplaystore.exception.GeneralException;
import com.cosplaystore.cosplaystore.mapper.CatetoryMapper;
import com.cosplaystore.cosplaystore.model.Catetory;
import com.cosplaystore.cosplaystore.repository.CatetoryRepo;
import com.cosplaystore.cosplaystore.service.CatetoryService;

@Service
public class CatetoryServiceImpl implements CatetoryService {
    @Autowired
    CatetoryMapper catetoryMapper;
    @Autowired
    CatetoryRepo catetoryRepo;

    @Override
    public CatetoryResponse addCatetory(CatetoryRequest catetoryRequest) {
        Catetory catetory = catetoryMapper.toCatetory(catetoryRequest);

        return catetoryMapper.toCatoryResponse(catetoryRepo.save(catetory));
    }

    @Override
    public Boolean catetoryExisted(String catetoryName) {
        List<Catetory> catetories = catetoryRepo.findByName(catetoryName);
        if (catetories.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void updateCatetory(int id, CatetoryRequest catetoryRequest) {
        Catetory catetory = getCatetoryById(id);
        catetory.setName(catetoryRequest.getName());
        catetoryRepo.save(catetory);

    }

    @Override
    public Optional<Catetory> catetoryExistedById(int id) {
        Optional<Catetory> cOptional = catetoryRepo.findById(id);
        return cOptional;
    }

    @Override
    public Optional<Catetory> findCatetoryById(int id) {

        Optional<Catetory> catetory = catetoryRepo.findById(id);
        return catetory;
    }

    @Override
    public void deleteCatetory(int id) {
        catetoryRepo.deleteById(id);
    }

    @Override
    public List<CatetoryResponse> getAllCatetory() {
        List<Catetory> catetories = catetoryRepo.findAll();
        if (catetories.isEmpty()) {
            throw new GeneralException(Message.CATETORY_LIST_FAILED);
        } else {
            return catetoryMapper.toListCatetoryResponses(catetories);
        }
    }

    @Override
    public CatetoryResponse getCatetory(int id) {
        Catetory catetory = getCatetoryById(id);
        return catetoryMapper.toCatoryResponse(catetory);
    }

    @Override
    public Catetory getCatetoryById(int id) {
        Optional<Catetory> cOptional = catetoryRepo.findById(id);
        if (cOptional.isPresent()) {
            return cOptional.get();
        } else {
            throw new GeneralException(Message.CATETORY_NOT_FOUND);
        }
    }

}

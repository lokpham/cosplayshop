package com.cosplaystore.cosplaystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosplaystore.cosplaystore.model.Catetory;
import java.util.List;

public interface CatetoryRepo extends JpaRepository<Catetory, Integer> {

    List<Catetory> findByName(String name);

}

package com.cosplaystore.cosplaystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cosplaystore.cosplaystore.model.Catetory;
import java.util.List;

@Repository
public interface CatetoryRepo extends JpaRepository<Catetory, Integer> {

    List<Catetory> findByName(String name);

    @Query("SELECT c FROM Catetory c WHERE c.parent IS NULL")
    List<Catetory> findNull();
}

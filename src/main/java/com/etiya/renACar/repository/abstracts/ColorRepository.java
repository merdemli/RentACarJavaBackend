package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.model.entities.concretes.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color,Integer> {

    boolean existsColorByNameIgnoreCase(String name);
}

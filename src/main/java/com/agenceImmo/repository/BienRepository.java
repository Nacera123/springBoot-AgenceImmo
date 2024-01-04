package com.agenceImmo.repository;

import com.agenceImmo.model.Bien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BienRepository extends JpaRepository<Bien, Integer>{


    List<Bien> findByActiveTrue();
}

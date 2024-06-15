package com._assignements.assignment_2.models;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface rectanglerepo extends JpaRepository<rectangle, Integer> {
    List<rectangle> findByWidthAndHeight(int width, int height); 
    List<rectangle> findByName(String name);
}


package com.works.repositories;

import com.works.entities.Current;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentRepository extends JpaRepository<Current, Integer> {
}
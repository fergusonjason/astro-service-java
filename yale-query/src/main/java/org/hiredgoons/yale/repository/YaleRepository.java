package org.hiredgoons.yale.repository;

import org.hiredgoons.yale.entity.Yale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YaleRepository extends JpaRepository<Yale, Integer> {
}

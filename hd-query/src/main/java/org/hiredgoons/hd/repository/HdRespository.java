package org.hiredgoons.hd.repository;

import org.hiredgoons.hd.entity.Hd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HdRespository extends JpaRepository<Hd, Integer> {
}

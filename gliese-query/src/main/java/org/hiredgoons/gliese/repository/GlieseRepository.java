package org.hiredgoons.gliese.repository;

import org.hiredgoons.gliese.entity.Gliese;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlieseRepository extends JpaRepository<Gliese, Long> {
}

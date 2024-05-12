package io.nqa.menetlus.repository;

import io.nqa.menetlus.entity.Menetlus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenetlusRepository extends JpaRepository<Menetlus, Long> {
}

package com.thoughtworks.capability.repository;

import com.thoughtworks.capability.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {

    Optional<Example> findById(Long id);

    Example save(Example example);
}

package tech.nullpointerexception.bender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.nullpointerexception.bender.model.Beer;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer> {



}

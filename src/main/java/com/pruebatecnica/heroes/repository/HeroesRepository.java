package com.pruebatecnica.heroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebatecnica.heroes.entity.Heroes;

/**
 * The Interface HeroesRepository.
 */
@Repository
public interface HeroesRepository  extends JpaRepository<Heroes, Long>{

}

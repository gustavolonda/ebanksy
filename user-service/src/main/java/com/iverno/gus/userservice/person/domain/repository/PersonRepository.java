package com.iverno.gus.userservice.person.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.iverno.gus.userservice.person.domain.entities.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, String>  {

}

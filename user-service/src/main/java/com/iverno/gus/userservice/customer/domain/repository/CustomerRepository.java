package com.iverno.gus.userservice.customer.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.iverno.gus.userservice.customer.domain.entities.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String>  {
	List<CustomerEntity> findByPersonEntityNameContainingIgnoreCaseOrPersonEntityPhoneContainingIgnoreCase(String name, String phone);
	List<CustomerEntity> findTop10ByOrderByPersonEntityNameAsc();
	List<CustomerEntity> findAllByActiveOrderByModifyDateDesc(boolean active);
}

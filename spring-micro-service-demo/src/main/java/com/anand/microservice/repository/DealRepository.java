package com.anand.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anand.microservice.bean.Deal;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {

}

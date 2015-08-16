package com.anand.cpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anand.cpm.bean.Deal;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {

}

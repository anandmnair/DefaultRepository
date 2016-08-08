package com.anand.batch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.anand.batch.bean.Deal;

public interface DealRepository extends ElasticsearchRepository<Deal,Long>{
	
	//@Query(value = "{\"bool\" : {\"must\" : {\"field\" : {\"dealName\" : \"?*\",\"analyze_wildcard\" : true}}}}")
	Page<Deal> findByDealNameLike(String dealName, Pageable pageable);
	
	//@Query(value = "{\"term\" : {\"dealName\" : \"?*\",\"analyze_wildcard\" : true}}")
	//Page<Deal> findByDealName(String dealName, Pageable pageable);

}

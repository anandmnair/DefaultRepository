package com.es.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.es.demo.bean.Deal;

public interface DealRepository extends ElasticsearchRepository<Deal, Long> {

}

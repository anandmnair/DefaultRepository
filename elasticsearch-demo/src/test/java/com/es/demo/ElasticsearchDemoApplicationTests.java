package com.es.demo;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.nestedQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import com.es.demo.bean.Country;
import com.es.demo.bean.Deal;
import com.es.demo.bean.Sbu;
import com.es.demo.repository.DealRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchDemoApplicationTests {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private DealRepository dealRepository;

	@Test
	public void test() {
		dealRepository.deleteAll();
		dealRepository.save(createDeal(1L));
		dealRepository.save(createDeal(2L));
		dealRepository.save(createDeal(3L));
		// IndexQuery indexQuery = new IndexQuery();
		// indexQuery.setIndexName("deals");
		// indexQuery.setType("deal");
		// indexQuery.setObject(deal);
		// dealRepository.save(deal);
		// elasticsearchTemplate.index(indexQuery);
		// elasticsearchTemplate.refresh(Deal.class);

		elasticsearchTemplate.putMapping(Deal.class);

		
		QueryBuilder builder  = boolQuery().must(matchAllQuery())
				.must(termsQuery("dealId", Arrays.asList("1","2")))
				.must(nestedQuery("sbu",boolQuery()
											.must(termsQuery("sbu.sbuCode", Arrays.asList("SbuCode1", "SbuCode2")))
											.must(termsQuery("sbu.country.countryId", Arrays.asList("1","2")))
											//.must(queryStringQuery("Sbu Name 1").field("sbu.sbuName"))
											
								))
				;

		

		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withIndices("deals")
				.withTypes("deal").build();

		System.out.println("searchQuery = " + searchQuery.getQuery().toString());

		Page<Deal> page = elasticsearchTemplate.queryForPage(searchQuery, Deal.class);

		elasticsearchTemplate.deleteIndex(Deal.class);
		assertEquals(2, page.getNumberOfElements());

	}

	private Deal createDeal(long id) {
		Deal deal = new Deal();
		deal.setDealId(id);
		deal.setDealCode("DealCode" + id);
		deal.setDealName("Deal Name " + id);
		deal.setPrimarySbu("PSBU");
		Sbu sbu = new Sbu();
		sbu.setSbuId(1L);
		sbu.setSbuCode("SbuCode" + id);
		sbu.setSbuName("Sbu Name" + id);
		Country country = new Country();
		country.setCountryId(id % 2 == 0 ? 2L : 1L);
		country.setCountryCode(id % 2 == 0 ? "FRA" : "IND");
		country.setCountryName(id % 2 == 0 ? "FRANCE" : "INDIA");
		sbu.setCountry(country);
		deal.setSbu(sbu);
		return deal;
	}

}

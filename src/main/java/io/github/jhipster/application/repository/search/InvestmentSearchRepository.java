package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Investment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Investment entity.
 */
public interface InvestmentSearchRepository extends ElasticsearchRepository<Investment, Long> {
}

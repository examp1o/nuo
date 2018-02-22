package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.CrowdfundingWallet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CrowdfundingWallet entity.
 */
public interface CrowdfundingWalletSearchRepository extends ElasticsearchRepository<CrowdfundingWallet, Long> {
}

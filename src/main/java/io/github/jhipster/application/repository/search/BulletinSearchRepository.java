package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Bulletin;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Bulletin entity.
 */
public interface BulletinSearchRepository extends ElasticsearchRepository<Bulletin, Long> {
}

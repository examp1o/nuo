package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Project;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Project entity.
 */
public interface ProjectSearchRepository extends ElasticsearchRepository<Project, Long> {
}

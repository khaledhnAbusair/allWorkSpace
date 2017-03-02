package com.progressoft.jip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progressoft.jip.entity.SiteEntity;
import com.progressoft.jip.entity.view.SiteView;

@Repository
public interface SiteRepository extends CrudRepository<SiteEntity, Integer> {

	<S extends SiteView> S findBySiteUrl(String siteUrl);
}

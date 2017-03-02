package com.progressoft.jip.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progressoft.jip.entity.DraftPageEntity;
import com.progressoft.jip.entity.PageEntity;
import com.progressoft.jip.entity.view.PageView;

@Repository
public interface DraftPageRepository extends CrudRepository<DraftPageEntity, String> {
	DraftPageEntity findByPublishedPage(PageView pageView);

	void deleteByPublishedPage(PageEntity pageEntity);

}

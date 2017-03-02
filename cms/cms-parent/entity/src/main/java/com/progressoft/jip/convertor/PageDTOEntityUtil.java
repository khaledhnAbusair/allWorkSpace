package com.progressoft.jip.convertor;

import com.progressoft.jip.datastracture.Page;
import com.progressoft.jip.datastracture.Site;
import com.progressoft.jip.entity.PageEntity;
import com.progressoft.jip.entity.SiteEntity;

public class PageDTOEntityUtil {
	private PageDTOEntityUtil() {
	}

	public static Page toDTO(PageEntity pageEntity) {
		Page pageDto = new Page();
		pageDto.setPageContent(pageEntity.getPageContent());
		pageDto.setHtml(pageEntity.getIsHtml());
		pageDto.setPageTitle(pageEntity.getPageTitle());
		pageDto.setPageUrl(pageEntity.getPageUrl());
		pageDto.setPublish(pageEntity.getIsPublish());
		pageDto.setSeo(pageEntity.getSeo());
		pageDto.setSiteId(siteDtoToEntity(pageEntity.getSiteId()));
		return pageDto;
	}

	public static PageEntity toEntity(Page pageDto) {
		PageEntity pageEntity = new PageEntity();
		pageEntity.setHtml(pageDto.getIsHtml());
		pageEntity.setPageContent(pageDto.getPageContent());
		pageEntity.setPageTitle(pageDto.getPageTitle());
		pageEntity.setPageUrl(pageDto.getPageUrl());
		pageEntity.setPublish(pageDto.getIsPublish());
		pageEntity.setSeo(pageDto.getSeo());
		pageEntity.setSiteId(siteEntityToDto(pageDto.getSiteId()));
		return pageEntity;
	}

	private static SiteEntity siteEntityToDto(Site site) {
		SiteEntity siteEntity = new SiteEntity();
		siteEntity.setSiteId(site.getSiteId());
		return siteEntity;
	}

	private static Site siteDtoToEntity(SiteEntity siteEntity) {
		Site site = new Site();
		site.setSiteId(siteEntity.getSiteId());
		return site;
	}
}

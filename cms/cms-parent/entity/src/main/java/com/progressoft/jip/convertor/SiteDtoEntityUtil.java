package com.progressoft.jip.convertor;

import com.progressoft.jip.datastracture.Site;
import com.progressoft.jip.entity.SiteEntity;

public class SiteDtoEntityUtil {

	private SiteDtoEntityUtil() {
	}

	public static Site toDTO(SiteEntity siteEntity) {
		Site site = new Site();
		site.setParentSiteName(siteEntity.getParentSiteName().getSiteName());
		site.setSiteId(siteEntity.getSiteId());
		site.setSiteLogo(siteEntity.getSiteLogo());
		site.setSiteName(siteEntity.getSiteName());
		site.setSiteUrl(siteEntity.getSiteUrl());
		site.setLandingPage(siteEntity.getLandingPage());
		site.setPages(siteEntity.getPages());
		return site;
	}

	public static SiteEntity toEntity(Site site) {
		SiteEntity siteEntity = new SiteEntity();
		siteEntity.setLandingPage(site.getLandingPage());
		siteEntity.setPages(site.getPages());
		// siteEntity.setParentSiteName(site.getParentSiteName());
		siteEntity.setSiteId(site.getSiteId());
		siteEntity.setSiteLogo(site.getSiteLogo());
		siteEntity.setSiteUrl(site.getSiteUrl());
		siteEntity.setSiteName(site.getSiteName());
		return siteEntity;
	}

}

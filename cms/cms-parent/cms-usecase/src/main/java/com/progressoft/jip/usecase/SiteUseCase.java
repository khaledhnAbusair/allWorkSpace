package com.progressoft.jip.usecase;

import java.io.OutputStream;
import java.util.List;

import com.progressoft.jip.entity.PageEntity;
import com.progressoft.jip.entity.SiteEntity;
import com.progressoft.jip.entity.view.SiteView;

public interface SiteUseCase {
	Iterable<SiteEntity> getAllSites();

	SiteView findOneSiteBysiteId(Integer siteId);

	public void editSite(SiteView siteView, String pageUrl);

	List<PageEntity> getAllPages(SiteView siteView);

	void addSite(SiteView siteView, Integer siteId);

	void addSiteLogo(byte[] siteLogo, Integer siteId);

	void copySiteImage(Integer siteId, OutputStream stream);

	List<PageEntity> getAllPagesBySiteUrl(String siteUrl);
}

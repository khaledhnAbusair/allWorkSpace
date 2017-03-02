package com.progressoft.jip.entity.view;

import java.util.Collection;
import java.util.List;

import com.progressoft.jip.entity.DraftPageEntity;
import com.progressoft.jip.entity.PageEntity;
import com.progressoft.jip.entity.SiteEntity;

public interface SiteView {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getSiteId()
	 */
	Integer getSiteId();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getSiteLogo()
	 */
	byte[] getSiteLogo();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getSiteName()
	 */
	String getSiteName();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getSiteUrl()
	 */
	String getSiteUrl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getParentSiteName()
	 */
	SiteEntity getParentSiteName();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getLandingPage()
	 */
	PageView getLandingPage();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getPages()
	 */
	List<PageEntity> getPages();

	Collection<SiteEntity> getSubSite();

	List<DraftPageEntity> getDraftsPages();

}
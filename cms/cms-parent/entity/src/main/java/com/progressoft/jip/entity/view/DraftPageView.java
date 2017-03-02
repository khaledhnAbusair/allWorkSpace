package com.progressoft.jip.entity.view;

import com.progressoft.jip.entity.PageEntity;
import com.progressoft.jip.entity.SiteEntity;

public interface DraftPageView {

	String getPageUrl();

	String getPageTitle();
 
	String getPageContent();

	String getSeo();

	PageEntity getPublishedPage();

	SiteEntity getSiteId();

	Boolean getIsLandingPage();

}
package com.progressoft.jip.entity.view;

public interface PageView {

	String getPageContent();

	String getPageTitle();

	String getPageUrl();

	String getSeo();

	Boolean getIsHtml();

	Boolean getIsPublish();

	SiteView getSiteId();

	Boolean getIsLandingPage();


}
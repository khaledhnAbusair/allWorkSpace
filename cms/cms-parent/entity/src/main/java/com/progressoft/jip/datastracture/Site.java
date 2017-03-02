package com.progressoft.jip.datastracture;

import java.util.List;

import com.progressoft.jip.entity.PageEntity;

public class Site {

	private Integer siteId;

	private byte[] siteLogo;

	private String siteName;

	private String siteUrl;

	private String parentSiteName;

	private PageEntity landingPage;

	private List<PageEntity> pages;

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public byte[] getSiteLogo() {
		return siteLogo;
	}

	public void setSiteLogo(byte[] siteLogo) {
		this.siteLogo = siteLogo;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getParentSiteName() {
		return parentSiteName;
	}

	public void setParentSiteName(String parentSiteName) {
		this.parentSiteName = parentSiteName;
	}

	public PageEntity getLandingPage() {
		return landingPage;
	}

	public void setLandingPage(PageEntity landingPage) {
		this.landingPage = landingPage;
	}

	public List<PageEntity> getPages() {
		return pages;
	}

	public void setPages(List<PageEntity> pages) {
		this.pages = pages;
	}

}

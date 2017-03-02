package com.progressoft.jip.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.progressoft.jip.entity.view.SiteView;

@Entity
@Table(name = "site")
public class SiteEntity implements Serializable, SiteView {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "site_id")
	private Integer siteId;

	@Lob
	@Column(name = "site_logo")
	@Basic(fetch = FetchType.LAZY)
	private byte[] siteLogo;

	@Column(name = "site_name", nullable = false)
	private String siteName;

	@Column(name = "site_url", nullable = false)
	private String siteUrl;

	@ManyToOne
	@JoinColumn(name = "parent_siteName")
	private SiteEntity parentSiteName;

	@OneToMany(mappedBy = "parentSiteName")
	private Collection<SiteEntity> subSite;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "landing_page", nullable = true)
	private PageEntity landingPage;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "siteId", fetch = FetchType.EAGER)
	private List<PageEntity> pages;

	@OneToMany(mappedBy = "siteId", fetch = FetchType.EAGER)
	private List<DraftPageEntity> draftsPages;

	@Override
	public List<DraftPageEntity> getDraftsPages() {
		return draftsPages;
	}

	public void setDraftsPages(List<DraftPageEntity> draftsPages) {
		this.draftsPages = draftsPages;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getSiteId()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getSiteId()
	 */
	@Override
	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getSiteLogo()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getSiteLogo()
	 */
	@Override
	public byte[] getSiteLogo() {
		return siteLogo;
	}

	public void setSiteLogo(byte[] siteLogo) {
		this.siteLogo = siteLogo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getSiteName()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getSiteName()
	 */
	@Override
	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getSiteUrl()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getSiteUrl()
	 */
	@Override
	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getParentSiteName()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getParentSiteName()
	 */
	@Override
	public SiteEntity getParentSiteName() {
		return parentSiteName;
	}

	public void setParentSiteName(SiteEntity parentSiteName) {
		this.parentSiteName = parentSiteName;
	}

	@Override
	public Collection<SiteEntity> getSubSite() {
		return subSite;
	}

	public void setSubSite(Collection<SiteEntity> subSite) {
		this.subSite = subSite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getLandingPage()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getLandingPage()
	 */
	@Override
	public PageEntity getLandingPage() {
		return landingPage;
	}

	public void setLandingPage(PageEntity landingPage) {
		this.landingPage = landingPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getPages()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.progressoft.jip.entity.SiteView#getPages()
	 */
	@Override
	public List<PageEntity> getPages() {
		return pages;
	}

	public void setPages(List<PageEntity> pages) {
		this.pages = pages;
	}

}

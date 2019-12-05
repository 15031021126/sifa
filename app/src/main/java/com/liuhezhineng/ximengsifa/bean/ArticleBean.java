package com.liuhezhineng.ximengsifa.bean;

import com.liuhezhineng.ximengsifa.bean.article.CommentBean;
import com.liuhezhineng.ximengsifa.bean.base.Base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AIqinfeng
 * @date 2018/4/20
 */

public class ArticleBean implements Base {

	private String createByName;
	private String createDate;
	private String updateByName;
	private String updateDate;
	private String id;
	private String title;
	private String link;
	private String image;
	private String keywords;
	private String description;
	private String hits;
	private AgencyCategoryVo agencyCategoryVo;
	private String siteId;
	private String siteName;
	private String files;
	private String type;
	private String thumbsUpCount;
	private List<CommentBean> commentList;
	private String commentCount;
	private String isThumbsUp;
	private String areaName;

    public String getAreaName() {
        return areaName == null ? "" : areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCreateByName() {
		return createByName == null ? "" : createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getCreateDate() {
		return createDate == null ? "" : createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateByName() {
		return updateByName == null ? "" : updateByName;
	}

	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}

	public String getUpdateDate() {
		return updateDate == null ? "" : updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getId() {
		return id == null ? "" : id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title == null ? "" : title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link == null ? "" : link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image == null ? "" : image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getKeywords() {
		return keywords == null ? "" : keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description == null ? "" : description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHits() {
		return hits == null ? "" : hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	public AgencyCategoryVo getAgencyCategoryVo() {
		return agencyCategoryVo;
	}

	public void setAgencyCategoryVo(AgencyCategoryVo agencyCategoryVo) {
		this.agencyCategoryVo = agencyCategoryVo;
	}

	public String getSiteId() {
		return siteId == null ? "" : siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName == null ? "" : siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getFiles() {
		return files == null ? "" : files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getType() {
		return type == null ? "" : type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getThumbsUpCount() {
		return thumbsUpCount == null ? "" : thumbsUpCount;
	}

	public void setThumbsUpCount(String thumbsUpCount) {
		this.thumbsUpCount = thumbsUpCount;
	}

	public List<CommentBean> getCommentList() {
		if (commentList == null) {
			return new ArrayList<>();
		}
		return commentList;
	}

	public void setCommentList(List<CommentBean> commentList) {
		this.commentList = commentList;
	}

	public String getCommentCount() {
		return commentCount == null ? "" : commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getIsThumbsUp() {
		return isThumbsUp == null ? "" : isThumbsUp;
	}

	public void setIsThumbsUp(String isThumbsUp) {
		this.isThumbsUp = isThumbsUp;
	}
}

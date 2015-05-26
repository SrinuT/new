package com.ihm.customer.entites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "slr_seller_review")
public class SlrSellerReview implements java.io.Serializable  {

	private long id;
	private String createdBy;
	private Date createdOn;
	private String internalUserId;
	private String recommendations;
	private String reviews;
	private float starRating;
	private String updatedBy;
	private Date updatedOn;
	private int version;
	private SlrSeller slrSeller;
	
	public SlrSellerReview() {
	}

	public SlrSellerReview(long id) {
		this.id = id;
	}
	
	public SlrSellerReview(long id, SlrSeller slrSeller, String createdBy,
			Date createdOn, String internalUserId, String recommendations,
			String reviews, Float starRating, String updatedBy, Date updatedOn) {
		this.id = id;
		this.slrSeller = slrSeller;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.internalUserId = internalUserId;
		this.recommendations = recommendations;
		this.reviews = reviews;
		this.starRating = starRating;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seller_id")
	public SlrSeller getSlrSeller() {
		return this.slrSeller;
	}

	public void setSlrSeller(SlrSeller slrSeller) {
		this.slrSeller = slrSeller;
	}
	
	@Column(name = "created_by")
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_on", length = 19)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "internal_user_id")
	public String getInternalUserId() {
		return this.internalUserId;
	}

	public void setInternalUserId(String internalUserId) {
		this.internalUserId = internalUserId;
	}

	@Column(name = "recommendations")
	public String getRecommendations() {
		return this.recommendations;
	}

	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}

	@Column(name = "reviews")
	public String getReviews() {
		return this.reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	@Column(name = "star_rating", precision = 12, scale = 0)
	public Float getStarRating() {
		return this.starRating;
	}

	public void setStarRating(Float starRating) {
		this.starRating = starRating;
	}

	@Column(name = "updated_by")
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on", length = 19)
	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
}

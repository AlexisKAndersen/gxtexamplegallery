package com.ratul.gxtexamplegalary.server.entity;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Comments {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    @Column(name="comments")
    private String comments;

    @Persistent
    @Column(name="posted_by")
    private String postedBy;
    
    @Persistent
    @Column(name="posted_date")
    private Date postedDate;

    
    public Comments(String comments, String postedBy, Date date) {
        this.comments = comments;
        this.postedDate = date;
        this.postedBy = postedBy;
   }

    public Long getId() {
        return id;
    }

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

   	
   
}
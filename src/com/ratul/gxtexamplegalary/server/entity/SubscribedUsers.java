package com.ratul.gxtexamplegalary.server.entity;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class SubscribedUsers {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    @Column(name="username")
    private String userName;

    @Persistent
    @Column(name="useremail")
    private String userEmail;
    
        
    public SubscribedUsers(String name, String email) {
        this.userEmail = email;
        this.userName = name;
   }

    public Long getId() {
        return id;
    }

	
	public void setId(Long id) {
		this.id = id;
	}

	public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
	   	
    }
}
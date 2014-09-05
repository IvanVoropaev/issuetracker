package com.voropaev.issuetracker.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotEmpty;

@Alias("issue")
public class Issue implements Serializable {

	private static final long serialVersionUID = -1049582422397680665L;
	
	private Integer id;
	private Date issueDate;
	@NotEmpty
	private String issueName;
	private String issueStatus;
	@NotEmpty
	private String issueDescription;
	private User user;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public String getIssueName() {
		return issueName;
	}
	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}
	public String getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}
	public String getIssueDescription() {
		return issueDescription;
	}
	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Issue: [Id: " + id + ". Date: " + issueDate + ". Name: " + issueName + ". Status: " + issueStatus + ". Description: " + issueDescription + ". User.id: " + user.getId() + ".]";
	}
	
	@Override
	public boolean equals(Object obj){
	    if (this == obj)
	        return true;
	    if (!(obj instanceof Issue))
	        return false;
	    Issue issue = (Issue) obj;
	    return ((id == issue.getId()) 
	    		&& (issueDate.equals(issue.getIssueDate())) 
	    		&& (issueDescription.equals(issue.getIssueDescription()))
	    		&& (issueName.equals(issue.getIssueName()))
	    		&& (issueStatus.equals(issue.getIssueStatus()))
	    		&& (user.getId() == issue.getUser().getId()));
	}
}

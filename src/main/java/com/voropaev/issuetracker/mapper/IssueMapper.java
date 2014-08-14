package com.voropaev.issuetracker.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.voropaev.issuetracker.domain.Issue;

@Repository
public interface IssueMapper {
	public List<Issue> getAllIssue();
	public void addIssue(Issue issue);
	public Issue getIssueById(Integer id);
	public void updateIssueStatus(Issue issue);
}

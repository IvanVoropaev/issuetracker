package com.voropaev.issuetracker.mapper;

import java.util.List;

import com.voropaev.issuetracker.domain.Issue;

public interface IssueMapper {
	public List<Issue> getAllIssue();
	public void addIssue(Issue issue);
	public Issue getIssueById(Integer id);
}

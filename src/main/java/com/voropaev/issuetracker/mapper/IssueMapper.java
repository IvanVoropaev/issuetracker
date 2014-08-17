package com.voropaev.issuetracker.mapper;

import java.util.List;

import com.voropaev.issuetracker.domain.Issue;

/**
 * 
 * @author Ivan Voropaev
 * 
 *<p>������ ��� ������� - �������� Issue</p>
 *
 */

public interface IssueMapper {
	/**
	 *<p>��������� ���� �������</p>
	 */
	public List<Issue> getAllIssue();
	/**
	 *<p>���������� ������</p>
	 *
	 *@param issue �����
	 */
	public void addIssue(Issue issue);
	/**
	 *<p>������� ������ �� ��������������</p>
	 *
	 *@param id ������������� ������
	 */
	public Issue getIssueById(Integer id);
	/**
	 *<p>��������� ������</p>
	 *
	 *@param issue �����
	 */
	public void updateIssueStatus(Issue issue);
}

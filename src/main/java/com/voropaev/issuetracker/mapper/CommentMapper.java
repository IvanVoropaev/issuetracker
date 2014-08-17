package com.voropaev.issuetracker.mapper;

import java.util.List;

import com.voropaev.issuetracker.domain.Comment;

/**
 * 
 * @author Ivan Voropaev
 * 
 *<p>������ ��� ������� - �������� Comment</p>
 *
 */

public interface CommentMapper {
	/**
	 *<p>���������� �����������</p>
	 *
	 *@param comment �����������
	 */
	public void addComment(Comment comment);
	/**
	 *<p>��������� ������ ������������ ��� ������</p>
	 *
	 *@param issueId ������������� ������
	 */
	public List<Comment> getCommentsByIssueId(Integer issueId);
}

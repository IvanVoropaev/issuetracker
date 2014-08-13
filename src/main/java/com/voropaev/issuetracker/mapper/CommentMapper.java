package com.voropaev.issuetracker.mapper;

import java.util.List;

import com.voropaev.issuetracker.domain.Comment;

public interface CommentMapper {
	public void addComment(Comment comment);
	public List<Comment> getCommentsByIssueId(Integer issueId);
}

package com.voropaev.issuetracker.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.voropaev.issuetracker.domain.Comment;

@Repository
public interface CommentMapper {
	public void addComment(Comment comment);
	public List<Comment> getCommentsByIssueId(Integer issueId);
}

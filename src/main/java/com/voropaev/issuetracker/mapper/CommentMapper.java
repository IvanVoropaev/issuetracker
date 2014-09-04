package com.voropaev.issuetracker.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.voropaev.issuetracker.domain.Comment;
import com.voropaev.issuetracker.domain.Issue;
import com.voropaev.issuetracker.domain.User;

@Repository
public interface CommentMapper {
	
	final String INSERT = "INSERT INTO comments (user_id,issue_id,comment_date,comment_status,comment_text)"
						+ "VALUES (#{user.id},#{issue.id},#{commentDate},#{commentStatus},#{commentText})";
	final String SELECT_BY_ISSUE_ID ="SELECT * FROM comments WHERE issue_id = #{issueId}";
	
	@Insert(INSERT)
	public void addComment(Comment comment);
	
	@Select(SELECT_BY_ISSUE_ID)
	@Results(value = {
		@Result(property="id", column="comment_id"),
		@Result(property="commentDate", column="comment_date"),
		@Result(property="commentStatus", column="comment_status"),
		@Result(property="commentText", column="comment_text"),
		@Result(property="user", column="user_id", javaType=User.class, one=@One(select="com.voropaev.issuetracker.mapper.UserMapper.getUserById")),
		@Result(property="issue", column="issue_id", javaType=Issue.class, one=@One(select="com.voropaev.issuetracker.mapper.IssueMapper.getIssueById"))
	})
	public List<Comment> getCommentsByIssueId(Integer issueId);
}

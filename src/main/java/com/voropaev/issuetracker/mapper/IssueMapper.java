package com.voropaev.issuetracker.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.voropaev.issuetracker.domain.Issue;
import com.voropaev.issuetracker.domain.User;

@Repository
public interface IssueMapper {
	
	final String SELECT_ALL = "SELECT * FROM issue";
	final String INSERT = "INSERT INTO issue (user_id,issue_name,issue_date,issue_status,issue_description)"
							+ "VALUES (#{user.id},#{issueName},#{issueDate},#{issueStatus},#{issueDescription})";
	final String SELECT_BY_ID = "SELECT * FROM issue WHERE issue_id = #{id}";
	final String UPDATE = "UPDATE issue"
							+ "SET issue_status = #{issueStatus}"
							+ "WHERE issue_id = #{id}";	
	
	@Select(SELECT_ALL)
	@Results(value = {
		@Result(property="id", column="issue_id"),
		@Result(property="issueDate", column="issue_date"),
		@Result(property="issueName", column="issue_name"),
		@Result(property="issueStatus", column="issue_status"),
		@Result(property="issueDescription", column="issue_description"),
		@Result(property="user", column="user_id", javaType=User.class, one=@One(select="com.voropaev.issuetracker.mapper.UserMapper.getUserById"))
	})
	public List<Issue> getAllIssue();
	
	@Insert(INSERT)
	public void addIssue(Issue issue);
	
	@Select(SELECT_BY_ID)
	@Results(value = {
		@Result(property="id", column="issue_id"),
		@Result(property="issueDate", column="issue_date"),
		@Result(property="issueName", column="issue_name"),
		@Result(property="issueStatus", column="issue_status"),
		@Result(property="issueDescription", column="issue_description"),
		@Result(property="user", column="user_id", javaType=User.class, one=@One(select="com.voropaev.issuetracker.mapper.UserMapper.getUserById"))
	})
	public Issue getIssueById(Integer id);
	
	@Update(UPDATE)
	public void updateIssueStatus(Issue issue);
}

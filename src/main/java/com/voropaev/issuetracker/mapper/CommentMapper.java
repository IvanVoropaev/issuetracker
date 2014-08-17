package com.voropaev.issuetracker.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.voropaev.issuetracker.domain.Comment;

/**
 * 
 * @author Ivan Voropaev
 * 
 *<p>Маппер для объекта - сущности Comment</p>
 *
 */
@Repository(value="commentMapper")
public interface CommentMapper {
	/**
	 *<p>Добавление комментария</p>
	 *
	 *@param comment комментарий
	 */
	public void addComment(Comment comment);
	/**
	 *<p>Получение списка комментариев для отчета</p>
	 *
	 *@param issueId идентификатор отчета
	 */
	public List<Comment> getCommentsByIssueId(Integer issueId);
}

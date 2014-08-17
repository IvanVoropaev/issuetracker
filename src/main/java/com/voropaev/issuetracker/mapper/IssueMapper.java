package com.voropaev.issuetracker.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.voropaev.issuetracker.domain.Issue;

/**
 * 
 * @author Ivan Voropaev
 * 
 *<p>Маппер для объекта - сущности Issue</p>
 *
 */
@Repository(value="issueMapper")
public interface IssueMapper {
	/**
	 *<p>Получение всех отчетов</p>
	 */
	public List<Issue> getAllIssue();
	/**
	 *<p>Добавление отчета</p>
	 *
	 *@param issue отчет
	 */
	public void addIssue(Issue issue);
	/**
	 *<p>Выборка отчета по идентификатору</p>
	 *
	 *@param id идентификатор отчета
	 */
	public Issue getIssueById(Integer id);
	/**
	 *<p>Изменение отчета</p>
	 *
	 *@param issue отчет
	 */
	public void updateIssueStatus(Issue issue);
}

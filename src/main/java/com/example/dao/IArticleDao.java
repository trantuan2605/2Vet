package com.example.dao;

import java.util.List;

import com.example.model.Article;

public interface IArticleDao {
	List<Article> getAllArticles();
	Article getArticleById(int articleId);
	void addArticle(Article article);
	void updateArticle(Article article);
	void deleteArticle(int articleId);
	boolean articleExists(String title, String category);
}

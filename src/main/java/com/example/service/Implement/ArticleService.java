package com.example.service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.IArticleDao;
import com.example.model.Article;
import com.example.service.IArticleService;

@Service
public class ArticleService implements IArticleService{

	@Autowired
	private IArticleDao articleDao;
	@Override
	public List<Article> getAllArticles() {
		// TODO Auto-generated method stub
		return articleDao.getAllArticles();
	}

	@Override
	public Article getArticleById(int articleId) {
		// TODO Auto-generated method stub
		return articleDao.getArticleById(articleId);
	}

	@Override
	public boolean addArticle(Article article) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateArticle(Article article) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteArticle(int articleId) {
		// TODO Auto-generated method stub
		
	}

}

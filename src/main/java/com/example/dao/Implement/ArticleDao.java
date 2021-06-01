package com.example.dao.Implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.IArticleDao;
import com.example.model.Article;
import com.twovet.base.common.BaseDao;

@Transactional
@Repository
public class ArticleDao extends BaseDao implements IArticleDao{
	
	public ArticleDao(EntityManagerFactory emf) {
		super(emf);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAllArticles() {
		EntityManager em = emf.createEntityManager();
		// TODO Auto-generated method stub
		String hql = "From Article as a ORDER BY a.articleId";
		List<Article> list = em.createQuery(hql).getResultList();
		em.close();
		return list;
	}

	@Override
	public Article getArticleById(int articleId) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		Article result = em.find(Article.class, articleId);
		em.close();
		return result;
	}

	@Override
	public void addArticle(Article article) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateArticle(Article article) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteArticle(int articleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean articleExists(String title, String category) {
		// TODO Auto-generated method stub
		return false;
	}

}

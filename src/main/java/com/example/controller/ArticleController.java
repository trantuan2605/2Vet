package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.AppUser;
import com.example.model.Article;
import com.example.model.DemoResult;
import com.example.model.ParamForm;
import com.example.service.IArticleService;
import com.example.service.Implement.AppUserService;
import com.twovet.base.common.ResultBean;

@RestController
@RequestMapping("articles")
public class ArticleController {
	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private AppUserService userService;
	
	@GetMapping("/getData")
	public ResponseEntity<ResultBean<DemoResult>> getArticles(){
		List<Article> articles = articleService.getAllArticles();
		AppUser user = userService.loadUserByUsername(2);
		System.out.println(user.getEncryptedPassword());
		ResultBean<DemoResult> data = new ResultBean<>();
		DemoResult result = new DemoResult();
		result.setPassword(user.getEncryptedPassword());
		result.setUsername(user.getUsername());
		result.setRole_name(user.getAppRoles().iterator().next().getRoleName());
		result.setArticles(articles);
		data.setMessage("success");
		data.setResult(result);
		return ResponseEntity.ok(data);
	}
	
	@GetMapping("/request")
	public ResponseEntity<ResultBean<DemoResult>> getData(@RequestParam("id") int id){
		List<Article> articles = articleService.getAllArticles();
		AppUser user = userService.loadUserByUsername(id);
		System.out.println(user.getEncryptedPassword());
		ResultBean<DemoResult> data = new ResultBean<>();
		DemoResult result = new DemoResult();
		result.setPassword(user.getEncryptedPassword());
		result.setUsername(user.getUsername());
		result.setRole_name(user.getAppRoles().iterator().next().getRoleName());
		result.setArticles(articles);
		data.setMessage("success");
		data.setResult(result);
		return ResponseEntity.ok(data);
	}
	
	@PostMapping("/demoPost")
	public ResponseEntity<ResultBean<DemoResult>> getDataForPost(@RequestBody ParamForm param){
		List<Article> articles = articleService.getAllArticles();
		AppUser user = userService.loadUserByUsername(param.getId());
		System.out.println(user.getEncryptedPassword());
		ResultBean<DemoResult> data = new ResultBean<>();
		DemoResult result = new DemoResult();
		result.setPassword(user.getEncryptedPassword());
		result.setUsername(user.getUsername());
		result.setRole_name(user.getAppRoles().iterator().next().getRoleName());
		result.setArticles(articles);
		data.setMessage("success");
		data.setResult(result);
		return ResponseEntity.ok(data);
	}
	
	@PostMapping("/getData")
	public ResponseEntity<ResultBean<?>> getDetailUser(@RequestBody ParamForm param){
		List<Article> articles = articleService.getAllArticles();
		AppUser user = userService.loadUserByUsername(param.getId());
		System.out.println(user.getEncryptedPassword());
		ResultBean<DemoResult> data = new ResultBean<>();
		DemoResult result = new DemoResult();
		result.setPassword(user.getEncryptedPassword());
		result.setUsername(user.getUsername());
		result.setRole_name(user.getAppRoles().iterator().next().getRoleName());
		result.setArticles(articles);
		data.setMessage("success");
		data.setResult(result);
		return ResponseEntity.ok(data);
	}
}

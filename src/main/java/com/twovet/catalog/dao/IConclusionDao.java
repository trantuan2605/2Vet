package com.twovet.catalog.dao;

import com.twovet.catalog.model.Conclusion;

public interface IConclusionDao {
	public Long insert(Conclusion conclusion);

	public Long edit(Conclusion conclusion);
	
	public Conclusion getDetailConclusion(String examCode);
}

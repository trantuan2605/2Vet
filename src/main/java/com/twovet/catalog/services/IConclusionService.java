package com.twovet.catalog.services;

import javax.servlet.http.HttpServletRequest;

import com.twovet.catalog.dto.ConclusionDTO;
import com.twovet.catalog.model.Conclusion;

public interface IConclusionService {

	public Long insert(Conclusion conclusion,  HttpServletRequest request);
	
	public Long edit(Conclusion conclusion, HttpServletRequest request);
	
	public ConclusionDTO getDetailConclusion(String examCode);
}

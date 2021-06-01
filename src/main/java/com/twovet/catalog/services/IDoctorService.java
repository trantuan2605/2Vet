package com.twovet.catalog.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.twovet.base.common.ResultDto;
import com.twovet.catalog.dto.DoctorDTO;
import com.twovet.catalog.model.Doctor;

public interface IDoctorService {
	public Long insert(Doctor doctor, List<DoctorDTO> lstDoctorCode, HttpServletRequest request);
	
	public Long edit(Doctor doctor, HttpServletRequest request);
	
	public DoctorDTO getDetailDoctor(String doctorCode);
	
	public <T> ResultDto<T> searchAdvance(Doctor doctor, int pageNum, int maxResult);
	
	public <T> ResultDto<T> getDoctorNotInSchedule(String start, String end);
	
	public List<DoctorDTO> getLstDoctorByBranch(String branchCode);
}

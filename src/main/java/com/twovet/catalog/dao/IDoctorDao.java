package com.twovet.catalog.dao;

import java.util.List;

import com.twovet.catalog.dto.DoctorDTO;
import com.twovet.catalog.model.Doctor;

public interface IDoctorDao {
	
	Long insert(Doctor doctor);
	
	Long edit(Doctor doctor);
	
	Doctor getDetailDoctor(String doctorCode);
	DoctorDTO getDetailDoctorExt(String cusCode);
	
	List<DoctorDTO> searchAdvance(Doctor doctor, int firstResult, int maxResult);
	
	Long getTotalSearchAd(Doctor doctor);
	
	List<DoctorDTO> getDoctorNotInSchedule(String start, String end);
	
	List<DoctorDTO> getLstDoctorByBranch(String branchCode);
}

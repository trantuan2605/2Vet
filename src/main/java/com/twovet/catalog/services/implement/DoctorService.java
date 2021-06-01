package com.twovet.catalog.services.implement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twovet.base.common.FunctionCommon;
import com.twovet.base.common.ResultDto;
import com.twovet.base.constant.Constants;
import com.twovet.catalog.dao.implement.DoctorDao;
import com.twovet.catalog.dto.DoctorDTO;
import com.twovet.catalog.dto.SpecDTO;
import com.twovet.catalog.model.Doctor;
import com.twovet.catalog.model.Spec;
import com.twovet.catalog.services.IDoctorService;

@Service
public class DoctorService implements IDoctorService{
	
	@Autowired
	private DoctorDao doctorDao;

	@Override
	public Long insert(Doctor doctor, List<DoctorDTO> lstDoctorCode, HttpServletRequest request) {
		String doctorCode = FunctionCommon.getCodeNextVal(lstDoctorCode, "2VNV", "%03d");
		doctor.setDoctorCode(doctorCode);
		//upload image and get path
		if (doctor.getFileImage().getSize() > 0) {
			String path = FunctionCommon.uploadAndGetPath(doctor.getFileImage(), request);
			doctor.setPath(path);
		}
		return doctorDao.insert(doctor);
	}

	@Override
	public DoctorDTO getDetailDoctor(String doctorCode) {
//		// TODO Auto-generated method stub
		Doctor doctor = doctorDao.getDetailDoctor(doctorCode);
		ModelMapper mm = new ModelMapper();
		DoctorDTO doctorDto = mm.map(doctor, DoctorDTO.class);
		if (doctorDto != null && doctorDto.getGender() != null) {
			switch (doctorDto.getGender()) {
			case 0:
				doctorDto.setGenderStr(Constants.GENDER.FEMALE);
				break;
			case 1:
				doctorDto.setGenderStr(Constants.GENDER.MALE);
				break;
			default:
				doctorDto.setGenderStr(Constants.GENDER.UNDEFINE);
				break;
			}
		} else {
			doctorDto.setGenderStr(Constants.GENDER.UNDEFINE);
		}
		if (doctorDto != null && doctorDto.getDob() != null) {
			 DateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT.DDMMYYYY);
			 doctorDto.setDobStr(formatter.format(doctorDto.getDob()));
		}
		if (doctorDto != null && doctorDto.getDow() != null) {
			DateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT.DDMMYYYY);
			doctorDto.setDowStr(formatter.format(doctorDto.getDow()));
		}
		
		// set full address
		DoctorDTO doctorExt = doctorDao.getDetailDoctorExt(doctorCode);
		doctorDto.setWardName(doctorExt.getWardName());
		doctorDto.setDistrictId(doctorExt.getDistrictId());
		doctorDto.setDistrictName(doctorExt.getDistrictName());
		doctorDto.setProvinceId(doctorExt.getProvinceId());
		doctorDto.setProvinceName(doctorExt.getProvinceName());
		return doctorDto;
	}

	@Override
	public Long edit(Doctor doctor, HttpServletRequest request) {
		if (doctor.getFileImage().getSize() > 0) {
			String path = FunctionCommon.uploadAndGetPath(doctor.getFileImage(), request);
			doctor.setPath(path);
		} else {
			doctor.setPath(StringUtils.isNotBlank(doctor.getPathHidden()) ? doctor.getPathHidden() : null);
		}
		return doctorDao.edit(doctor);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> ResultDto<T> searchAdvance(Doctor doctor, int pageNum, int maxResult) {
		List<DoctorDTO> lst = doctorDao.searchAdvance(doctor, (pageNum -1)*maxResult, maxResult);
		ResultDto<DoctorDTO> result = new ResultDto<>();
		result.setDatas((List<DoctorDTO>) lst);
		Long total = doctorDao.getTotalSearchAd(doctor);
		int lastPageNumber = (int) Math.ceil(total.doubleValue()/maxResult);
		result.setTotalRecord(total.intValue());
		result.setLastPage(lastPageNumber);
		return (ResultDto<T>) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> ResultDto<T> getDoctorNotInSchedule(String start, String end) {
		List<DoctorDTO> lst = doctorDao.getDoctorNotInSchedule(start, end);
		ResultDto<DoctorDTO> result = new ResultDto<>();
		result.setDatas((List<DoctorDTO>) lst);
		return (ResultDto<T>) result;
	}

	@Override
	public List<DoctorDTO> getLstDoctorByBranch(String branchCode) {
		List<DoctorDTO> result = doctorDao.getLstDoctorByBranch(branchCode);
		return result;
	}

}

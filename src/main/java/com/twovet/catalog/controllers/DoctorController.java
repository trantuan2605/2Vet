package com.twovet.catalog.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.twovet.base.common.BaseController;
import com.twovet.base.common.FunctionCommon;
import com.twovet.base.common.ParamBean;
import com.twovet.base.common.ResultDto;
import com.twovet.base.common.SearchAdvanceParamBean;
import com.twovet.base.constant.ViewNameConstants;
import com.twovet.catalog.dto.BranchDTO;
import com.twovet.catalog.dto.CustomerDTO;
import com.twovet.catalog.dto.DistrictDTO;
import com.twovet.catalog.dto.DoctorDTO;
import com.twovet.catalog.dto.ProvinceDTO;
import com.twovet.catalog.dto.WardDTO;
import com.twovet.catalog.model.Doctor;
import com.twovet.catalog.services.implement.BranchService;
import com.twovet.catalog.services.implement.DoctorService;
import com.twovet.catalog.services.implement.ProvinceService;

@Controller
@RequestMapping("catalog/doctor")
public class DoctorController extends BaseController{
	@Autowired 
	DoctorService doctorService;
	@Autowired
	ProvinceService provinceService;
	@Autowired
	BranchService branchService;
	
	private List<BranchDTO> branchs;
	private List<ProvinceDTO> provinces;
	private List<DistrictDTO> districts;
	private List<WardDTO> wards;
	private List<DoctorDTO> lstDoctorSeqCode;
	
	@Override
	public String initScreen(Model model, HttpSession session) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		setActiveLink(attr, session);
		model.addAttribute("title", "Danh mục Bác sĩ");
		ResultDto<DoctorDTO> result = doctorService.searchAdvance(new Doctor(), 0, 0);
		if (result.getDatas() != null && !result.getDatas().isEmpty()) {
			lstDoctorSeqCode = result.getDatas();
		} else {
			lstDoctorSeqCode = new ArrayList<>();
		}
		result.setCurrentPage(1);
		result.setMaxSize(size);
		model.addAttribute("lstDoctor", result);
		model.addAttribute("doctorDto", new DoctorDTO());
		Doctor doctor = new Doctor();
		
		model.addAttribute("doctor", doctor);
		// get list province
		provinces = provinceService.getAll();
		model.addAttribute("provinces", provinces != null ? provinces : new ArrayList<>());
		model.addAttribute("districts", new ArrayList<DistrictDTO>());
		model.addAttribute("wards", new ArrayList<WardDTO>());
		branchs = branchService.getAllBranchs();
		model.addAttribute("branchs", branchs);
		return ViewNameConstants.CATALOG_DOCTOR;
	}

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		// TODO Auto-generated method stub
		new FunctionCommon(servletRequestAttributes, httpSession);
	}
	
	@PostMapping(value = "/add")
	public String addNew(ModelMap map, HttpServletRequest request,@ModelAttribute Doctor paramBean) {
		Long count = doctorService.insert(paramBean, lstDoctorSeqCode, request);
		ResultDto<DoctorDTO> result = doctorService.searchAdvance(new Doctor(), 0, 0);
		result.setCurrentPage(1);
		result.setMaxSize(size);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
			lstDoctorSeqCode = result.getDatas();
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstDoctor", result);
		return "pages/catalog/doctor :: #refresh-section";
	}
	
	@PostMapping(value = "/edit")
	public String edit(ModelMap map, HttpServletRequest request, @ModelAttribute Doctor paramBean) {
		Doctor doctor = paramBean;
		Long count = doctorService.edit(doctor, request);
		ResultDto<DoctorDTO> result = doctorService.searchAdvance(new Doctor(), 0, 0);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstDoctor", result);
		map.addAttribute("branchs", branchs);
		return "pages/catalog/doctor :: #refresh-section";
	}
	
	@GetMapping("/showDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String doctorCode = request.getParameter("doctorCode");
		String modeScreen = request.getParameter("mode");
		DoctorDTO result = doctorService.getDetailDoctor(doctorCode);
		map.addAttribute("doctorDto", result);
		map.addAttribute("provinces", provinces != null ? provinces : new ArrayList<ProvinceDTO>());
		districts = new ArrayList<DistrictDTO>();
		districts.add(new DistrictDTO(result.getDistrictId(), result.getDistrictName(), result.getProvinceId()));
		map.addAttribute("districts", districts);
		wards = new ArrayList<WardDTO>();
		wards.add(new WardDTO(result.getWardId(), result.getWardName(), result.getDistrictId()));
		map.addAttribute("wards", wards);
		map.addAttribute("branchs", branchs);
		return "edit".equals(modeScreen) ? "pages/catalog/doctorEdit :: #detailDoctorEdit" : "pages/catalog/doctorCrud :: #detailDoctor";
	}
	
	@PostMapping(value = "/searchAdvance", consumes = "application/json", produces = "application/json")
	public String searchAdvance(ModelMap map, HttpServletRequest request, @RequestBody ParamBean<Doctor> paramBean) {
		String currentPageStr = paramBean.getCurrentPage();
		Doctor doctor = paramBean.getData();
		int currentPage = currentPageStr != null ? Integer.valueOf(currentPageStr) : 1;
		ResultDto<DoctorDTO> result = doctorService.searchAdvance(doctor, currentPage, size);
		result.setCurrentPage(Integer.valueOf(currentPage));
		result.setMaxSize(size);
		map.addAttribute("lstDoctor", result);
		return "pages/catalog/doctor :: #refresh-section";
	}

	public List<ProvinceDTO> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<ProvinceDTO> provinces) {
		this.provinces = provinces;
	}

	public List<DistrictDTO> getDistricts() {
		return districts;
	}

	public void setDistricts(List<DistrictDTO> districts) {
		this.districts = districts;
	}

	public List<WardDTO> getWards() {
		return wards;
	}

	public void setWards(List<WardDTO> wards) {
		this.wards = wards;
	}

	public List<DoctorDTO> getLstDoctorSeqCode() {
		return lstDoctorSeqCode;
	}

	public void setLstDoctorSeqCode(List<DoctorDTO> lstDoctorSeqCode) {
		this.lstDoctorSeqCode = lstDoctorSeqCode;
	}

	public final List<BranchDTO> getBranchs() {
		return branchs;
	}

	public final void setBranchs(List<BranchDTO> branchs) {
		this.branchs = branchs;
	}
	
}

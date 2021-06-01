package com.twovet.base.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.twovet.catalog.dto.BranchDTO;
import com.twovet.catalog.dto.CustomerDTO;
import com.twovet.catalog.dto.DistrictDTO;
import com.twovet.catalog.dto.DoctorDTO;
import com.twovet.catalog.dto.WardDTO;
import com.twovet.catalog.services.implement.DistrictService;
import com.twovet.catalog.services.implement.WardService;

@Controller
@RequestMapping("api/")
public class CommonApi {
	@Autowired
	DistrictService districtService;
	@Autowired
	WardService wardService;
	
	public final static String FRAGMENT_DISTRICT = " :: #reload-district";
	public final static String FRAGMENT_WARD = " :: #reload-ward";
	public final static String FRAGMENT_PREFIX = " :: #";
	
	@GetMapping("/getDistrict")
	public String getDistrict(ModelMap map, HttpServletRequest request) {
		String provinceId = request.getParameter("provinceId");
		String fragmentResult = request.getParameter("fragmentResult");
		String fragment = request.getParameter("fragment");
		List<DistrictDTO> districts = districtService.getLstDistrict(provinceId);
		map.addAttribute("districts", districts);
		if (fragmentResult.contains("customer")) {
			map.addAttribute("customerDto", new CustomerDTO());
		}
		if (fragmentResult.contains("doctor")) {
			map.addAttribute("doctorDto", new DoctorDTO());
		}
		if (fragmentResult.contains("branch")) {
			map.addAttribute("branchDto", new BranchDTO());
		}
		String idFragment = fragmentResult.concat(FRAGMENT_PREFIX).concat(fragment); 
		return idFragment;
	}
	
	@GetMapping("/getWard")
	public String getWard(ModelMap map, HttpServletRequest request) {
		String districtId = request.getParameter("districtId");
		String fragmentResult = request.getParameter("fragmentResult");
		String fragment = request.getParameter("fragment");
		List<WardDTO> wards = wardService.getLstWard(districtId);
		map.addAttribute("wards", wards);
		if (fragmentResult.contains("customer")) {
			map.addAttribute("customerDto", new CustomerDTO());
		}
		if (fragmentResult.contains("doctor")) {
			map.addAttribute("doctorDto", new DoctorDTO());
		}
		if (fragmentResult.contains("branch")) {
			map.addAttribute("branchDto", new BranchDTO());
		}
		map.addAttribute("customerDto", new CustomerDTO());
		String idFragment = fragmentResult.concat(FRAGMENT_PREFIX).concat(fragment); 
		return idFragment;
	}

}

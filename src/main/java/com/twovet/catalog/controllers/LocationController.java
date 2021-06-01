package com.twovet.catalog.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twovet.base.common.ResultBean;
import com.twovet.catalog.dto.DistrictDTO;
import com.twovet.catalog.dto.ProvinceDTO;
import com.twovet.catalog.dto.WardDTO;
import com.twovet.catalog.services.implement.DistrictService;
import com.twovet.catalog.services.implement.ProvinceService;
import com.twovet.catalog.services.implement.WardService;

@Controller
@RequestMapping("catalog/location")
public class LocationController  {

	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private DistrictService districtService;
	@Autowired
	private WardService wardService;

	@GetMapping("/getLstProvince")
	@ResponseBody
	public ResponseEntity<?> getLstProvince(ModelMap map, HttpServletRequest request) {
		ResultBean<ProvinceDTO> result = new ResultBean<>();
		List<ProvinceDTO> lstProvince = new ArrayList<>();
		lstProvince = provinceService.getAll();
		result.setResults(lstProvince);
		return ResponseEntity.ok(result);

	}
	
	@GetMapping("/getLstDistrictByProvinceId")
	@ResponseBody
	public ResponseEntity<?> getLstDistrictByProvinceId(ModelMap map, HttpServletRequest request) {
		ResultBean<DistrictDTO> result = new ResultBean<>();
		String provinceId = request.getParameter("provinceId");
		List<DistrictDTO> lstDistrict = new ArrayList<>();
		lstDistrict = districtService.getLstDistrict(provinceId);
		result.setResults(lstDistrict);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/getLstWardByDistrictId")
	@ResponseBody
	public ResponseEntity<?> getLstWardByDistrictId(ModelMap map, HttpServletRequest request) {
		ResultBean<WardDTO> result = new ResultBean<>();
		String districtId = request.getParameter("districtId");
		List<WardDTO> lstWard = new ArrayList<>();
		lstWard = wardService.getLstWard(districtId);
		result.setResults(lstWard);
		return ResponseEntity.ok(result);
	}

}

package com.twovet.examination.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.twovet.base.common.BaseController;
import com.twovet.base.common.FunctionCommon;
import com.twovet.base.common.ResultDto;
import com.twovet.base.constant.ViewNameConstants;
import com.twovet.catalog.dto.BranchDTO;
import com.twovet.catalog.dto.DoctorDTO;
import com.twovet.catalog.dto.ExamClinicalDTO;
import com.twovet.catalog.dto.ExamSubClinicalDTO;
import com.twovet.catalog.dto.SymptomDTO;
import com.twovet.catalog.model.Branch;
import com.twovet.catalog.model.Doctor;
import com.twovet.catalog.model.ExamSubClinical;
import com.twovet.catalog.services.implement.BranchService;
import com.twovet.catalog.services.implement.ConclusionService;
import com.twovet.catalog.services.implement.DoctorService;
import com.twovet.catalog.services.implement.ExamClinicalService;
import com.twovet.catalog.services.implement.ExamSubClinicalService;
import com.twovet.catalog.services.implement.SymptomService;
import com.twovet.widget.dto.GroupItemDTO;
import com.twovet.widget.services.implement.WidgetService;

@Controller
@RequestMapping("examination/summary")
public class SummaryController extends BaseController{
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ExamClinicalService examClinicalService;
	
	@Autowired
	ExamSubClinicalService examSubClinicalService;
	
	@Autowired
	ConclusionService conclusionService;
	
	private List<ExamSubClinicalDTO> subExams;
	
	@Autowired
	DoctorService doctorService;
	ResultDto<DoctorDTO> lstDoctor =  new ResultDto<>();
	
	@Autowired
	BranchService branchService;
	ResultDto<BranchDTO> lstBranch =  new ResultDto<>();
	
	@Autowired
	WidgetService widgetService;
	List<GroupItemDTO> lstGroup3NDByAppGr = new ArrayList<>();
	
	@Autowired
	SymptomService symptomService;
	List<SymptomDTO> lstSymptom = new ArrayList<>();
	
	List<GroupItemDTO> lstServiceSubEx = new ArrayList<>();
	List<SymptomDTO> lstSymptomSubEx = new ArrayList<>();
	
	
	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);
	}

	@Override
	public String initScreen(Model model, HttpSession httpSession) {
		String examCode = request.getParameter("examCode");
		ExamClinicalDTO examDTO = examClinicalService.getViewDetailExam(examCode);
		ExamSubClinical param = new ExamSubClinical();
		param.setExamClinicalCode(examCode);
		subExams = examSubClinicalService.getListExecuteDateByCode(param,0, 0); 
		model.addAttribute("title", "Tổng quát khám chữa");
		model.addAttribute("examDTO", examDTO);
		model.addAttribute("subExams", subExams);
		model.addAttribute("conclutionDto", conclusionService.getDetailConclusion(examCode));
		//init doctor
		lstDoctor = doctorService.searchAdvance(new Doctor(), 0, 0);
		model.addAttribute("lstDoctor", lstDoctor.getDatas());
		//init branch
		lstBranch = branchService.searchAdvance(new Branch(), 0, 0);
		model.addAttribute("lstBranch", lstBranch.getDatas());
		/// init lstGroup3NDByAppGr
		lstGroup3NDByAppGr = widgetService.getLstGroup3NDByAppGr("1");
		model.addAttribute("lstService", lstGroup3NDByAppGr);
		/// init symptom list code
		lstSymptom = symptomService.listAllSymptom();
		model.addAttribute("lstSymptom", lstSymptom);
		ExamClinicalDTO examClinicalDTO = examClinicalService.getViewDetailExam(examCode);
		if(examClinicalDTO != null) {
			String lstServiceCdStr = examClinicalDTO.getServiceCodeList();
			final List<String> lstServiceCd = Arrays.asList(lstServiceCdStr.split(","));
			List<String> lstServiceCdTrim = lstServiceCd.stream().map(String::trim).collect(Collectors.toList());
			lstServiceSubEx = lstGroup3NDByAppGr.stream().
					filter(group3nd -> lstServiceCdTrim.contains(group3nd.getGroup3ndCode())).
					collect(Collectors.toList());
			
			String lstSymptomCdStr = examClinicalDTO.getSymptomCodeList();
			final List<String> lstSymptomCd = Arrays.asList(lstSymptomCdStr.split(","));
			List<String> lstSymptomCdTrim = lstSymptomCd.stream().map(String::trim).collect(Collectors.toList());
			lstSymptomSubEx = lstSymptom.stream().
					filter(item -> lstSymptomCdTrim.contains(item.getSymptomCode())).
					collect(Collectors.toList());
		}
		model.addAttribute("lstServiceSubEx", lstServiceSubEx);
		model.addAttribute("lstSymptomSubEx", lstSymptomSubEx);
		return ViewNameConstants.EXAM_SUMMARY;
	}
	
	@GetMapping("/showDataDetail")
	public String showDataDetail(ModelMap map) {
		String examCode = request.getParameter("examCode");
		String executionDate = request.getParameter("executionDate");
		ExamSubClinical param = new ExamSubClinical();
		param.setExamClinicalCode(examCode);
		param.setExecutionDateStr(executionDate);
		List<ExamSubClinicalDTO> lstProgress3s = examSubClinicalService.getListSeviceByCode(param,0, 0);
		map.addAttribute("lstProgress3s", lstProgress3s != null ? lstProgress3s : new ArrayList<>());
		map.addAttribute("conclutionDto", conclusionService.getDetailConclusion(examCode));
		
		
		return "pages/examination/progress5 :: #tbodyData";
	}
	

}

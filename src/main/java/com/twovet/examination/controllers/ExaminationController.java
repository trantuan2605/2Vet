package com.twovet.examination.controllers;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twovet.base.common.BaseController;
import com.twovet.base.common.FunctionCommon;
import com.twovet.base.common.ResultDto;
import com.twovet.base.constant.Constants;
import com.twovet.base.constant.ViewNameConstants;
import com.twovet.catalog.dto.BranchDTO;
import com.twovet.catalog.dto.ConclusionDTO;
import com.twovet.catalog.dto.CustomerDTO;
import com.twovet.catalog.dto.DistrictDTO;
import com.twovet.catalog.dto.DoctorDTO;
import com.twovet.catalog.dto.ExamClinicalCommonDTO;
import com.twovet.catalog.dto.ExamClinicalDTO;
import com.twovet.catalog.dto.ExamSubClinicalDTO;
import com.twovet.catalog.dto.ExamSubClinicalRequestDTO;
import com.twovet.catalog.dto.PetDTO;
import com.twovet.catalog.dto.ProvinceDTO;
import com.twovet.catalog.dto.SymptomDTO;
import com.twovet.catalog.dto.VacinationDTO;
import com.twovet.catalog.dto.WardDTO;
import com.twovet.catalog.model.Branch;
import com.twovet.catalog.model.Conclusion;
import com.twovet.catalog.model.Customer;
import com.twovet.catalog.model.CustomerType;
import com.twovet.catalog.model.Doctor;
import com.twovet.catalog.model.ExamClinical;
import com.twovet.catalog.model.ExamSubClinical;
import com.twovet.catalog.model.Pet;
import com.twovet.catalog.services.implement.BranchService;
import com.twovet.catalog.services.implement.ConclusionService;
import com.twovet.catalog.services.implement.CustomerService;
import com.twovet.catalog.services.implement.DoctorService;
import com.twovet.catalog.services.implement.ExamClinicalService;
import com.twovet.catalog.services.implement.ExamSubClinicalService;
import com.twovet.catalog.services.implement.PetService;
import com.twovet.catalog.services.implement.RegisExaminationService;
import com.twovet.catalog.services.implement.SymptomService;
import com.twovet.catalog.services.implement.VacinationService;
import com.twovet.widget.dto.GroupItemDTO;
import com.twovet.widget.services.implement.WidgetService;

import groovy.util.logging.Log4j;

@Log4j
@Controller
@RequestMapping("examination")
public class ExaminationController extends BaseController{
	
	@SuppressWarnings("unused")
	private List<ExamClinicalCommonDTO> lstExamSeqCode;
	
	private List<BranchDTO> branchs;
	
	@Autowired
	RegisExaminationService eventService;
	@Autowired
	BranchService branchService;
	@Autowired
	CustomerService customerService;
	@Autowired
	PetService petService;
	@Autowired
	DoctorService doctorService;
	
	@Autowired
	ExamClinicalService examClinicalService;
	
	@Autowired
	WidgetService widgetService;
	
	@Autowired
	private VacinationService vacineService;
	
	@Autowired
	private ConclusionService conclusionService;
	
	
	ResultDto<PetDTO> lstPet = new ResultDto<>();
	ResultDto<DoctorDTO> lstDoctor =  new ResultDto<>();
	List<GroupItemDTO> lstGroup3NDByAppGr = new ArrayList<>();
	List<VacinationDTO> lstVacine =  new ArrayList<>();
	ResultDto<BranchDTO> lstBranch =  new ResultDto<>();
	List<GroupItemDTO> lstServiceSubEx = new ArrayList<>();
	List<SymptomDTO> lstSymptom = new ArrayList<>();
	CustomerDTO lstCusInfo = new CustomerDTO();
	private String examCode ;
	private String petCode ;
	private Long examId ;
	private Date sysDate = new Date();
	private String timeIn;
	private String contextRoot;
	@Autowired
	ExamSubClinicalService examSubClinicalService;
	
	@Autowired
	SymptomService symptomService;
 
	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);
	}
	
	private int activePanelNum;

	@Override
	public String initScreen(Model model, HttpSession httpSession) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		contextRoot = httpSession.getServletContext().getContextPath();
		List<ExamClinicalCommonDTO> lstExamDTO = new ArrayList<>();
		ExamClinical param = new ExamClinical();
		lstExamDTO = (List<ExamClinicalCommonDTO>) examClinicalService.getExamClinical(param, 0, 0);
		if (lstExamDTO.isEmpty()) {
			lstExamSeqCode = new ArrayList<>();
		} else {
			lstExamSeqCode = lstExamDTO;
		}
		setActiveLink(attr, httpSession);
		model.addAttribute("title", "Danh sách khám chữa");
		model.addAttribute("examiDTO", new ExamClinicalCommonDTO());
		model.addAttribute("contextRoot", contextRoot);
		model.addAttribute("isDoctor", FunctionCommon.checkRole(httpSession, Constants.ROLE.DOCTOR));
		model.addAttribute("modeAdd", Constants.EVENT.IS_NOT_MODE_ADD);
		model.addAttribute("contextRoot", contextRoot);
		model.addAttribute("listExam", lstExamDTO);
		
		return ViewNameConstants.EXAM_LIST;
	}
	
	@GetMapping("/progress")
	public String redirectToProgress(Model model, HttpSession httpSession, HttpServletRequest request) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		String contextRoot = httpSession.getServletContext().getContextPath();
		// initial Customer Type
		lstPet = petService.searchAdvance(new Pet(), 0, 0);
		/// init lstGroup3NDByAppGr
		lstGroup3NDByAppGr = widgetService.getLstGroup3NDByAppGr("1");
		model.addAttribute("lstService", lstGroup3NDByAppGr);
		// innit Vacine
		lstVacine = vacineService.getAllVacinations();
		model.addAttribute("lstVacine", lstVacine);
		//init doctor
		lstDoctor = doctorService.searchAdvance(new Doctor(), 0, 0);
		model.addAttribute("lstDoctor", lstDoctor.getDatas());
		//init branch
		lstBranch = branchService.searchAdvance(new Branch(), 0, 0);
		model.addAttribute("lstBranch", lstBranch.getDatas());
		
		//init sysdate
		model.addAttribute("standardDate", sysDate);
		
		/// init symptom list code
		lstSymptom = symptomService.listAllSymptom();
		model.addAttribute("lstSymptom", lstSymptom);

		int activePanelNum = 0;
		String activePanelStr = request.getParameter("activePanelNum");
		
		if (StringUtils.isNotBlank(activePanelStr)) {
			try {
				activePanelNum = Integer.parseInt(activePanelStr);
			} catch (Exception e) {
				
			}
		}
		setActiveLink(attr, httpSession);
		model.addAttribute("title", "Thông tin khám chữa");
		model.addAttribute("examiDTO", new ExamClinicalCommonDTO());
		model.addAttribute("contextRoot", contextRoot);
		model.addAttribute("isDoctor", FunctionCommon.checkRole(httpSession, Constants.ROLE.DOCTOR));
		model.addAttribute("modeAdd", Constants.EVENT.IS_NOT_MODE_ADD);
		model.addAttribute("contextRoot", contextRoot);
		//model.addAttribute("lstPet", result.getDatas());
		model.addAttribute("petDto", new PetDTO());
		model.addAttribute("customerDto", new CustomerDTO());

		branchs = branchService.getAllBranchs();
		model.addAttribute("branchs", branchs);
		model.addAttribute("activePanelNum", activePanelNum);
		String examClinicalCode = request.getParameter("examCode");
		String examIdStr = request.getParameter("examId");
		String petCodeStr = request.getParameter("petCode");
		String timeInStr = request.getParameter("timeIn");
		
		model.addAttribute("examCode", examClinicalCode);
		model.addAttribute("examId", examIdStr);
		model.addAttribute("petCode", petCodeStr);
		model.addAttribute("timeIn", timeInStr);
		if (StringUtils.isNoneBlank(examIdStr)) {
			model.addAttribute("examIdHidden", examIdStr);
		}
		String lstServiceCdStr = "";
		if (StringUtils.isNoneBlank(examClinicalCode)) {
			ExamClinical examDtl = examClinicalService.getDetailExam(examClinicalCode);
			if (examDtl != null) {
				lstServiceCdStr = examDtl.getServiceCodeList();
				model.addAttribute("serviceCodeLstHidden", lstServiceCdStr);
			}
		}
		
		if (StringUtils.isNotBlank(lstServiceCdStr)) {
			final List<String> lstServiceCd = Arrays.asList(lstServiceCdStr.split(","));
			List<String> lstServiceCdTrim = lstServiceCd.stream().map(String::trim).collect(Collectors.toList());
			lstServiceSubEx = lstGroup3NDByAppGr.stream().
					filter(group3nd -> lstServiceCdTrim.contains(group3nd.getGroup3ndCode())).
					collect(Collectors.toList());
		}
		
		model.addAttribute("lstServiceSubEx", lstServiceSubEx);
		
		List<ExamSubClinicalDTO> lstProgress3s = null;
		if (activePanelNum == Constants.EXAMINA_SCREEN.SUB_CLINICAL) {
			model.addAttribute("examCodeHidden", examClinicalCode);
		}
		
		lstProgress3s = examSubClinicalService.getListProgress3Dto(examClinicalCode);
		Map<Long, List<String>> mapSelected = new HashMap<>();
		
		if (lstProgress3s != null && !lstProgress3s.isEmpty()) {
			for (ExamSubClinicalDTO examSubClinicalDTO : lstProgress3s) {
				List<String> itemCodes = new ArrayList<>();
				if (StringUtils.isNotBlank(examSubClinicalDTO.getExtItemCode())) {
					itemCodes = Arrays.asList(examSubClinicalDTO.getExtItemCode().split(","));
				}
				mapSelected.put(examSubClinicalDTO.getId(), itemCodes);
			}
		}
		
		ConclusionDTO progress4Dtl = null;
//		if (activePanelNum == Constants.EXAMINA_SCREEN.CONCLUSION) {
//			progress4Dtl = conclusionService.getDetailConclusion(examClinicalCode);
//			model.addAttribute("progress4Dtl", progress4Dtl);
//		}
		progress4Dtl = conclusionService.getDetailConclusion(examClinicalCode);
		model.addAttribute("lstProgress3s", lstProgress3s != null ? lstProgress3s : new ArrayList<>());
		model.addAttribute("progress4Dtl", progress4Dtl != null ? progress4Dtl : new ConclusionDTO());
		model.addAttribute("mapSelected", mapSelected);
		model.addAttribute("examDTO", new ExamClinicalDTO());
		model.addAttribute("contextRoot", contextRoot);
		return ViewNameConstants.EXAM_PROGRESS;
	}
	
	@SuppressWarnings("unused")
	@PostMapping(value = "/process1")
	public String addExam(ModelMap map, HttpServletRequest request, @ModelAttribute ExamClinical paramBean) {
		ExamClinical exam = examClinicalService.insert(paramBean, lstExamSeqCode);
		examCode  = exam.getExamClinicalCode();
		examId = exam.getId();
		petCode = exam.getPetCode();
		
		List<ExamClinicalCommonDTO> result = new ArrayList<>();
		ExamClinical param = new ExamClinical();
		result = (List<ExamClinicalCommonDTO>) examClinicalService.getExamClinical(param, 0, 0);
		
		String message = "";
		if (exam != null) {
			message = "SUCCESS";
			lstExamSeqCode = result;
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("examCode", examCode);
		map.addAttribute("examId", examId);
		map.addAttribute("petCode", petCode);
		map.addAttribute("lstExam", result);
		
		return "pages/examination/progress2 :: #progress2Step";
	}
	
	
	@PostMapping(value = "/process2")
//	@ResponseBody
	public String process2(ModelMap map, HttpServletRequest request, @ModelAttribute ExamClinical paramBean) {
		ExamClinical exam = paramBean;
		if (StringUtils.isNotBlank(exam.getServiceCodeList())) {
			List<String> lstServiceCd = Arrays.asList(exam.getServiceCodeList().split(","));
			List<String> lstServiceCdTrim = lstServiceCd.stream().map(String::trim).collect(Collectors.toList());
			lstServiceSubEx = lstGroup3NDByAppGr.stream().
					filter(group3nd -> lstServiceCdTrim.contains(group3nd.getGroup3ndCode())).
					collect(Collectors.toList());
		}
		Long count = examClinicalService.edit(exam);
		timeIn = FunctionCommon.getDate2String(exam.getHospitalizeDate());
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
		map.addAttribute("lstServiceSubEx", lstServiceSubEx);
		map.addAttribute("lstDoctor", lstDoctor.getDatas());
		map.addAttribute("branchs", branchs);
		map.addAttribute("timeIn", timeIn);
		return "pages/examination/progress3 :: #data-refresh-progress3";
	}
	
	@PostMapping(value = "/process3")
	public String process3(ModelMap map, HttpServletRequest request, @ModelAttribute ExamSubClinicalRequestDTO paramBean) {
//		List<ExamClinicalCommonDTO> result = examClinicalService.getExamClinical(new ExamClinical(), 0, 0);
		Gson gson = new GsonBuilder()
		        .excludeFieldsWithModifiers(Modifier.TRANSIENT)
		        .create();                      
		java.lang.reflect.Type listType = new TypeToken<ArrayList<ExamSubClinical>>() {}.getType();
		ArrayList<ExamSubClinical> lstExamSub = gson.fromJson(paramBean.getLstSubClinicalStr() , listType);
		int index = 0;
		for (ExamSubClinical examSubClinical : lstExamSub) {
			examSubClinical.setFileImage(paramBean.getFileImage()[index]);
			index ++;
		}
		
		ArrayList<ExamSubClinical> lstExamSubDel = gson.fromJson(paramBean.getLstSubClinDelStr() , listType);
		Long resultUpdate = examSubClinicalService.update(lstExamSub, lstExamSubDel, request);
		String message = "";
		if (resultUpdate.compareTo(0L) > 0) {
			message = "SUCCESS";
//			lstExamSeqCode = result;
		} else  {
			message = "ERROR";
		}
		map.addAttribute("message", message);
//		map.addAttribute("lstExam", result);
		List<ExamSubClinicalDTO> lstProgress3s = examSubClinicalService.getListProgress3Dto(paramBean.getExamClinicalCode());
		Map<Long, List<String>> mapSelected = new HashMap<>();
		if (lstProgress3s != null && !lstProgress3s.isEmpty()) {
			for (ExamSubClinicalDTO examSubClinicalDTO : lstProgress3s) {
				List<String> itemCodes = new ArrayList<>();
				if (StringUtils.isNotBlank(examSubClinicalDTO.getExtItemCode())) {
					itemCodes = Arrays.asList(examSubClinicalDTO.getExtItemCode().split(","));
				}
				mapSelected.put(examSubClinicalDTO.getId(), itemCodes);
			}
		}
		map.addAttribute("lstProgress3s", lstProgress3s);
		map.addAttribute("lstDoctor", lstDoctor.getDatas());
		map.addAttribute("branchs", branchs);
		map.addAttribute("lstServiceSubEx", lstServiceSubEx);
		map.addAttribute("examCode", examCode);
		map.addAttribute("mapSelected", mapSelected);
		return "pages/examination/progress3 :: #data-refresh-progress3";
	}
	
	@PostMapping(value = "/process4")
	public String process4(ModelMap map, HttpServletRequest request, @ModelAttribute Conclusion paramBean) {
		Conclusion conclu = paramBean;
		Long count = conclusionService.insert(conclu, request);
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		map.addAttribute("examCode", examCode);
		map.addAttribute("message", message);
		//return "pages/examination/progress4 :: #progress3Step";
		return "pages/examination/progress4 :: #msg-add-progress4";
	}
	
	@GetMapping("/showDetailPetByCode")
	public String showDetailPetByCode(ModelMap map, HttpServletRequest request) {
		String petCode = request.getParameter("petCode");
		PetDTO petDto = petService.getDetailPet(petCode);
		map.addAttribute("lstPet", lstCusInfo.getPetDtos());
		map.addAttribute("petDto", petDto);
		return  "pages/examination/progress1 :: #pet-info-add";
	}
	
	@GetMapping("/getDoctorByBranch")
	@ResponseBody
	public ResponseEntity<?> getSpecsByBreedCodeToJson(ModelMap map, HttpServletRequest request) {
		String branchCode = request.getParameter("branchCode");
		List<DoctorDTO> doctors = new ArrayList<>();
		doctors = doctorService.getLstDoctorByBranch(branchCode);
		return ResponseEntity.ok(doctors);
	}
	
	@PutMapping(value = "/setupDataPS", consumes = "application/json", produces = "application/json")
	public String setupDataPs(ModelMap map, HttpSession httpSession, HttpServletRequest request, @RequestBody com.twovet.base.common.RequestBody paramBean) {
		List<GroupItemDTO> listAllItem = widgetService.getAllLstGroup3NDForExam();
		List<GroupItemDTO> lstServicesExtra = new ArrayList<>();
		List<GroupItemDTO> lstMedsExtra = new ArrayList<>();
		List<GroupItemDTO> lstProdsExtra = new ArrayList<>();
		List<String> lstItem3ndCodeTmp = new ArrayList<>();
		lstItem3ndCodeTmp.addAll(Arrays.asList(paramBean.getLstItem3ndCode()));
		if (lstItem3ndCodeTmp == null || lstItem3ndCodeTmp.isEmpty()) {
			lstItem3ndCodeTmp = new ArrayList<>();
		}
		List<String> lstItemCodeData = new ArrayList<>();
		if (StringUtils.isNotBlank(paramBean.getId())) {
			Long idExamSub = Long.valueOf(paramBean.getId());
			ExamSubClinical subExam = examSubClinicalService.getDetailSub(idExamSub);
			if (subExam != null && StringUtils.isNotBlank(subExam.getExtItemCode())) {
				lstItemCodeData = (Arrays.asList(subExam.getExtItemCode().split(",")));
			}
		}
		for (GroupItemDTO groupItemDTO : listAllItem) {
			switch (groupItemDTO.getAppGroupId()) {
			case 1:
				lstServicesExtra.add(groupItemDTO);
				break;
			case 2:
				lstProdsExtra.add(groupItemDTO);
				break;
			case 3:
				lstMedsExtra.add(groupItemDTO);
				break;
			default:
				break;
			}
		}
		map.addAttribute("lstServicesExtra", lstServicesExtra);
		map.addAttribute("lstProdsExtra", lstProdsExtra);
		map.addAttribute("lstMedsExtra", lstMedsExtra);
//		if (!lstItemCodeData.isEmpty() && lstItem3ndCodeTmp.size() < lstItemCodeData.size()) {
//			lstItem3ndCodeTmp.retainAll(lstItemCodeData);
//		} else if (!lstItemCodeData.isEmpty() && lstItem3ndCodeTmp.size() > lstItemCodeData.size()) {
//			lstItem3ndCodeTmp.addAll(lstItemCodeData);
//		}
		lstItem3ndCodeTmp.addAll(lstItemCodeData);
		map.addAttribute("lstItem3ndCodeTmp", lstItem3ndCodeTmp.stream().distinct().collect(Collectors.toList()));
		return  "pages/examination/popupPS :: #showDataPS";
	}

	public List<BranchDTO> getBranchs() {
		return branchs;
	}

	public void setBranchs(List<BranchDTO> branchs) {
		this.branchs = branchs;
	}
	@GetMapping("/getLstCus")
	public String getLstCus(ModelMap map, HttpSession httpSession, HttpServletRequest request) {
		List<CustomerDTO> result = customerService.listAllCustomer();
		map.addAttribute("lstCustomer", result);
		return  "pages/examination/popupCus :: #showDataCus";
	}
	
	@GetMapping("/showCusDetail")
	public String showDetail(ModelMap map, HttpServletRequest request) {
		String cusCode = request.getParameter("cusCode");
		lstCusInfo = customerService.getDetailCustomer(cusCode);
		map.addAttribute("customerDto", lstCusInfo);
		map.addAttribute("lstPet", lstCusInfo.getPetDtos());
		map.addAttribute("petDto", new PetDTO());
		return  "pages/examination/progress1 :: #cusDetail";
	}
	
//	@GetMapping("/process5")
//	public String process5(ModelMap map, HttpServletRequest request) {
//		String examCode = request.getParameter("examCode");
//		ExamClinicalDTO examDTO = examClinicalService.getViewDetailExam(examCode);
//		map.addAttribute("examDTO", examDTO);
//		return  "pages/examination/progress5 :: #viewAllExam";
//	}
}

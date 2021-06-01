package com.twovet.booking.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.model.AppUser;
import com.twovet.base.common.BaseController;
import com.twovet.base.common.FunctionCommon;
import com.twovet.base.common.ParamBean;
import com.twovet.base.common.ResultBean;
import com.twovet.base.common.ResultDto;
import com.twovet.base.constant.Constants;
import com.twovet.base.constant.ViewNameConstants;
import com.twovet.catalog.dto.BranchDTO;
import com.twovet.catalog.dto.CustomerDTO;
import com.twovet.catalog.dto.DoctorDTO;
import com.twovet.catalog.dto.EventDTO;
import com.twovet.catalog.dto.PetDTO;
import com.twovet.catalog.dto.RegistrationExaminationDTO;
import com.twovet.catalog.dto.SpecDTO;
import com.twovet.catalog.model.Doctor;
import com.twovet.catalog.model.RegistrationExamination;
import com.twovet.catalog.services.implement.BranchService;
import com.twovet.catalog.services.implement.CustomerService;
import com.twovet.catalog.services.implement.DoctorService;
import com.twovet.catalog.services.implement.PetService;
import com.twovet.catalog.services.implement.RegisExaminationService;

@Controller
@RequestMapping("booking")
public class BookingController extends BaseController{
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

	@Override
	public void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession httpSession) {
		new FunctionCommon(servletRequestAttributes, httpSession);
	}

	@Override
	public String initScreen(Model model, HttpSession httpSession) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		String contextRoot = httpSession.getServletContext().getContextPath();
		
		setActiveLink(attr, httpSession);
		model.addAttribute("title", "Lịch khám");
		model.addAttribute("examiDTO", new RegistrationExaminationDTO());
		model.addAttribute("contextRoot", contextRoot);
		model.addAttribute("isDoctor", FunctionCommon.checkRole(httpSession, Constants.ROLE.DOCTOR));
		model.addAttribute("modeAdd", Constants.EVENT.IS_NOT_MODE_ADD);
		model.addAttribute("contextRoot", contextRoot);
		return ViewNameConstants.BOOKING_CALENDAR;
	}
	
	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> addEvent(ModelMap map, HttpServletRequest request,@RequestBody ParamBean<RegistrationExamination> paramBean) {
		Long count = eventService.insert(paramBean.getData());
		ResultBean<SpecDTO> result = new ResultBean<>();
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		result.setMessage(message);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping(value = "/edit", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> updateEvent(ModelMap map, HttpServletRequest request,
			@RequestBody ParamBean<RegistrationExamination> paramBean, HttpSession httpSession) {
		RegistrationExamination paramExam = paramBean.getData();
		if (Constants.EXAMINA.ACCEPTED.equals(paramExam.getEditType())) {
			AppUser userInfo = FunctionCommon.getUserInfo(httpSession);
			if (userInfo != null && userInfo.getDoctor() != null) {
				paramExam.setDoctorAssign(userInfo.getDoctor().getDoctorCode());
			}
		}
		Long count = eventService.edit(paramExam);
		ResultBean<SpecDTO> result = new ResultBean<>();
		String message = "";
		if (count != null && count.compareTo(0L) > 0) {
			message = "SUCCESS";
		} else  {
			message = "ERROR";
		}
		result.setMessage(message);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/getEventForMonth")
	@ResponseBody
	public ResponseEntity<?> getEventForMonth(ModelMap map, HttpServletRequest request) {
//		ResultBean<RegistrationExaminationDTO> result = new ResultBean<>();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		boolean searchByWaiting = Boolean.parseBoolean(request.getParameter("searchByWaiting"));
		boolean searchByAssign = Boolean.parseBoolean(request.getParameter("searchByAssign"));
		List<RegistrationExaminationDTO> events = new ArrayList<>();
		RegistrationExamination param = new RegistrationExamination();
		param.setStartTimeStr(startTime);
		param.setEndTimeStr(endTime);
		param.setSearchByAssign(searchByAssign);
		param.setSearchByWaiting(searchByWaiting);
		events = eventService.searchAdvance(param, 0, 0);
//		result.setResults(events);
//		result.setMessage("SUCCESS");
		ModelMapper mm = new ModelMapper();
		List<EventDTO> result = events.stream()
				.map(event -> {
					EventDTO dto = mm.map(event, EventDTO.class);
					if (StringUtils.isNoneBlank(event.getDoctorAssign())) {
						dto.setBackgroundColor(Constants.EVENT.ASSINED);
						dto.setBorderColor(Constants.EVENT.ASSINED);
						dto.setBorderColor(Constants.EVENT.ASSINED);
						dto.setTextColor(Constants.EVENT.ASSINED_TEXT);
					} else {
						dto.setBackgroundColor(Constants.EVENT.WAITING);
						dto.setBorderColor(Constants.EVENT.WAITING);
						dto.setTextColor(Constants.EVENT.WAITING_TEXT);
					}
					return dto;
				})
				.collect(Collectors.toList());
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/getDataInit")
	public String getDataInit(ModelMap map, HttpServletRequest request, HttpSession httpSession) {
		ResultBean<EventDTO> result = new ResultBean<>();
//		List<BranchDTO> branchs = branchService.getAllBranchs();
//		List<CustomerDTO> customers = customerService.listAllCustomer();
//		
//		ResultDto<DoctorDTO> doctorRs = doctorService.searchAdvance(new Doctor(), 0, 0);
//		List<DoctorDTO> doctors = new ArrayList<>();
//		if (doctorRs != null && !doctorRs.getDatas().isEmpty()) {
//			doctors = doctorRs.getDatas();
//		}
		EventDTO data= new EventDTO();
//		data.setBranchs(branchs);
		result.setMessage("SUCCESS");
		result.setResult(data);
//		map.addAttribute("branchs", branchs);
//		map.addAttribute("customers", customers);
//		map.addAttribute("doctors", doctors);
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		this.setDataMM(map, startTime, endTime);
		map.addAttribute("examiDTO", new RegistrationExaminationDTO());
		map.addAttribute("isDoctor", FunctionCommon.checkRole(httpSession, Constants.ROLE.DOCTOR));
		map.addAttribute("modeAdd", Constants.EVENT.IS_MODE_ADD);
		return "pages/booking/detailEvent :: #detailEvent";
	}
	
	@GetMapping("/getLstPetByCusCode")
	public String getLstPetByCusCode(ModelMap map, HttpServletRequest request) {
		String cusCode = request.getParameter("cusCode");
		List<PetDTO> pets = new ArrayList<>();
		pets = petService.getPetByCusCode(cusCode);
		map.addAttribute("pets", pets);
		map.addAttribute("examiDTO", new RegistrationExaminationDTO());
		return "pages/booking/detailEvent :: #pets-droplist-add";
	}
	
	@GetMapping("/getDetail")
	public String getDetail(ModelMap map, HttpServletRequest request, HttpSession httpSession) {
		Long id = Long.valueOf(request.getParameter("publicId"));
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		RegistrationExaminationDTO detail = eventService.getDetailRegisExam(id);
		map.addAttribute("examiDTO", detail);
		List<PetDTO> pets = new ArrayList<>();
		pets = petService.getPetByCusCode(detail.getCusCode());
		map.addAttribute("pets", pets);
		this.setDataMM(map, startTime, endTime);
		map.addAttribute("isDoctor", FunctionCommon.checkRole(httpSession, Constants.ROLE.DOCTOR));
		map.addAttribute("modeAdd", Constants.EVENT.IS_NOT_MODE_ADD);
		return "pages/booking/detailEvent :: #detailEvent";
	}
	
	private void setDataMM(ModelMap map, String start, String end) {
		List<BranchDTO> branchs = branchService.getAllBranchs();
		List<CustomerDTO> customers = customerService.listAllCustomer();
		
		// not use get doctor in schedule on time
//		ResultDto<DoctorDTO> doctorRs = doctorService.getDoctorNotInSchedule(start, end);
		
		// get all doctor
		ResultDto<DoctorDTO> doctorRs = doctorService.searchAdvance(new Doctor(), 0, 0);
		List<DoctorDTO> doctors = new ArrayList<>();
		if (doctorRs != null && !doctorRs.getDatas().isEmpty()) {
			doctors = doctorRs.getDatas();
		}
		map.addAttribute("branchs", branchs);
		map.addAttribute("customers", customers);
		map.addAttribute("doctors", doctors);
	}

}

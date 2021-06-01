package com.twovet.base.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import com.example.model.AppUser;
import com.twovet.Navigation.dto.MenuDTO;
import com.twovet.base.constant.Constants;
import com.twovet.catalog.model.CustomerType;

public class FunctionCommon {

	public FunctionCommon(ServletRequestAttributes servletRequestAttributes, HttpSession session) {
		setActiveLink(servletRequestAttributes, session);
	}
	
	public FunctionCommon() {
		
	}

	private void setActiveLink(ServletRequestAttributes servletRequestAttributes, HttpSession session) {
		if (servletRequestAttributes == null) {
			servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			if (session == null) {
				session = servletRequestAttributes.getRequest().getSession();
			}
		}
		if (session != null) {
			@SuppressWarnings("unchecked")
			List<MenuDTO> lstMenu = (List<MenuDTO>) session.getAttribute("lstMenu");
			String requestPath = (String) servletRequestAttributes.getRequest().getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
			for (MenuDTO menuDTO : lstMenu) {
				if (requestPath.contains(menuDTO.getUrl()) || (menuDTO.getUrl().contains("widget") && requestPath.contains("widget"))) {
					if (menuDTO.getChilds().size() > 0) {
						for (MenuDTO child : menuDTO.getChilds()) {
							if (child.getUrl().equals(requestPath)) {
								child.setActiveClass(true);
								menuDTO.setOpenClass(true);
								menuDTO.setActiveClass(true);
							} else {
								child.setActiveClass(false);
							}
						}
					} else {
						menuDTO.setActiveClass(true);
					}
				} else {
					menuDTO.setOpenClass(false);
					menuDTO.setActiveClass(false);
				}
			}
			session.setAttribute("lstMenu", lstMenu);
		}
	}
	
	public List<CustomerType> intialCustomerTypes() {
		List<CustomerType> customerTypes = new ArrayList<>();
		customerTypes.add(new CustomerType(1, "Khách hàng cá nhân"));
		customerTypes.add(new CustomerType(2, "Khách hàng doanh nghiệp"));
		customerTypes.add(new CustomerType(3, "Khách hàng trong nước"));
		customerTypes.add(new CustomerType(4, "Khách hàng nước ngoài"));
		return customerTypes;
	}
	
	public static <T> T map(Class<T> type, Object[] tuple){
		   List<Class<?>> tupleTypes = new ArrayList<>();
		   for(Object field : tuple){
			   if (field == null) {
				   field = "";
				}
		      tupleTypes.add(field.getClass());
		   }
		   try {
		      Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
		      return ctor.newInstance(tuple);
		   } catch (Exception e) {
		      throw new RuntimeException(e);
		   }
		}
	
	public static <T> List<T> map(Class<T> type, List<Object[]> records){
		   List<T> result = new LinkedList<>();
		   for(Object[] record : records){
		      result.add(map(type, record));
		   }
		   return result;
		}
	public static Date getDateFromString(String dateStr) {
		DateFormat sourceFormat = new SimpleDateFormat(Constants.DATE_FORMAT.DDMMYYYY);
		String dateAsString = "25/12/2010";
		Date date = new Date();
		try {
			date = sourceFormat.parse(dateAsString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String getDate2String(Date dateSrc) {
		DateFormat sourceFormat = new SimpleDateFormat(Constants.DATE_FORMAT.DDMMYYYY);
		try {
			String dateStr = sourceFormat.format(dateSrc);
			return dateStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean checkRole(HttpSession session, String roleName) {
		Set<String> roles = new HashSet<>();
		roles = (Set<String>) session.getAttribute("lstRole");
		if (roles == null) {
			return false;
		}
		return roles.contains(roleName);
	}
	
	public static AppUser getUserInfo(HttpSession session) {
		AppUser userInfo = (AppUser) session.getAttribute("userInfo");
		return userInfo != null ? userInfo : new AppUser();
	}
	
	public static String getLastCodeVal(List<Integer> lstLastCode, String codeCharacter) {
		String code = "";
		if (lstLastCode != null && !lstLastCode.isEmpty()) {
			int last3ndCode = Collections.max(lstLastCode); 
			int next3ndCode = last3ndCode + 1;
			code = codeCharacter.concat(String.format("%03d", next3ndCode));
		}
		return code;
	}
	
	public static String getLastCodeVal(List<Integer> lstLastCode, String codeCharacter, String format) {
		String code = "";
		if (lstLastCode != null && !lstLastCode.isEmpty()) {
			int last3ndCode = Collections.max(lstLastCode); 
			int next3ndCode = last3ndCode + 1;
			code = codeCharacter.concat(String.format(format, next3ndCode));
		}
		return code;
	}
	
	public static <T> String getCodeNextVal(List<T> lstObject, String codeCharacter, String format) {
		List<Integer> lstLastCode = getLstSeqCode(lstObject);
		String code = "";
		if (lstLastCode != null && !lstLastCode.isEmpty()) {
			int last3ndCode = Collections.max(lstLastCode); 
			int next3ndCode = last3ndCode + 1;
			code = codeCharacter.concat(String.format(format, next3ndCode));
		}
		return code;
	}
	
	public static <T> String getCodeNextVal(List<T> lstObject, String codeCharacter, String format, int nextVal) {
		List<Integer> lstLastCode = getLstSeqCode(lstObject);
		String code = "";
		if (lstLastCode != null && !lstLastCode.isEmpty()) {
			int last3ndCode = Collections.max(lstLastCode); 
			int next3ndCode = last3ndCode + nextVal;
			code = codeCharacter.concat(String.format(format, next3ndCode));
		}
		return code;
	}
	
	public static <T> List<Integer> getLstSeqCode(List<T> lstObject) {
		List<Object> lstSeqCode = new ArrayList<>();
		if (lstObject != null && !lstObject.isEmpty()) {
			lstSeqCode = lstObject.stream()
					.map(e ->{
						try {
							Field f = e.getClass().getDeclaredField("sequenceCode");
							f.setAccessible(true);
							return f.get(e);
						} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
								| SecurityException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						return e;
					})
					.collect(Collectors.toList());
		} else {
			lstSeqCode = new ArrayList<Object>();
			lstSeqCode.add(0);
		}
		List<Integer> lstResult = new ArrayList<Integer>();
		for (Object obj : lstSeqCode) {
			if (obj instanceof BigInteger) {
				BigInteger bigObj = (BigInteger) obj;
				lstResult.add(bigObj.intValue());
			}
		}
		return lstResult;
	}
	
	public static String uploadAndGetPath(MultipartFile multipartFile, HttpServletRequest request) {
		String fileName = org.springframework.util.StringUtils.cleanPath(multipartFile.getOriginalFilename());
		try {
			boolean upload = FileUploadUtil.uploadFile(fileName, multipartFile);
			if (upload) {
				String contextRoot = request.getServletContext().getContextPath().toString();
				String imgPath = request.getHeader("origin") + contextRoot;
				return imgPath + "/" +  FileUploadUtil.properties.getDocBase() + "/" + fileName;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}

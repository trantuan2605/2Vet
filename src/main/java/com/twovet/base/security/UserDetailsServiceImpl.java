package com.twovet.base.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.dao.IUserDao;
import com.example.model.AppRole;
import com.example.model.AppUser;
import com.example.model.UserNotExist;
import com.twovet.Navigation.dao.Implement.MenuDao;
import com.twovet.Navigation.dto.MenuDTO;
import com.twovet.Navigation.service.Implement.MenuService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private IUserDao userDao;
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private PasswordEncoder passWordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		AppUser user = userDao.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found!");
		}
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		Set<AppRole> appRoles = user.getAppRoles();
		Set<String> roleNames = new HashSet<>();
		if (appRoles != null) {
			for (AppRole appRole : appRoles) {
				grantedAuthorities.add(new SimpleGrantedAuthority(appRole.getRoleName()));
				roleNames.add(appRole.getRoleName());
			}
			
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			List<MenuDTO> lstMenu = menuService.getAllMenu();
			session.setAttribute("lstMenu", lstMenu);
			session.setAttribute("lstRole", roleNames);
			session.setAttribute("userInfo", user);
			String contextRoot = session.getServletContext().getContextPath();
			session.setAttribute("contextRoot", contextRoot);
			
			return new User(user.getUsername(),passWordEncoder.encode(user.getEncryptedPassword()), grantedAuthorities);
		}
		
		return new UserNotExist();
	}

}

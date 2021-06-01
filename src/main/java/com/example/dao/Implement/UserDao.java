package com.example.dao.Implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.IUserDao;
import com.example.model.AppUser;
import com.example.model.ParamForm;
import com.example.model.UserRole;
import com.twovet.base.common.BaseDao;

@Repository
public class UserDao extends BaseDao implements IUserDao{

public UserDao(EntityManagerFactory emf) {
		super(emf);
	}
	//	@PersistenceContext
//	private EntityManager entityManager;
//	@Autowired
//	private EntityManagerFactory emf;
	@Override
	public AppUser findByUsername(Integer id) {
		EntityManager em = emf.createEntityManager();
		AppUser result = em.find(AppUser.class, id);
		em.close();
		return result;
	}
	@Override
	public AppUser findByEmail(String username) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<AppUser> query = em.createNamedQuery("AppUser.findByName", AppUser.class);
		query.setParameter("username", username);
		
		List<AppUser> users = query.getResultList();
		em.close();
		if (users.size() > 0) {
			return users.get(0);
		}
		return new AppUser();
	}
	@Override
	public List<AppUser> getAllUser() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<AppUser> query = em.createNamedQuery("AppUser.findAllUser", AppUser.class);
		List<AppUser> result = query.getResultList();
		em.close();
		return result;
	}
	@Override
	@Transactional
	public int deleteUser(List<AppUser> users) throws Exception{
				int count = 0;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			for (AppUser appUser : users) {
				AppUser user = em.find(AppUser.class, appUser.getId());
				user.setDeleteFlg(0);
				em.merge(user);
				count++;
			}
			em.flush();
			em.getTransaction().commit();			
		} catch (Exception e) {
			em.getTransaction().rollback();
			count = 0;
			throw e;
		}
		finally {
			em.close();
		}
		
		return count;
	}
	@Override
	public int updateUser(EntityManager em, ParamForm param) throws Exception {
				int count = 0;
		em.getTransaction().begin();
		try {
			AppUser app = em.find(AppUser.class, param.getUserId());
			app.setEncryptedPassword(param.getPassword());
			List<UserRole> userRoles = this.getUserRolesByUserId(em, param.getUserId());
			for (UserRole userRole : userRoles) {
				UserRole newRole = new UserRole();
				if (userRole.getRoleId() == 1) {
					if (param.getRole() == 1) {
						newRole.setRoleId(2);
						newRole.setUserId(param.getUserId());
						newRole.setId(userRole.getId());
						if (userRoles.size() > 1) {
							newRole.setId(userRoles.get(1).getId());
							em.remove(em.contains(newRole) ? newRole : em.merge(newRole));
						}
					} else if (param.getRole() == 2) {
						System.out.println("Xóa role admin");
						newRole.setRoleId(2);
						newRole.setUserId(param.getUserId());
						newRole.setId(userRole.getId());
						if (userRoles.size() > 1) {
							newRole.setId(userRoles.get(1).getId());
							em.remove(newRole);
						}
					} else {
						System.out.println("Thêm role");
						newRole.setRoleId(2);
						newRole.setUserId(param.getUserId());
						newRole.setId(userRole.getId());
						em.persist(newRole);
					}
					
				} else if (userRole.getRoleId() == 2) {
					if (param.getRole() == 1) {
						System.out.println("Xóa role admin");
						newRole.setRoleId(1);
						newRole.setUserId(param.getUserId());
						newRole.setId(userRole.getId());
						if (userRoles.size() > 1) {
							newRole.setId(userRoles.get(1).getId());
							em.remove(newRole);
						}
					} else if (param.getRole() == 2) {
						System.out.println("Xóa role member");
						newRole.setId(userRole.getId());
						newRole.setRoleId(2);
						newRole.setUserId(param.getUserId());
						if (userRoles.size() > 1) {
							newRole.setId(userRoles.get(1).getId());
							em.remove(newRole);
						}
					} else {
						System.out.println("Thêm role");
						newRole.setRoleId(1);
						newRole.setUserId(param.getUserId());
						em.merge(newRole);
					}
				}
				em.merge(newRole);
				break;
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			count=0;
			throw e;
		}
		finally {
			em.close();
		}
		return count;
	}
	@Override
	public List<UserRole> getUserRolesByUserId(EntityManager em, int id) {
				List<UserRole> userRoles = new ArrayList<>();
//		EntityManager em = emf.createEntityManager();
		TypedQuery<UserRole> query = em.createNamedQuery("UserRole.findByUserId", UserRole.class);
		query.setParameter("userId", id);
		userRoles = query.getResultList();
		em.close();
		return userRoles;
	}


}

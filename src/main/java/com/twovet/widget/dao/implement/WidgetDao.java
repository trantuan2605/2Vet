package com.twovet.widget.dao.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.twovet.base.common.BaseDao;
import com.twovet.base.common.FunctionCommon;
import com.twovet.widget.dao.IWidgetDao;
import com.twovet.widget.dto.AppGroupDTO;
import com.twovet.widget.dto.GroupItemDTO;
import com.twovet.widget.model.AppGroup;
import com.twovet.widget.model.GroupItem;

@Repository
public class WidgetDao extends BaseDao implements IWidgetDao{

	public WidgetDao(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public GroupItem getGroupItem(String groupCode) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<GroupItem> query = em.createNamedQuery("GroupItem.findGroupItemByCode", GroupItem.class);
		query.setParameter("group2ndCode", groupCode);
		GroupItem result = new GroupItem();
		try {
			result = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public GroupItemDTO getDetailGroup3nd(String group3ndCode) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		sql.append("SELECT gi.id id, gi.app_group_id appGroupId, group_1st_code group1stCode, gi.group_1st_name group1stName, gi.group_2nd_code group2ndCode, gi.group_2nd_name group2ndName, ");
		sql.append(" gi.group_3nd_code group3ndCode, gi.group_3nd_name group3ndName, gi.ITEM_SPEC_1 itemSpec1, gi.ITEM_SPEC_2 itemSpec2, gi.ITEM_SPEC_3 itemSpec3, ");
		sql.append(" gi.ITEM_SPEC_4 itemSpec4, gi.ITEM_SPEC_5 itemSpec5, gi.ITEM_SPEC_6 itemSpec6, gi.ITEM_SPEC_7 itemSpec7, gi.ITEM_SPEC_8 itemSpec8, gi.ITEM_SPEC_9 itemSpec9, ");
		sql.append(" gi.ITEM_SPEC_10 itemSpec10, gi.descript descript, gi.path path ");
		sql.append(" FROM group_item gi LEFT JOIN  app_group ag ON gi.app_group_id = ag.id ");
		sql.append(" WHERE gi.group_3nd_code =:group3ndCode");
		EntityManager em = emf.createEntityManager();
		query = em.createNativeQuery(sql.toString());
		query.setParameter("group3ndCode", group3ndCode);
		try {
			List<Object[]> list = query.getResultList();
			List<GroupItemDTO> result = FunctionCommon.map(GroupItemDTO.class, list);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return new GroupItemDTO();
	}

	@Override
	public List<AppGroupDTO> getLstGroup1ST(int appGroupId) {
		StringBuilder sql = new StringBuilder();
		EntityManager em = emf.createEntityManager();
		List<AppGroupDTO> result = new ArrayList<AppGroupDTO>();
		Query query = null;
		try {
			sql.append(" SELECT gi.group_1st_code group1stCode,  gi.app_group_id groupId, gi.group_1st_name group1stName from group_item gi "); 
			sql.append(" WHERE gi.app_group_id =:appGroupId GROUP BY gi.group_1st_code ");
			sql.append(" ORDER BY itemCode DESC");
			query = em.createNativeQuery(sql.toString());
			query.setParameter("appGroupId", appGroupId);
			@SuppressWarnings("unchecked")
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(AppGroupDTO.class, list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public List<GroupItemDTO> getLstGroup2ND(String group1stCode) {
		StringBuilder sql = new StringBuilder();
		EntityManager em = emf.createEntityManager();
		List<GroupItemDTO> result = new ArrayList<GroupItemDTO>();
		Query query = null;
		try {
			sql.append(" SELECT gi.group_1st_code group1stCode,  gi.group_1st_name group1stName, gi.group_2nd_code group2ndCode,  gi.group_2nd_name group2ndName, "); 
			sql.append(" CAST(SUBSTRING(gi.group_2nd_code, CHAR_LENGTH( gi.group_1st_code)+1, (CHAR_LENGTH( gi.group_2nd_code)-CHAR_LENGTH( gi.group_1st_code))) AS UNSIGNED) last2ndCodeInt "); 
			sql.append(" FROM  group_item gi  WHERE gi.GROUP_1ST_CODE =:group1stCode AND gi.GROUP_2ND_CODE IS NOT NULL ");
			sql.append(" GROUP BY  gi.GROUP_2ND_CODE ORDER BY gi.GROUP_2ND_CODE DESC");
			query = em.createNativeQuery(sql.toString());
			query.setParameter("group1stCode", group1stCode);
			@SuppressWarnings("unchecked")
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(GroupItemDTO.class, list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public List<GroupItemDTO> getLstGroup3ND(String group2ndCode) {
		StringBuilder sql = new StringBuilder();
		EntityManager em = emf.createEntityManager();
		List<GroupItemDTO> result = new ArrayList<GroupItemDTO>();
		Query query = null;
		try {
			sql.append(" SELECT gi.group_1st_code group1stCode, gi.group_1st_name group1stName, gi.group_2nd_code group2ndCode, gi.group_2nd_name group2ndName, "); 
			sql.append(" gi.group_3nd_code group3ndCode, gi.group_3nd_name group3ndName, gi.ITEM_SPEC_1 itemSpec1, gi.ITEM_SPEC_2 itemSpec2, ");
			sql.append(" gi.ITEM_SPEC_3 itemSpec3, gi.ITEM_SPEC_4 itemSpec4, gi.ITEM_SPEC_5 itemSpec5, gi.ITEM_SPEC_6 itemSpec6, ");
			sql.append(" gi.ITEM_SPEC_7 itemSpec7, gi.ITEM_SPEC_8 itemSpec8, gi.ITEM_SPEC_9 itemSpec9 , gi.ITEM_SPEC_10 itemSpec10, gi.descript descript, gi.app_group_id appGroupId, ");
			sql.append(" CAST(SUBSTRING(gi.group_3nd_code, CHAR_LENGTH( gi.group_2nd_code)+1, (CHAR_LENGTH( gi.group_3nd_code)-CHAR_LENGTH( gi.group_2nd_code))) AS UNSIGNED) last3ndCodeInt ");
			sql.append(" FROM group_item gi WHERE gi.GROUP_2ND_CODE =:group2ndCode AND gi.GROUP_2ND_CODE IS NOT NULL AND (gi.GROUP_3ND_CODE IS NOT NULL AND  CHAR_LENGTH( gi.GROUP_3ND_CODE) > 0) ");
			sql.append(" ORDER BY gi.GROUP_2ND_CODE DESC");
			query = em.createNativeQuery(sql.toString());
			query.setParameter("group2ndCode", group2ndCode);
			@SuppressWarnings("unchecked")
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(GroupItemDTO.class, list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public List<AppGroupDTO> getAllGroup(int appGroupId) {
		EntityManager em = emf.createEntityManager();
		List<GroupItemDTO> groupItems = new ArrayList<>();
		List<AppGroupDTO> result = new ArrayList<>();
		
		
		try {
			Query getGroup1stQuery = this.queryGetGroup1st(em);
			getGroup1stQuery.setParameter("appGroupId", appGroupId);
			
			Query getGroup2ndQuery = this.queryGetGroup2nd(em);
			getGroup2ndQuery.setParameter("appGroupId", appGroupId);
			
			@SuppressWarnings("unchecked")
			List<Object[]> list1st = getGroup1stQuery.getResultList();
			result = FunctionCommon.map(AppGroupDTO.class, list1st);
			
			@SuppressWarnings("unchecked")
			List<Object[]> list2nd = getGroup2ndQuery.getResultList();
			groupItems = FunctionCommon.map(GroupItemDTO.class, list2nd);
			
			for (AppGroupDTO dto: result) {
				List<GroupItemDTO> lstTmp = groupItems.stream()
						.filter(x -> dto.getGroup1stCode().equals(x.getGroup1stCode()))
						.collect(Collectors.toList());
				dto.setLstGroupItemDTO(lstTmp);
				int sizeRows = lstTmp.size()/6;
				if (lstTmp.size()%6 != 0) {
					sizeRows+=1;
				}
				dto.setSizeRows(sizeRows);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public List<AppGroupDTO> getAllWidget() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<AppGroup> query = em.createNamedQuery("AppGroup.findAll", AppGroup.class);
		List<AppGroup> groups = new ArrayList<>();
		List<AppGroupDTO> result = new ArrayList<>();
		try {
			groups = query.getResultList();
			ModelMapper mm = new ModelMapper();
			result = groups.stream()
					.map(group -> mm.map(group, AppGroupDTO.class))
					.collect(Collectors.toList());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}
	
	private Query queryGetGroup1st(EntityManager em) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		sql.append(" SELECT gi.group_1st_code group1stCode,  gi.app_group_id groupId, gi.group_1st_name group1stName, "); 
		sql.append(" CAST(SUBSTRING(gi.group_1st_code, 5, (CHAR_LENGTH( gi.group_1st_code)-3)) AS UNSIGNED )last1stCodeInt FROM group_item gi "); 
		sql.append(" WHERE gi.app_group_id =:appGroupId GROUP BY gi.group_1st_code ");
		sql.append(" ORDER BY gi.group_1st_code DESC");
		query = em.createNativeQuery(sql.toString());
		return query;
	}
	
	private Query queryGetGroup2nd(EntityManager em) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		sql.append(" SELECT gi.group_1st_code group1stCode,  gi.group_1st_name group1stName, gi.group_2nd_code group2ndCode,  gi.group_2nd_name group2ndName "); 
		sql.append(" FROM  group_item gi  WHERE gi.app_group_id =:appGroupId AND gi.GROUP_2ND_CODE IS NOT NULL ");
		sql.append(" GROUP BY  gi.GROUP_2ND_CODE ORDER BY gi.GROUP_2ND_CODE DESC");
		query = em.createNativeQuery(sql.toString());
		return query;
	}

	@Override
	public Long edit(GroupItem groupItem) {
		EntityManager em = emf.createEntityManager();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE group_item SET group_3nd_name =:group3ndName, descript =:descript ");
		switch (groupItem.getGroupId()) {
		case 1:
			sql.append(", item_spec_1 =:itemSpec1 ");
			break;
		case 2:
			sql.append(", item_spec_3 =:itemSpec3, item_spec_2 =:itemSpec2, item_spec_4 =:itemSpec4, path =:path ");
			break;
		case 3:
			sql.append(", item_spec_6 =:itemSpec6, item_spec_8 =:itemSpec8, item_spec_5 =:itemSpec5, item_spec_7 =:itemSpec7 ");
			break;
		default:
			break;
		}
		sql.append(" where id =:id ");
		Long result = 0L;
		try {
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("group3ndName", groupItem.getGroup3ndName());
			query.setParameter("descript", groupItem.getDescript());
			switch (groupItem.getGroupId()) {
			case 1:
				query.setParameter("itemSpec1", groupItem.getItemSpec1());
				break;
			case 2:
				query.setParameter("itemSpec3", groupItem.getItemSpec3());
				query.setParameter("itemSpec2", groupItem.getItemSpec2());
				query.setParameter("itemSpec4", groupItem.getItemSpec4());
				query.setParameter("path", groupItem.getPath());
				break;
			case 3:
				query.setParameter("itemSpec6", groupItem.getItemSpec6());
				query.setParameter("itemSpec8", groupItem.getItemSpec8());
				query.setParameter("itemSpec5", groupItem.getItemSpec5());
				query.setParameter("itemSpec7", groupItem.getItemSpec7());
				break;
			default:
				break;
			}
			query.setParameter("id", groupItem.getId());
			int countUpdate = query.executeUpdate();
			em.getTransaction().commit();
			result = Long.valueOf(countUpdate);
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public Long insert(GroupItem groupItem) {
		EntityManager em = emf.createEntityManager();
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO group_item ( ");
		sql.append(" group_1st_code, app_group_id, group_1st_name, group_2nd_code, group_2nd_name, ");
		sql.append(" group_3nd_code, group_3nd_name, descript ");
		switch (groupItem.getGroupId()) {
		case 1:
			sql.append(" , item_spec_1 ");
			break;
		case 2:
			sql.append(" , item_spec_3, item_spec_2, item_spec_4, path ");
			break;
		case 3:
			sql.append(" , item_spec_6, item_spec_8, item_spec_5, item_spec_7 ");
			break;

		default:
			break;
		}
		sql.append( ") ");
		sql.append(" values (:group1stCode, :appGroupId, :group1stName, :group2ndCode, :group2ndName, :group3ndCode, :group3ndName, :descript ");
		switch (groupItem.getGroupId()) {
		case 1:
			sql.append(" , :itemSpec1 ");
			break;
		case 2:
			sql.append(" , :itemSpec3, :itemSpec2, :itemSpec4, :path ");
			break;
		case 3:
			sql.append(" , :itemSpec6, :itemSpec8, :itemSpec5, :itemSpec7 ");
			break;

		default:
			break;
		}
		sql.append( ") ");
		Long result = 0L;
		try {
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("group1stCode", groupItem.getGroup1stCode());
			query.setParameter("appGroupId", groupItem.getGroupId());
			query.setParameter("group1stName", groupItem.getGroup1stName());
			query.setParameter("group2ndCode", groupItem.getGroup2ndCode());
			query.setParameter("group2ndName", groupItem.getGroup2ndName());
			query.setParameter("group3ndCode", groupItem.getGroup3ndCode());
			query.setParameter("group3ndName", groupItem.getGroup3ndName());
			query.setParameter("descript", groupItem.getDescript());
			switch (groupItem.getGroupId()) {
			case 1:
				query.setParameter("itemSpec1", groupItem.getItemSpec1());
				break;
			case 2:
				query.setParameter("itemSpec3", groupItem.getItemSpec3());
				query.setParameter("itemSpec2", groupItem.getItemSpec2());
				query.setParameter("itemSpec4", groupItem.getItemSpec4());
				query.setParameter("path", groupItem.getPath());
				break;
			case 3:
				query.setParameter("itemSpec6", groupItem.getItemSpec6());
				query.setParameter("itemSpec8", groupItem.getItemSpec8());
				query.setParameter("itemSpec5", groupItem.getItemSpec5());
				query.setParameter("itemSpec7", groupItem.getItemSpec7());
				break;

			default:
				break;
			}
			int countInsert = query.executeUpdate();
			em.getTransaction().commit();
			result = Long.valueOf(countInsert);
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public Long insert1st(GroupItem groupItem) {
		EntityManager em = emf.createEntityManager();
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO group_item ( ");
		sql.append(" group_1st_code, app_group_id, group_1st_name, descript ");
		sql.append( ") ");
		sql.append(" values (:group1stCode, :appGroupId, :group1stName, :descript ");
		sql.append( ") ");
		Long result = 0L;
		try {
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("group1stCode", groupItem.getGroup1stCode());
			query.setParameter("appGroupId", groupItem.getGroupId());
			query.setParameter("group1stName", groupItem.getGroup1stName());
			query.setParameter("descript", groupItem.getDescript());
			int countInsert = query.executeUpdate();
			em.getTransaction().commit();
			result = Long.valueOf(countInsert);
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public GroupItem getGroupItemBy1stCode(String group1stCode) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<GroupItem> query = em.createNamedQuery("GroupItem.findGroupItem2nd", GroupItem.class);
		query.setParameter("group1stCode", group1stCode);
		GroupItem result = new GroupItem();
		try {
			result = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public Long insert2nd(GroupItem groupItem) {
		EntityManager em = emf.createEntityManager();
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO group_item ( ");
		sql.append(" group_1st_code, app_group_id, group_1st_name,group_2nd_code, group_2nd_name, descript ");
		sql.append( ") ");
		sql.append(" values (:group1stCode, :appGroupId, :group1stName, :group2ndCode, :group2ndName, :descript ");
		sql.append( ") ");
		Long result = 0L;
		try {
			em.getTransaction().begin();
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("group1stCode", groupItem.getGroup1stCode());
			query.setParameter("appGroupId", groupItem.getGroupId());
			query.setParameter("group1stName", groupItem.getGroup1stName());
			query.setParameter("group2ndCode", groupItem.getGroup2ndCode());
			query.setParameter("group2ndName", groupItem.getGroup2ndName());
			query.setParameter("descript", groupItem.getDescript());
			int countInsert = query.executeUpdate();
			em.getTransaction().commit();
			result = Long.valueOf(countInsert);
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public AppGroup getGroupById(Long groupId) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<AppGroup> query = em.createNamedQuery("AppGroup.findById", AppGroup.class);
		query.setParameter("groupId", groupId);
		AppGroup result = new AppGroup();
		try {
			result = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public List<GroupItemDTO> getLstGroup3NDByAppGr(String appGroupId) {
		StringBuilder sql = new StringBuilder();
		Query query = null;
		
		List<GroupItemDTO> result = new ArrayList<>();
		sql.append("SELECT DISTINCT GROUP_2ND_CODE group3ndCode , ");
		sql.append(" GROUP_2ND_NAME group3ndName ");
		sql.append(" FROM  group_item gr");
		sql.append(" WHERE ");
		sql.append(" gr.APP_GROUP_ID =:appGroupId and GROUP_2ND_CODE is NOT NULL ");
		sql.append(" ORDER BY group3ndCode DESC");
		EntityManager em = emf.createEntityManager();
		query = em.createNativeQuery(sql.toString());
		query.setParameter("appGroupId", appGroupId);
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(GroupItemDTO.class, list);
			if (result != null && !result.isEmpty()) {
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public List<GroupItemDTO> getAllLstGroup3NDForExam() {
		StringBuilder sql = new StringBuilder();
		EntityManager em = emf.createEntityManager();
		List<GroupItemDTO> result = new ArrayList<GroupItemDTO>();
		Query query = null;
		try {
			sql.append(" SELECT gi.group_1st_code group1stCode, gi.group_1st_name group1stName, gi.group_2nd_code group2ndCode, gi.group_2nd_name group2ndName, "); 
			sql.append("  gi.group_3nd_code group3ndCode, gi.group_3nd_name group3ndName, gi.descript descript, gi.app_group_id appGroupId ");
			sql.append(" FROM group_item gi WHERE gi.GROUP_2ND_CODE IS NOT NULL AND (gi.GROUP_3ND_CODE IS NOT NULL AND  CHAR_LENGTH( gi.GROUP_3ND_CODE) > 0) ");
			sql.append(" ORDER BY gi.GROUP_2ND_CODE DESC");
			query = em.createNativeQuery(sql.toString());
			@SuppressWarnings("unchecked")
			List<Object[]> list = query.getResultList();
			result = FunctionCommon.map(GroupItemDTO.class, list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

}

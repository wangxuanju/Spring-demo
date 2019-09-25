###  DAO接口的编写，实现的功能不停的更新迭代，一般多次才能写好
```java
@MyBatisRepository
public interface AdminDao(){
	
	void saveAdmin(Admin admin);//增加(保存)管理员，传入管理员，返回空
	void updateAdmin(Admin admin);//更新管理员，传入管理员，返回空
	void deleteAdmin(int admin_id);//删除管理员，传入管理员ID，返回空（深究）
        //查询方式有多种
	Admin findById(int admin_id);//通过管理员ID来查询管理员，传入的参数是管理员ID，返回的是管理员
	Admin findByCode(String adminCode);//通过管理员编号来查询管理员，传入的参数是管理员编号，返回的管理员
	
  
	void updateByPassword(Admin admin);
	
	void updatePassword(Map<String, Object> param);
  
  
	
	void deleteAdminRoles(int adminId);//用于和AdaminRole进行交互
		
	void saveAdminRoles(Map<String, Object> adminRoles);//用于和AdaminRole进行交互
	
	
	List<Module> findModulesByAdmin(int adminId);
	
	List<AdminVo> findByList(AdminVo admin);
}
```

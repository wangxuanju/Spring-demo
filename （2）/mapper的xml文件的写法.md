头文件的格式都是固定的（一般没差别）
```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
```
mapper标签
```java
<mapper namespace="com.example.gerenxuexi999.dao.AdminDao"> //namespace为相应dao接口
</mapper>
```
整体流程:注意返回类型和参数值的写法（参考dao接口）
```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.cscbms.dao.AdminDao">
	<select id="findByPage" 
		parameterType="com.cscbms.entity.page.Page" 
		resultMap="adminMap">
		
		select * from admininfo
		where adminId in (
			select a.adminId
			from admininfo a
			left join adminrole ar on a.adminId=ar.adminId
			left join roleinfo ri on ri.roleId=ar.roleId
			left join rolemodule rm on rm.roleId=ri.roleId
			<where>
				<if test="roleName!=null &amp;&amp; roleName.length()>0">
					and ri.name like '%'||#{roleName}||'%'
				</if>
				<if test="moduleId!=null">
					and rm.moduleId=#{moduleId}
				</if>
			</where>
		) order by adminId limit #{begin},#{end}
	
	</select>
	<select id="selectRoles" 
		parameterType="int" 
		resultType="com.cscbms.entity.Role">
		select * from roleinfo where roleId in (
			select roleId from adminrole
			where adminId=#{id}
		)
	</select>
	<resultMap id="adminMap" type="com.cscbms.entity.Admin">
		<id column="adminId" property="adminId" />
		<collection ofType="com.cscbms.entity.Role"
			property="roles" javaType="java.util.ArrayList"
			column="adminId" select="selectRoles">
		</collection>
	</resultMap>
	
	<select id="findRows"
		parameterType="com.cscbms.entity.page.Page"
		resultType="int">
		select count(*) from admininfo
		where adminId in (
			select a.adminId
			from admininfo a
			left join adminrole ar on a.adminId=ar.adminId
			left join roleinfo ri on ri.roleId=ar.roleId
			left join rolemodule rm on rm.roleId=ri.roleId
			<where>
				<if test="roleName!=null &amp;&amp; roleName.length()>0">
					and ri.name like '%'||#{roleName}||'%'
				</if>
				<if test="moduleId!=null">
					and rm.moduleId=#{moduleId}
				</if>
			</where>
		)
	</select>	
	
	<update id="updatePassword" parameterType="hashMap">
		update admininfo set password=#{password}
		where 
		adminId in 
		<foreach collection="ids" open="(" close=")" separator="," item="id">
			#{id}
		</foreach>
	</update>
	
	<select id="findById" parameterType="int" resultMap="adminMap">
		select * from admininfo where adminId=#{id}
	</select>
	
	<insert id="saveAdmin" useGeneratedKeys="true" keyProperty="adminId" parameterType="com.cscbms.entity.Admin">
		insert into admininfo(adminCode,password,name,telephone,email,enrollDate) values(
			#{adminCode,jdbcType=VARCHAR},
			#{password,jdbcType=VARCHAR},
			#{name,jdbcType=VARCHAR},
			#{telephone,jdbcType=VARCHAR},
			#{email,jdbcType=VARCHAR},
			#{enrollDate,jdbcType=TIMESTAMP}
		)
	</insert>
	
	<insert id="saveAdminRoles" parameterType="hashMap">
		insert into adminrole(adminId,roleId) values(
			#{adminId,jdbcType=NUMERIC},
			#{roleId,jdbcType=NUMERIC}
		)
	</insert>
	
	<update id="updateAdmin" parameterType="com.cscbms.entity.Admin">
		update admininfo set 
			name=#{name,jdbcType=VARCHAR},
			telephone=#{telephone,jdbcType=VARCHAR},
			adminCode=#{adminCode,jdbcType=VARCHAR},
			email=#{email,jdbcType=VARCHAR}
		where adminId=#{adminId}
	</update>
	
	<delete id="deleteAdminRoles" parameterType="int">
		delete from adminrole where adminId=#{adminId}
	</delete>
	
	<delete id="deleteAdmin" parameterType="int">
		delete from admininfo where adminId=#{id}
	</delete>	
	
	<select id="findByCode" 
		parameterType="string"
		resultType="com.cscbms.entity.Admin">
		select * from admininfo where adminCode=#{adminCode}
	</select>
	
	<update id="updateByPassword" parameterType="com.cscbms.entity.Admin">
		update admininfo set 
			password=#{password,jdbcType=VARCHAR}
		where adminId=#{adminId}
	</update>
	
	<select id="findModulesByAdmin" 
		parameterType="int"
		resultType="com.cscbms.entity.Module">
		select * from moduleinfo where moduleId in (
			select rm.moduleId
			from adminrole ar
			inner join roleinfo ri on ri.roleId=ar.roleId
			inner join rolemodule rm on rm.roleId=ri.roleId
			where ar.adminId=#{adminId}
		) order by moduleId
	</select>
	
	<select id="findByList" parameterType="com.cscbms.vo.AdminVo" resultType="com.cscbms.vo.AdminVo">
		select * from admininfo 
		<where>
		<if test="adminCode!=null">
			and adminCode=#{adminCode}
		</if>
		<if test="password!=null">
			and password=#{password}
		</if>
                </where>
	</select>
  
  
</mapper>
```

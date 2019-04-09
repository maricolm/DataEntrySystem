package com.pdata.edu.mapper;

import java.util.List;

import com.pdata.col.pojo.ToolForGetCount;
import com.pdata.edu.pojo.AttrOfPro;
import com.pdata.edu.pojo.DispensePro;
import com.pdata.edu.pojo.Edu;
import com.pdata.edu.pojo.Pro;
import com.pdata.edu.pojo.Tools;
import com.pdata.school.pojo.Sch;
import com.pdata.until.PageBean;

public interface EduMapper {
	public Edu loginEdu(Edu edu);//教育厅登陆
	public void modifyInfo(Edu edu);//修改个人信息
	public Edu findUserByEid(Edu edu);//通过id查询用户
	public void addSch(Sch sch);//添加学校账号
	public Boolean verifyAddAccount(String account);//异步验证添加的账号是否存在
	public int findSchCount();//查询用户总数
	public List<Sch> findUsers(PageBean<Sch> pb);//分页查询用户
	public void resetPswdSch(Sch sch);//重置用户密码
	public void deleteSchBySid(String sId);//通过用户ID删除用户
	public void modifypswd(Edu edu);//修改密码
	public void addPro(Pro pro);//添加项目（不是发布项目）
	public List<AttrOfPro> findProAttrByPid(String aPid);//通过项目ID查询所属的属性
	
	public Pro findProById(String pId);//通过项目ID查询项目
	public void addProAttr(AttrOfPro attr);//添加项目属性
	public Boolean verifyArgsaAlias(AttrOfPro attr);//异步验证添加的账号是否存在
	
	public Boolean ajaxVerifyProAlias(String alias);//异步验证项目别名
	public void deleteAttrByaId(String aId);//通过属性ID删除属性
	public int findProCount(String condition);//查询项目总数
	public int queryProStateDcl(String condition);//查询待处理项目
	public List<Pro> findPro(PageBean<Pro> pb);//分页查询项目列表
	public void deleteProByPid(String pId);//删除项目
	public void deleteAttrByaPid(String pId);//通过项目ID删除项目下面所有的属性
	public String getProPaliasByPid(String pId);//通过项目ID，获取项目别名
	public List<String> getAttrByProPid(String aPid);//通过项目ID，获取项目的属性
	
	public void updataProUrl(Pro pro);//更新项目中模版的连接位置
	public void updataState(String pId);//更新项目状态
	public List<String> getAttrNameByProPid(String aPid);//通过项目ID，获取项目的属性
	public List<Sch> findAllEdu();//查询所有的学校用户
	public void insertSchForPro(DispensePro dpp);//把学校用户与发布的项目关联
	public void updateDProAPState(String dId);//通过ID给用户分配任务
	
	public int queryCountUserOfPro(Tools t);//查看项目对应的用户的总个数
	public List<DispensePro> querySchOfPro(PageBean<DispensePro> pb);//查看与项目相对应的用户
	public int getLenAttrOfProByPid(String pId);//根据项目id获取项目属性的个数
	public int getCountOfColDate(ToolForGetCount tool);//获得总个数
	public String getAlias(String pId);//通过项目ID，查询项目的别名
	
	public List<DispensePro> getDispensePro(String pid);
	public void modifyStateOftask(String did);
	public int getCountOfSigleSchoolDate(ToolForGetCount tool);
	
	public void modifyProTaskState(ToolForGetCount tool);//修改任务状态
	public void modifyDataStateTo2(ToolForGetCount tool);
	public void endPro(String pId);//结束整个项目
}

package com.ivan.ssm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ivan.ssm.domain.Employee;
import com.ivan.ssm.domain.Message;
import com.ivan.ssm.service.EmployeeService;

/**
 * 处理员工增删改查
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {
	
	//注入service
	@Autowired
	private EmployeeService service;
	
	/**
	 * 查询所有员工
	 * @param pn 查询的页码
	 * @return
	 * 分页查询,默认从第一页开始查询
	 */
	@RequestMapping("/emps")
	public String name(@RequestParam(value="pn", defaultValue="1")Integer pn, Model model) {
		//分页查询
		//引入pageHelper
		PageHelper.startPage(pn, 5);//传入页码及每页的个数
		List<Employee> emps = service.getAllEmpWithDept();//查询所有员工
		//包装成PageInfo--封装了分页详细信息
		PageInfo pageinfo = new PageInfo(emps, 5);//连续显示5页
		
		model.addAttribute("pageinfo", pageinfo);
		
		return "list";
	}
	
	/**
	 * 以json形式返回分页信息
	 * 导入jackson包
	 * @param pn 查询的页码
	 * @return
	 */
	@RequestMapping("/empsjson")
	@ResponseBody//自动将对象转成json字符串
	public Message getEmpWithJson(@RequestParam(value="pn", defaultValue="1")Integer pn){
		//分页查询
		//引入pageHelper
		PageHelper.startPage(pn, 5);//传入页码及每页的个数
		List<Employee> emps = service.getAllEmpWithDept();//查询所有员工
		//包装成PageInfo--封装了分页详细信息
		PageInfo pageinfo = new PageInfo(emps, 5);//连续显示5页
		
		return Message.success().add("pageinfo", pageinfo);//返回通用的处理结果对象,其中包含json对象
	}
	
	/**
	 * URI风格
	 * -> /emp/{id}  GET----查询员工
	 * -> /emp       POST----保存员工
	 * -> /emp/{id}  PUT----修改员工
	 * -> /emp/{id}  DELETE----删除员工
	 * 
	 * 后端JSR303校验
	 * 导入包hibernate-validator
	 * @return
	 */
	//保存新增员工信息
	@ResponseBody
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public Message saveEmp(@Valid Employee employee,BindingResult result) {//后端JSR303校验,绑定BindingResult校验结果
		
		//封装错误信息
		Map<String, Object> map = new HashMap<>();
		if (result.hasErrors()) {
			//校验失败,返回失败信息
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				map.put(error.getField(), error.getDefaultMessage());//错误字段+错误信息
			}
			return Message.fail().add("errorFields", map);
		} else {
			service.saveEmp(employee);//保存新增员工信息
			
			return Message.success();
		}
	}
	
	//每次保存员工信息时先校验用户名是否已存在
	@ResponseBody
	@RequestMapping("/checkuser")
	public Message checkEmpName(String empName) {
		//判断员工名是否合法
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5}$)";
		if (!empName.matches(regx)) {
			return Message.fail().add("val_msg", "用户名可以是2-5个汉字或6-16位英文、数字、下划线、横杠组成");
		}
		
		//数据库员工名重复校验
		boolean b = service.checkUser(empName);//校验用户名是否已存在
		//返回true则可用,否则不可用
		
		if (b) {
			return Message.success();
		} else {
			return Message.fail().add("val_msg", "用户名已存在，不可用！");
		}
	}
	
	/**
	 * URI风格
	 * -> /emp/{id}  GET----查询员工
	 * -> /emp       POST----保存员工
	 * -> /emp/{id}  PUT----修改员工
	 * -> /emp/{id}  DELETE----删除员工
	 * @return
	 */
	//根据id查询员工信息
	@ResponseBody
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	public Message getEmp(@PathVariable("id")Integer id) {
		
		Employee employee = service.getEmp(id);//根据id查询员工信息
		
		return Message.success().add("emp", employee);
	}
	
	/**
	 * URI风格
	 * -> /emp/{id}  GET----查询员工
	 * -> /emp       POST----保存员工
	 * -> /emp/{id}  PUT----修改员工
	 * -> /emp/{id}  DELETE----删除员工
	 * @return
	 */
	//保存员工更新信息
	@ResponseBody
	@RequestMapping(value="/emp/{id}",method=RequestMethod.PUT)
	public Message saveEmp(Employee employee) {
		
		//PUT方法  request.getParameter("xx")中的数据拿不到
		//Employee [id=1, name=null, gender=null, email=null, dept_id=null, department=null]
		//数据封装不上:原因是tomcat先将数据封装成map,再调用request.getParameter("xx");而Spring MVC只是调用request.getParameter("xx")
		//解决方案:引入Filter-->
		/*
		 <!-- ajax发送PUT请求时服务器能解析到数据 -->
		    <filter>
		    	<filter-name>httpPutFormContentFilter</filter-name>
		    	<filter-class>org.springframework.web.filter.HttpPutFormContentFilter</filter-class>
		    </filter>
		    <filter-mapping>
		    	<filter-name>httpPutFormContentFilter</filter-name>
		    	<url-pattern>/*</url-pattern>
		    </filter-mapping>
		 */
		
		service.updateEmp(employee);//保存员工更新信息
		
		return Message.success();
	}
	
	/**
	 * URI风格
	 * -> /emp/{id}  GET----查询员工
	 * -> /emp       POST----保存员工
	 * -> /emp/{id}  PUT----修改员工
	 * -> /emp/{id}  DELETE----删除员工
	 * @return
	 */
	//删除单个员工和批量删除员工2合1
	//删除单个员工:1
	//批量删除员工:1-2-3-4...
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Message deleteEmp(@PathVariable("ids")String ids) {
		
		if (ids.contains("-")) {
			//批量删除
			String[] str_ids = ids.split("-");
			//组装id集合
			List<Integer> del_ids = new ArrayList<>();
			for (String str_id : str_ids) {
				del_ids.add(Integer.parseInt(str_id));
			}
			service.deleteEmpBatch(del_ids);//批量删除员工
		} else {
			//单个删除
			Integer id = Integer.parseInt(ids);
			service.deleteEmp(id);//删除单个员工
		}
		
		return Message.success();
	}
}

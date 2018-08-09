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
 * ����Ա����ɾ�Ĳ�
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {
	
	//ע��service
	@Autowired
	private EmployeeService service;
	
	/**
	 * ��ѯ����Ա��
	 * @param pn ��ѯ��ҳ��
	 * @return
	 * ��ҳ��ѯ,Ĭ�ϴӵ�һҳ��ʼ��ѯ
	 */
	@RequestMapping("/emps")
	public String name(@RequestParam(value="pn", defaultValue="1")Integer pn, Model model) {
		//��ҳ��ѯ
		//����pageHelper
		PageHelper.startPage(pn, 5);//����ҳ�뼰ÿҳ�ĸ���
		List<Employee> emps = service.getAllEmpWithDept();//��ѯ����Ա��
		//��װ��PageInfo--��װ�˷�ҳ��ϸ��Ϣ
		PageInfo pageinfo = new PageInfo(emps, 5);//������ʾ5ҳ
		
		model.addAttribute("pageinfo", pageinfo);
		
		return "list";
	}
	
	/**
	 * ��json��ʽ���ط�ҳ��Ϣ
	 * ����jackson��
	 * @param pn ��ѯ��ҳ��
	 * @return
	 */
	@RequestMapping("/empsjson")
	@ResponseBody//�Զ�������ת��json�ַ���
	public Message getEmpWithJson(@RequestParam(value="pn", defaultValue="1")Integer pn){
		//��ҳ��ѯ
		//����pageHelper
		PageHelper.startPage(pn, 5);//����ҳ�뼰ÿҳ�ĸ���
		List<Employee> emps = service.getAllEmpWithDept();//��ѯ����Ա��
		//��װ��PageInfo--��װ�˷�ҳ��ϸ��Ϣ
		PageInfo pageinfo = new PageInfo(emps, 5);//������ʾ5ҳ
		
		return Message.success().add("pageinfo", pageinfo);//����ͨ�õĴ���������,���а���json����
	}
	
	/**
	 * URI���
	 * -> /emp/{id}  GET----��ѯԱ��
	 * -> /emp       POST----����Ա��
	 * -> /emp/{id}  PUT----�޸�Ա��
	 * -> /emp/{id}  DELETE----ɾ��Ա��
	 * 
	 * ���JSR303У��
	 * �����hibernate-validator
	 * @return
	 */
	//��������Ա����Ϣ
	@ResponseBody
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public Message saveEmp(@Valid Employee employee,BindingResult result) {//���JSR303У��,��BindingResultУ����
		
		//��װ������Ϣ
		Map<String, Object> map = new HashMap<>();
		if (result.hasErrors()) {
			//У��ʧ��,����ʧ����Ϣ
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				map.put(error.getField(), error.getDefaultMessage());//�����ֶ�+������Ϣ
			}
			return Message.fail().add("errorFields", map);
		} else {
			service.saveEmp(employee);//��������Ա����Ϣ
			
			return Message.success();
		}
	}
	
	//ÿ�α���Ա����Ϣʱ��У���û����Ƿ��Ѵ���
	@ResponseBody
	@RequestMapping("/checkuser")
	public Message checkEmpName(String empName) {
		//�ж�Ա�����Ƿ�Ϸ�
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5}$)";
		if (!empName.matches(regx)) {
			return Message.fail().add("val_msg", "�û���������2-5�����ֻ�6-16λӢ�ġ����֡��»��ߡ�������");
		}
		
		//���ݿ�Ա�����ظ�У��
		boolean b = service.checkUser(empName);//У���û����Ƿ��Ѵ���
		//����true�����,���򲻿���
		
		if (b) {
			return Message.success();
		} else {
			return Message.fail().add("val_msg", "�û����Ѵ��ڣ������ã�");
		}
	}
	
	/**
	 * URI���
	 * -> /emp/{id}  GET----��ѯԱ��
	 * -> /emp       POST----����Ա��
	 * -> /emp/{id}  PUT----�޸�Ա��
	 * -> /emp/{id}  DELETE----ɾ��Ա��
	 * @return
	 */
	//����id��ѯԱ����Ϣ
	@ResponseBody
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	public Message getEmp(@PathVariable("id")Integer id) {
		
		Employee employee = service.getEmp(id);//����id��ѯԱ����Ϣ
		
		return Message.success().add("emp", employee);
	}
	
	/**
	 * URI���
	 * -> /emp/{id}  GET----��ѯԱ��
	 * -> /emp       POST----����Ա��
	 * -> /emp/{id}  PUT----�޸�Ա��
	 * -> /emp/{id}  DELETE----ɾ��Ա��
	 * @return
	 */
	//����Ա��������Ϣ
	@ResponseBody
	@RequestMapping(value="/emp/{id}",method=RequestMethod.PUT)
	public Message saveEmp(Employee employee) {
		
		//PUT����  request.getParameter("xx")�е������ò���
		//Employee [id=1, name=null, gender=null, email=null, dept_id=null, department=null]
		//���ݷ�װ����:ԭ����tomcat�Ƚ����ݷ�װ��map,�ٵ���request.getParameter("xx");��Spring MVCֻ�ǵ���request.getParameter("xx")
		//�������:����Filter-->
		/*
		 <!-- ajax����PUT����ʱ�������ܽ��������� -->
		    <filter>
		    	<filter-name>httpPutFormContentFilter</filter-name>
		    	<filter-class>org.springframework.web.filter.HttpPutFormContentFilter</filter-class>
		    </filter>
		    <filter-mapping>
		    	<filter-name>httpPutFormContentFilter</filter-name>
		    	<url-pattern>/*</url-pattern>
		    </filter-mapping>
		 */
		
		service.updateEmp(employee);//����Ա��������Ϣ
		
		return Message.success();
	}
	
	/**
	 * URI���
	 * -> /emp/{id}  GET----��ѯԱ��
	 * -> /emp       POST----����Ա��
	 * -> /emp/{id}  PUT----�޸�Ա��
	 * -> /emp/{id}  DELETE----ɾ��Ա��
	 * @return
	 */
	//ɾ������Ա��������ɾ��Ա��2��1
	//ɾ������Ա��:1
	//����ɾ��Ա��:1-2-3-4...
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Message deleteEmp(@PathVariable("ids")String ids) {
		
		if (ids.contains("-")) {
			//����ɾ��
			String[] str_ids = ids.split("-");
			//��װid����
			List<Integer> del_ids = new ArrayList<>();
			for (String str_id : str_ids) {
				del_ids.add(Integer.parseInt(str_id));
			}
			service.deleteEmpBatch(del_ids);//����ɾ��Ա��
		} else {
			//����ɾ��
			Integer id = Integer.parseInt(ids);
			service.deleteEmp(id);//ɾ������Ա��
		}
		
		return Message.success();
	}
}

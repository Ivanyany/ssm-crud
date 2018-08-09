package com.ivan.ssm.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * ͨ�õķ�����
 * @author Administrator
 *
 */
public class Message {
	//״̬�� 100�ɹ�   200ʧ��
	private int code;
	//��ʾ��Ϣ
	private String msg;
	//�û�Ҫ���ظ������������
	private Map<String, Object> extend = new HashMap<>();
	
	//����ɹ�
	public static Message success(){
		Message result = new Message();
		result.setCode(100);
		result.setMsg("����ɹ�");
		return result;
	}
	
	//����ɹ�
	public static Message fail(){
		Message result = new Message();
		result.setCode(200);
		result.setMsg("����ʧ��");
		return result;
	}
	
	//�������
	public Message add(String key, Object value) {
		this.getExtend().put(key, value);
		return this;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getExtend() {
		return extend;
	}
	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}	
}

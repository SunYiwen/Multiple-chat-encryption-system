package com.Sunsss.socket;



import java.util.Vector;
/*
 * Ҫʵ��ÿ���ͻ���֮���ͨ�ţ�����Ҫ����һ��ChatManager�����й���
 * */
public class ChatManager {
      //��Ϊһ�����������ֻ��һ��ChatManager������Ҫ����һ������
	
	private ChatManager(){}
	private static final ChatManager cm= new ChatManager();
	public static  ChatManager getchaChatManager(){
		return cm;
	}
	
	Vector<ChatSocket> vector=new Vector<ChatSocket>();
	public void add(ChatSocket cs){
		    vector.add(cs);//��ÿһ���̼߳��뼯����
	}
	public void remove(ChatSocket cs) {
		vector.remove(cs);
	}
	public  void publish(ChatSocket cs,String chatinfo){//��ʾ��ǰ���̸߳������е�ÿһ���̷߳��͵���Ϣ��Ҳ����ǰ�Ŀͻ��˸�ÿһ���ͻ��˷�����Ϣ
		//Ҫ�����������е��̷߳�����Ϣ�ͱ�������������
		for (int i = 0; i < vector.size(); i++) {
			ChatSocket csChatSocket=vector.get(i);
			if(!csChatSocket.equals(cs)){//����Ҫ�жϲ������ǰ�ͻ��˷�����Ϣ��Ҳ�������Լ�������Ϣ
				csChatSocket.out(chatinfo);//������Ϣ�������Ŀͻ���
			}
		}
	}
}

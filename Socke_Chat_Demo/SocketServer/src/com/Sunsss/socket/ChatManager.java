package com.Sunsss.socket;



import java.util.Vector;
/*
 * 要实现每个客户端之间的通信，则需要定义一个ChatManager来进行管理
 * */
public class ChatManager {
      //因为一个聊天服务器只有一个ChatManager所以需要创建一个单例
	
	private ChatManager(){}
	private static final ChatManager cm= new ChatManager();
	public static  ChatManager getchaChatManager(){
		return cm;
	}
	
	Vector<ChatSocket> vector=new Vector<ChatSocket>();
	public void add(ChatSocket cs){
		    vector.add(cs);//将每一个线程加入集合中
	}
	public void remove(ChatSocket cs) {
		vector.remove(cs);
	}
	public  void publish(ChatSocket cs,String chatinfo){//表示当前的线程给集合中的每一个线程发送的信息，也即当前的客户端给每一个客户端发送信息
		//要给集合中所有的线程发送信息就必须遍历这个集合
		for (int i = 0; i < vector.size(); i++) {
			ChatSocket csChatSocket=vector.get(i);
			if(!csChatSocket.equals(cs)){//则需要判断不许给当前客户端发送信息，也即不给自己发送信息
				csChatSocket.out(chatinfo);//发送信息给其他的客户端
			}
		}
	}
}

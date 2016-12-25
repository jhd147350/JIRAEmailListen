package com.test;

import java.util.ArrayList;
import java.util.List;

import hydra.importmailmessage.imp.ImportMessageToJIRA;
import hydra.importmessage.inter.ImportMessage;
import hydra.mailbox.abs.AbstractMailboxListen;
import hydra.mailbox.imp.OutlookEmailboxListen;
import hydra.tool.MailboxType;
import hydra.vo.abs.MailboxAccount;
import hydra.vo.imp.JIRAAccount;
import hydra.vo.imp.OutlookMailboxAccount;
import hydra.vo.inter.Account;
public class Client {
	public static void main(String args[]){
		/*Account emailaccount=new MailboxAccount(MailboxType.WY163, null, "段雪超", "ddd");
		Account jiraaccount=new JIRAAccount("张三", "ddd");
		QQEmailboxListen emailboxListen=new QQEmailboxListen(null);
		Message emailmessage=emailboxListen.getEmail((MailboxAccount)emailaccount);
		ImportMessage mailtojira=new ImportMessageToJIRA();
		mailtojira.execute(jiraaccount, emailmessage);*/
		test1();
	}
	public static void test1(){
		List<MailboxAccount> accounts=new ArrayList<MailboxAccount>();
		accounts.add(new OutlookMailboxAccount(MailboxType.OUTLOOK, null, "duan.xuechao@21vianet.com", "dD.5885808"));
		System.out.println(accounts.get(0).getUsername());
		AbstractMailboxListen listen =new OutlookEmailboxListen(null);
		Account jiraaccount=new JIRAAccount("张三", "ddd");
		ImportMessage mailtojira=new ImportMessageToJIRA();
		listen.getEmail(accounts);
		synchronized(listen){
			while(true){
				try {
					listen.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(listen.mailMessage!=null){
					mailtojira.execute(jiraaccount, listen.mailMessage);
					listen.notify();
				}
			}
		}
	}
}

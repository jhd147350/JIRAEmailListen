package com.test;

import java.util.ArrayList;
import java.util.List;

import hydra.importmailmessage.imp.ImportMessageToJIRA;
import hydra.importmessage.inter.ImportMessage;
import hydra.mailbox.abs.AbstractMailboxListen;
import hydra.mailbox.imp.QQEmailboxListen;
import hydra.tool.MailboxType;
import hydra.vo.JIRAAccount;
import hydra.vo.MailboxAccount;
import hydra.vo.inter.Account;
public class Client {
	public static void main(String args[]){
		/*Account emailaccount=new MailboxAccount(MailboxType.WY163, null, "��ѩ��", "ddd");
		Account jiraaccount=new JIRAAccount("����", "ddd");
		QQEmailboxListen emailboxListen=new QQEmailboxListen(null);
		Message emailmessage=emailboxListen.getEmail((MailboxAccount)emailaccount);
		ImportMessage mailtojira=new ImportMessageToJIRA();
		mailtojira.execute(jiraaccount, emailmessage);*/
		test1();
	}
	public static void test1(){
		List<MailboxAccount> accounts=new ArrayList<MailboxAccount>();
		accounts.add(new MailboxAccount(MailboxType.QQ, null, "����", "dd"));
		accounts.add(new MailboxAccount(MailboxType.QQ, null, "����", "dd"));
		accounts.add(new MailboxAccount(MailboxType.QQ, null, "����", "dd"));
		AbstractMailboxListen listen =new QQEmailboxListen(null);
		Account jiraaccount=new JIRAAccount("����", "ddd");
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

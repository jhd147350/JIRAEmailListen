package com.test;

import hydra.importmailmessage.imp.ImportMessageToJIRA;
import hydra.importmessage.inter.ImportMessage;
import hydra.mailbox.imp.QQEmailboxListen;
import hydra.tool.MailboxType;
import hydra.vo.MailboxAccount;
import hydra.vo.inter.Account;
import hydra.vo.inter.Message;
import hydra.vo.JIRAAccount;
public class Client {
	public static void main(String args[]){
		Account emailaccount=new MailboxAccount(MailboxType.WY163, null, "¶ÎÑ©³¬", "ddd");
		Account jiraaccount=new JIRAAccount("ÕÅÈý", "ddd");
		QQEmailboxListen emailboxListen=new QQEmailboxListen(null);
		Message emailmessage=emailboxListen.getEmail((MailboxAccount)emailaccount);
		ImportMessage mailtojira=new ImportMessageToJIRA();
		mailtojira.execute(jiraaccount, emailmessage);
	}
}

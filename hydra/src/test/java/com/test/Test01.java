package com.test;

import java.util.List;

import hydra.mailbox.imp.OutlookEmailboxListen;
import hydra.tool.PropertiesHelper;
import hydra.vo.MailMessage;
import hydra.vo.OutlookMailboxAccount;

public class Test01 {

	public static void main(String[] args) {
		//在config.properties 中以键值对的方式添加邮箱和密码
		// TODO Auto-generated method stub
		List<OutlookMailboxAccount> accounts = PropertiesHelper.readProperties();
		while(true){
			for(OutlookMailboxAccount temp:accounts){
				OutlookEmailboxListen listen=new OutlookEmailboxListen(null);
				List<MailMessage> emails =  listen.getUnReadMails(temp);
				for(MailMessage temp1:emails){
					System.out.println(temp1.getSubject());
				}
			}
			try {
				Thread.currentThread().sleep(10l*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}

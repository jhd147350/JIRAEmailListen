package hydra.importmailmessage.imp;

import hydra.importmailmessage.abs.ImportMailMessage;
import hydra.vo.JIRAAccount;
import hydra.vo.MailMessage;
import hydra.vo.inter.Account;

public class ImportMessageToJIRA extends ImportMailMessage{

	@Override
	public String getSession(Account account) {
		// TODO Auto-generated method stub
		return "abc";
	}
	@Override
	protected void import_exe(Account account, MailMessage mailMessage) {
		// TODO Auto-generated method stub
		JIRAAccount jiraaccount=(JIRAAccount)account;
		System.out.println("����ʹ�����cookie��"+getSession(account)+"��¼"+jiraaccount.getUsername()+"���˺Ų�������task");
	}

}
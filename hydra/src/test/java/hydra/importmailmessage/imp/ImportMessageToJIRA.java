package hydra.importmailmessage.imp;

import hydra.importmessage.abs.AbstractImportMailMessage;
import hydra.vo.abs.MailMessage;
import hydra.vo.imp.JIRAAccount;
import hydra.vo.inter.Account;

public class ImportMessageToJIRA extends AbstractImportMailMessage{

	@Override
	protected String getSession(Account account) {
		// TODO Auto-generated method stub
		return "abc";
	}
	@Override
	protected void import_exe(Account account, MailMessage mailMessage) {
		// TODO Auto-generated method stub
		JIRAAccount jiraaccount=(JIRAAccount)account;
		System.out.println("我们使用这个cookie："+getSession(account)+"登录"+jiraaccount.getUsername()+"的账号并创建了task/n"+mailMessage.getBody());
	}

}

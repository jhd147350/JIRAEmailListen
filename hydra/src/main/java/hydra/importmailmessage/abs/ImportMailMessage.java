package hydra.importmailmessage.abs;

import hydra.importmessage.inter.ImportMessage;
import hydra.vo.MailMessage;
import hydra.vo.inter.Account;


public abstract class ImportMailMessage implements ImportMessage {
	
	public void execute(Account account, hydra.vo.inter.Message message) {
		import_exe(account,(MailMessage)message);
	}
	protected abstract String getSession(Account account);
	protected abstract void import_exe(Account account,MailMessage mailMessage);
	
}

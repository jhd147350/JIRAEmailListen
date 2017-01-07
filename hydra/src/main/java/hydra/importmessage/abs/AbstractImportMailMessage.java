package hydra.importmessage.abs;

import hydra.importmessage.inter.ImportMessage;
import hydra.vo.abs.MailMessage;
import hydra.vo.inter.Account;


public abstract class AbstractImportMailMessage implements ImportMessage {
	
	public void execute(Account account, hydra.vo.inter.Message message) {
		import_exe(account,(MailMessage)message);
	}
	protected abstract String getSession(Account account);
	protected abstract void import_exe(Account account,MailMessage mailMessage);
	
}

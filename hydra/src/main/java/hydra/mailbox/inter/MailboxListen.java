package hydra.mailbox.inter;

import java.util.List;

import hydra.vo.MailMessage;
import hydra.vo.MailboxAccount;

public interface MailboxListen {
	public MailMessage getEmail(MailboxAccount account);
	public List<MailMessage> getUnReadMails(MailboxAccount account);
}

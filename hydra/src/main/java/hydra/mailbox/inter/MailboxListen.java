package hydra.mailbox.inter;

import hydra.vo.MailMessage;
import hydra.vo.MailboxAccount;

public interface MailboxListen {
	public MailMessage getEmail(MailboxAccount account);
}

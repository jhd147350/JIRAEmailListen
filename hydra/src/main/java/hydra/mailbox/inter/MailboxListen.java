package hydra.mailbox.inter;

import hydra.vo.abs.MailMessage;
import hydra.vo.abs.MailboxAccount;

public interface MailboxListen {
	public MailMessage getEmail(MailboxAccount account);
}

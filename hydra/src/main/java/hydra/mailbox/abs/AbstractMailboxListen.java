package hydra.mailbox.abs;

import hydra.mailbox.inter.MailboxListen;
import hydra.tool.MailboxType;
import hydra.vo.MailMessage;
import hydra.vo.MailboxAccount;

public abstract class AbstractMailboxListen implements MailboxListen{
	protected MailboxType mailboxType;
	private MailboxListen nextmailboxListen;
	public AbstractMailboxListen(MailboxListen nextmailboxListen){
		this.nextmailboxListen=nextmailboxListen;
	}
	public MailMessage getEmail(MailboxAccount account) {
		// TODO Auto-generated method stub
		if(account.getMailboxtype()==mailboxType){
			return get_Email(account);
		}else if(this.nextmailboxListen!=null){
			return nextmailboxListen.getEmail(account);
		}else{
			System.out.println("没有适合的监听器监听此邮箱账户");
			return null;
		}
	}
	protected abstract MailMessage get_Email(MailboxAccount account);
}

package hydra.mailbox.imp;

import java.util.List;

import hydra.mailbox.abs.AbstractMailboxListen;
import hydra.mailbox.inter.MailboxListen;
import hydra.tool.MailboxType;
import hydra.vo.abs.MailMessage;
import hydra.vo.abs.MailboxAccount;

public class QQEmailboxListen extends AbstractMailboxListen {

	public QQEmailboxListen(MailboxListen nextmailboxListen) {
		super(nextmailboxListen);
		// TODO Auto-generated constructor stub
		this.mailboxType=MailboxType.QQ;
	}

	@Override
	protected MailMessage get_Email(MailboxAccount account) {
		// TODO Auto-generated method stub
		System.out.println("���ǵ�¼"+account.getUsername()+"���˺Ų���ȡ���µ��ʼ�");
		return new MailMessage(null, null, null, null, null, null, null);
	}

	public List<MailMessage> getUnReadMails(MailboxAccount account) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MailMessage getEmail(MailboxAccount account) {
		// TODO Auto-generated method stub
		return super.getEmail(account);
	}

}

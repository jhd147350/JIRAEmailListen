package hydra.mailbox.imp;

import hydra.mailbox.abs.AbstractMailboxListen;
import hydra.mailbox.inter.MailboxListen;
import hydra.tool.MailboxType;
import hydra.vo.MailMessage;
import hydra.vo.MailboxAccount;

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

}
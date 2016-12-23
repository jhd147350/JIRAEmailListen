package hydra.vo;

import hydra.tool.MailboxType;
import hydra.vo.inter.Account;

public class MailboxAccount implements Account{
	private MailboxType mailboxtype;
	private String subdir;
	private String username;
	private String password;
	
	public MailboxAccount(MailboxType mailboxtype, String subdir, String username, String password) {
		super();
		this.mailboxtype = mailboxtype;
		this.subdir = subdir;
		this.username = username;
		this.password = password;
	}



	public String getSubdir() {
		return subdir;
	}
	public void setSubdir(String subdir) {
		this.subdir = subdir;
	}
	public MailboxType getMailboxtype() {
		return mailboxtype;
	}
	public void setMailboxtype(MailboxType mailboxtype) {
		this.mailboxtype = mailboxtype;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

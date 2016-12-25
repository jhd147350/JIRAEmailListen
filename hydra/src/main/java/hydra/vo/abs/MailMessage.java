package hydra.vo.abs;

import java.util.Date;

import hydra.tool.MailboxType;
import hydra.vo.inter.Message;

public class MailMessage implements Message{
	private String subject;
	private String addresser;
	private String[] receiver;
	private String[] cc;
	private String body;
	private Date date;
	private MailboxType mailboxtype;
	public MailMessage(String subject, String addresser, String[] receiver, String[] cc, String body, Date date,
			MailboxType mailboxtype) {
		super();
		this.subject = subject;
		this.addresser = addresser;
		this.receiver = receiver;
		this.cc = cc;
		this.body = body;
		this.date = date;
		this.mailboxtype = mailboxtype;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAddresser() {
		return addresser;
	}
	public void setAddresser(String addresser) {
		this.addresser = addresser;
	}
	public String[] getReceiver() {
		return receiver;
	}
	public void setReceiver(String[] receiver) {
		this.receiver = receiver;
	}
	public String[] getCc() {
		return cc;
	}
	public void setCc(String[] cc) {
		this.cc = cc;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public MailboxType getMailboxtype() {
		return mailboxtype;
	}
	public void setMailboxtype(MailboxType mailboxtype) {
		this.mailboxtype = mailboxtype;
	}
	
}

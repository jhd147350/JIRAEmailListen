package hydra.mailbox.abs;

import java.util.List;

import hydra.mailbox.inter.MailboxListen;
import hydra.tool.MailboxType;
import hydra.vo.MailMessage;
import hydra.vo.MailboxAccount;
import hydra.vo.inter.Account;

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
	public MailMessage mailMessage=null;
	
	public void getEmail(List<MailboxAccount> account) {
		final AbstractMailboxListen abstractMailboxListen=this;
		final List<MailboxAccount> accounts =account;
		final int L =accounts.size();
		Runnable getemail = new Runnable() {
			public void run() {
				synchronized (abstractMailboxListen) {
					int j=0;
					while(j<10){
						System.out.println("第"+j+"次监听");
						j++;
						for(int i=0;i<L;i++){
							if(accounts.get(i).getMailboxtype()==mailboxType){
								mailMessage= get_Email(accounts.get(i));
								abstractMailboxListen.notify();
								try {
									abstractMailboxListen.wait();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}							
							}else if(nextmailboxListen!=null){
								mailMessage=nextmailboxListen.getEmail(accounts.get(i));
							}else{
								System.out.println("没有适合的监听器监听此邮箱账户");
								System.exit(1);
							}
						}
					}
				}
			}
		};
		
		Thread thread=new Thread(getemail);
		thread.start();
	}
	protected abstract MailMessage get_Email(MailboxAccount account);
}

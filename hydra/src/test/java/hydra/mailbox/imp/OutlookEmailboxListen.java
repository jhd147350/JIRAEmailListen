package hydra.mailbox.imp;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import hydra.mailbox.abs.AbstractMailboxListen;
import hydra.mailbox.inter.MailboxListen;
import hydra.tool.MailboxType;
import hydra.vo.MailMessage;
import hydra.vo.MailboxAccount;
import hydra.vo.OutlookMailboxAccount;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.service.ConflictResolutionMode;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.EmailMessageSchema;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.EmailAddressCollection;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;
import microsoft.exchange.webservices.data.search.filter.SearchFilter;

public class OutlookEmailboxListen extends AbstractMailboxListen {

	private Folder inbox;
	private ItemView view;
	private SearchFilter sf;
	private FindItemsResults<Item> findResults;

	public OutlookEmailboxListen(MailboxListen nextmailboxListen) {
		super(nextmailboxListen);
		// TODO Auto-generated constructor stub
		this.mailboxType = MailboxType.OUTLOOK;
	}

	public List<MailMessage> getUnReadMails(MailboxAccount account) {
		// TODO Auto-generated method stub
		initComponent((OutlookMailboxAccount) account);
		return queryUnReadMails((OutlookMailboxAccount) account);
		//return ;
	}

	private void initComponent(OutlookMailboxAccount account) {
		// 连接邮箱
		try {
			inbox = Folder.bind(account.getEs(), WellKnownFolderName.Inbox);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("pls check your email and password!");
		}
		// 准备进行未读邮件查取
		// 最多查询到的数量
		view = new ItemView(10);
		// 过滤未读邮件
		sf = new SearchFilter.IsEqualTo(EmailMessageSchema.IsRead, false);
	}

	private List<MailMessage> queryUnReadMails(OutlookMailboxAccount account) {
		
		List<MailMessage> data=new ArrayList<MailMessage>();
		
		try {
			// 查询返回结果
			findResults = account.getEs().findItems(inbox.getId(), sf, view);
			// get all unread numbers.
			// unReadNum = findResults.getTotalCount();
			// System.out.println("" + unReadNum);

			for (Item item : findResults.getItems()) {
				EmailMessage message = EmailMessage.bind(account.getEs(), item.getId()); // 输出未读邮件信息
								System.out.print(message.getSender());
				//System.out.println("Subject:" + message.getSubject()); //
				MessageBody mb = message.getBody(); //
				//System.out.println("body-->" + mb.toString());
				MailMessage mm=new MailMessage();
				//mm.setAddresser(message.);
				mm.setBody(mb.toString());
				EmailAddressCollection ccRecipients = message.getCcRecipients();
				List<EmailAddress> list = ccRecipients.getItems();
				String[] cc=new String[list.size()];
				for(int i=0;i<list.size();i++){
					cc[i]=list.get(i).getAddress();
					//address.getAddress();
				}
				mm.setCc(cc);
				mm.setSubject(message.getSubject());
				
				data.add(mm);

				// 将邮件置为已读 // message.setIsRead(true); // 更新到服务器上 //
				//message.update(ConflictResolutionMode.AlwaysOverwrite);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("pls check your email and password!");
		}
		return data;

	}

	@Override
	protected MailMessage get_Email(MailboxAccount account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MailMessage getEmail(MailboxAccount account) {
		// TODO Auto-generated method stub
		return super.getEmail(account);
	}

}

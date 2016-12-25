package hydra.mailbox.imp;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import hydra.mailbox.abs.AbstractMailboxListen;
import hydra.mailbox.inter.MailboxListen;
import hydra.tool.MailboxType;
import hydra.vo.abs.MailMessage;
import hydra.vo.abs.MailboxAccount;
import hydra.vo.imp.OutlookMailboxAccount;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.service.ConflictResolutionMode;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
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
		// ��������
		try {
			inbox = Folder.bind(account.getEs(), WellKnownFolderName.Inbox);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("pls check your email and password!");
		}
		// ׼������δ���ʼ���ȡ
		// ����ѯ��������
		view = new ItemView(1);
		// ����δ���ʼ�
		sf = new SearchFilter.IsEqualTo(EmailMessageSchema.IsRead, false);
	}

	private List<MailMessage> queryUnReadMails(OutlookMailboxAccount account) {
		
		List<MailMessage> data=new ArrayList<MailMessage>();
		
		try {
			// ��ѯ���ؽ��
			findResults = account.getEs().findItems(inbox.getId(), sf, view);
			// get all unread numbers.
			// unReadNum = findResults.getTotalCount();
			// System.out.println("" + unReadNum);

			for (Item item : findResults.getItems()) {
				EmailMessage message = EmailMessage.bind(account.getEs(), item.getId()); // ���δ���ʼ���Ϣ
								System.out.print(message.getSender());
				//System.out.println("Subject:" + message.getSubject()); //
				MessageBody mb = message.getBody(); //
				//System.out.println("body-->" + mb.toString());
				MailMessage mm=new MailMessage(null, null, null, null, null, null, null);
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

				// ���ʼ���Ϊ�Ѷ� // message.setIsRead(true); // ���µ��������� //
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
		initComponent((OutlookMailboxAccount) account);
		MailMessage mm=new MailMessage(null, null, null, null, null, null, null);
		try {
		// ��ѯ���ؽ��
		findResults = ((OutlookMailboxAccount)account).getEs().findItems(inbox.getId(), sf, view);
		// get all unread numbers.
		// unReadNum = findResults.getTotalCount();
		// System.out.println("" + unReadNum);
			for (Item item : findResults.getItems()) {
				EmailMessage message;
				
				message = EmailMessage.bind(((OutlookMailboxAccount)account).getEs(), item.getId());
				//System.out.print(message.getSender());
				//System.out.println("Subject:" + message.getSubject()); //
				MessageBody mb = message.getBody(); //
				//System.out.println("body-->" + mb.toString());
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
				// ���ʼ���Ϊ�Ѷ� // 
				message.setIsRead(true); // ���µ��������� //
				message.update(ConflictResolutionMode.AlwaysOverwrite);
					
			}
		} catch (ServiceLocalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		return mm;
	}

}

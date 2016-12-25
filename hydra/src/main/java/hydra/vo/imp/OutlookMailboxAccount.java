package hydra.vo.imp;

import java.net.URI;
import java.net.URISyntaxException;

import hydra.tool.MailboxType;
import hydra.tool.URLStrings;
import hydra.vo.abs.MailboxAccount;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;

public class OutlookMailboxAccount extends MailboxAccount {
	
	private ExchangeService es;
	private ExchangeCredentials ec;

	public OutlookMailboxAccount(MailboxType mailboxtype, String subdir, String username, String password) {
		super(mailboxtype, subdir, username, password);
		// TODO Auto-generated constructor stub
		initLoginInfo();
	}
	private void initLoginInfo() {
		es = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		ec = new WebCredentials(this.getUsername(), this.getPassword());
		es.setCredentials(ec);
		// Setting the URL of the Service
		try {
			es.setUrl(new URI(URLStrings.OUTLOOK));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ExchangeService getEs() {
		return es;
	}

	public void setEs(ExchangeService es) {
		this.es = es;
	}

	public ExchangeCredentials getEc() {
		return ec;
	}

	public void setEc(ExchangeCredentials ec) {
		this.ec = ec;
	}

}

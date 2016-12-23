package hydra.importmessage.inter;

import hydra.vo.inter.Account;
import hydra.vo.inter.Message;

public interface ImportMessage {
	public void execute(Account account,Message message );
}


package hydra.vo.imp;

import hydra.vo.inter.Account;

public class JIRAAccount implements Account {
	private String username;
	private String password;
	
	public JIRAAccount(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

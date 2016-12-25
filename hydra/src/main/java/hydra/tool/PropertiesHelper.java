package hydra.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import hydra.vo.OutlookMailboxAccount;

public class PropertiesHelper {


	public static List<OutlookMailboxAccount> readProperties() {

		// HashMap<String, String> m=new HashMap<String,String>();
		List<OutlookMailboxAccount> users = new ArrayList<OutlookMailboxAccount>();
		File file = new File("config.properties");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Properties p = new Properties();
		try {

			p.load(new FileInputStream(file));
			// FIRSTRUN = Boolean.parseBoolean(p.getProperty(FIRSTRUN_STRING));
			Set<Object> keySet = p.keySet();
			Object[] array = keySet.toArray();
			for (Object o : keySet) {
				OutlookMailboxAccount account=new OutlookMailboxAccount(o.toString(), p.get(o).toString());
				//users.add(new OutlookMailboxAccount(o.toString(), p.get(o).toString()));
				users.add(account);
				// m.put(o.toString(), p.get(o).toString());
				/*
				 * System.out.println(o); System.out.println(p.get(o));
				 */
			}
			// p.get(array)
			// p.getProperty(keySet.)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(users.toString());
		return users;
	}

	
	//if password is null then remove the email from list, otherwise add a new email to the list
	public static void writeProperties(String email, String password) {

		File file = new File("config.properties");
		//file.

		Properties p = new Properties();
		try {
			//load the previous data
			FileInputStream input = new FileInputStream(file);
			p.load(input);
			input.close();

			//overwrite the previous data
			FileOutputStream output = new FileOutputStream(file);
			// p.put(FIRSTRUN_STRING, "false");
			// p.setProperty("phone22", "123456");
			// p.put(USERNAME_STRING, USERNAME);
			// p.put(PASSWORD_STRING, PASSWORD);
			if(password==null){
				p.remove(email);
			}else{
				p.put(email, password);
			}
			

			p.store(output, "new add");
			output.close();
			System.out.println("Your info has been saved!");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}

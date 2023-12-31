package bus;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManagerAccounts {
	// public static methods: write/read
	private static String filePath = "src//data//accounts.ser";

	public static void serialize(ArrayList<Account> listFromConsole) throws IOException {
		// Write into file : Serialize
		FileOutputStream fileOutputStream = new FileOutputStream(filePath);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(listFromConsole);
		objectOutputStream.close();
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Account> deserialize() throws IOException, ClassNotFoundException {
		// Read from file : Deserialize
		ArrayList<Account> listFromFile = new ArrayList<Account>();
		FileInputStream fileInputStream = new FileInputStream(filePath);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		listFromFile = (ArrayList<Account>) objectInputStream.readObject();
		objectInputStream.close();

		return listFromFile;
	}
	
	public static void saveNewAccount(Account account) throws ClassNotFoundException, IOException {		
		ArrayList<Account> listOfAcounts = deserialize();
		
		listOfAcounts.add(account);
		
		serialize(listOfAcounts);
	}
}

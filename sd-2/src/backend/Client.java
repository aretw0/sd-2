package backend;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.Account;

public class Client {
	
	public static String host = "localhost";
	
	public static Account myAcc;
	
	private static BankAcc stub;
	
	public static boolean startStub() {
		try {
			 Registry registry = LocateRegistry.getRegistry(Client.host, 1988);
			 Client.stub = (BankAcc) registry.lookup("Bank");
			 return true;
		} catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
		return false;
	}
	
	public static void dumpServer() {
		try {
			Client.stub.dumpList();
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
	
	public static boolean login(String cpf,String password) {
		
		try {
			 String acc = Client.stub.login(cpf, password);
			 if (acc != null) {
				 String[] accinfo = acc.split("#");
				 Client.myAcc = new Account(accinfo);
				 
				 return true;
			 } else {
				 System.out.println("Falha no login");
				 return false;
			 }
		} catch (Exception e) {
           System.err.println("Client exception: " + e.toString());
           e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean createAcc(String cpf, String fName, String lName, String address, String birthDate, String phoneNumber,
			String password) {
		try {
			int acN = Client.stub.createAcc(cpf, fName, lName, address, birthDate, phoneNumber, password);
			if (acN != -1) {
				Client.myAcc = new Account(acN,cpf,fName,lName,address,birthDate,phoneNumber);	
				return true;
			}
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
           e.printStackTrace();
		}
		
		
		return false;
	}	
	
	public static boolean lootCC(String password, double ammount) {
		try {
			if (Client.stub.lootCC(password, Client.myAcc.getAccNumber(), ammount)) {
				Client.myAcc.setBalanceCC(Client.myAcc.getBalanceCC() - ammount);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		
		return false;
	}
	public static boolean lootCP(String password, double ammount) {
		try {
			if (Client.stub.lootCC(password, Client.myAcc.getAccNumber(), ammount)) {
				Client.myAcc.setBalanceCP(Client.myAcc.getBalanceCP() - ammount);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean depositCC(String password, double ammount) {
		try {
			if (Client.stub.depositCC(password, Client.myAcc.getAccNumber(), ammount)) {
				Client.myAcc.setBalanceCC(Client.myAcc.getBalanceCC() + ammount);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		return false;
	}
	public static boolean depositCP(String password, double ammount) {
		try {
			if (Client.stub.depositCC(password, Client.myAcc.getAccNumber(), ammount)) {
				Client.myAcc.setBalanceCP(Client.myAcc.getBalanceCP() + ammount);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean transfer(String password, int accountDest, double ammount) {
		try {
			if (Client.stub.transfer(password, Client.myAcc.getAccNumber(), accountDest, ammount)) {
				Client.myAcc.setBalanceCC(Client.myAcc.getBalanceCC() - ammount);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		return false;
	}
	
	public static Double balanceCC(String password) {
		try {
			Double balCC = Client.stub.balanceCC(password, Client.myAcc.getAccNumber());
			if (balCC != null) {
				Client.myAcc.setBalanceCC(balCC);
				return balCC;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		return null;
	}
	public static Double balanceCP(String password) {
		try {
			Double balCP = Client.stub.balanceCP(password, Client.myAcc.getAccNumber());
			if (balCP != null) {
				Client.myAcc.setBalanceCP(balCP);
				return balCP;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		return null;
	}
	public static boolean invest(String password, double ammount) {
		try {
			if (Client.stub.invest(password, Client.myAcc.getAccNumber(), ammount)) {
				Client.myAcc.setBalanceCC(Client.myAcc.getBalanceCC() - ammount);
				Client.myAcc.setFixedIncome(ammount);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		return false;
	}
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

        try {
           
            if (Client.startStub()) {
            	System.out.println(Client.createAcc("09540704456", "Arthur", "Silva", "Rua dos bobos nº 0","25/05/1994", "96522210", "anarquia"));
//            	System.out.println(Client.login("16929553772", "9iWKgkJvRzZ"));
            	System.out.println(Client.depositCC("anarquia",500));
//            	System.out.println(Client.depositCP("anarquia.88",700));
            	System.out.println(Client.myAcc);            	
//            	System.out.println(Client.lootCP("anarquia",150));
            	System.out.println(Client.invest("anarquia", 250));
            	System.out.println(Client.myAcc);
//            	Client.dumpServer();
            }
            
           
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
	}*/

}

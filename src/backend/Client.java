package backend;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.Account;

public class Client {
	
	public static final int SUCCESS = 200; // SUCESSO
	public static final int FAIL = 500; // SERVER ERROR
	public static final int PERM_DEN = 403; // PERMISSION DENIED
	public static final int NOT_FOUND = 404; // NOT FOUND
	public static final int NOT_MOD = 304; // NOT MODIFIED
	
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
	
	public static int lootCC(String password, double ammount) {
		try {
			int cod = Client.stub.lootCC(password, Client.myAcc.getAccNumber(), ammount);
			if (cod == Client.SUCCESS) {
				Client.myAcc.setBalanceCC(Client.myAcc.getBalanceCC() - ammount);
			} 
			return cod;
		
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		
		return Client.FAIL;
	}
	public static int lootCP(String password, double ammount) {
		try {
			int cod = Client.stub.lootCC(password, Client.myAcc.getAccNumber(), ammount);
			if (cod == Client.SUCCESS){
				Client.myAcc.setBalanceCP(Client.myAcc.getBalanceCP() - ammount);
			} 
			return cod;

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		
		return Client.FAIL;
	}
	
	public static int depositCC(String password, double ammount) {
		try {
			int cod = Client.stub.depositCC(password, Client.myAcc.getAccNumber(), ammount); 
			if (cod == Client.SUCCESS) {
				Client.myAcc.setBalanceCC(Client.myAcc.getBalanceCC() + ammount);
			}
			return cod;
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		return Client.FAIL;
	}
	public static int depositCP(String password, double ammount) {
		try {
			int cod = Client.stub.depositCC(password, Client.myAcc.getAccNumber(), ammount);
			if (cod == Client.SUCCESS) {
				Client.myAcc.setBalanceCP(Client.myAcc.getBalanceCP() + ammount);
			}
			return cod;
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		return Client.FAIL;
	}
	
	public static int transfer(String password, int accountDest, double ammount) {
		try {
			int cod = Client.stub.transfer(password, Client.myAcc.getAccNumber(), accountDest, ammount);
			if (cod == Client.SUCCESS) {
				Client.myAcc.setBalanceCC(Client.myAcc.getBalanceCC() - ammount);
			}
			return cod;
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		return Client.FAIL;
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
	public static int invest(String password, double ammount) {
		try {
			int cod = Client.stub.invest(password, Client.myAcc.getAccNumber(), ammount);
			if (cod == Client.SUCCESS) {
				Client.myAcc.setBalanceCC(Client.myAcc.getBalanceCC() - ammount);
				Client.myAcc.setFixedIncome(Client.myAcc.getFixedIncome() + ammount);
			}
			return cod;
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		return Client.FAIL;
	}
	
	public static int lootInvest(String password, double ammount) {
		try {
			int cod = Client.stub.lootInvest(password, Client.myAcc.getAccNumber(), ammount);
			if (cod == Client.SUCCESS) {
				Client.myAcc.setFixedIncome(Client.myAcc.getFixedIncome() - ammount);
			}
			return cod;
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		return Client.FAIL;
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

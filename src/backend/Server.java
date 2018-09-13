package backend;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import common.*;

public class Server implements BankAcc{
	
	public static final int SUCCESS = 200; // SUCESSO
	public static final int FAIL = 500; // SERVER ERROR
	public static final int PERM_DEN = 403; // PERMISSION DENIED
	public static final int NOT_FOUND = 404; // NOT FOUND
	public static final int NOT_MOD = 304; // NOT MODIFIED
	
	private static List<Account> acList = new ArrayList<Account>();
	
	private void populateAcc() {
		acList.add(new Account("64373790354","Leon","Haverfield","Rua Maranhão, 38, Santa Maria","25/03/1998","59237980","f2XHN6A0xZHH"));
		acList.add(new Account("16929553772","Oliva","Freidel","Rua Dr João Marcelino","10/08/1970","33487888","9iWKgkJvRzZ"));
		acList.add(new Account("46338744355","Shayne","Arbaugh","Rua 6 de janeiro, 1988, Santo Antonio","08/02/1994","96652910","4Vp"));
//		acList.add(new Account("68397203131","Lindsey","Dibley","Av Rio Mossoró, 90, Alto de São Manoel","23/05/1992","24529837","LV7"));
//		acList.add(new Account("38109792126","Tisha","Roediger","Av Rio Branco, 1010, Bom Jardim","15/01/2000","84646324","XgRuqQE2smYF060mG0IU"););
//		acList.add(new Account("71985863642","Lisa","Petronzio","Rua 2 de abril, 15, Costa e Silva","13/01/1989","62309535","wTZNM1XFP"));
	}
	@Override
	public void dumpList() throws RemoteException
	{
		for (Iterator iterator = acList.iterator(); iterator.hasNext();) {
			Account account = (Account) iterator.next();
			System.out.print(account);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			System.setProperty("java.rmi.server.hostname", "192.168.43.233");
			//criar objeto servidor
			
			Server server = new Server();
			server.populateAcc();
			server.dumpList();
			
			BankAcc stub = (BankAcc) UnicastRemoteObject.exportObject(server, 0);
			
			LocateRegistry.createRegistry( 1099 );
			
			Registry registry = LocateRegistry.getRegistry("192.168.43.233", 1099);
			
			/* O método bind é então chamado no stub do registro para vincular 
			 * o stub do objeto remoto ao nome "Bank" no registro.*/
			
			
			registry.bind("Bank", stub);

			System.err.println("Servidor pronto:");
			
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public String login(String cpf, String password) throws RemoteException
	{
		for (Iterator<Account> iterator = acList.iterator(); iterator.hasNext();) {
			Account account = (Account) iterator.next();
			if (account.getCpf().equals(cpf) && account.getPassword().equals(password)) {
				return account.dumpStr();
			}
		
		}
		return null;
	}
	@Override
	public int createAcc(String cpf, String fName, String lName, String address, String birthDate, String phoneNumber,String password)// throws RemoteException
	{
		// TODO Auto-generated method stub
		
		Account newAc = new Account(cpf,fName,lName,address,birthDate,phoneNumber,password);
		
		/*for (Iterator iterator = acList.iterator(); iterator.hasNext();) {
			Account account = (Account) iterator.next();
			if (account.getCpf() == newAc.getCpf()) {
				return Server.PERM_DEN;
			}
			
		}*/
		
		acList.add(newAc);
		
		return newAc.getAccNumber();
	}
	@Override
	public int lootCC(String password, int account, double ammount) throws RemoteException
	{
		
		for (Iterator<Account> iterator = acList.iterator(); iterator.hasNext();) {
			Account ac = (Account) iterator.next();
			if (ac.getAccNumber() == account) {
				if (ac.getPassword().equals(password)) {
					if (ac.getBalanceCC() >= ammount) {					
						ac.setBalanceCC(ac.getBalanceCC() - ammount);
						
						return Server.SUCCESS;
					} else {
						System.out.println("Saldo insuficiente");
						return Server.NOT_MOD;
					}
				} else {
					System.out.println("Senha errada");
					return Server.PERM_DEN;
				}
				
			}
		}
		return Server.NOT_FOUND;
	}
	@Override
	public int lootCP(String password, int account, double ammount) throws RemoteException
	{
		for (Iterator<Account> iterator = acList.iterator(); iterator.hasNext();) {
			Account ac = (Account) iterator.next();
			if (ac.getAccNumber() == account) {
				if (ac.getPassword().equals(password)) {					
					if (ac.getBalanceCP() >= ammount) {					
						ac.setBalanceCP(ac.getBalanceCP() - ammount);
						
						return Server.SUCCESS;
					} else {
						System.out.println("Saldo insuficiente");
						return Server.NOT_MOD;
					}
				} else {
					System.out.println("Senha errada");
					return Server.PERM_DEN;
				}
			}
		}
		return Server.NOT_FOUND;
	}
	@Override
	public int depositCP(String password, int account, double ammount) throws RemoteException
	{
		for (Iterator<Account> iterator = acList.iterator(); iterator.hasNext();) {
			Account ac = (Account) iterator.next();
			if (ac.getAccNumber() == account) {
				if (ac.getAccNumber() == account) {
					if (ac.getPassword().equals(password)) {					
						ac.setBalanceCP(ac.getBalanceCP() + ammount);
						return Server.SUCCESS;
					} else {
						System.out.println("Senha errada");
						return Server.PERM_DEN;
					}
				}
			}
		}
		return Server.NOT_FOUND;
	}
	
	@Override
	public int depositCC(String password, int account, double ammount)throws RemoteException
	{
		for (Iterator<Account> iterator = acList.iterator(); iterator.hasNext();) {
			Account ac = (Account) iterator.next();
			if (ac.getAccNumber() == account) {
				if (ac.getPassword().equals(password)) {					
					ac.setBalanceCC(ac.getBalanceCC() + ammount);
					return Server.SUCCESS;
				} else {
					System.out.println("Senha errada");
					return Server.PERM_DEN;
				}
			}
		}
		
		return Server.NOT_FOUND;
	}
	
	@Override
    public int transfer(String password, int accountRem, int accountDest, double ammount) throws RemoteException 
    {
    	if (accountRem == accountDest) {
    		return Server.NOT_MOD;
    	}
    	
    	Account dest = null;
    	for (Iterator<Account> iterator = acList.iterator(); iterator.hasNext();) {
			Account ac = (Account) iterator.next();
			if (ac.getAccNumber() == accountRem) {
				if (ac.getPassword().equals(password)) {
					
					if (ac.getBalanceCC() >= ammount) {
						ac.setBalanceCC(ac.getBalanceCC() - ammount);
					} else {
						System.out.println("Saldo insuficiente");
						return Server.NOT_MOD;
					}
				} else {
					System.out.println("Senha errada");
					return Server.PERM_DEN;
				}
			}
			if (ac.getAccNumber() == accountDest) {
				dest = ac;
			}
		}
    	
    	if (dest != null) {
    		dest.setBalanceCC(dest.getBalanceCC() + ammount);
    	} else {
    		return Server.NOT_FOUND;
    	}
    	
		return Server.SUCCESS;
	}
	
	@Override
    public Double balanceCC(String password, int account) throws RemoteException
    {
    	for (Iterator<Account> iterator = acList.iterator(); iterator.hasNext();) {
			Account ac = (Account) iterator.next();
			if (ac.getPassword().equals(password) && (ac.getAccNumber() == account)) {
				return ac.getBalanceCC();
			}
		}
    	return null;
    }
	@Override
    public Double balanceCP(String password, int account) throws RemoteException
    {
    	for (Iterator<Account> iterator = acList.iterator(); iterator.hasNext();) {
			Account ac = (Account) iterator.next();
			if (ac.getPassword().equals(password) && (ac.getAccNumber() == account)) {
				return ac.getBalanceCP();
			}
		}
    	
    	return null;
    }
	
	@Override
    public int invest(String password, int account, double ammount) throws RemoteException
    {
    	for (Iterator<Account> iterator = acList.iterator(); iterator.hasNext();) {
			Account ac = (Account) iterator.next();
			if (ac.getAccNumber() == account) {
				if (ac.getPassword().equals(password)) {					
					if (ac.getBalanceCC() >= ammount) {
						ac.setBalanceCC(ac.getBalanceCC() - ammount);
						ac.setFixedIncome(ac.getFixedIncome() + ammount);
						return Server.SUCCESS;
					} else {
						System.out.println("Saldo insuficiente");
						return Server.NOT_MOD;
					}
				} else {
					System.out.println("Senha errada");
					return Server.PERM_DEN;
				}
			}
		}
    	return Server.NOT_FOUND;
    }
	
	@Override
    public int lootInvest(String password, int account, double ammount) throws RemoteException
    {
    	for (Iterator<Account> iterator = acList.iterator(); iterator.hasNext();) {
			Account ac = (Account) iterator.next();
			if (ac.getAccNumber() == account) {
				if (ac.getPassword().equals(password)) {					
					if (ac.getFixedIncome() >= ammount) {
						ac.setFixedIncome(ac.getFixedIncome() - ammount);
						return Server.SUCCESS;
					} else {
						System.out.println("Saldo insuficiente");
						return Server.NOT_MOD;
					}
				} else {
					System.out.println("Senha errada");
					return Server.PERM_DEN;
				}
			}
		}
    	return Server.NOT_FOUND;
    }
}

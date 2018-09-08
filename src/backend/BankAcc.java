package backend;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.Account;;
public interface BankAcc extends Remote {
	
	// método a ser implementado
	
	String login(String cpf, String password) throws RemoteException;
	
    int createAcc(String cpf, String fName, String lName, String address, String birthDate, String phoneNumber,String password) throws RemoteException;
    
    boolean lootCC(String password, int account, double ammount) throws RemoteException;
    boolean lootCP(String password, int account, double ammount) throws RemoteException;
    
    boolean depositCC(String password, int account, double ammount) throws RemoteException;
    boolean depositCP(String password, int account, double ammount) throws RemoteException;
    
    boolean transfer(String password, int accountRem, int accountDest, double ammount) throws RemoteException;
    
    Double balanceCC(String password, int account) throws RemoteException;
    Double balanceCP(String password, int account) throws RemoteException;
    
    boolean invest(String password, int account, double ammount) throws RemoteException;
    
    void dumpList() throws RemoteException;

}

package common;

import java.util.Random;

public class Account {
	
	private String cpf;
	private String fName;
	private String lName;
	private String address;
	private String birthDate;
	private String phoneNumber;
	
	private int accNumber;
	private String password;
	
	private double balanceCC = 0;
	private double balanceCP = 0;
	
	private double fixedIncome = 0;
	
	static final int MINC = 1000;
	static final int MAXC = 9999;
	
	private static Random generator = new Random();

	public double getFixedIncome() {
		return fixedIncome;
	}

	public void setFixedIncome(double fixedIncome) {
		this.fixedIncome = fixedIncome;
	}

	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getfName() {
		return fName;
	}


	public void setfName(String fName) {
		this.fName = fName;
	}


	public String getlName() {
		return lName;
	}


	public void setlName(String lName) {
		this.lName = lName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public int getAccNumber() {
		return accNumber;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public double getBalanceCC() {
		return balanceCC;
	}


	public void setBalanceCC(double balanceCC) {
		this.balanceCC = balanceCC;
	}


	public double getBalanceCP() {
		return balanceCP;
	}


	public void setBalanceCP(double balanceCP) {
		this.balanceCP = balanceCP;
	}

	public String dumpStr() {
		return this.cpf + "#" + this.fName + "#"+ this.lName + "#" + this.birthDate + "#" + this.phoneNumber + "#" + this.address + "#" + this.accNumber +  "#" + this.balanceCC + "#" + this.balanceCP + "#" + this.fixedIncome;
	}
	
	public Account(String[] info) {
		this.cpf = info[0];
		this.fName = info[1];
		this.lName = info[2];
		this.birthDate = info[3];
		this.phoneNumber = info[4];
		this.address = info[5];
		this.accNumber = Integer.parseInt(info[6]);
		
		this.balanceCC = Double.parseDouble(info[7]);
		this.balanceCP = Double.parseDouble(info[8]);
		this.fixedIncome = Double.parseDouble(info[9]);
	}
	
	public Account(String cpf, String fName, String lName, String address, String birthDate, String phoneNumber,
			String password) {
		
		this.accNumber = Account.MINC + (int)(this.generator.nextDouble() * ((Account.MAXC - Account.MINC) + 1));
		this.cpf = cpf;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	public Account(int accNumber, String cpf, String fName, String lName, String address, String birthDate, String phoneNumber) {
		
		this.accNumber = accNumber;
		this.cpf = cpf;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
	}
	
	public Account() {
		
	}
	public Account(String cpf,int accNumber) {
		this.cpf = cpf;
		this.accNumber = accNumber;
	}
	
	public Account(Account ac) {
		this.accNumber = ac.getAccNumber();
		this.cpf = ac.getCpf();
		this.fName = ac.getfName();
		this.lName = ac.getlName();
		this.address = ac.getAddress();
		this.birthDate = ac.getBirthDate();
		this.phoneNumber = ac.getPhoneNumber();
		this.password = ac.getPassword();
		
		this.balanceCC = ac.getBalanceCC();
		this.balanceCP = ac.getBalanceCP();
		this.fixedIncome = ac.getFixedIncome();
	}

	@Override
	public String toString() {
		return "\nCPF:\t\t" +  this.cpf + "\nNome:\t\t" + this.fName + " "+ this.lName + "\nData de Nasc.:\t" + this.birthDate + "\nTelefone:\t" + this.phoneNumber + "\nEndereço:\t" + this.address + "\nConta:\t\t" + this.accNumber +  "\nBalanço (CC):\t" + this.balanceCC + "\nBalanço (CP):\t" + this.balanceCP + "\nRenda Fixa:\t" + this.fixedIncome;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Account ac = new Account("651651617","Arthur","Silva","Rua dos bobos, nº0","25/05/1994","999768606","anarquia.88");
		
		System.out.println(ac);
	}

}

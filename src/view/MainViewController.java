package view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import backend.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainViewController implements Initializable{
	@FXML public Label ccNumber;
	@FXML public Label cpNumber;
	@FXML public Label ccValue;
	@FXML public Label cpValue;
	
	@FXML public Button investButton;
	@FXML public Button rendiButton;
	@FXML public Button depositButton;
	@FXML public Button lootButton;
	@FXML public Button transferButton;
	
	@FXML public ComboBox<String> depositAccount;
	@FXML public ComboBox<String> lootAccount;
	@FXML public ComboBox<String> transfertFromAccount;
	
	@FXML public TextField depositValue;
	@FXML public TextField lootValue;
	@FXML public TextField transferValue;
	@FXML public TextField transferToAccount;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> contas = FXCollections.observableArrayList();;
		contas.add("Conta Corrente");
		contas.add("Conta Poupança");
		depositAccount.setItems(contas);
		lootAccount.setItems(contas);
		transfertFromAccount.setItems(contas);
		
		ccNumber.setText(String.valueOf(Client.myAcc.getAccNumber()));
		cpNumber.setText(String.valueOf(Client.myAcc.getAccNumber()));
//		ccValue.textProperty().bind(new SimpleDoubleProperty(Client.myAcc.getBalanceCC()).asString());
//		cpValue.textProperty().bind(new SimpleDoubleProperty(Client.myAcc.getBalanceCP()).asString());
		ccValue.setText(String.valueOf(Client.myAcc.getBalanceCC()));
		cpValue.setText(String.valueOf(Client.myAcc.getBalanceCP()));
		
		
		depositValue.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	depositValue.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		        if (!depositValue.getText().isEmpty()) 
		        	depositButton.setDisable(false);
		        else
		        	depositButton.setDisable(true);
		    }
		});
		lootValue.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	lootValue.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		        if (!lootValue.getText().isEmpty()) 
		        	lootButton.setDisable(false);
		        else
		        	lootButton.setDisable(true);
		    }
		});
		transferValue.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	transferValue.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		        if (!transferValue.getText().isEmpty() && !transferToAccount.getText().isEmpty()) 
		        	transferButton.setDisable(false);
		        else
		        	transferButton.setDisable(true);
		    }
		});
		transferToAccount.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	transferToAccount.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		        if (!transferValue.getText().isEmpty() && !transferToAccount.getText().isEmpty()) 
		        	transferButton.setDisable(false);
		        else
		        	transferButton.setDisable(true);
		    }
		});
		
	}

	public static void updateCCBalance() {
//		ccValue.setText(String.valueOf(Client.myAcc.getBalanceCC()));
		
	}	
	
	public String confirmPass() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Confirme sua senha");
		dialog.setHeaderText(null);
		dialog.setContentText("Confirme sua senha:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    return result.get();
		}
		return null;
	}
	
	public void showError(String err) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText("Error: "+ err);

		alert.showAndWait();
	}
	
	public void showInfor(String info) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Informação");
		alert.setHeaderText(null);
		alert.setContentText(info);

		alert.showAndWait();
	}
	
	@FXML
	public void toInvest() {
		if (Client.login(Client.myAcc.getCpf(), confirmPass())){
			
			try {
				AnchorPane pane = FXMLLoader.load(getClass().getResource("Investments.fxml"));
				Scene cadastro = new Scene(pane);
				Stage stage = new Stage();
				stage.setScene(cadastro);
				stage.initStyle(StageStyle.UTILITY);
				stage.setTitle("Investimentos da CC: "+Client.myAcc.getAccNumber());		
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initOwner((Stage) lootButton.getScene().getWindow());
				stage.showAndWait();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	@FXML
	public void toRendi() {
		if (Client.login(Client.myAcc.getCpf(), confirmPass())){
			
			try {
				AnchorPane pane = FXMLLoader.load(getClass().getResource("Yield.fxml"));
				Scene cadastro = new Scene(pane);
				Stage stage = new Stage();
				stage.setScene(cadastro);
				stage.initStyle(StageStyle.UTILITY);
				stage.setTitle("Rendimento da CP: "+Client.myAcc.getAccNumber());		
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initOwner((Stage) lootButton.getScene().getWindow());
				stage.showAndWait();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	@FXML
	public void deposit() {
		String pass = confirmPass();
		if (pass != null) {
			if (Double.parseDouble(depositValue.getText()) > 0) {
				if (depositAccount.getSelectionModel().getSelectedIndex() == 0) {
					if (Client.depositCC(pass, Double.parseDouble(depositValue.getText()))) {
						ccValue.setText(String.valueOf(Client.balanceCC(pass)));
						showInfor("Deposito Efetuado com sucesso");
					}						
					else 
						showError("Senha incorreta");
					
				}
				if (depositAccount.getSelectionModel().getSelectedIndex() == 1) {
					if (Client.depositCP(pass, Double.parseDouble(depositValue.getText()))) {
						cpValue.setText(String.valueOf(Client.balanceCP(pass)));
						showInfor("Deposito Efetuado com sucesso");
					}						
					else 
						showError("Senha incorreta");
				}
			}
			else 
				showError("Valor do deposito deve ser positivo");			
			
		}
		else {
			showError("Senha em branco");
		}
	}
	
	@FXML
	public void loot() {
		String pass = confirmPass();
		if (pass != null) {
			if (Double.parseDouble(lootValue.getText()) > 0) {
				if (lootAccount.getSelectionModel().getSelectedIndex() == 0) {
					if (Client.lootCC(pass, Double.parseDouble(lootValue.getText()))) {
						ccValue.setText(String.valueOf(Client.myAcc.getBalanceCC()));
						showInfor("Saque Efetuado com sucesso");
					}
					else 
						showError("Senha incorreta e/ou saldo insuficiente");
					
				}
				if (lootAccount.getSelectionModel().getSelectedIndex() == 1) {
					if (Client.lootCP(pass, Double.parseDouble(lootValue.getText()))){
						cpValue.setText(String.valueOf(Client.myAcc.getBalanceCP()));
						showInfor("Saque Efetuado com sucesso");
					}
					else 
						showError("Senha incorreta e/ou saldo insuficiente");
				}
			}
			else 
				showError("Valor do saque deve ser positivo");			
			
		}
		else {
			showError("Senha em branco");
		}
	}
	
	@FXML
	public void transfer() {
		String pass = confirmPass();
		if (pass != null) {
			if (Double.parseDouble(transferValue.getText()) > 0) {
				if (lootAccount.getSelectionModel().getSelectedIndex() == 0) {
					if (Client.transfer(pass, Client.myAcc.getAccNumber(), Integer.parseInt(transferToAccount.getText()),  Double.parseDouble(transferValue.getText()))) {
						ccValue.setText(String.valueOf(Client.myAcc.getBalanceCC()));
						showInfor("Transferencia Efetuado com sucesso");
					}
					else 
						showError("Senha incorreta, saldo insuficiente ou conta destino inexistente");
					
				}
				if (lootAccount.getSelectionModel().getSelectedIndex() == 1) {
					if (Client.transfer(pass, Client.myAcc.getAccNumber(), Integer.parseInt(transferToAccount.getText()),  Double.parseDouble(transferValue.getText()))){
						ccValue.setText(String.valueOf(Client.myAcc.getBalanceCC()));
						showInfor("Transferencia Efetuado com sucesso");
					}
					else 
						showError("Senha incorreta, saldo insuficiente ou conta destino inexistente");
					
				}
			}
			else 
				showError("Valor do saque deve ser positivo");			
			
		}
		else {
			showError("Senha em branco");
		}
	}
	
}

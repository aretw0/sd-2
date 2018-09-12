package view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import backend.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class InvestmentsController implements Initializable{
	@FXML public Button investButton;
	@FXML public TextField investValueField;
	@FXML public Label investValueLabel;
	@FXML public Label month3;
	@FXML public Label month6;
	@FXML public Label month12;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (Client.myAcc.getFixedIncome() > 0) {
			investValueLabel.setText(String.valueOf(Client.myAcc.getFixedIncome()));
			month3.setText(String.valueOf(Client.myAcc.getFixedIncome()*(Math.pow(1.015, 3))));
			month6.setText(String.valueOf(Client.myAcc.getFixedIncome()*(Math.pow(1.015, 6))));
			month12.setText(String.valueOf(Client.myAcc.getFixedIncome()*(Math.pow(1.015, 12))));
		}		
		
		investValueField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	investValueField.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		        if (!investValueField.getText().isEmpty()) 
		        	investButton.setDisable(false);
		        else
		        	investButton.setDisable(true);
		    }
		});
		
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
	public void invest() {
		if (Double.parseDouble(investValueField.getText()) <= 0) {
			showError("Valor do investimento deve ser positivo");
			return;
		}
		String pass = confirmPass();
		if (Client.invest(pass, Double.parseDouble(investValueField.getText()))) {
			showInfor("Investimento realizado com sucesso");
			investValueLabel.setText(String.valueOf(Client.myAcc.getFixedIncome()));
			month3.setText(String.valueOf(Client.myAcc.getFixedIncome()*(Math.pow(1.015, 3))));
			month6.setText(String.valueOf(Client.myAcc.getFixedIncome()*(Math.pow(1.015, 6))));
			month12.setText(String.valueOf(Client.myAcc.getFixedIncome()*(Math.pow(1.015, 12))));
			MainViewController.updateCCBalance();
		}
		else {
			showError("Saldo insuficiente e/ou senha incorreta");
		}
		
	}

}

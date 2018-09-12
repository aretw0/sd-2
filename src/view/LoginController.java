package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import backend.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable{
	
	@FXML public Button loginButton;
	@FXML public Button createButton;
	@FXML public TextField cpfInput;
	@FXML public PasswordField passInput;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cpfInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {		        
		    	if (!newValue.matches("\\d*")) {
		    		cpfInput.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		        if (!cpfInput.getText().isEmpty() && !passInput.getText().isEmpty()) 
		        	loginButton.setDisable(false);
		        else
		        	loginButton.setDisable(true);
		    }
		});
		passInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!cpfInput.getText().isEmpty() && !passInput.getText().isEmpty()) 
		        	loginButton.setDisable(false);
		        else
		        	loginButton.setDisable(true);
		    }
		});
		
	}	
	
	public void showError(String err) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText("Error: "+ err);

		alert.showAndWait();
	}
	
	@FXML
	public void login() {
		if (Client.login(cpfInput.getText(), passInput.getText())) {
			
			try {
				AnchorPane pane = FXMLLoader.load(getClass().getResource("MainView.fxml"));
				Scene cadastro = new Scene(pane);
				Stage stage = new Stage();
				stage.setScene(cadastro);
				stage.initStyle(StageStyle.UTILITY);
				stage.setTitle("Conta Bancária");		
				stage.initModality(Modality.NONE);
//					stage.initOwner((Stage) loginButton.getScene().getWindow());
				((Stage)loginButton.getScene().getWindow()).close();
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}			
		else {
			showError("CPF e/ou senha incorretos");
		}
	}
	
	@FXML 
	public void toCreate() {		
		try {
			AnchorPane pane;
			pane = FXMLLoader.load(getClass().getResource("Create.fxml"));
			Scene cadastro = new Scene(pane);
			Stage stage = new Stage();
			stage.setScene(cadastro);
			stage.initStyle(StageStyle.UTILITY);
			stage.setTitle("Criar Conta");		
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner((Stage) loginButton.getScene().getWindow());
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}


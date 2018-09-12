package view;

import java.net.URL;
import java.util.ResourceBundle;

import backend.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CreateController implements Initializable{
	
	@FXML public TextField firstNameField;
	@FXML public TextField lastNameField;
	@FXML public PasswordField passField;
	@FXML public TextField cpfField;
	@FXML public TextField adressField;
	@FXML public TextField birthField;
	@FXML public TextField phoneField;
	
	@FXML public Button create;
 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		firstNameField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && !passField.getText().isEmpty() && !cpfField.getText().isEmpty() && !adressField.getText().isEmpty() && !birthField.getText().isEmpty() && !phoneField.getPromptText().isEmpty()) 
		        	create.setDisable(false);
		        else
		        	create.setDisable(true);
		    }
		});
		lastNameField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		    	if (!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && !passField.getText().isEmpty() && !cpfField.getText().isEmpty() && !adressField.getText().isEmpty() && !birthField.getText().isEmpty() && !phoneField.getPromptText().isEmpty()) 
		        	create.setDisable(false);
		        else
		        	create.setDisable(true);
		    }
		});
		passField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		    	if (!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && !passField.getText().isEmpty() && !cpfField.getText().isEmpty() && !adressField.getText().isEmpty() && !birthField.getText().isEmpty() && !phoneField.getPromptText().isEmpty()) 
		        	create.setDisable(false);
		        else
		        	create.setDisable(true);
		    }
		});
		cpfField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	cpfField.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		        if (!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && !passField.getText().isEmpty() && !cpfField.getText().isEmpty() && !adressField.getText().isEmpty() && !birthField.getText().isEmpty() && !phoneField.getPromptText().isEmpty()) 
		        	create.setDisable(false);
		        else
		        	create.setDisable(true);
		    }
		});
		adressField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		    	if (!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && !passField.getText().isEmpty() && !cpfField.getText().isEmpty() && !adressField.getText().isEmpty() && !birthField.getText().isEmpty() && !phoneField.getPromptText().isEmpty()) 
		        	create.setDisable(false);
		        else
		        	create.setDisable(true);
		    }
		});
		birthField.accessibleTextProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		    	if (!newValue.matches("\\d*")) {
		        	cpfField.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    	if (!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && !passField.getText().isEmpty() && !cpfField.getText().isEmpty() && !adressField.getText().isEmpty() && !birthField.getText().isEmpty() && !phoneField.getPromptText().isEmpty()) 
		        	create.setDisable(false);
		        else
		        	create.setDisable(true);
		    }
		});
		phoneField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		    	if (!newValue.matches("\\d*")) {
		        	cpfField.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    	if (!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && !passField.getText().isEmpty() && !cpfField.getText().isEmpty() && !adressField.getText().isEmpty() && !birthField.getText().isEmpty() && !phoneField.getPromptText().isEmpty()) 
		        	create.setDisable(false);
		        else
		        	create.setDisable(true);
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
	
	public void showInfor(String info) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Informação");
		alert.setHeaderText(null);
		alert.setContentText(info);

		alert.showAndWait();
	}
	
	@FXML
	public void create() {
		if (Client.createAcc(cpfField.getText(), firstNameField.getText(), lastNameField.getText(), adressField.getText(), birthField.getText(), phoneField.getText(), passField.getText())){
			showInfor("Cadastro realizado com sucesso");
			cpfField.clear();
			firstNameField.clear();
			lastNameField.clear();
			adressField.clear();
			birthField.clear();
			phoneField.clear();
			passField.clear();
		}else {
			showError("Error no cadastro, tente novamente");
		}
	}

}

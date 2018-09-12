package view;

import java.net.URL;
import java.util.ResourceBundle;

import backend.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class YieldController implements Initializable{

	@FXML public Label yieldValueLabel;
	@FXML public Label month3;
	@FXML public Label month6;
	@FXML public Label month12;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (Client.myAcc.getBalanceCP() > 0) {
			yieldValueLabel.setText(String.valueOf(Client.myAcc.getBalanceCP()));
			month3.setText(String.valueOf(Client.myAcc.getBalanceCP()*(Math.pow(1.005, 3))));
			month6.setText(String.valueOf(Client.myAcc.getBalanceCP()*(Math.pow(1.005, 6))));
			month12.setText(String.valueOf(Client.myAcc.getBalanceCP()*(Math.pow(1.005, 12))));
		}	
		
	}

}

package rocket.app.view;

import eNums.eAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;

	// Create private instance variables for:
	// TextBox - txtIncome
	// TextBox - txtExpenses
	// TextBox - txtCreditScore
	// TextBox - txtHouseCost
	// ComboBox - loan term... 15 year or 30 year
	// Labels - various labels for the controls
	// Button - button to calculate the loan payment
	// Label - to show error messages (exception throw, payment exception)

	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private ComboBox cmbTerm;
	@FXML
	private Button CalculateLoanPayment;

	@FXML
	private Label creditScore;
	@FXML
	private Label Term;
	@FXML
	private Label Income;
	@FXML
	private Label Error;
	@FXML
	private Label Expenses;
	@FXML
	private Label houseExpenses;
	@FXML
	private Label downPayment;
	@FXML
	private Label lblMortgagePayment;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	public void btnCalculatePayment(ActionEvent event) {
		Object message = null;

		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();

		lq.setiTerm(Integer.parseInt(cmbTerm.getPromptText()));
		lq.setExpenses(Double.parseDouble((txtIncome.getText())));
		lq.setdAmount(Integer.parseInt(txtHouseCost.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setiDownPayment(Integer.parseInt(downPayment.getText()));

		if (cmbTerm.getValue() == "15 Years") {
			lq.setiTerm(15 * 12);
		} else if (cmbTerm.getValue() == "30 Years") {
			lq.setiTerm(30 * 12);
		}

		a.setLoanRequest(lq);
		// send lq as a message to RocketHub
		mainApp.messageSend(lq);
	}

	public void HandleLoanRequestDetails(LoanRequest lRequest) {

		double payment = lRequest.getdPayment();
		if (payment <= 0.36 * (lRequest.getIncome() / 12)
				&& payment <= 0.28 * (lRequest.getIncome() / 12 - lRequest.getExpenses())) {
			System.out.format("%.2f", payment);
		}

	}

}

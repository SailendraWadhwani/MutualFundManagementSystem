package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyFundForm extends FormBean {
	private String fundName;
	private String amount;
	
	public String getFundName() {
		return fundName;
	}
	
	public double getAmountAsDouble() {
		try {
			return Double.parseDouble(amount);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (fundName == null || fundName.trim().length() == 0) {
			errors.add("Fund Name is required!");
		}
		
		if (amount == null || amount.trim().length() == 0) {
			errors.add("Amount is required!");
		}
		
		if (errors.size() != 0) {
			return errors;
		}
		
		double amountD;
		try {
			amountD = Double.parseDouble(amount);
		} catch (NumberFormatException e) {
			errors.add("Amount should be a number");
			return errors;
		}
		
		if (amountD <= 0) {
			errors.add("Invalid amount! Please enter a positive number!");
		}
		
		return errors;
	}
}
package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestCheckFormBean extends FormBean{
	private String withdrawamount;
	
	public String getwithdrawamount() {
		return withdrawamount;
	}
	
	public void setwithdrawamount(String withdrawamount) {
		this.withdrawamount = withdrawamount;
	}
	
	public double getAmountAsDouble() {
		double buywithdrawamount;
		
		try {
			buywithdrawamount = Double.parseDouble(withdrawamount);
			return buywithdrawamount;
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (withdrawamount == null || withdrawamount.length() == 0) {
			errors.add("Please enter the  withdrawamount");
		}
		
		if (withdrawamount.matches(".*[<>\"].*")) {
	            errors.add("Input should contain only valid numerical value");
	     }

		try {
			double buywithdrawamount = Double.parseDouble(withdrawamount);
			if (buywithdrawamount <= 0) {
                errors.add("Withdraw amount value should be a positive value");
			}
		} catch (NumberFormatException e) {
			errors.add("Withdraw amount is not an integer");
		}

		return errors;
	}
}
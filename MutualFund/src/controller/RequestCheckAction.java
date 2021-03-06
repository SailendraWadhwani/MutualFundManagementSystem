package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.Model;
import model.MyDAOException;
import model.TransactionDAO;

import org.genericdao.DAOException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;



import databean.CustomerBean;
import databean.EmployeeBean;
import databean.TransactionBean;

import formbean.DepositCheckFormBean;
import formbean.RequestCheckFormBean;


public class RequestCheckAction extends Action{
	private FormBeanFactory<RequestCheckFormBean> formBeanFactory = FormBeanFactory.getInstance(RequestCheckFormBean.class);
	private final String actionPage = "customer-requestcheck.jsp";
	private final String successPage = "customer-confirmation.jsp";

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	public RequestCheckAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "customer-requestcheck.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			int customerId = (Integer) request.getSession(false).getAttribute("customerId");
			  
			RequestCheckFormBean form = formBeanFactory.create(request);
            request.setAttribute("form", form);
            
            CustomerBean customer = customerDAO.read(customerId);
            request.setAttribute("customer", customer);
            
            double availableBalance = customer.getCash();
            DecimalFormat formatter = new DecimalFormat("#,##0.00");
			request.setAttribute("availableBalance", formatter.format(availableBalance));

            if (!form.isPresent()) {
                return actionPage;
            }

            errors.addAll(form.getValidationErrors());

            if (errors.size() != 0) {
                return actionPage;
            }
            
            double amount = form.getAmountAsDouble();
			transactionDAO.requestCheck(customerId, amount);
			formatter = new DecimalFormat("#,##0.00");
			
			request.setAttribute("message","Your request for check for $ " 
					+ "<b>" + formatter.format(form.getAmountAsDouble()) 
					+ "</b>"+ " has been queued as a pending transaction");
			
            return successPage;
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
			return "error.jsp";
        } catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
		
	}
}

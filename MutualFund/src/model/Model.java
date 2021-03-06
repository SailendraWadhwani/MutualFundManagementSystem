/**
 * @author Team Snipers (Team 1)
 * Jan 17, 2013
 */

package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import model.MyDAOException;

public class Model {
	
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;
	private TransactionHistoryDAO transactionHistoryDAO; 
	private PositionDAO positionDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			customerDAO = new CustomerDAO(jdbcDriver, jdbcURL, "customer");
			employeeDAO = new EmployeeDAO(jdbcDriver, jdbcURL, "employee");
			fundDAO = new FundDAO(jdbcDriver, jdbcURL, "fund");
			fundPriceHistoryDAO = new FundPriceHistoryDAO(jdbcDriver, jdbcURL, "pricehistory");
			positionDAO = new PositionDAO(jdbcDriver, jdbcURL, "position");
			transactionDAO = new TransactionDAO(jdbcDriver, jdbcURL, "transaction");
			
			transactionHistoryDAO = new TransactionHistoryDAO(jdbcDriver, jdbcURL, ""); //don't need to create any table. 
			
			
		} catch (MyDAOException e) {
			throw new ServletException(e);
		}
	}
	
	public CustomerDAO getCustomerDAO() { return customerDAO;}
	public EmployeeDAO getEmployeeDAO() { return employeeDAO;}
	public FundDAO getFundDAO() { return fundDAO;}
	public FundPriceHistoryDAO getFundPriceHistoryDAO() { return fundPriceHistoryDAO; }
	public PositionDAO getPositionDAO() { return positionDAO; }
	public TransactionDAO getTransactionDAO() { return transactionDAO; }
	public TransactionHistoryDAO getTransactionHistoryDAO() { return transactionHistoryDAO; }
}

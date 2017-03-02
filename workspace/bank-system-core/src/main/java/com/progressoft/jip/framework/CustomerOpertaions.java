/**
 * 
 */
package com.progressoft.jip.framework;

import java.util.Iterator;

import com.progressoft.jip.entity.Account;
import com.progressoft.jip.entity.Customer;

/**
 * @author khaled
 *
 */
public interface CustomerOpertaions {

	public void createAccount(Account account);

	public void closeAccount(Account account);

	public void update(Customer customer);

	public void deposit(double balance,Account account);

	public void withdrawMoney(double balance,Account account);

	public Iterator<Customer> query();
}

package com.account.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.account.model.Account;

public class AccountStub {
	private static Map<Long, Account> accounts = new HashMap<Long, Account>();

	private static Long idIndex = 3L;

	static {
		Account a = new Account(1L, "Rajat", "Kumar", "1994789");
		accounts.put(1L, a);
		Account b = new Account(2L, "R", "C", "1994");
		accounts.put(2L, b);
	}

	public List<Account> findAll() {
		return new ArrayList<Account>(accounts.values());
	}

	public Account findById(Long id) {
		return accounts.get(id) != null ? accounts.get(id) : null;
	}

	public Account deleteById(Long id) {
		return accounts.get(id) != null ? accounts.remove(id) : null;
	}

	public Account save(Account account) {
		idIndex += 1;
		account.setId(idIndex);
		accounts.put(idIndex, account);
		return account;
	}
}
package com.account.web;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.account.common.exceptions.AccountNotFoundException;
import com.account.dao.AccountStub;
import com.account.model.Account;

@RestController
@RequestMapping("api/")
public class AccountController {
	
	private AccountStub entity = new AccountStub();

	@GetMapping("/accounts")
	public List<Account> retrieveAllAccounts() {
		return entity.findAll();
	}
	
	@GetMapping("/accounts/{id}")
	public Account retrieveAccount(@PathVariable long id) throws AccountNotFoundException {
		Account account = entity.findById(id);

		if (account == null) {
			throw new AccountNotFoundException("id-" + id);
		}
		
		return account;
	}
	
//	@DeleteMapping("/accounts/{id}")
	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public Account deleteAccount(@PathVariable long id) {
		Account account = entity.deleteById(id);

		if (account == null) {
			throw new AccountNotFoundException("id-" + id);
		}
		
		return account;
	}
	
	@PostMapping("/accounts")
	public ResponseEntity<Object> createAccount(@RequestBody Account account) {
		Account savedAccount = entity.save(account);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedAccount.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}

package com.backend.cryptography.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.cryptography.controllers.dto.CreateTransactionRequest;
import com.backend.cryptography.controllers.dto.TransactionResponse;
import com.backend.cryptography.controllers.dto.UpdateTransactionRequest;
import com.backend.cryptography.services.TransactionService;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

	private final TransactionService service;
	
	public TransactionController(TransactionService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody CreateTransactionRequest request) {
		service.create(request);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UpdateTransactionRequest request) {
		service.update(id, request);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<Page<TransactionResponse>> listAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
															 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		var body = service.listAll(page, pageSize);
		return ResponseEntity.ok(body);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TransactionResponse> findById(@PathVariable Long id) {
		var body = service.findById(id);
		return ResponseEntity.ok(body);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}

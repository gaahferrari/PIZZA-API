package com.store.Pizza.controller;

import com.store.Pizza.DTO.CustomerDTO;
import com.store.Pizza.DTO.CustomerOrdersDTO;
import com.store.Pizza.entity.Customer;
import com.store.Pizza.request.CustomerRequest;
import com.store.Pizza.responses.BaseBodyError;
import com.store.Pizza.responses.BaseBodyResponse;
import com.store.Pizza.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Get All Employees", description = "Get All Employees", tags = {"Employee"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
    @GetMapping
    public ResponseEntity<BaseBodyResponse<List<CustomerDTO>>> getAllCustomers() {
        return ResponseEntity.status(200).body(customerService.getAll()) ;
    }

    @Operation(summary = "Get Employee By ID", description = "Get Employee By ID", tags = {"Employee"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
    @GetMapping("/{id}")
    public ResponseEntity<BaseBodyResponse<CustomerOrdersDTO>> getOrdersById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(customerService.getByOrder(id));
    }
    @Operation(summary = "Delete Employee", description = "Delete Employee", tags = {"Employee"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        customerService.deleteCustomerAndWallet(id);
        return ResponseEntity.status(200).body("O cliente com o ID: " + id + " foi excluído com sucesso!");
    }

    @Operation(summary = "Create Employee", description = "Create Employee's information", tags = {"Employee"}, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = BaseBodyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BaseBodyError.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = BaseBodyError.class)))})
    @PostMapping
    public ResponseEntity<BaseBodyResponse<Customer>> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.status(201).body(customerService.create(request));
    }


}

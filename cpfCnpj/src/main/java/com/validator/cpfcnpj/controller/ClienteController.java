package com.validator.cpfcnpj.controller;

import com.validator.cpfcnpj.dto.ClienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {

    @PostMapping
    @Operation(summary = "Cria um novo cliente", description = "Cria um cliente validando CPF e CNPJ")
    public ResponseEntity<String> criar(@RequestBody @Valid ClienteDTO dto) {
        return ResponseEntity.ok("Cliente recebido com sucesso!");
    }

}

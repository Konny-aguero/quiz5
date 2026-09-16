package com.logiexpress.controller;

import com.logiexpress.business.PaqueteService;
import com.logiexpress.domain.Estado;
import com.logiexpress.domain.Paquete;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/paquetes")
public class PaqueteController {

    private final PaqueteService paqueteService;

    public PaqueteController(PaqueteService paqueteService) {
        this.paqueteService = paqueteService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Registrar un nuevo paquete")
    @ApiResponse(responseCode = "201", description = "Paquete creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Error de validación o peso excedido")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    public ResponseEntity<PaqueteResponseDto> registrarPaquete(@Valid @RequestBody PaqueteRequestDto requestDto) {
        
        Paquete paquete = new Paquete();
        paquete.setDescripcion(requestDto.getDescripcion());
        paquete.setPesoKg(requestDto.getPesoKg());
        paquete.setEstado(Estado.REGISTRADO);
        paquete.setCodigoRastreo(UUID.randomUUID().toString()); // Generación de código único

        Paquete paqueteGuardado = paqueteService.registrarPaquete(paquete, requestDto.getClienteId());

        PaqueteResponseDto responseDto = mapearAResponseDto(paqueteGuardado);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERADOR')")
    @Operation(summary = "Consultar paquetes por estado con paginación")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    public ResponseEntity<Page<PaqueteResponseDto>> obtenerPaquetesPorEstado(
            @RequestParam Estado estado, 
            Pageable pageable) {
        
        Page<Paquete> paquetes = paqueteService.obtenerPaquetesPorEstado(estado, pageable);
        
        Page<PaqueteResponseDto> responsePage = paquetes.map(this::mapearAResponseDto);
        
        return ResponseEntity.ok(responsePage);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar un paquete")
    @ApiResponse(responseCode = "204", description = "Paquete eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Paquete no encontrado")
    public ResponseEntity<Void> eliminarPaquete(@PathVariable Long id) {
        paqueteService.eliminarPaquete(id);
        return ResponseEntity.noContent().build();
    }


    private PaqueteResponseDto mapearAResponseDto(Paquete paquete) {
        PaqueteResponseDto dto = new PaqueteResponseDto();
        dto.setCodigoRastreo(paquete.getCodigoRastreo());
        dto.setDescripcion(paquete.getDescripcion());
        dto.setPesoKg(paquete.getPesoKg());
        if (paquete.getEstado() != null) {
            dto.setEstado(paquete.getEstado().name());
        }
        return dto;
    }
}
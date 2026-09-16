package com.logiexpress.business;

import com.logiexpress.data.ClienteRepository;
import com.logiexpress.data.PaqueteRepository;
import com.logiexpress.domain.Cliente;
import com.logiexpress.domain.Paquete;
import com.logiexpress.exception.PesoExcedidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaqueteServiceTest {

    @Mock
    private PaqueteRepository paqueteRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private PaqueteService paqueteService;

    @Test
    void debeRegistrarPaqueteExitosamente_CuandoPesoEsValido() {
        Paquete paquete = new Paquete();
        paquete.setPesoKg(15.0);
        Cliente cliente = new Cliente();
        
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(paqueteRepository.save(any(Paquete.class))).thenReturn(paquete);

        Paquete resultado = paqueteService.registrarPaquete(paquete, 1L);

        assertNotNull(resultado);
        verify(paqueteRepository, times(1)).save(paquete);
    }

    @Test
    void debeLanzarPesoExcedidoException_CuandoPesoSupera30Kg() {
        Paquete paquete = new Paquete();
        paquete.setPesoKg(35.0);

        assertThrows(PesoExcedidoException.class, () -> {
            paqueteService.registrarPaquete(paquete, 1L);
        });
        verify(paqueteRepository, never()).save(any());
    }
}
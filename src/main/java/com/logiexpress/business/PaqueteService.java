package com.logiexpress.business;

import com.logiexpress.data.ClienteRepository;
import com.logiexpress.data.PaqueteRepository;
import com.logiexpress.domain.Cliente;
import com.logiexpress.domain.Estado;
import com.logiexpress.domain.Paquete;
import com.logiexpress.exception.ClienteNoEncontradoException;
import com.logiexpress.exception.PesoExcedidoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaqueteService {

    private final PaqueteRepository paqueteRepository;
    private final ClienteRepository clienteRepository;

    public PaqueteService(PaqueteRepository paqueteRepository, ClienteRepository clienteRepository) {
        this.paqueteRepository = paqueteRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Paquete registrarPaquete(Paquete paquete, Long clienteId) {
        if (paquete.getPesoKg() > 30.0) {
            throw new PesoExcedidoException("El peso del paquete supera el límite de 30.0 kg");
        }

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNoEncontradoException("El cliente con ID " + clienteId + " no existe."));
        
        paquete.setCliente(cliente);
        return paqueteRepository.save(paquete);
    }


    @Transactional(readOnly = true)
    public Page<Paquete> obtenerPaquetesPorEstado(Estado estado, Pageable pageable) {
        return paqueteRepository.findByEstadoPaginado(estado, pageable);
    }

    @Transactional
    public void eliminarPaquete(Long id) {
        if (!paqueteRepository.existsById(id)) {
            throw new RuntimeException("El paquete con ID " + id + " no existe.");
        }
        paqueteRepository.deleteById(id);
    }
}
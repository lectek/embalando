package com.embalando.service;

import com.embalando.model.Compra;
import com.embalando.model.Produto;
import com.embalando.model.Cliente;
import com.embalando.repository.CompraRepository;
import com.embalando.repository.ProdutoRepository;
import com.embalando.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Registra uma nova compra com os produtos escolhidos e vincula ao cliente.
     */
    public Compra registrarCompra(Long clienteId, List<Long> produtoIds, String descricao) {
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new EntityNotFoundException("Cliente com ID " + clienteId + " não encontrado."));

        List<Produto> produtos = produtoRepository.findAllById(produtoIds);

        if (produtos.isEmpty()) {
            throw new IllegalArgumentException("Lista de produtos está vazia ou IDs inválidos.");
        }

        Compra compra = new Compra(descricao, LocalDate.now(), cliente);

        for (Produto produto : produtos) {
            compra.adicionarProduto(produto);
        }

        // Salvar a compra já calcula o valor total internamente
        return compraRepository.save(compra);
    }

    /**
     * Retorna todas as compras registradas no sistema.
     */
    public List<Compra> listarTodas() {
        return compraRepository.findAll();
    }

    /**
     * Busca uma compra específica pelo ID.
     */
    public Compra buscarPorId(Long id) {
        return compraRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Compra com ID " + id + " não encontrada."));
    }
}

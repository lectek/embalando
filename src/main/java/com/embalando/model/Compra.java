package com.embalando.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    private double valor;

    @Column(nullable = false)
    private LocalDate data;

    // 🔗 Relação com Cliente (muitas compras para um cliente)
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // 🔗 Relação com Produtos (uma compra tem vários produtos)
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos = new ArrayList<>();

    // ✅ Construtores
    public Compra() {}

    public Compra(String descricao, LocalDate data, Cliente cliente) {
        this.descricao = descricao;
        this.data = data;
        this.cliente = cliente;
    }

    // ✅ Getters e Setters
    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
        calcularValorTotal();
    }

    // ✅ Adicionar produto individualmente e atualizar valor
    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
        produto.setCompra(this); // mantém bidirecionalidade
        calcularValorTotal();
    }

    // ✅ Calcular valor total da compra
    public void calcularValorTotal() {
        this.valor = produtos.stream()
                .mapToDouble(p -> p.getQuantidade() * p.getPrecoUnitario())
                .sum();
    }
}

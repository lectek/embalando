package com.embalando.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private double valor;
    private LocalDate data;

    // 🔗 Relação de volta com o Cliente (muitos para um)
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // 🔗 Relação com Produtos (uma compra tem vários produtos)
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
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
        calcularValorTotal(); // atualiza o valor automaticamente
    }

    // ✅ Adicionar produto individualmente e atualizar valor
    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
        calcularValorTotal(); // atualiza o valor automaticamente
    }

    // ✅ Calcular valor total com base nos produtos
    public double calcularValorTotal(){
        double total = produtos.stream()
            .mapToDouble(p -> p.getQuantidade() * p.getPrecoUnitario())
            .sum();
        this.valor = total; // sincroniza com o campo
        return total;
    }
}


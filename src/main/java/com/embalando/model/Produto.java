package com.embalando.model;

import jakarta.persistence.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int quantidade;
    private double precoUnitario;

    // 🔗 Relacionamento com Compra (muitos produtos para uma compra)
    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    // ✅ Construtores
    public Produto() {}

    public Produto(String nome, int quantidade, double precoUnitario, Compra compra) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.compra = compra;
    }

    // ✅ Método para calcular o valor total do produto (qtd * preço)
    public double calcularTotal() {
        return quantidade * precoUnitario;
    }

    // ✅ Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
}

package com.embalando;

import com.embalando.model.Cliente;
import com.embalando.model.Compra;
import com.embalando.model.Produto;

import java.time.LocalDate;

public class TesteLoja {

    public static void main(String[] args) {

        // Criar cliente
        Cliente cliente = new Cliente("Alex Morais", "(83) 99999-9999", "Rua das Laranjeiras");

        // Criar compra
        Compra compra = new Compra("Compra de teste", LocalDate.now(), cliente);

        // Criar produtos
        Produto p1 = new Produto("Caixa de papelão", 5, 2.5, compra);
        Produto p2 = new Produto("Fita adesiva", 3, 4.0, compra);

        // Adicionar produtos à compra
        compra.adicionarProduto(p1);
        compra.adicionarProduto(p2);

        // Resultado no console
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Descrição da compra: " + compra.getDescricao());
        System.out.println("Produtos:");

        for (Produto p : compra.getProdutos()) {
            System.out.println("- " + p.getNome() + " x" + p.getQuantidade() + " = R$ " + p.calcularTotal());
        }

        System.out.println("Valor total calculado: R$ " + compra.getValor());
    }
}

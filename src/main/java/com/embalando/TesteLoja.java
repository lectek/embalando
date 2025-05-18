package com.embalando;

import com.embalando.model.Cliente;
import com.embalando.model.Compra;
import com.embalando.model.Produto;

import java.time.LocalDate;

public class TesteLoja {

    public static void main(String[] args) {

        // Criando um cliente usando builder (se sua classe tiver @Builder)
        Cliente cliente = Cliente.builder()
                .nome("Alex Morais")
                .telefone("(83) 99999-9999")
                .email("alex@email.com") // necessário se houver @Email e @NotBlank
                .cpf("12345678901")      // necessário se houver validação
                .build();

        // Criar uma compra associada ao cliente
        Compra compra = new Compra("Compra de teste", LocalDate.now(), cliente);

        // Criar produtos associados à compra
        Produto p1 = new Produto("Caixa de papelão", 5, 2.5, compra);
        Produto p2 = new Produto("Fita adesiva", 3, 4.0, compra);

        // Adicionar produtos à compra
        compra.adicionarProduto(p1);
        compra.adicionarProduto(p2);

        // Imprimir resultados
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Descrição da compra: " + compra.getDescricao());
        System.out.println("Produtos:");

        for (Produto p : compra.getProdutos()) {
            System.out.printf("- %s x%d = R$ %.2f%n", p.getNome(), p.getQuantidade(), p.calcularTotal());
        }

        System.out.printf("Valor total calculado: R$ %.2f%n", compra.getValor());
    }
}

package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Automato automato = new Automato();

        System.out.println("=== SIMULADOR DE AFD ===");

        System.out.print("Quantidade de estados: ");
        int quantidadeEstados = entrada.nextInt();
        entrada.nextLine();

        if (quantidadeEstados <= 0) {
            System.out.println("Erro: informe pelo menos um estado.");
            entrada.close();
            return;
        }

        for (int i = 0; i < quantidadeEstados; i++) {
            System.out.print("Nome do estado " + (i + 1) + ": ");
            String estado = entrada.nextLine().trim();

            if (estado.isEmpty() || automato.getEstados().contains(estado)) {
                System.out.println("Erro: estado vazio ou já cadastrado.");
                entrada.close();
                return;
            }

            automato.adicionarEstado(estado);
        }

        System.out.print("Quantidade de símbolos do alfabeto (1 ou 2): ");
        int quantidadeSimbolos = entrada.nextInt();
        entrada.nextLine();

        if (quantidadeSimbolos <= 0 || quantidadeSimbolos > 2) {
            System.out.println("Erro: informe um ou dois símbolos.");
            entrada.close();
            return;
        }

        for (int i = 0; i < quantidadeSimbolos; i++) {
            System.out.print("Símbolo " + (i + 1) + ": ");
            String simbolo = entrada.nextLine();

            if (simbolo.length() != 1
                    || automato.getAlfabeto().contains(simbolo.charAt(0))) {
                System.out.println("Erro: símbolo inválido ou repetido.");
                entrada.close();
                return;
            }

            automato.adicionarSimbolo(simbolo.charAt(0));
        }

        System.out.print("Estado inicial: ");
        String inicial = entrada.nextLine().trim();
        automato.definirEstadoInicial(inicial);

        System.out.print("Quantidade de estados finais: ");
        int quantidadeFinais = entrada.nextInt();
        entrada.nextLine();
    }
}
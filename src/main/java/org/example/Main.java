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

        if (quantidadeFinais < 0 || quantidadeFinais > quantidadeEstados) {
            System.out.println("Erro: quantidade de estados finais inválida.");
            entrada.close();
            return;
        }

        for (int i = 0; i < quantidadeFinais; i++) {
            System.out.print("Estado final " + (i + 1) + ": ");
            String estadoFinal = entrada.nextLine().trim();

            if (!automato.getEstados().contains(estadoFinal)
                    || automato.getEstadosFinais().contains(estadoFinal)) {
                System.out.println(
                        "Erro: estado final inexistente ou já cadastrado."
                );
                entrada.close();
                return;
            }

            automato.adicionarEstadoFinal(estadoFinal);
        }

        System.out.println("\n=== CADASTRO DAS TRANSIÇÕES ===");

        for (String origem : automato.getEstados()) {
            for (char simbolo : automato.getAlfabeto()) {
                System.out.println("\nEstado de origem: " + origem);
                System.out.println("Símbolo lido: " + simbolo);

                System.out.print("Estado de destino: ");
                String destino = entrada.nextLine().trim();

                automato.adicionarTransicao(origem, simbolo, destino);
            }
        }

        System.out.println("\nCadastro das transições concluído!");

        if (!automato.validarAFD()) {
            System.out.println("Autômato inválido!");
            entrada.close();
            return;
        }

        System.out.println("Autômato válido!");

        boolean continuar = true;

        while (continuar) {
            System.out.print(
                    "\nDigite uma cadeia (ou 'sair' para encerrar): "
            );
            String cadeia = entrada.nextLine();

            if (cadeia.equalsIgnoreCase("sair")) {
                continuar = false;
            } else {
                boolean aceita = automato.simularCadeia(cadeia);

                if (aceita) {
                    System.out.println("CADEIA ACEITA!");
                } else {
                    System.out.println("CADEIA REJEITADA!");
                }
            }
        }

        entrada.close();
    }
}
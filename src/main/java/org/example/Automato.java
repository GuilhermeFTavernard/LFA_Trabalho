package org.example;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

public class Automato {
    private Set<String> estados;
    private Set<Character> alfabeto;
    private Map<String, String> transicoes;
    private String estadoInicial;
    private Set<String> estadosFinais;
    private boolean transicaoDuplicada;

    public Automato() {
        estados = new HashSet<>();
        alfabeto = new HashSet<>();
        transicoes = new HashMap<>();
        estadosFinais = new HashSet<>();
        transicaoDuplicada = false;
    }

    public void adicionarEstado(String estado) {
        estados.add(estado);
    }

    public void adicionarSimbolo(char simbolo) {
        alfabeto.add(simbolo);
    }

    public void definirEstadoInicial(String estado) {
        estadoInicial = estado;
    }

    public void adicionarEstadoFinal(String estado) {
        estadosFinais.add(estado);
    }

    public Set<String> getEstados() {
        return estados;
    }

    public Set<Character> getAlfabeto() {
        return alfabeto;
    }

    public Set<String> getEstadosFinais() {
        return estadosFinais;
    }

    public void adicionarTransicao(String origem, char simbolo, String destino) {

        String chave = origem + "," + simbolo;

        if (transicoes.containsKey(chave)) {
            transicaoDuplicada = true;
        } else {
            transicoes.put(chave, destino);
        }
    }

    public String consultarTransicao(String estado, char simbolo) {
        String chave = estado + "," + simbolo;
        return transicoes.get(chave);
    }


    public boolean simularCadeia(String cadeia) {

        String estadoAtual = estadoInicial;

        for (int i = 0; i < cadeia.length(); i++) {

            char simbolo = cadeia.charAt(i);

            if (!alfabeto.contains(simbolo)) {
                return false;
            }

            String proximoEstado = consultarTransicao(estadoAtual, simbolo);

            if (proximoEstado == null) {
                return false;
            }

            estadoAtual = proximoEstado;
        }

        return estadosFinais.contains(estadoAtual);
    }


    public boolean validarAFD() {

        if (estados.isEmpty() || alfabeto.isEmpty()) {
            return false;
        }

        if (estadoInicial == null || !estados.contains(estadoInicial)) {
            return false;
        }

        if (!estados.containsAll(estadosFinais)) {
            return false;
        }

        if (transicaoDuplicada) {
            return false;
        }

        for (Map.Entry<String, String> entrada : transicoes.entrySet()) {

            String chave = entrada.getKey();
            String destino = entrada.getValue();

            int separador = chave.lastIndexOf(',');

            String origem = chave.substring(0, separador);
            char simbolo = chave.charAt(separador + 1);

            if (!estados.contains(origem)) {
                return false;
            }

            if (!alfabeto.contains(simbolo)) {
                return false;
            }

            if (!estados.contains(destino)) {
                return false;
            }
        }

        for (String estado : estados) {
            for (char simbolo : alfabeto) {

                String chave = estado + "," + simbolo;

                if (!transicoes.containsKey(chave)) {
                    return false;
                }
            }
        }

        return true;
    }
}

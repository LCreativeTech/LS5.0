package locaspace.service;

import locaspace.model.*;
import java.io.*;
import java.util.*;

/**
 * Classe responsável pela persistência dos dados em arquivos de texto.
 */
public class GerenciadorDados {

    private final String ARQUIVO_CLIENTES = "clientes.txt";
    private final String ARQUIVO_ESTABELECIMENTOS = "estabelecimentos.txt";
    private final String ARQUIVO_UNIDADES = "unidades.txt";
    private final String ARQUIVO_RESERVAS = "reservas.txt";

    public void salvarCliente(Cliente c) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_CLIENTES, true))) {
            bw.write(c.getId() + "," + c.getLogin() + "," + c.getSenha() + "," + c.getNome() + "," + c.getCpf());
            bw.newLine();
            System.out.println("Cliente " + c.getNome() + " salvo em arquivo.");
        } catch (IOException e) {
            System.err.println("ERRO ao salvar Cliente: " + e.getMessage());
        }
    }

    public void salvarEstabelecimento(Estabelecimento e) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_ESTABELECIMENTOS, true))) {
            bw.write(e.getId() + "," + e.getNome() + "," + e.getEndereco() + "," + e.getTipo() + "," + e.getLocatario().getId());
            bw.newLine();
            System.out.println("Estabelecimento " + e.getNome() + " salvo em arquivo.");
        } catch (IOException ex) {
            System.err.println("ERRO ao salvar Estabelecimento: " + ex.getMessage());
        }
    }

    public void salvarUnidade(Unidade u) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_UNIDADES, true))) {
            bw.write(u.getId() + "," + u.getNome() + "," + u.getEstabelecimento().getId() + "," + u.getTipo() + "," + u.getPrecoDiaria() + "," + u.isDisponivel());
            bw.newLine();
            System.out.println("Unidade " + u.getNome() + " salva em arquivo.");
        } catch (IOException ex) {
            System.err.println("ERRO ao salvar Unidade: " + ex.getMessage());
        }
    }

    public void salvarReserva(Reserva r) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_RESERVAS, true))) {
            Integer idUnidade = r.getUnidadeReservada().getId();

            bw.write(r.getId() + "," + r.getCliente().getId() + "," + idUnidade + "," + r.getStatus());
            bw.newLine();
            System.out.println("Reserva " + r.getId() + " salva em arquivo.");
        } catch (IOException ex) {
            System.err.println("ERRO ao salvar Reserva: " + ex.getMessage());
        }
    }
}
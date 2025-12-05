package locaspace.test;

import locaspace.model.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class STesteUnidadeSistema {

    /**
     * Testa o cálculo do custo total da reserva.
     */
    @Test
    void testarCalculoCustoTotal() {
        // Setup
        Locatario loc = new Locatario(10, "loc1", "123", "Contato", "999");
        Estabelecimento est = new Estabelecimento(1, "Hotel Teste", "Rua A", TipoEstabelecimento.HOTEL, loc);
        Unidade unidadeTeste = new Unidade("Quarto 101", est, TipoEstabelecimento.QUARTO, 150.00, true);
        Cliente cli = new Cliente(20, "cli1", "456", "Maria", "123");

        Calendar calIni = Calendar.getInstance();
        calIni.set(2025, Calendar.DECEMBER, 1);

        Calendar calFim = Calendar.getInstance();
        calFim.set(2025, Calendar.DECEMBER, 4);

        Reserva r = new Reserva(cli, unidadeTeste, calIni.getTime(), calFim.getTime(), 2);

        // 3 diárias * 150.00 = 450.00
        assertEquals(450.00, r.calcularCustoTotal(), 0.001);
    }

    /**
     * Testa a alteração de status da reserva pelo locatário.
     */
    @Test
    void testarConfirmacaoReserva() {
        Locatario loc = new Locatario(10, "loc1", "123", "Contato", "999");
        Estabelecimento est = new Estabelecimento(1, "Hotel Teste", "Rua A", TipoEstabelecimento.HOTEL, loc);
        Unidade unidadeTeste = new Unidade("Quarto 101", est, TipoEstabelecimento.QUARTO, 100.00, true);
        Cliente cli = new Cliente(20, "cli1", "456", "Maria", "123");

        Date inicio = new Date();
        Date fim = new Date(inicio.getTime() + 86400000);

        Reserva r = new Reserva(cli, unidadeTeste, inicio, fim, 1);

        assertEquals(StatusReserva.PENDENTE, r.getStatus());
        loc.confirmarReserva(r);
        assertEquals(StatusReserva.CONFIRMADA, r.getStatus());
    }

    /**
     * Testa se a unidade está disponível para reserva.
     */
    @Test
    void testarDisponibilidadeUnidade() {
        // CORREÇÃO: Substituído SALA por SALA_EVENTOS
        Unidade u = new Unidade("Sala", null, TipoEstabelecimento.SALA_EVENTOS, 80.0, true);

        assertTrue(u.isDisponivel());

        u.setDisponivel(false);

        assertFalse(u.isDisponivel());
    }

    /**
     * Testa reserva com datas inválidas, fim antes do início.
     */
    @Test
    void testarReservaDatasInvalidas() {

        Cliente cli = new Cliente(1, "cli", "123", "José", "999");
        Unidade u = new Unidade("Q1", null, TipoEstabelecimento.QUARTO, 100, true);

        Calendar c = Calendar.getInstance();
        c.set(2025, Calendar.JANUARY, 10);
        Date fim = c.getTime();
        c.set(2025, Calendar.JANUARY, 5);
        Date inicio = c.getTime();

        Date inicioInvalido = new Date(fim.getTime() + 86400000);
        Date fimValido = fim;

        assertThrows(IllegalArgumentException.class,
                () -> new Reserva(cli, u, inicioInvalido, fimValido, 1));
    }

    /**
     * Testa custo total quando for 0 diárias.
     */
    @Test
    void testarCustoTotalSemDiarias() {
        Cliente cli = new Cliente(1, "cli", "123", "José", "999");
        Unidade u = new Unidade("Q1", null, TipoEstabelecimento.QUARTO, 120, true);

        Date agora = new Date();

        Reserva r = new Reserva(cli, u, agora, agora, 1);

        assertEquals(0, r.calcularCustoTotal(), 0.001);
    }

    /**
     * Testa cancelamento da reserva.
     */
    @Test
    void testarCancelamentoReserva() {
        Cliente cli = new Cliente(1, "cli", "123", "José", "999");
        Unidade u = new Unidade("Q1", null, TipoEstabelecimento.QUARTO, 120, true);

        Date ini = new Date();
        Date fim = new Date(ini.getTime() + 86400000);

        Reserva r = new Reserva(cli, u, ini, fim, 1);

        r.cancelar(); // Método implementado na classe Reserva

        assertEquals(StatusReserva.CANCELADA, r.getStatus());
    }

    /**
     * Testa alteração de status para negada.
     */
    @Test
    void testarNegarReserva() {
        Locatario loc = new Locatario(10, "loc1", "123", "Contato", "999");
        Estabelecimento est = new Estabelecimento(1, "Hotel Teste", "Rua A", TipoEstabelecimento.HOTEL, loc);
        Unidade u = new Unidade("Q1", est, TipoEstabelecimento.QUARTO, 200, true);
        Cliente cli = new Cliente(1, "c", "1", "Ana", "555");

        Date ini = new Date();
        Date fim = new Date(ini.getTime() + 86400000);

        Reserva r = new Reserva(cli, u, ini, fim, 1);

        loc.negarReserva(r); // Método implementado na classe Locatario

        assertEquals(StatusReserva.NEGADA, r.getStatus());
    }

    /**
     * Testa número de hóspedes inválido de 0 ou negativo.
     */
    @Test
    void testarNumeroHospedesInvalido() {
        Cliente cli = new Cliente(1, "cli", "123", "José", "999");
        Unidade u = new Unidade("Q1", null, TipoEstabelecimento.QUARTO, 100, true);

        Date ini = new Date();
        Date fim = new Date(ini.getTime() + 86400000);

        assertThrows(IllegalArgumentException.class,
                () -> new Reserva(cli, u, ini, fim, 0));
    }

    /**
     * Testa tornar unidade ocupada após reserva confirmada.
     */
    @Test
    void testarUnidadeFicaOcupada() {
        Locatario loc = new Locatario(1, "loc", "1", "Contato", "999");
        Estabelecimento est = new Estabelecimento(1, "E", "Rua", TipoEstabelecimento.HOTEL, loc);
        Unidade u = new Unidade("Q1", est, TipoEstabelecimento.QUARTO, 100, true);

        Cliente cli = new Cliente(1, "c", "1", "Ana", "555");

        Date ini = new Date();
        Date fim = new Date(ini.getTime() + 86400000);

        Reserva r = new Reserva(cli, u, ini, fim, 1);

        loc.confirmarReserva(r);

        assertFalse(u.isDisponivel());
    }
}
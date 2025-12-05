package locaspace.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reserva {
    private static int nextId = 1;
    private int id;
    private Cliente cliente;
    private Unidade unidade;
    private Date inicio;
    private Date fim;
    private StatusReserva status;
    private int numeroHospedes;

    public Reserva(Cliente cliente, Unidade unidade, Date inicio, Date fim, int numeroHospedes) {
        if (numeroHospedes <= 0) {
            throw new IllegalArgumentException("O número de hóspedes deve ser positivo.");
        }

        if (inicio.after(fim)) {
            throw new IllegalArgumentException("A data de início da reserva não pode ser depois da data de fim.");
        }

        this.id = nextId++;
        this.cliente = cliente;
        this.unidade = unidade;
        this.inicio = inicio;
        this.fim = fim;
        this.numeroHospedes = numeroHospedes;
        this.status = StatusReserva.PENDENTE;
    }

    public double calcularCustoTotal() {
        long diffInMillies = fim.getTime() - inicio.getTime();
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        int diarias = (int) diffInDays;

        if (diarias > 0) {
            return (double) diarias * unidade.getPrecoDiaria();
        }

        return 0.0;
    }

    public void cancelar() {
        this.setStatus(StatusReserva.CANCELADA);
    }

    // Getters exigidos por GerenciadorDados
    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public StatusReserva getStatus() { return status; }

    public Unidade getUnidadeReservada() { // Método exigido pelo GerenciadorDados
        return unidade;
    }

    // Getter necessário para Locatario.confirmarReserva()
    public Unidade getUnidade() { return unidade; }

    // Setter
    public void setStatus(StatusReserva status) {
        this.status = status;
    }
}
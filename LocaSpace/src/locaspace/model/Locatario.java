package locaspace.model;

public class Locatario extends Usuario {
    private String contato;
    private String telefone;

    public Locatario(Integer id, String login, String senha, String contato, String telefone) {
        super(id, login, senha);
        this.contato = contato;
        this.telefone = telefone;
    }

    public void confirmarReserva(Reserva r) {
        r.setStatus(StatusReserva.CONFIRMADA);
        // Define a unidade como indisponível (ocupada)
        r.getUnidade().setDisponivel(false);
    }

    public void negarReserva(Reserva reserva) {
        reserva.setStatus(StatusReserva.NEGADA);
    }

    public void gerenciarDisponibilidade(Unidade u, boolean status) {
        u.setDisponivel(status);
    }

    public void cancelarReserva(Reserva r) {
        r.setStatus(StatusReserva.CANCELADA);
        r.getUnidade().setDisponivel(true);
    }

    // Getters
    public String getContato() { return contato; }
    public String getTelefone() { return telefone; }
}
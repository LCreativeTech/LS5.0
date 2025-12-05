package locaspace.model;

import java.util.List;

public class Administrador extends Usuario {

    public Administrador(Integer id, String login, String senha) {
        super(id, login, senha);
    }

    public void gerenciarUsuario(Usuario u) {
        System.out.println("Gerenciando usuário: " + u.getLogin());
    }

    public void visualizarRelatorio() {
        System.out.println("RELATÓRIO DO SISTEMA: Resumo de cadastros e reservas...");
    }

    public void bloquearUsuario(Integer idUsuario) {
        System.out.println("Usuário bloqueado: " + idUsuario);
    }

    public void gerenciarTipoEstabelecimento(String tipo) {
        // Simula a gestão de tipos ou enums dinâmicos
        System.out.println("Novo tipo de estabelecimento cadastrado/gerenciado: " + tipo);
    }

    public void auditarLog() {
        System.out.println("Logs do sistema auditados.");
    }
}
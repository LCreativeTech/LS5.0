package locaspace.ui;

import locaspace.model.*;
import locaspace.service.GerenciadorDados;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SistemaPrincipal extends JFrame {

    private GerenciadorDados gerenciador = new GerenciadorDados();
    private Random random = new Random();

    // CLIENTE
    private JTextField txtLogin, txtNome, txtCpf;

    // ESTABELECIMENTO
    private JTextField txtIdEst, txtNomeEst, txtEnderecoEst, txtIdLocatarioEst;
    private JComboBox<TipoEstabelecimento> comboTipoEst;

    // UNIDADE
    private JTextField txtNomeUnidade, txtPrecoUnidade, txtIdEstUnidade;
    private JComboBox<TipoEstabelecimento> comboTipoUnidade;
    private JCheckBox checkDisponivelUnidade;

    public SistemaPrincipal() {
        setTitle("LocaSpace - Cadastro Centralizado");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("1. Cliente", criarPainelCliente());
        tabbedPane.addTab("2. Estabelecimento", criarPainelEstabelecimento());
        tabbedPane.addTab("3. Unidade", criarPainelUnidade());

        add(tabbedPane);
        setVisible(true);
    }

    // 1. CADASTRO DE CLIENTE

    private JPanel criarPainelCliente() {
        JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));

        painel.add(new JLabel("E-mail:"));
        txtLogin = new JTextField();
        painel.add(txtLogin);

        painel.add(new JLabel("Nome Completo:"));
        txtNome = new JTextField();
        painel.add(txtNome);

        painel.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        painel.add(txtCpf);

        painel.add(new JLabel(""));
        painel.add(new JLabel(""));

        JButton btnSalvar = new JButton("Salvar Cliente");
        btnSalvar.addActionListener(e -> cadastrarCliente());
        painel.add(btnSalvar);

        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        container.add(painel, BorderLayout.NORTH);
        return container;
    }

    private void cadastrarCliente() {
        try {
            int id = random.nextInt(900) + 100;

            String login = txtLogin.getText();
            String nome = txtNome.getText();
            String cpf = txtCpf.getText();

            String senhaMock = "123456";

            if (login.isEmpty() || nome.isEmpty() || cpf.isEmpty()) {
                throw new IllegalArgumentException("Preencha todos os campos obrigatórios.");
            }

            // A senha agora usa a string mockada
            Cliente c = new Cliente(id, login, senhaMock, nome, cpf);
            gerenciador.salvarCliente(c);
            JOptionPane.showMessageDialog(this, "Cliente " + nome + " salvo com ID: " + id + "!");

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Erro de Validação: " + ex.getMessage()); // validação
        } catch (Exception ex) {
            // Se o erro de "txtSenha is null" persistir, o problema é no ambiente (IDE/compilação)
            JOptionPane.showMessageDialog(this, "Erro inesperado ao cadastrar Cliente: " + ex.getMessage()); // erros gerais
        }
    }
    // 2. CADASTRO DE ESTABELECIMENTO
    private JPanel criarPainelEstabelecimento() {
        JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));

        painel.add(new JLabel("ID do Estabelecimento:"));
        txtIdEst = new JTextField();
        painel.add(txtIdEst);

        painel.add(new JLabel("Nome do Estabelecimento:"));
        txtNomeEst = new JTextField();
        painel.add(txtNomeEst);

        painel.add(new JLabel("Endereço:"));
        txtEnderecoEst = new JTextField();
        painel.add(txtEnderecoEst);

        painel.add(new JLabel("ID do Locatário (Proprietário):"));
        txtIdLocatarioEst = new JTextField();
        painel.add(txtIdLocatarioEst);

        painel.add(new JLabel("Tipo (Empreendimento):"));
        comboTipoEst = new JComboBox<>(new TipoEstabelecimento[]{
                TipoEstabelecimento.HOTEL,
                TipoEstabelecimento.POUSADA,
                TipoEstabelecimento.HOSTEL,
                TipoEstabelecimento.RESORT
        });
        painel.add(comboTipoEst);

        painel.add(new JLabel(""));

        JButton btnSalvar = new JButton("Salvar Estabelecimento");
        btnSalvar.addActionListener(e -> cadastrarEstabelecimento());
        painel.add(btnSalvar);

        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        container.add(painel, BorderLayout.NORTH);
        return container;
    }

    private void cadastrarEstabelecimento() {
        try {
            int id = Integer.parseInt(txtIdEst.getText());
            int idLocatario = Integer.parseInt(txtIdLocatarioEst.getText());

            Locatario locatarioMock = new Locatario(idLocatario, "mock", "mock", "", "");

            TipoEstabelecimento tipo = (TipoEstabelecimento) comboTipoEst.getSelectedItem();

            Estabelecimento est = new Estabelecimento(id, txtNomeEst.getText(), txtEnderecoEst.getText(), tipo, locatarioMock);
            gerenciador.salvarEstabelecimento(est);
            JOptionPane.showMessageDialog(this, "Estabelecimento " + est.getNome() + " salvo!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: ID e ID do Locatário devem ser números válidos."); // conversão
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar Estabelecimento: " + e.getMessage()); // erros gerais
        }
    }


    // 3. CADASTRO DE UNIDADE


    private JPanel criarPainelUnidade() {
        JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));

        painel.add(new JLabel("ID do Estabelecimento:"));
        txtIdEstUnidade = new JTextField();
        painel.add(txtIdEstUnidade);

        painel.add(new JLabel("Nome da Unidade (Ex: Quarto 101):"));
        txtNomeUnidade = new JTextField();
        painel.add(txtNomeUnidade);

        painel.add(new JLabel("Tipo da Unidade/Serviço:"));

        comboTipoUnidade = new JComboBox<>(new TipoEstabelecimento[]{
                TipoEstabelecimento.QUARTO,
                TipoEstabelecimento.SUITE,
                TipoEstabelecimento.RESTAURANTE,
                TipoEstabelecimento.GARAGEM
        });
        painel.add(comboTipoUnidade);

        painel.add(new JLabel("Preço Diária (R$):"));
        txtPrecoUnidade = new JTextField();
        painel.add(txtPrecoUnidade);

        painel.add(new JLabel("Disponível para Reserva:"));
        checkDisponivelUnidade = new JCheckBox("Sim");
        painel.add(checkDisponivelUnidade);

        JButton btnSalvar = new JButton("Salvar Unidade");
        btnSalvar.addActionListener(e -> cadastrarUnidade());
        painel.add(btnSalvar);

        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        container.add(painel, BorderLayout.NORTH);
        return container;
    }

    private void cadastrarUnidade() {
        try {
            int idEst = Integer.parseInt(txtIdEstUnidade.getText());


            Locatario locatarioMock = new Locatario(999, "mock", "mock", "", "");
            Estabelecimento estMock = new Estabelecimento(idEst, "Local Mock " + idEst, "Rua Mock", TipoEstabelecimento.HOTEL, locatarioMock);

            double preco = Double.parseDouble(txtPrecoUnidade.getText());
            TipoEstabelecimento tipo = (TipoEstabelecimento) comboTipoUnidade.getSelectedItem();
            boolean disponivel = checkDisponivelUnidade.isSelected();

            Unidade unidade = new Unidade(txtNomeUnidade.getText(), estMock, tipo, preco, disponivel);

            gerenciador.salvarUnidade(unidade);
            JOptionPane.showMessageDialog(this, "Unidade '" + unidade.getNome() + "' salva!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro: ID do Estabelecimento e Preço devem ser números válidos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar Unidade: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SistemaPrincipal::new);
    }
}
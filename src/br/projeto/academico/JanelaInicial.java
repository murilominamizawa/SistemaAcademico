package br.projeto.academico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class JanelaInicial extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // ABA 1: Dados Pessoais
    private JTextField txtRgm;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtEndereco;
    private JTextField txtMunicipio;
    private JComboBox<String> cbUf;
    private JFormattedTextField txtDataNascimento;
    private JFormattedTextField txtCpf;
    private JFormattedTextField txtCelular;

    // ABA 2: Curso
    private JComboBox<String> cbCurso;
    private JComboBox<String> cbCampus;
    private JRadioButton rbMatutino;
    private JRadioButton rbVespertino;
    private JRadioButton rbNoturno;
    private ButtonGroup grupoPeriodo;

    // ABA 3: Notas e Faltas
    private JTextField txtRgmNotas;
    private JComboBox<String> cbDisciplina;
    private JComboBox<String> cbSemestre;
    private JTextField txtNota;
    private JTextField txtFaltas;
    private JLabel lblNomeDinamico;
    private JLabel lblCursoDinamico;

    // ABA 4: Boletim
    private JLabel lblNomeBoletim;
    private JLabel lblCursoBoletim;
    private JLabel lblMediaBoletim;
    private javax.swing.table.DefaultTableModel modelTabela;
    private javax.swing.JTable tabelaBoletim;
    private JTextField txtRgmBoletimBusca;

    // Botões de TEXTO — ABA 1
    private JButton btnSalvarDP, btnAlterarDP, btnConsultarDP;
    private JButton btnExcluirDP, btnLimparDP, btnSairDP;

    // Botões de ÍCONE — ABA 1
    private JButton btnIconSalvarDP, btnIconAlterarDP, btnIconConsultarDP;
    private JButton btnIconExcluirDP, btnIconLimparDP, btnIconSairDP;

    // Botões de TEXTO — ABA 2
    private JButton btnSalvarCurso, btnAlterarCurso, btnConsultarCurso;
    private JButton btnExcluirCurso, btnLimparCurso, btnSairCurso;

    // Botões de ÍCONE — ABA 2
    private JButton btnIconSalvarCurso, btnIconAlterarCurso, btnIconConsultarCurso;
    private JButton btnIconExcluirCurso, btnIconLimparCurso, btnIconSairCurso;

    // Botões de TEXTO — ABA 3 
    private JButton btnSalvarNF, btnAlterarNF, btnConsultarNF;
    private JButton btnExcluirNF, btnLimparNF, btnSairNF;

    // Botões de ÍCONE — ABA 3
    private JButton btnIconSalvarNF, btnIconAlterarNF, btnIconConsultarNF;
    private JButton btnIconExcluirNF, btnIconLimparNF, btnIconSairNF;

    // Limite máximo de faltas permitidas que será 15
    private static final int MAX_FALTAS = 15;

    // Caminhos dos ícones
    private static final String ICO_SALVAR    = "/icons/Salvar.png";
    private static final String ICO_ALTERAR   = "/icons/Alterar.png";
    private static final String ICO_CONSULTAR = "/icons/Consultar.png";
    private static final String ICO_EXCLUIR   = "/icons/Excluir.png";
    private static final String ICO_LIMPAR    = "/icons/Limpar.png";
    private static final String ICO_SAIR      = "/icons/Sair.png";

    // ──────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JanelaInicial frame = new JanelaInicial();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //  CONSTRUTOR PRINCIPAL
    public JanelaInicial() {
        setTitle("Sistema Acadêmico – UNICID");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 780, 620);
        setLocationRelativeTo(null);
        setResizable(false);

        // MENU
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnAluno = new JMenu("Aluno");
        menuBar.add(mnAluno);

        JMenuItem mntmSalvar    = new JMenuItem("Salvar");
        JMenuItem mntmAlterar   = new JMenuItem("Alterar");
        JMenuItem mntmConsultar = new JMenuItem("Consultar");
        JMenuItem mntmExcluir   = new JMenuItem("Excluir");
        JMenuItem mntmSair      = new JMenuItem("Sair");

        mntmSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        mntmSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));

        mnAluno.add(mntmSalvar);
        mnAluno.add(mntmAlterar);
        mnAluno.add(mntmConsultar);
        mnAluno.add(mntmExcluir);
        mnAluno.addSeparator();
        mnAluno.add(mntmSair);

        JMenu mnNotasFaltas = new JMenu("Notas e Faltas");
        menuBar.add(mnNotasFaltas);

        JMenuItem mntmSalvarNf    = new JMenuItem("Salvar");
        JMenuItem mntmAlterarNf   = new JMenuItem("Alterar");
        JMenuItem mntmExcluirNf   = new JMenuItem("Excluir");
        JMenuItem mntmConsultarNf = new JMenuItem("Consultar");

        mntmAlterarNf.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));

        mnNotasFaltas.add(mntmSalvarNf);
        mnNotasFaltas.add(mntmAlterarNf);
        mnNotasFaltas.add(mntmExcluirNf);
        mnNotasFaltas.add(mntmConsultarNf);

        JMenu mnAjuda = new JMenu("Ajuda");
        menuBar.add(mnAjuda);
        JMenuItem mntmSobre = new JMenuItem("Sobre");
        mnAjuda.add(mntmSobre);

        // CONTEÚDO PRINCIPAL
        contentPane = new JPanel(new BorderLayout(0, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        //  ABA 1 – DADOS PESSOAIS
        JPanel panelDP = new JPanel(null);
        tabbedPane.addTab("Dados Pessoais", null, panelDP, "Cadastro de informações pessoais");

        addLabel(panelDP, "RGM:",  20, 20, 40, 22);
        txtRgm = addTextField(panelDP, 62, 18, 110, 25);

        addLabel(panelDP, "Nome:", 185, 20, 42, 22);
        txtNome = addTextField(panelDP, 228, 18, 510, 25);

        addLabel(panelDP, "Data de Nascimento:", 20, 60, 140, 22);
        addLabel(panelDP, "CPF:", 310, 60, 35, 22);

        try {
            MaskFormatter mData = new MaskFormatter("##/##/####");
            mData.setPlaceholderCharacter('_');
            txtDataNascimento = new JFormattedTextField(mData);
            txtDataNascimento.setBounds(162, 58, 130, 25);
            panelDP.add(txtDataNascimento);

            MaskFormatter mCpf = new MaskFormatter("###.###.###-##");
            mCpf.setPlaceholderCharacter('_');
            txtCpf = new JFormattedTextField(mCpf);
            txtCpf.setBounds(347, 58, 391, 25);
            panelDP.add(txtCpf);

            addLabel(panelDP, "E-mail:", 20, 100, 50, 22);
            txtEmail = addTextField(panelDP, 70, 98, 668, 25);

            addLabel(panelDP, "Endereço:", 20, 140, 70, 22);
            txtEndereco = addTextField(panelDP, 92, 138, 646, 25);

            addLabel(panelDP, "Município:", 20, 180, 70, 22);
            txtMunicipio = addTextField(panelDP, 92, 178, 180, 25);

            addLabel(panelDP, "UF:", 282, 180, 25, 22);
            String[] estados = {"SP","RJ","MG","ES","PR","SC","RS","BA","GO","DF",
                                "AM","PA","MT","MS","RO","AC","RR","AP","TO","MA",
                                "PI","CE","RN","PB","PE","AL","SE"};
            cbUf = new JComboBox<>(estados);
            cbUf.setBounds(308, 178, 65, 25);
            panelDP.add(cbUf);

            addLabel(panelDP, "Celular:", 385, 180, 55, 22);
            MaskFormatter mCel = new MaskFormatter("(##)#####-####");
            mCel.setPlaceholderCharacter('_');
            txtCelular = new JFormattedTextField(mCel);
            txtCelular.setBounds(440, 178, 300, 25);
            panelDP.add(txtCelular);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSeparator sep1 = new JSeparator();
        sep1.setBounds(10, 220, 740, 2);
        panelDP.add(sep1);

        // Botões de texto – ABA 1
        JPanel painelBotoesDP = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 4));
        painelBotoesDP.setBounds(0, 225, 760, 40);
        btnSalvarDP    = new JButton("Salvar");
        btnAlterarDP   = new JButton("Alterar");
        btnConsultarDP = new JButton("Consultar");
        btnExcluirDP   = new JButton("Excluir");
        btnLimparDP    = new JButton("Limpar");
        btnSairDP      = new JButton("Sair");
        estilizarBotao(btnSalvarDP,    new Color(0, 153, 76));
        estilizarBotao(btnAlterarDP,   new Color(0, 102, 204));
        estilizarBotao(btnConsultarDP, new Color(102, 102, 0));
        estilizarBotao(btnExcluirDP,   new Color(204, 0, 0));
        estilizarBotao(btnLimparDP,    new Color(130, 130, 130));
        estilizarBotao(btnSairDP,      new Color(80, 80, 80));
        painelBotoesDP.add(btnSalvarDP);
        painelBotoesDP.add(btnAlterarDP);
        painelBotoesDP.add(btnConsultarDP);
        painelBotoesDP.add(btnExcluirDP);
        painelBotoesDP.add(btnLimparDP);
        painelBotoesDP.add(btnSairDP);
        panelDP.add(painelBotoesDP);

        // Botões de ícone – ABA 1
        JPanel painelIconesDP = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 4));
        painelIconesDP.setBounds(0, 268, 760, 75);
        btnIconSalvarDP    = criarBotaoIcone("Salvar",    ICO_SALVAR);
        btnIconAlterarDP   = criarBotaoIcone("Alterar",   ICO_ALTERAR);
        btnIconConsultarDP = criarBotaoIcone("Consultar", ICO_CONSULTAR);
        btnIconExcluirDP   = criarBotaoIcone("Excluir",   ICO_EXCLUIR);
        btnIconLimparDP    = criarBotaoIcone("Limpar",    ICO_LIMPAR);
        btnIconSairDP      = criarBotaoIcone("Sair",      ICO_SAIR);
        painelIconesDP.add(btnIconSalvarDP);
        painelIconesDP.add(btnIconAlterarDP);
        painelIconesDP.add(btnIconConsultarDP);
        painelIconesDP.add(btnIconExcluirDP);
        painelIconesDP.add(btnIconLimparDP);
        painelIconesDP.add(btnIconSairDP);
        panelDP.add(painelIconesDP);

        //  ABA 2 – CURSO
        JPanel panelCurso = new JPanel(null);
        tabbedPane.addTab("Curso", null, panelCurso, "Vínculo acadêmico");

        addLabel(panelCurso, "Curso:", 20, 30, 55, 22);
        String[] cursos = {
            "Análise e Desenvolvimento de Sistemas",
            "Ciência da Computação",
            "Engenharia de Software",
            "Sistemas de Informação"
        };
        cbCurso = new JComboBox<>(cursos);
        cbCurso.setBounds(80, 28, 640, 25);
        panelCurso.add(cbCurso);

        addLabel(panelCurso, "Campus:", 20, 75, 60, 22);
        String[] campi = {"Tatuapé", "Pinheiros", "Anália Franco", "São Miguel"};
        cbCampus = new JComboBox<>(campi);
        cbCampus.setBounds(80, 73, 250, 25);
        panelCurso.add(cbCampus);

        addLabel(panelCurso, "Período:", 20, 120, 60, 22);
        rbMatutino   = new JRadioButton("Matutino");
        rbVespertino = new JRadioButton("Vespertino");
        rbNoturno    = new JRadioButton("Noturno");
        rbNoturno.setSelected(true);
        rbMatutino.setBounds(85,  118, 100, 25);
        rbVespertino.setBounds(195, 118, 110, 25);
        rbNoturno.setBounds(315,  118, 100, 25);
        panelCurso.add(rbMatutino);
        panelCurso.add(rbVespertino);
        panelCurso.add(rbNoturno);
        grupoPeriodo = new ButtonGroup();
        grupoPeriodo.add(rbMatutino);
        grupoPeriodo.add(rbVespertino);
        grupoPeriodo.add(rbNoturno);

        JSeparator sep2 = new JSeparator();
        sep2.setBounds(10, 160, 740, 2);
        panelCurso.add(sep2);

        // Botões de texto – ABA 2
        JPanel painelBotoesCurso = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 4));
        painelBotoesCurso.setBounds(0, 165, 760, 40);
        btnSalvarCurso    = new JButton("Salvar");
        btnAlterarCurso   = new JButton("Alterar");
        btnConsultarCurso = new JButton("Consultar");
        btnExcluirCurso   = new JButton("Excluir");
        btnLimparCurso    = new JButton("Limpar");
        btnSairCurso      = new JButton("Sair");
        estilizarBotao(btnSalvarCurso,    new Color(0, 153, 76));
        estilizarBotao(btnAlterarCurso,   new Color(0, 102, 204));
        estilizarBotao(btnConsultarCurso, new Color(102, 102, 0));
        estilizarBotao(btnExcluirCurso,   new Color(204, 0, 0));
        estilizarBotao(btnLimparCurso,    new Color(130, 130, 130));
        estilizarBotao(btnSairCurso,      new Color(80, 80, 80));
        painelBotoesCurso.add(btnSalvarCurso);
        painelBotoesCurso.add(btnAlterarCurso);
        painelBotoesCurso.add(btnConsultarCurso);
        painelBotoesCurso.add(btnExcluirCurso);
        painelBotoesCurso.add(btnLimparCurso);
        painelBotoesCurso.add(btnSairCurso);
        panelCurso.add(painelBotoesCurso);

        // Botões de ícone – ABA 2
        JPanel painelIconesCurso = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 4));
        painelIconesCurso.setBounds(0, 208, 760, 75);
        btnIconSalvarCurso    = criarBotaoIcone("Salvar",    ICO_SALVAR);
        btnIconAlterarCurso   = criarBotaoIcone("Alterar",   ICO_ALTERAR);
        btnIconConsultarCurso = criarBotaoIcone("Consultar", ICO_CONSULTAR);
        btnIconExcluirCurso   = criarBotaoIcone("Excluir",   ICO_EXCLUIR);
        btnIconLimparCurso    = criarBotaoIcone("Limpar",    ICO_LIMPAR);
        btnIconSairCurso      = criarBotaoIcone("Sair",      ICO_SAIR);
        painelIconesCurso.add(btnIconSalvarCurso);
        painelIconesCurso.add(btnIconAlterarCurso);
        painelIconesCurso.add(btnIconConsultarCurso);
        painelIconesCurso.add(btnIconExcluirCurso);
        painelIconesCurso.add(btnIconLimparCurso);
        painelIconesCurso.add(btnIconSairCurso);
        panelCurso.add(painelIconesCurso);

        //  ABA 3 – NOTAS E FALTAS
        JPanel panelNF = new JPanel(null);
        tabbedPane.addTab("Notas e Faltas", null, panelNF, "Lançamento de notas e faltas");

        addLabel(panelNF, "RGM:", 20, 22, 40, 22);
        txtRgmNotas = addTextField(panelNF, 62, 20, 110, 25);

        lblNomeDinamico = new JLabel("← Digite o RGM e pressione Tab");
        lblNomeDinamico.setBounds(185, 20, 550, 25);
        lblNomeDinamico.setForeground(Color.GRAY);
        lblNomeDinamico.setFont(lblNomeDinamico.getFont().deriveFont(Font.ITALIC));
        panelNF.add(lblNomeDinamico);

        lblCursoDinamico = new JLabel("");
        lblCursoDinamico.setBounds(20, 52, 700, 22);
        lblCursoDinamico.setForeground(new Color(0, 100, 180));
        lblCursoDinamico.setFont(lblCursoDinamico.getFont().deriveFont(Font.BOLD));
        panelNF.add(lblCursoDinamico);

        JPanel pLanc = new JPanel(null);
        pLanc.setBorder(new TitledBorder("Lançamento"));
        pLanc.setBounds(10, 84, 740, 110);
        panelNF.add(pLanc);

        addLabel(pLanc, "Disciplina:", 10, 22, 75, 22);
        String[] disciplinas = {
            "Programação Orientada a Objetos",
            "Estrutura de Dados",
            "Banco de Dados",
            "Engenharia de Requisitos",
            "Algoritmos",
            "Redes de Computadores"
        };
        cbDisciplina = new JComboBox<>(disciplinas);
        cbDisciplina.setBounds(88, 20, 630, 25);
        pLanc.add(cbDisciplina);

        addLabel(pLanc, "Semestre:", 10, 62, 70, 22);
        String[] semestres = {"2023-1","2023-2","2024-1","2024-2","2025-1","2025-2","2026-1","2026-2"};
        cbSemestre = new JComboBox<>(semestres);
        cbSemestre.setSelectedItem("2026-1");
        cbSemestre.setBounds(82, 60, 120, 25);
        pLanc.add(cbSemestre);

        addLabel(pLanc, "Nota:", 215, 62, 40, 22);
        txtNota = addTextField(pLanc, 257, 60, 80, 25);

        addLabel(pLanc, "Faltas:", 355, 62, 48, 22);
        txtFaltas = addTextField(pLanc, 405, 60, 80, 25);

        // Aviso regra de faltas
        JLabel lblRegraFaltas = new JLabel(
            "\u26a0 Aluno com mais de " + MAX_FALTAS + " faltas será reprovado por falta, independente da nota.");
        lblRegraFaltas.setBounds(10, 200, 740, 20);
        lblRegraFaltas.setForeground(new Color(180, 80, 0));
        lblRegraFaltas.setFont(lblRegraFaltas.getFont().deriveFont(Font.ITALIC, 11f));
        panelNF.add(lblRegraFaltas);

        JSeparator sep3 = new JSeparator();
        sep3.setBounds(10, 225, 740, 2);
        panelNF.add(sep3);

        // Botões de texto – ABA 3
        JPanel painelBotoesNF = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 4));
        painelBotoesNF.setBounds(0, 230, 760, 40);
        btnSalvarNF    = new JButton("Salvar");
        btnAlterarNF   = new JButton("Alterar");
        btnConsultarNF = new JButton("Consultar");
        btnExcluirNF   = new JButton("Excluir");
        btnLimparNF    = new JButton("Limpar");
        btnSairNF      = new JButton("Sair");
        estilizarBotao(btnSalvarNF,    new Color(0, 153, 76));
        estilizarBotao(btnAlterarNF,   new Color(0, 102, 204));
        estilizarBotao(btnConsultarNF, new Color(102, 102, 0));
        estilizarBotao(btnExcluirNF,   new Color(204, 0, 0));
        estilizarBotao(btnLimparNF,    new Color(130, 130, 130));
        estilizarBotao(btnSairNF,      new Color(80, 80, 80));
        painelBotoesNF.add(btnSalvarNF);
        painelBotoesNF.add(btnAlterarNF);
        painelBotoesNF.add(btnConsultarNF);
        painelBotoesNF.add(btnExcluirNF);
        painelBotoesNF.add(btnLimparNF);
        painelBotoesNF.add(btnSairNF);
        panelNF.add(painelBotoesNF);

        // Botões de ícone – ABA 3
        JPanel painelIconesNF = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 4));
        painelIconesNF.setBounds(0, 273, 760, 75);
        btnIconSalvarNF    = criarBotaoIcone("Salvar",    ICO_SALVAR);
        btnIconAlterarNF   = criarBotaoIcone("Alterar",   ICO_ALTERAR);
        btnIconConsultarNF = criarBotaoIcone("Consultar", ICO_CONSULTAR);
        btnIconExcluirNF   = criarBotaoIcone("Excluir",   ICO_EXCLUIR);
        btnIconLimparNF    = criarBotaoIcone("Limpar",    ICO_LIMPAR);
        btnIconSairNF      = criarBotaoIcone("Sair",      ICO_SAIR);
        painelIconesNF.add(btnIconSalvarNF);
        painelIconesNF.add(btnIconAlterarNF);
        painelIconesNF.add(btnIconConsultarNF);
        painelIconesNF.add(btnIconExcluirNF);
        painelIconesNF.add(btnIconLimparNF);
        painelIconesNF.add(btnIconSairNF);
        panelNF.add(painelIconesNF);

        // FocusListener para carregar aluno ao sair do campo RGM
        txtRgmNotas.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String rgm = txtRgmNotas.getText().trim();
                if (rgm.isEmpty()) return;
                Aluno al = new AlunoDAO().consultar(rgm);
                if (al != null) {
                    lblNomeDinamico.setText("Aluno: " + al.getNome());
                    lblNomeDinamico.setForeground(Color.BLACK);
                    lblNomeDinamico.setFont(lblNomeDinamico.getFont().deriveFont(Font.BOLD));
                    lblCursoDinamico.setText("Curso: " + al.getCurso()
                        + "  |  Campus: " + al.getCampus()
                        + "  |  Período: " + al.getPeriodo());
                } else {
                    lblNomeDinamico.setText("\u26a0 Aluno não encontrado para este RGM.");
                    lblNomeDinamico.setForeground(Color.RED);
                    lblNomeDinamico.setFont(lblNomeDinamico.getFont().deriveFont(Font.ITALIC));
                    lblCursoDinamico.setText("");
                }
            }
        });

        //  ABA 4 – BOLETIM

        JPanel panelBoletim = new JPanel(null);
        tabbedPane.addTab("Boletim", null, panelBoletim, "Consulta de rendimento");

        JPanel pBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        pBusca.setBorder(new TitledBorder("Consultar Boletim"));
        pBusca.setBounds(10, 10, 740, 55);
        panelBoletim.add(pBusca);

        pBusca.add(new JLabel("RGM:"));
        txtRgmBoletimBusca = new JTextField(10);
        pBusca.add(txtRgmBoletimBusca);

        JButton btnGerarBoletim = new JButton("Gerar Boletim \u25b6");
        btnGerarBoletim.setBackground(new Color(0, 120, 215));
        btnGerarBoletim.setForeground(Color.WHITE);
        btnGerarBoletim.setFocusPainted(false);
        pBusca.add(btnGerarBoletim);

        JButton btnLimparBoletim = new JButton("Limpar");
        pBusca.add(btnLimparBoletim);

        lblNomeBoletim = new JLabel("Nome do Aluno: ");
        lblNomeBoletim.setBounds(15, 72, 740, 20);
        lblNomeBoletim.setFont(new Font("Tahoma", Font.BOLD, 12));
        panelBoletim.add(lblNomeBoletim);

        lblCursoBoletim = new JLabel("Curso: ");
        lblCursoBoletim.setBounds(15, 95, 740, 20);
        lblCursoBoletim.setFont(new Font("Tahoma", Font.BOLD, 12));
        panelBoletim.add(lblCursoBoletim);

        lblMediaBoletim = new JLabel("");
        lblMediaBoletim.setBounds(15, 118, 700, 20);
        lblMediaBoletim.setFont(new Font("Tahoma", Font.BOLD, 12));
        panelBoletim.add(lblMediaBoletim);

        JLabel lblLegendaFaltas = new JLabel(
            "\u26a0 Reprovado por Falta: mais de " + MAX_FALTAS + " faltas, independente da nota.");
        lblLegendaFaltas.setBounds(15, 138, 740, 18);
        lblLegendaFaltas.setFont(new Font("Tahoma", Font.ITALIC, 11));
        lblLegendaFaltas.setForeground(new Color(180, 80, 0));
        panelBoletim.add(lblLegendaFaltas);

        String[] colunas = {"Disciplina", "Semestre", "Nota", "Faltas", "Situação"};
        modelTabela = new javax.swing.table.DefaultTableModel(null, colunas) {
            private static final long serialVersionUID = 1L;
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabelaBoletim = new javax.swing.JTable(modelTabela);
        tabelaBoletim.setRowHeight(22);
        tabelaBoletim.getTableHeader().setReorderingAllowed(false);

        tabelaBoletim.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    String sit = (String) table.getModel().getValueAt(row, 4);
                    setBackground("Aprovado".equals(sit)
                        ? new Color(220, 255, 220)
                        : new Color(255, 220, 220));
                }
                return this;
            }
        });

        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(tabelaBoletim);
        scroll.setBounds(10, 160, 740, 255);
        panelBoletim.add(scroll);

        btnGerarBoletim.addActionListener(e -> gerarBoletim());
        txtRgmBoletimBusca.addActionListener(e -> gerarBoletim());
        btnLimparBoletim.addActionListener(e -> {
            txtRgmBoletimBusca.setText("");
            lblNomeBoletim.setText("Nome do Aluno: ");
            lblCursoBoletim.setText("Curso: ");
            lblMediaBoletim.setText("");
            modelTabela.setRowCount(0);
        });

        //  WIRING — botões de texto
        btnSalvarDP.addActionListener(e    -> executarSalvar());
        btnSalvarCurso.addActionListener(e -> executarSalvar());
        btnSalvarNF.addActionListener(e    -> executarSalvarNota());

        btnAlterarDP.addActionListener(e    -> executarAlterar());
        btnAlterarCurso.addActionListener(e -> executarAlterar());
        btnAlterarNF.addActionListener(e    -> executarAlterar());

        btnConsultarDP.addActionListener(e    -> executarConsultar());
        btnConsultarCurso.addActionListener(e -> executarConsultar());
        btnConsultarNF.addActionListener(e    -> executarConsultar());

        btnExcluirDP.addActionListener(e    -> executarExcluir());
        btnExcluirCurso.addActionListener(e -> executarExcluir());
        btnExcluirNF.addActionListener(e    -> executarExcluir());

        btnLimparDP.addActionListener(e    -> limparCampos());
        btnLimparCurso.addActionListener(e -> limparCampos());
        btnLimparNF.addActionListener(e    -> limparCampos());

        btnSairDP.addActionListener(e    -> System.exit(0));
        btnSairCurso.addActionListener(e -> System.exit(0));
        btnSairNF.addActionListener(e    -> System.exit(0));

        // botões de ícone ABA 1 
        btnIconSalvarDP.addActionListener(e    -> executarSalvar());
        btnIconAlterarDP.addActionListener(e   -> executarAlterar());
        btnIconConsultarDP.addActionListener(e -> executarConsultar());
        btnIconExcluirDP.addActionListener(e   -> executarExcluir());
        btnIconLimparDP.addActionListener(e    -> limparCampos());
        btnIconSairDP.addActionListener(e      -> System.exit(0));

        // botões de ícone ABA 2
        btnIconSalvarCurso.addActionListener(e    -> executarSalvar());
        btnIconAlterarCurso.addActionListener(e   -> executarAlterar());
        btnIconConsultarCurso.addActionListener(e -> executarConsultar());
        btnIconExcluirCurso.addActionListener(e   -> executarExcluir());
        btnIconLimparCurso.addActionListener(e    -> limparCampos());
        btnIconSairCurso.addActionListener(e      -> System.exit(0));

        // botões de ícone ABA 3
        btnIconSalvarNF.addActionListener(e    -> executarSalvarNota());
        btnIconAlterarNF.addActionListener(e   -> executarAlterar());
        btnIconConsultarNF.addActionListener(e -> executarConsultar());
        btnIconExcluirNF.addActionListener(e   -> executarExcluir());
        btnIconLimparNF.addActionListener(e    -> limparCampos());
        btnIconSairNF.addActionListener(e      -> System.exit(0));

        // menu 
        mntmSalvar.addActionListener(e    -> executarSalvar());
        mntmAlterar.addActionListener(e   -> executarAlterar());
        mntmConsultar.addActionListener(e -> executarConsultar());
        mntmExcluir.addActionListener(e   -> executarExcluir());
        mntmSair.addActionListener(e      -> System.exit(0));

        mntmSalvarNf.addActionListener(e    -> executarSalvarNota());
        mntmAlterarNf.addActionListener(e   -> JOptionPane.showMessageDialog(null,
            "Selecione o registro na tabela do Boletim para alterar."));
        mntmExcluirNf.addActionListener(e   -> JOptionPane.showMessageDialog(null,
            "Selecione o registro na tabela do Boletim para excluir."));
        mntmConsultarNf.addActionListener(e -> gerarBoletim());

        mntmSobre.addActionListener(e -> JOptionPane.showMessageDialog(null,
            "Sistema Acadêmico – UNICID\nVersão 1.0\nDesenvolvido para avaliação de POO.",
            "Sobre", JOptionPane.INFORMATION_MESSAGE));
    }

    
    private JButton criarBotaoIcone(String tooltip, String caminhoIcone) {
        JButton btn = new JButton();
        btn.setToolTipText(tooltip);
        btn.setPreferredSize(new Dimension(100, 60));
        btn.setFocusPainted(false);

        java.net.URL urlIcone = getClass().getResource(caminhoIcone);
        if (urlIcone != null) {
            java.awt.Image img = new ImageIcon(urlIcone)
                .getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(img));
        } else {
            // Se não achar a imagem, mostra o texto
            btn.setText(tooltip);
            btn.setFont(btn.getFont().deriveFont(Font.BOLD, 10f));
        }
        return btn;
    }

    //  ESTILO DOS BOTÕES DE TEXTO
    private void estilizarBotao(JButton btn, Color cor) {
        btn.setPreferredSize(new Dimension(100, 28));
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(btn.getFont().deriveFont(Font.BOLD));
    }

    //  MONTA OBJETO ALUNO A PARTIR DOS CAMPOS
    private Aluno montarAlunoDosCampos() {
        Aluno a = new Aluno();
        a.setRgm(txtRgm.getText().trim());
        a.setNome(txtNome.getText().trim());
        a.setDataNascimento(txtDataNascimento.getText().trim());
        a.setCpf(txtCpf.getText().trim());
        a.setEmail(txtEmail.getText().trim());
        a.setEndereco(txtEndereco.getText().trim());
        a.setMunicipio(txtMunicipio.getText().trim());
        a.setUf(cbUf.getSelectedItem().toString());
        a.setCelular(txtCelular.getText().trim());
        a.setCurso(cbCurso.getSelectedItem().toString());
        a.setCampus(cbCampus.getSelectedItem().toString());
        if (rbMatutino.isSelected())        a.setPeriodo("Matutino");
        else if (rbVespertino.isSelected()) a.setPeriodo("Vespertino");
        else                                a.setPeriodo("Noturno");
        return a;
    }

    //  AÇÕES CRUD
    private void executarSalvar() {
        Aluno aluno = montarAlunoDosCampos();
        if (aluno.getRgm().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo RGM é obrigatório.", "Validação", JOptionPane.WARNING_MESSAGE);
            txtRgm.requestFocus();
            return;
        }
        if (aluno.getNome().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo Nome é obrigatório.", "Validação", JOptionPane.WARNING_MESSAGE);
            txtNome.requestFocus();
            return;
        }
        // Impede RGM duplicado
        if (new AlunoDAO().consultar(aluno.getRgm()) != null) {
            JOptionPane.showMessageDialog(this,
                "Já existe um aluno cadastrado com o RGM: " + aluno.getRgm()
                + "\nUse o botão 'Alterar' para modificar os dados.",
                "RGM Duplicado", JOptionPane.ERROR_MESSAGE);
            txtRgm.requestFocus();
            return;
        }
        new AlunoDAO().salvar(aluno);
    }

    private void executarSalvarNota() {
        String rgm        = txtRgmNotas.getText().trim();
        String disciplina = cbDisciplina.getSelectedItem().toString();
        String semestre   = cbSemestre.getSelectedItem().toString();
        String strNota    = txtNota.getText().trim();
        String strFaltas  = txtFaltas.getText().trim();

        if (rgm.isEmpty() || strNota.isEmpty() || strFaltas.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Preencha o RGM, a Nota e as Faltas antes de salvar.",
                "Validação", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            double nota   = Double.parseDouble(strNota.replace(",", "."));
            int    faltas = Integer.parseInt(strFaltas);

            if (nota < 0 || nota > 10) {
                JOptionPane.showMessageDialog(this,
                    "A nota deve estar entre 0 e 10.", "Validação", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (faltas < 0) {
                JOptionPane.showMessageDialog(this,
                    "O número de faltas não pode ser negativo.", "Validação", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (faltas > MAX_FALTAS) {
                JOptionPane.showMessageDialog(this,
                    "Atenção: o aluno possui " + faltas + " faltas, acima do limite de "
                    + MAX_FALTAS + ".\nSerá registrado como REPROVADO POR FALTA, "
                    + "independente da nota informada.",
                    "Reprovação por Falta", JOptionPane.WARNING_MESSAGE);
            }

            
            List<NotaFalta> existentes = new NotaFaltaDAO().listarNotasPorRgm(rgm);
            for (NotaFalta nfExist : existentes) {
                if (nfExist.getDisciplina().equals(disciplina)
                        && nfExist.getSemestre().equals(semestre)) {
                    JOptionPane.showMessageDialog(this,
                        "Já existe um lançamento para:\n"
                        + "  Disciplina: " + disciplina + "\n"
                        + "  Semestre:   " + semestre + "\n\n"
                        + "Use o botão 'Alterar' para modificar o registro existente.",
                        "Lançamento Duplicado", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            new NotaFaltaDAO().salvar(new NotaFalta(rgm, disciplina, semestre, nota, faltas));
            txtNota.setText("");
            txtFaltas.setText("");
            JOptionPane.showMessageDialog(this, "Nota salva com sucesso.", "OK", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Os campos Nota e Faltas devem ser numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void executarConsultar() {
        String rgmBusca = JOptionPane.showInputDialog(this, "Informe o RGM para consultar:");
        if (rgmBusca == null || rgmBusca.trim().isEmpty()) return;
        Aluno al = new AlunoDAO().consultar(rgmBusca.trim());
        if (al != null) {
            preencherCamposComAluno(al);
            JOptionPane.showMessageDialog(this, "Dados do aluno carregados com sucesso.");
        } else {
            JOptionPane.showMessageDialog(this,
                "Nenhum aluno encontrado com o RGM: " + rgmBusca.trim(),
                "Não Encontrado", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void executarAlterar() {
        Aluno aluno = montarAlunoDosCampos();
        if (aluno.getRgm().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Consulte o aluno primeiro antes de alterar.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int ok = JOptionPane.showConfirmDialog(this,
            "Confirma a alteração dos dados do aluno " + aluno.getNome() + "?",
            "Confirmar Alteração", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            new AlunoDAO().alterar(aluno);
        }
    }

    private void executarExcluir() {
        String rgm = txtRgm.getText().trim();
        if (rgm.isEmpty()) {
            rgm = JOptionPane.showInputDialog(this, "Informe o RGM do aluno para excluir:");
            if (rgm == null || rgm.trim().isEmpty()) return;
        }
        final String rgmFinal = rgm.trim();
        int ok = JOptionPane.showConfirmDialog(this,
            "ATENÇÃO: Isso excluirá permanentemente o aluno e todas as suas notas!\n"
            + "Deseja continuar com a exclusão do RGM: " + rgmFinal + "?",
            "Confirmar Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (ok == JOptionPane.YES_OPTION) {
            new AlunoDAO().excluir(rgmFinal);
            limparCampos();
        }
    }

   
    private void gerarBoletim() {
        String rgm = txtRgmBoletimBusca.getText().trim();
        if (rgm.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Informe o RGM para gerar o boletim.", "Atenção", JOptionPane.WARNING_MESSAGE);
            txtRgmBoletimBusca.requestFocus();
            return;
        }
        Aluno al = new AlunoDAO().consultar(rgm);
        if (al == null) {
            JOptionPane.showMessageDialog(this,
                "Nenhum aluno localizado para o RGM: " + rgm,
                "Não Encontrado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        lblNomeBoletim.setText("Nome do Aluno: " + al.getNome()
            + "   |   RGM: " + al.getRgm() + "   |   CPF: " + al.getCpf());
        lblCursoBoletim.setText("Curso: " + al.getCurso()
            + "   |   Campus: " + al.getCampus() + "   |   Período: " + al.getPeriodo());

        modelTabela.setRowCount(0);
        List<NotaFalta> lista = new NotaFaltaDAO().listarNotasPorRgm(rgm);
        if (lista.isEmpty()) {
            lblMediaBoletim.setText("Nenhuma nota lançada para este aluno.");
            lblMediaBoletim.setForeground(Color.GRAY);
            return;
        }

        double soma = 0;
        int aprovados = 0;
        for (NotaFalta nf : lista) {
          
            String situacao;
            if (nf.getFaltas() > MAX_FALTAS) {
                situacao = "Reprovado";
            } else if (nf.getNota() >= 6.0) {
                situacao = "Aprovado";
            } else {
                situacao = "Reprovado";
            }

            if ("Aprovado".equals(situacao)) aprovados++;
            soma += nf.getNota();
            modelTabela.addRow(new Object[]{
                nf.getDisciplina(), nf.getSemestre(),
                String.format("%.1f", nf.getNota()),
                nf.getFaltas(),
                situacao
            });
        }

        double media = soma / lista.size();
        lblMediaBoletim.setText(String.format(
            "Média Geral: %.2f  |  Aprovado em %d de %d disciplina(s)  |  (Limite de faltas: %d)",
            media, aprovados, lista.size(), MAX_FALTAS));
        lblMediaBoletim.setForeground(media >= 6.0 ? new Color(0, 120, 0) : Color.RED);
    }

    //  PREENCHER CAMPOS COM DADOS DO ALUNO CONSULTADO
    private void preencherCamposComAluno(Aluno al) {
        txtRgm.setText(al.getRgm());
        txtNome.setText(al.getNome());
        txtDataNascimento.setText(al.getDataNascimento());
        txtCpf.setText(al.getCpf());
        txtEmail.setText(al.getEmail());
        txtEndereco.setText(al.getEndereco());
        txtMunicipio.setText(al.getMunicipio());
        cbUf.setSelectedItem(al.getUf());
        txtCelular.setText(al.getCelular());
        cbCurso.setSelectedItem(al.getCurso());
        cbCampus.setSelectedItem(al.getCampus());
        switch (al.getPeriodo() == null ? "" : al.getPeriodo()) {
            case "Matutino":   rbMatutino.setSelected(true);   break;
            case "Vespertino": rbVespertino.setSelected(true); break;
            default:           rbNoturno.setSelected(true);    break;
        }

        lblNomeBoletim.setText("Nome do Aluno: " + al.getNome());
        lblCursoBoletim.setText("Curso: " + al.getCurso() + "  (" + al.getPeriodo() + ")");
        modelTabela.setRowCount(0);

        List<NotaFalta> lista = new NotaFaltaDAO().listarNotasPorRgm(al.getRgm());
        for (NotaFalta nf : lista) {
            String sit;
            if (nf.getFaltas() > MAX_FALTAS) {
                sit = "Reprovado";
            } else if (nf.getNota() >= 6.0) {
                sit = "Aprovado";
            } else {
                sit = "Reprovado";
            }
            modelTabela.addRow(new Object[]{
                nf.getDisciplina(), nf.getSemestre(),
                String.format("%.1f", nf.getNota()), nf.getFaltas(), sit
            });
        }
    }

    //  LIMPAR TODOS OS CAMPOS
    private void limparCampos() {
        txtRgm.setText("");
        txtNome.setText("");
        try {
            txtDataNascimento.setValue(null);
            txtCpf.setValue(null);
            txtCelular.setValue(null);
        } catch (Exception ignored) {
            txtDataNascimento.setText("");
            txtCpf.setText("");
            txtCelular.setText("");
        }
        txtEmail.setText("");
        txtEndereco.setText("");
        txtMunicipio.setText("");
        cbUf.setSelectedIndex(0);
        cbCurso.setSelectedIndex(0);
        cbCampus.setSelectedIndex(0);
        rbNoturno.setSelected(true);

        txtRgmNotas.setText("");
        txtNota.setText("");
        txtFaltas.setText("");
        lblNomeDinamico.setText("\u2190 Digite o RGM e pressione Tab");
        lblNomeDinamico.setForeground(Color.GRAY);
        lblNomeDinamico.setFont(lblNomeDinamico.getFont().deriveFont(Font.ITALIC));
        lblCursoDinamico.setText("");

        lblNomeBoletim.setText("Nome do Aluno: ");
        lblCursoBoletim.setText("Curso: ");
        lblMediaBoletim.setText("");
        modelTabela.setRowCount(0);
    }

    //  UTILITÁRIOS DE LAYOUT
    private void addLabel(JPanel p, String texto, int x, int y, int w, int h) {
        JLabel lbl = new JLabel(texto);
        lbl.setBounds(x, y, w, h);
        p.add(lbl);
    }

    private JTextField addTextField(JPanel p, int x, int y, int w, int h) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, w, h);
        p.add(tf);
        return tf;
    }
}

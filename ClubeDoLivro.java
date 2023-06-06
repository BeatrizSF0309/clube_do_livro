import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ClubeDoLivro extends JFrame {

   

    private JTextField campoNomeLeitor, campoNomeLivro, campoAutorLivro;
    private JComboBox<String> comboBoxGenero;
    private JTextArea areaLivrosLidos;
    private JButton botaoCadastrarLeitor;
    private JButton botaoCadastrarLivro, botaoRegistrarLeitura;

    private String[] generos = { "Ficção", "Romance", "Aventura", "Suspense", "Não-ficção" };

    private String[] leitores = new String[100];
    private String[][] livros = new String[100][3];
    private int contadorLeitores = 0;
    private int contadorLivros = 0;

    public ClubeDoLivro() {
        super("ClubeDoLivro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(new Color(208, 203, 205)); // Definindo a cor de fundo usando RGB

        JPanel painelCadastro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel labelNomeLeitor = new JLabel("Nome do Leitor:");
        campoNomeLeitor = new JTextField();
        campoNomeLeitor.setForeground(Color.black);
        campoNomeLeitor.setPreferredSize(new Dimension(200, campoNomeLeitor.getPreferredSize().height)); // Definindo o tamanho
                                                                                                         

        JLabel labelGenero = new JLabel("Gênero do Livro:");
        comboBoxGenero = new JComboBox<>(generos);
        comboBoxGenero.setPreferredSize(new Dimension(200, comboBoxGenero.getPreferredSize().height)); // Definindo o tamanho
                                                                                                       

        JLabel labelNomeLivro = new JLabel("Nome do Livro:");
        campoNomeLivro = new JTextField();
        campoNomeLivro.setPreferredSize(new Dimension(200, campoNomeLivro.getPreferredSize().height)); // Definindo o tamanho
                                                                                                       
                                                                                                    
        JLabel labelAutorLivro = new JLabel("Autor do Livro:");
        campoAutorLivro = new JTextField();
        campoAutorLivro.setPreferredSize(new Dimension(200, campoAutorLivro.getPreferredSize().height)); // Definindo o tamanho
                                                                                                                                                                                                               
        botaoCadastrarLeitor = new JButton("Cadastrar Leitor");
        botaoCadastrarLeitor.setForeground(Color.black);
        botaoCadastrarLeitor.setBackground(new Color(208, 167, 235));
        botaoCadastrarLivro = new JButton("Cadastrar Livro");
        botaoCadastrarLivro.setForeground(Color.black);
        botaoCadastrarLivro.setBackground(new Color(208, 167, 235));
        botaoRegistrarLeitura = new JButton("Registrar Leitura");
        botaoRegistrarLeitura.setForeground(Color.white);
        botaoRegistrarLeitura.setBackground(new Color(0, 0, 0));


 // gbc é uma instância da classe gridbagconstraints, geralmente é usada para configurar o layout, 
 //essa classe gbc define os parâmetros que
  //controla a posição e o comportamento dos componentes da tela 
  // eu utilizei aqui para deixar a tela responsivo. É bem generico o uso dessa instancia, mas eu so consegui deixar
  // responsivo dessa forma.

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelCadastro.add(labelNomeLeitor, gbc);
        gbc.gridx = 1;
        painelCadastro.add(campoNomeLeitor, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelCadastro.add(labelGenero, gbc);
        gbc.gridx = 1;
        painelCadastro.add(comboBoxGenero, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelCadastro.add(labelNomeLivro, gbc);
        gbc.gridx = 1;
        painelCadastro.add(campoNomeLivro, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelCadastro.add(labelAutorLivro, gbc);
        gbc.gridx = 1;
        painelCadastro.add(campoAutorLivro, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        painelCadastro.add(botaoCadastrarLeitor, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        painelCadastro.add(botaoCadastrarLivro, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        painelCadastro.add(botaoRegistrarLeitura, gbc);

        areaLivrosLidos = new JTextArea();
        areaLivrosLidos.setEditable(false);

        painelPrincipal.add(painelCadastro, BorderLayout.NORTH);
        painelPrincipal.add(new JScrollPane(areaLivrosLidos), BorderLayout.CENTER);

        getContentPane().add(painelPrincipal);

        botaoCadastrarLeitor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarLeitor();
            }
        });

        botaoCadastrarLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
            }
        });

        botaoRegistrarLeitura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarLeitura();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cadastrarLeitor() {
        String nomeLeitor = campoNomeLeitor.getText();
        if (!nomeLeitor.isEmpty()) {
            leitores[contadorLeitores] = nomeLeitor;
            contadorLeitores++;
            JOptionPane.showMessageDialog(this, "Leitor cadastrado com sucesso!");
            campoNomeLeitor.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, informe o nome do leitor.");
        }
    }

    private void cadastrarLivro() {
        String nomeLivro = campoNomeLivro.getText();
        String autorLivro = campoAutorLivro.getText();
        String generoLivro = (String) comboBoxGenero.getSelectedItem();
        if (!nomeLivro.isEmpty() && !autorLivro.isEmpty()) {
            livros[contadorLivros][0] = nomeLivro;
            livros[contadorLivros][1] = autorLivro;
            livros[contadorLivros][2] = generoLivro;
            contadorLivros++;
            JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
            campoNomeLivro.setText("");
            campoAutorLivro.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos do livro.");
        }
    }

    private void registrarLeitura() {
        if (contadorLeitores == 0) {
            JOptionPane.showMessageDialog(this, "Não há leitores cadastrados.");
            return;
        }

        if (contadorLivros == 0) {
            JOptionPane.showMessageDialog(this, "Não há livros cadastrados.");
            return;
        }

        String nomeLeitor = (String) JOptionPane.showInputDialog(this, "Selecione o leitor:", "Registrar Leitura",
                JOptionPane.QUESTION_MESSAGE, null, leitores, leitores[0]);

        if (nomeLeitor != null) {
            StringBuilder listaLivros = new StringBuilder();
            for (int i = 0; i < contadorLivros; i++) {
                if (livros[i][0] != null) {
                    listaLivros.append("- ").append(livros[i][0]).append(" (").append(livros[i][1]).append(")\n");
                }
            }

            String livroLido = (String) JOptionPane.showInputDialog(this, "Selecione o livro lido:",
                    "Registrar Leitura",
                    JOptionPane.QUESTION_MESSAGE, null, listaLivros.toString().split("\n"),
                    listaLivros.toString().split("\n")[0]);

            if (livroLido != null) {
                areaLivrosLidos.append("Leitor: " + nomeLeitor + "\n");
                areaLivrosLidos.append("Livro lido: " + livroLido + "\n");
                areaLivrosLidos.append("\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClubeDoLivro();
            }
        });
    }
}
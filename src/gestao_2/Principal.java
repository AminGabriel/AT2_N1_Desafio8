package gestao_2;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) {
        // Array principal
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuarioAtual = null;

        // Declaração de opções para o menu
        Object[] opcoes = {"Passar dias", "Criar usuário", "Entrar como usuário", "Sair"};
        Object opcao;

        do {
            opcao = JOptionPane.showInputDialog(null, "Escolha a opção:", "MENU PRINCIPAL", JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

            if (opcao == "Passar dias") {
            	if (usuarios.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum usuário existente.");
                } else {
                    int dias = Usuario.solicitarNumeroPositivo("Informe o tempo decorrido (em dias):");
                    if (dias > 0) {
                        for (Usuario usuario : usuarios) {
                            usuario.passarDias(dias);
                        }
                        JOptionPane.showMessageDialog(null, "Dias passados com sucesso para todos os usuários!");
                    }
                }
            } else if (opcao == "Criar usuário") {
                String nome = JOptionPane.showInputDialog("Informe o seu nome:");
                String departamento = JOptionPane.showInputDialog("Informe o seu departamento:");
                
                if ((nome == null || nome.trim().isEmpty()) && (departamento == null || departamento.trim().isEmpty())) {
                    Usuario novoUsuario = new Usuario();
                    usuarios.add(novoUsuario);
                } else if ((nome == null || nome.trim().isEmpty()) && (departamento != null)) {
                    Usuario novoUsuario = new Usuario(true, departamento);
                    usuarios.add(novoUsuario);
                } else if ((departamento == null || departamento.trim().isEmpty()) && (nome != null)) {
                    Usuario novoUsuario = new Usuario(nome, true);
                    usuarios.add(novoUsuario);
                } else if (nome != null && departamento != null) {
                    Usuario novoUsuario = new Usuario(nome, departamento);
                    usuarios.add(novoUsuario);
                }

                JOptionPane.showMessageDialog(null, "Usuário criado com sucesso!");
            } else if (opcao == "Entrar como usuário") {
                if (usuarios.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum usuário disponível. Crie um primeiro.");
                } else {
                    usuarioAtual = Usuario.selecionarUsuario(usuarios);
                    if (usuarioAtual != null) {
                        usuarioAtual.menuUsuario();
                    }
                }
            } else if (opcao == "Sair") {
                JOptionPane.showMessageDialog(null, "Tchau");
            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        } while (opcao != "Sair");

        System.exit(0);
    }
}

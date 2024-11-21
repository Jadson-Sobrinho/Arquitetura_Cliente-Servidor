import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int porta = 12345;
        Gerenciador gerenciador = new Gerenciador();

        try (ServerSocket server = new ServerSocket(porta)) {
            System.out.println("Servidor aguardando conexão na porta: " + porta);

            // Aceitar conexão do cliente
            Socket cliente = server.accept();
            System.out.println("Cliente conectado: " + cliente.getInetAddress());

            // Fluxos de entrada e saída
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);

            String mensagem;
            while ((mensagem = entrada.readLine()) != null) {
                System.out.println("Mensagem do Cliente: " + mensagem);

                // Processar comandos recebidos do cliente
                String resposta = processarComando(mensagem, gerenciador);
                saida.println(resposta);

                // Encerrar se o cliente enviar "Sair"
                if (mensagem.equalsIgnoreCase("Sair")) {
                    break;
                }
            }

            cliente.close();
            System.out.println("Conexão encerrada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String processarComando(String mensagem, Gerenciador gerenciador) {
        String[] partes = mensagem.split(" ");
        String comando = partes[0];

        switch (comando.toUpperCase()) {
            case "ADD": // Comando: ADD ID NOME IDADE EMAIL
                try {
                    int id = Integer.parseInt(partes[1]);
                    String nome = partes[2];
                    int idade = Integer.parseInt(partes[3]);
                    String email = partes[4];
                    gerenciador.adicionarOuAtualizarDados(id, new Dados(nome, idade, email));
                    return "Dados adicionados/atualizados para ID: " + id;
                } catch (Exception e) {
                    return "Erro no comando ADD. Formato esperado: ADD ID NOME IDADE EMAIL";
                }

            case "GET": // Comando: GET ID
                try {
                    int id = Integer.parseInt(partes[1]);
                    Dados dados = gerenciador.consultarDados(id);
                    return (dados != null) ? "Dados para ID " + id + ": " + dados : "Nenhum dado encontrado para ID: " + id;
                } catch (Exception e) {
                    return "Erro no comando GET. Formato esperado: GET ID";
                }

            case "DEL": // Comando: DEL ID
                try {
                    int id = Integer.parseInt(partes[1]);
                    gerenciador.removerDados(id);
                    return "Dados removidos para ID: " + id;
                } catch (Exception e) {
                    return "Erro no comando DEL. Formato esperado: DEL ID";
                }
            default:
                return "Comando inválido. Comandos disponíveis: ADD, GET, DEL, SAIR.";
        }
    }
}

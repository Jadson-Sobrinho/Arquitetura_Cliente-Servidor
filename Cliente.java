import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        String servidorIP = "127.0.0.1"; // IP do servidor
        int porta = 12345; // Porta do servidor

        try (Socket socket = new Socket(servidorIP, porta)) {
            System.out.println("Conectado ao servidor.");

            // Criar fluxos de entrada e saída
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Comunicação com o servidor
            String mensagem;
            while (true) {
                System.out.print("Comandos disponíveis: ADD, GET, DEL, SAIR: ");
                mensagem = teclado.readLine();
                saida.println(mensagem);
                if (mensagem.equalsIgnoreCase("sair")) {
                    break;
                }
                System.out.println("Mensagem do Servidor: " + entrada.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

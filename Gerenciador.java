import java.util.HashMap;
import java.util.Map;

public class Gerenciador {
    private Map<Integer, Dados> mapaDados;

    // Construtor
    public Gerenciador() {
        this.mapaDados = new HashMap<>();
    }

    // Adicionar ou atualizar dados associados a um ID
    public void adicionarOuAtualizarDados(int id, Dados dados) {
        mapaDados.put(id, dados);
        System.out.println("Dados adicionados/atualizados para ID: " + id);
    }

    // Consultar dados por ID
    public Dados consultarDados(int id) {
        return mapaDados.get(id);
    }

    // Remover dados por ID
    public void removerDados(int id) {
        if (mapaDados.remove(id) != null) {
            System.out.println("Dados removidos para ID: " + id);
        } else {
            System.out.println("Nenhum dado encontrado para ID: " + id);
        }
    }

    // Exibir todos os dados
    public void exibirTodosDados() {
        System.out.println("Dados armazenados:");
        for (Map.Entry<Integer, Dados> entry : mapaDados.entrySet()) {
            System.out.println("ID: " + entry.getKey() + " -> " + entry.getValue());
        }
    }
}

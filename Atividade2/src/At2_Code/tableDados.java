package At2_Code;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TheDe
 */
//Tabela modelo para captação dos dados do banco de dados SQL
public class tableDados {

    //Criando a lista dos dados
    private static final List<inserirDados> lista = new ArrayList<>();
    //Definindo o titulo e a quantidade das colunas 
    private String[] colunas = {"id", "Titulo", "Data lan.", "Categoria"};

    public tableDados() {
    }

    //Conta o numero de colunas
    public int getColumnCount() {
        return colunas.length;
    }

    //lista os dados para a tabela
    public static List<inserirDados> listar() {
        return lista;
    }

    //conta o numero de linhas
    public static int getRowCount() {
        return lista.size();
    }

    //adiciona nova linha
    public static void addRow(inserirDados reg) {
        lista.add(reg);
    }

    //remove uma linha
    public static void RemoveRow(int r) {
        lista.remove(r);
    }

    //capita o valor de uma variavel com base na linha e coluna em que ela esta
    public Object getValueAt(int linha, int coluna) {
        if (linha >= 0 && linha < getRowCount() && coluna >= 0 && coluna < getColumnCount()) {
            inserirDados ida = lista.get(linha);

            switch (coluna) {
                case 0:
                    return ida.getId();
                case 1:
                    return ida.getTitulo();
                case 2:
                    return ida.getDataLancamento();
                case 3:
                    return ida.getCategoria();

                default:
                    throw new IndexOutOfBoundsException("Coluna inválida");
            }
        } else {
            throw new IndexOutOfBoundsException("Linha ou coluna inválida");
        }
    }

    //Altera o valor de uma variavel com base na linha e coluna em que ela esta, aplica a variavel o valor inserido pela variavel "value"
    public void setValueAt(Object value, int linha, int coluna) {
        if (linha >= 0 && linha < getRowCount() && coluna >= 0 && coluna < getColumnCount()) {
            inserirDados ida = lista.get(linha);

            switch (coluna) {
                case 0:
                    ida.setId((int) value);
                    break;
                case 1:
                    ida.setTitulo((String) value);
                    break;
                case 2:
                    ida.setDataLancamento((String) value);
                    break;
                case 3:
                    ida.setCategoria((String) value);
                    break;
                default:
                    throw new IndexOutOfBoundsException("Coluna inválida");
            }
        } else {
            throw new IndexOutOfBoundsException("Linha ou coluna inválida");
        }
    }
}

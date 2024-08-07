package At2_Code;

import At2_Viewn.editarFilmes;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;

/**
 *
 * @author TheDe
 */
//Classe para realizar as relações entre o codigo e o banco de dados
public class Conexao {

    public Statement stmt = null;
    public Connection conn = null;
    public String url = "jdbc:mysql://localhost:3306/cenaflix";
    public String user = "root";
    public String password = "Gaukken__1610";

    //Conectando-se ao banco de dados
    public boolean conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Falha na conexão com o banco de dados: " + ex.getMessage());
            return false;
        }
    }

    //Desconectando-se do banco de dados
    public void desconectar() {
        try {
            System.out.println("\nConexão finalizada");
            conn.close();
        } catch (SQLException ex) {
        }
    }

    //Exibe no console todos os dados presentes no banco de dados, para que ao testar o programa perceba o mecanismo de captação de dados!
    public void exibirDados() throws ParseException {
        try {
            //Chamamos o metodo conectar como garantia de que o banco de dados esteja ativo(não é obrigatorio pois ele´ja é chamado ao iniciar o sistema, porem evita possiveis falhas)!
            conectar();
            //Criação do Statement para execução de comandos no Banco de Dados.
            Statement st = conn.createStatement();
            //Definir o comando que deseja usar
            String comand = "SELECT * FROM filmes";
            ResultSet rs = st.executeQuery(comand);
            //Separando as variaveis que retornaram da pesquisa
            int id;
            String titulo;
            String dateToStringFormated = "  /  /    ";
            String categoria;
            //Executando o comando junto da separação das variaveis  em lista
            while (rs.next()) {
                id = rs.getInt("id");
                titulo = rs.getString("nome");
                Date datalancamento = rs.getDate("datalancamento");
                //adaptando a variavel "datalancamento" para o formato String
                String dateToString = datalancamento.toString();
                //Retirando todos os valores diferentes de numeros desta variavel
                String dateToStringE = dateToString.replaceAll("[^0-9]", "");
                //Formatando a variavel para o formato dd/mm/aaaa
                if (dateToStringE.length() == 8) {
                    String a = dateToStringE.substring(6) + "/";
                    String b = dateToStringE.substring(4, 6) + "/";
                    String c = dateToStringE.substring(0, 4);
                    dateToStringFormated = a + b + c;
                }
                //seguindo a separação destas variaveis
                categoria = rs.getString("categoria");
                //Exibindo esses dados classificados em hordem no console
                System.out.println("\nID: " + id + "\nNome: " + titulo + "\nData:" + dateToStringFormated + "\nCategoria: " + categoria);

                //Aplicando estes dados a tabela modelo que usaremos para pesquisa e edição destes dados
                if (0 == id || "".equals(titulo) || "".equals(categoria) || "".equals(dateToStringFormated)) {
                    System.out.println("Problema com os dados de inserção");
                } else {
                    inserirDados isert = new inserirDados();

                    isert.setId(id);
                    isert.setTitulo(titulo);
                    isert.setDataLancamento(dateToStringFormated);
                    isert.setCategoria(categoria);

                    tableDados lista = new tableDados();
                    lista.addRow(isert);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Falha na conexão com o banco de dados: " + ex.getMessage());
        }
    }

    //Metodo para exclusão das linhas, consta com a aplicação do id no banco de dados Sql(idRow) e com o id da linha da tabela modelo(linha)
    public void deletarLinha(int idRow, int linha) {
        try {
            //Chamamos o metodo conectar como garantia de que o banco de dados esteja ativo(não é obrigatorio pois ele´ja é chamado ao iniciar o sistema, porem evita possiveis falhas)!
            conectar();
            //Criando um PreparedStatement para execução do comando no Banco de Dados SQL, e definindo um valo ? que sera o id da linha a ser excluida
            PreparedStatement st = conn.prepareStatement("DELETE FROM filmes WHERE id = ?");
            st.setInt(1, idRow);
            try {
                //Execução do comando de forma a contar quantas vezes ele foi executado (ele sempre sera executado uma nunica vez pois não podem ser criadas linhas com o mesmo id no SQL.
                //Serve como mecanismo para analizar se o id a ser excluido é valido, caso nenhuma linha seja excluida há problemas com o idRow.
                int del = st.executeUpdate();
                //mecanismo de verificação do numero de linhas excluidas.
                if (del > 0) {
                    //caso haja linhas excluidas no bd SQL, excluimos ela da tabela modelo tambem atravez do comando abaixo 
                    tableDados lista = new tableDados();
                    lista.RemoveRow(linha);
                    System.out.println("Item excluído com sucesso");
                } else {
                    System.out.println("O ID especificado não existe na tabela");
                }
            } catch (IndexOutOfBoundsException ex) {
                System.err.println("Erro ao deletar linha, Erro: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println("Falha na conexão com o banco de dados: " + ex.getMessage());
        }
    }

    //Mecanismo de adição de novos filmes ao banco de dados, consta com a implementação dos dados do filme (titulo, data e categoria)
    public void adicionarFilme(String titulo, String data, String categoria) throws SQLException {
        //Chamamos o metodo conectar como garantia de que o banco de dados esteja ativo(não é obrigatorio pois ele´ja é chamado ao iniciar o sistema, porem evita possiveis falhas)!
        conectar();
        //criação dos comandos de execução com antecedencia, para melhor integridade do mecanismo.
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // Definindo o comando a ser aplicado no SQL
            String sql = "INSERT INTO filmes (nome, datalancamento, categoria) VALUES (?, STR_TO_DATE(?, '%d/%m/%Y'), ?)";

            // Prepara a declaração com o comando SQL e especifica que queremos recuperar os valore '?' gerados no comando.
            preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Definir os valores dos '?' por ordem em que aparecem no comando
            preparedStatement.setString(1, titulo);
            preparedStatement.setString(2, data);
            preparedStatement.setString(3, categoria);

            // Executar o comando de inserção
            int rowsAffected = preparedStatement.executeUpdate();

            //Caso haja a inserção o valor tem que ser maior que 0
            if (rowsAffected > 0) {
                System.out.println("Filme cadastrado com sucesso");

                // Recuperar o ID gerado para o filme inserido para colocar este filme na tabela modelo
                rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    //verificando se todos os valores estão preenchidos corretamente
                    if (0 == id || "".equals(titulo) || "".equals(categoria) || "".equals(data)) {
                        System.out.println("Problema com os dados de inserção");
                    } else {
                        //aplicando os valores na tabela modelo
                        inserirDados isert = new inserirDados();

                        isert.setId(id);
                        isert.setTitulo(titulo);
                        isert.setDataLancamento(data);
                        isert.setCategoria(categoria);

                        tableDados lista = new tableDados();
                        lista.addRow(isert);
                        System.err.println("Filme adicionado a tabela com exito!");
                    }
                }
            } else {
                System.out.println("Falha ao cadastrar o filme");
            }
        } catch (SQLException ex) {
            System.out.println("Falha na conexão com o banco de dados: " + ex.getMessage());
        } finally {
            // Fechar os recursos
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar ResultSet: " + e.getMessage());
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar PreparedStatement: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }

    //mecanismo de edição dos filmes cadastrados no banco de dados SQL, consta com o titulo da coluna a ser modificada, o valor a ser aplicado, e o id da linha que é modificada
    public void editarFilme(String editC, String editV, int idRow) throws SQLException {
        //Chamamos o metodo conectar como garantia de que o banco de dados esteja ativo(não é obrigatorio pois ele´ja é chamado ao iniciar o sistema, porem evita possiveis falhas)!
        conectar();
        ResultSet rs = null;
        try {
            //Criando o comando a ser executado no bd SQL
            Statement st = conn.createStatement();

            String sql = "UPDATE filmes SET " + editC + " = " + editV + " WHERE id = " + idRow;
            //executando o comando
            int edited = st.executeUpdate(sql);
            //verificando se foi executado com exito
            if (edited > 0) {
                System.err.println("Item editado com sucesso");
            } else {
                System.err.println("O ID especificado não existe na tabela");
            }
        } catch (SQLException ex) {
            System.out.println("Falha na conexão com o banco de dados: " + ex.getMessage());
        }
    }

    //Mecanismo para deletar todo o banco de dados SQL (não foi usado, mas serve para formatar o banco de dados caso tenha algum problema)
    public void deletarTudo() {
        try {
            //Chamamos o metodo conectar como garantia de que o banco de dados esteja ativo(não é obrigatorio pois ele´ja é chamado ao iniciar o sistema, porem evita possiveis falhas)!
            conectar();

            //Criando o comando a ser executado no bd SQL
            Statement st = conn.createStatement();
            String comand = "DELETE FROM filmes WHERE id >= 0 ";

            //executando o comando
            int del = st.executeUpdate(comand);

            //verifica se o comando foi executado corretamente
            if (del > 0) {
                System.out.println("Item excluído com sucesso");
            } else {
                System.out.println("O ID especificado não existe na tabela");
            }

        } catch (SQLException ex) {
            System.out.println("Falha na conexão com o banco de dados: " + ex.getMessage());
        }
    }
}

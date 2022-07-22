import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args)
    {
        List<String> logins;
        List<String> amigos;
        List<String> posts;
        Scanner leitor = new Scanner(System.in);
        String usuario = null;

        // Ler arquivos de dados e preenche as listas
        try {
            try (Stream<String> lines = Files.lines(Paths.get("logins.txt"))) { logins = lines.collect(Collectors.toList()); }
            try (Stream<String> lines = Files.lines(Paths.get("amigos.txt"))) { amigos = lines.collect(Collectors.toList()); }
            try (Stream<String> lines = Files.lines(Paths.get("posts.txt"))) { posts = lines.collect(Collectors.toList()); }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Fica em loop até o usuário conseguir efetuar um login
        System.out.println("-------------------------------------------------------");
        System.out.println("-----------            LOGIN                -----------");
        System.out.println("-------------------------------------------------------");
        while (true)
        {
            // Recebe a informação de login e senha
            System.out.print("Username: ");
            String user = leitor.nextLine();

            System.out.print("Password: ");
            String pass = leitor.nextLine();

            // Verifica se existe uma conta que bate com os dados informados
            for (int i = 0; i < logins.size(); i++)
            {
                String[] split = logins.get(i).split("\\s");
                if (user.equals(split[0]) && pass.equals(split[1]))
                {
                    usuario = user;
                    System.out.println("Logado com sucesso");
                    break;
                }
            }

            // Se conseguiu logar sai
            if(usuario != null)
            {
                break;
            }

            // Se não conseguiu diz a mensagem e continua em loop de login
            System.out.print("Usuario ou senha incorreto");

            // Lê novamente a linha para não bugar a próxima leitura
            leitor.nextLine();
        }

        // Fica em loop na lista de amigos até o usuário efetuar uma ação
        while (true)
        {
            System.out.println("-------------------------------------------------------");
            System.out.println("-----------         LISTA DE AMIGOS         -----------");
            System.out.println("-------------------------------------------------------");

            int totalAmigos = 0;

            // Verifica se existe amigos para o usuário conectado
            for (int i = 0; i < amigos.size(); i++)
            {
                String[] split = amigos.get(i).split("\\s");
                if (usuario.equals(split[0]))
                {
                    totalAmigos++;
                    System.out.println(totalAmigos + ". " + split[1]);
                }
            }

            // Se não tem nenhum amigo informa
            if(totalAmigos == 0)
            {
                System.out.println("----------     Nenhum amigo encontrado!    ------------");
            }

            System.out.println("-------------------------------------------------------");
            if(totalAmigos > 0)
            {
                System.out.println("1 - Adicionar amigo | 2 - Ver posts amigo | 3 - Remover amigo");
            }
            else
            {
                System.out.println("Voce ainda nao tem amigos digite 1 para adicionar");
            }
            System.out.print("> ");

            int acao = leitor.nextInt();

            if(acao == 1)
            {
                // Lê a linha novamente para não bugar
                leitor.nextLine();

                // Fica em loop até adicionar algum amigo ou voltar pra lista de amigos
                System.out.println("-------------------------------------------------------");
                System.out.println("-----------          BUSCAR PESSOAS         -----------");
                System.out.println("-------------------------------------------------------");
                System.out.println("Digite o numero 1 para voltar para a lista de amigos");
                while(true)
                {
                    boolean encontrou = false;

                    // Lista as pessoas que são possiveis de adicionar
                    for (int i = 0; i < logins.size(); i++)
                    {
                        String[] split = logins.get(i).split("\\s");
                        if (!usuario.equals(split[0]))
                        {
                            System.out.println(split[0]);
                        }
                    }

                    // Recebe a informação do nome do amigo
                    System.out.print("Digite o nome da pessoa: ");
                    String nomeAmigo = leitor.nextLine();

                    // Volta pra lista de amigos
                    if(nomeAmigo.contains("1"))
                    {
                        break;
                    }

                    // Verifica se existe uma conta que bate com o nome do amigo
                    for (int i = 0; i < logins.size(); i++)
                    {
                        String[] split = logins.get(i).split("\\s");
                        if (nomeAmigo.equals(split[0]))
                        {
                            // adiciona o nome do amigo pra lista
                            amigos.add(usuario + " " + nomeAmigo);
                            System.out.println("Amigo adicionado com sucesso");
                            encontrou = true;
                            break;
                        }
                    }

                    if(encontrou)
                    {
                        break;
                    }

                    // Se não conseguiu diz a mensagem e continua em loop de buscar amigos
                    System.out.print("Amigo nao encontrado ou nao e um usuario");

                    // Lê novamente a linha para não bugar a próxima leitura
                    leitor.nextLine();
                }
            }

            if(acao == 2)
            {
                // Lê a linha novamente para não bugar
                leitor.nextLine();

                System.out.println("-------------------------------------------------------");
                System.out.println("-----------              POSTS              -----------");
                System.out.println("-------------------------------------------------------");
                System.out.println("Digite o numero 1 para voltar para a lista de amigos");
                while(true)
                {
                    // Recebe a informação do nome do amigo
                    System.out.print("Digte o nome do amigo: ");
                    String nomeAmigo = leitor.nextLine();

                    // Volta pra lista de amigos
                    if(nomeAmigo.contains("1"))
                    {
                        break;
                    }

                    System.out.print("Amigo nao possui nenhum post");

                    // Lê novamente a linha para não bugar a próxima leitura
                    leitor.nextLine();
                }
            }

            if(acao == 3 && totalAmigos > 0)
            {
                // Lê a linha novamente para não bugar
                leitor.nextLine();

                // Fica em loop até adicionar algum amigo ou voltar pra lista de amigos
                System.out.println("-------------------------------------------------------");
                System.out.println("-----------          REMOVER AMIGO          -----------");
                System.out.println("-------------------------------------------------------");
                System.out.println("Digite o numero 1 para voltar para a lista de amigos");
                while(true)
                {
                    boolean encontrou = false;

                    // Recebe a informação do nome do amigo
                    System.out.print("Digte o nome do amigo: ");
                    String nomeAmigo = leitor.nextLine();

                    // Volta pra lista de amigos
                    if(nomeAmigo.contains("1"))
                    {
                        break;
                    }

                    // Verifica se existe uma conta que bate com o nome do amigo
                    for (int i = 0; i < logins.size(); i++)
                    {
                        String[] split = logins.get(i).split("\\s");
                        if (nomeAmigo.equals(split[0]))
                        {
                            // adiciona o nome do amigo pra lista
                            amigos.remove(i);
                            System.out.println("Amigo removido com sucesso");
                            encontrou = true;
                            break;
                        }
                    }

                    if(encontrou)
                    {
                        break;
                    }

                    // Se não conseguiu diz a mensagem e continua em loop de buscar amigos
                    System.out.print("Amigo nao encontrado ou nao e um usuario");

                    // Lê novamente a linha para não bugar a próxima leitura
                    leitor.nextLine();
                }
            }
        }
    }
}
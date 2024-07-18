import java.util.Random;
import java.util.Scanner;

//-----------------------------------------------------------------------------------------------------------------------------------------
// MOLDE
//-----------------------------------------------------------------------------------------------------------------------------------------

//Definição da classe Tamagotchi
public class Tamagotchi {

    //Definição dos atributos da classe do Tamagotchi
    private int fome;
    private int felicidade;
    private int idade;
    private int saude;
    private String nome;
    private EstiloVida estiloVida;
    private int energia;
    private Humor humor;
    private int higiene;

//-----------------------------------------------------------------------------------------------------------------------------------------
// ESTADOS
//-----------------------------------------------------------------------------------------------------------------------------------------

    //Enumeração para os diferentes estilos de vida do Tamagotchi
    public enum EstiloVida {
        BEBE,
        ADOLESCENTE,
        ADULTO,
        IDOSO
    }

    // Enumeração para diferentes doenças que o Tamagotchi pode ter
    public enum Doenca {
        RESFRIADO,
        DOR_DE_ESTÔMAGO,
        FERIDA
    }

    // Enumeração para os diferentes humores do Tamagotchi
    public enum Humor{
        FELIZ,
        TRISTE,
        REVOLTADO,
        CANSADO
    }

    // Atributo que guarda a doença atual do Tamagotchi
    private Doenca doencaAtual = null;


//-----------------------------------------------------------------------------------------------------------------------------------------
// CHAMADA DOS IMPORTS
//-----------------------------------------------------------------------------------------------------------------------------------------


// Declaração de objetos Scanner e Random para entrada de dados e geração de números pseudoaleatórios
static Scanner scanner = new Scanner(System.in);
static Random random = new Random();

//-----------------------------------------------------------------------------------------------------------------------------------------
// ATRIBUTOS PADRÃO
//-----------------------------------------------------------------------------------------------------------------------------------------

    // Método construtor da classe Tamagotchi
    public Tamagotchi() {


        // Pede o nome do Tamagotchi ao usuário
        System.out.println("Digite o nome do seu Tamagotchi: ");
        this.nome = scanner.nextLine();

        // Inicializa os atributos do Tamagotchi com valores padrão
        fome = 100;
        felicidade = 100;
        idade = 0;
        estiloVida = EstiloVida.BEBE;
        energia = 100;
        saude = 100;
        humor = Humor.FELIZ;
        higiene = 100;
    }

//-----------------------------------------------------------------------------------------------------------------------------------------
// AÇÕES
//-----------------------------------------------------------------------------------------------------------------------------------------
    
    // Métodos para realizar ações com o Tamagotchi

    public void status() { // 1: STATUS

        System.out.println("Nome: " + nome);
        System.out.println("Fome: " + fome);
        System.out.println("Felicidade: " + felicidade);
        System.out.println("Energia: " + energia);
        System.out.println("Idade: " + idade);
        System.out.println("Estilo de Vida: " + estiloVida);
        System.out.println("Saúde:" +saude);
    }

    
    public void brincar() { // 2: BRINCAR
        if (felicidade < 90) {
            felicidade += 20;
            higiene -= 10;
            System.out.println(nome + " está brincando muito!");
            setHumor(Humor.FELIZ);
        } else {
            System.out.println(nome + " cansou de brincar! Deixa ele descansar");
            setHumor(Humor.CANSADO);
        }
    }

    public void alimentar() { // 3: ALIMENTAR
        if (fome < 90) {
            fome += 20;
            setHumor(Humor.FELIZ);
            System.out.println(nome + " comeu!");
            setHumor(Humor.FELIZ);
        } else {
            System.out.println(nome + " não está com fome.");
            setHumor(Humor.REVOLTADO);
        }
    }

    public void dormir() { // 4: DORMIR
        if (energia < 90) {
            energia += 20;
        
            System.out.println(nome + " está dormindo!");

            // Diminui a fome e a felicidade mais lentamente enquanto dorme
            for (int i = 0; i < 10; i++) {
                fome -= 1;
                felicidade -= 1;

                if (fome <= 0) {
                    System.out.println(nome + " morreu de fome! :(");
                    System.exit(0);
                } else if (felicidade <= 0) {
                    System.out.println(nome + " morreu de tristeza! :(");
                    System.exit(0);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(nome + " acordou!");
        }else {
            System.out.println(nome + " já está dormindo.");
        }
    }

    public void darRemedio() { // 5: DAR REMÉDIO

        if (doencaAtual == null && saude < 30) {
            saude += 40;
            System.out.println(nome + " quase ficou doente, mas está se sentindo melhor!");
            setHumor(Humor.FELIZ);
        }else if (doencaAtual != null && saude < 30){
            saude += 70;
            System.out.println(nome + " não está mais com " + doencaAtual + "!");
            doencaAtual = null;
            setHumor(Humor.FELIZ);
        }else {

            System.out.println(nome + " não precisa de remédio agora.");
        }
    }

    public void darBanho() { // 6: DAR BANHO
        if (higiene < 50) {
            higiene += 50;
            System.out.println(nome + " tomou um banhozinho e está cheiroso!");
        } else {
            System.out.println(nome + " já está limpo!");
        }
    }

//-----------------------------------------------------------------------------------------------------------------------------------------
// AÇÕES PASSIVAS
//-----------------------------------------------------------------------------------------------------------------------------------------

    // Método para definir o humor do Tamagotchi
    public void setHumor(Humor novoHumor) { 
        if (novoHumor != humor) {
            System.out.println(nome + " está se sentindo " + novoHumor + " agora!"); // Printa o humor atual
            humor = novoHumor;
        }
    }

    // Método para simular a passagem de tempo e as mudanças no estado do Tamagotchi
    public void passarTempo() {
        fome -= 5;
        felicidade -= 5;
        energia -= 3;
        saude -= 5;
        higiene -= 5;
        idade++;
        
        
        //Motivos para o Tamagotchi morrer

        if (fome <= 0) { 
            System.out.println(nome + " morreu de fome! :(");
            System.exit(0);
        } else if (felicidade <= 0) {
            System.out.println(nome + " morreu de tristeza! :(");
            System.exit(0);
        } else if (idade >= 100) {
            System.out.println(nome + " morreu de velhice! :(");
            System.exit(0);
        } else if (energia <= 0) {
            System.out.println(nome + " morreu de exaustão! :(");
        } else if (saude <= 0) {
            System.out.println(nome + " morreu doente! :(");
            System.exit(0);
        }

        // Aumenta a idade do Tamagotchi

        if (idade >= 10 && estiloVida == EstiloVida.BEBE) {
            estiloVida = EstiloVida.ADOLESCENTE;
            System.out.println(nome + " está crescendo e agora é um adolescente!");
        } else if (idade >= 20 && estiloVida == EstiloVida.ADOLESCENTE) {
            estiloVida = EstiloVida.ADULTO;
            System.out.println(nome + " está crescendo e agora é um adulto!");
        } else if (idade >= 60 && estiloVida == EstiloVida.ADULTO) {
            estiloVida = EstiloVida.IDOSO;
            System.out.println(nome + " está crescendo e agora é um idoso!");
        }

        // Faz o Tamagotchi ficar doente "aleatoriamente"

        if (saude < 30 && Math.random() < 0.7) {
            doencaAtual = Doenca.values()[(int) (Math.random() * Doenca.values().length)];
            System.out.println(nome + " está doente com " + doencaAtual.toString().toLowerCase() + "!");
        }
  
        //Diminui os status do Tamagotchi de acordo com a doença que ele está

        if (doencaAtual != null) {
            switch (doencaAtual) {
                case RESFRIADO:
                    felicidade -= 10;
                    energia -= 10;
                    break;
                
                case DOR_DE_ESTÔMAGO:
                    fome -= 10;
                    felicidade -= 10;
                    break;

                case FERIDA:
                    energia -= 10;
                    saude -= 10;
                    break;
            }
        }


    //Define o evento aleatório
    if (random.nextDouble()< 0.3) {
        eventoAleatorio();
    }
}


    // Método para simular eventos aleatórios que afetam o Tamagotchi
    private void eventoAleatorio() {
        int evento = random.nextInt(3);
        switch (evento) {
            case 0:
                System.out.println("Hoje está um dia chuvoso! " + nome + " está com preguiça!");
                energia -= 20;
                break;
            case 1:
                System.out.println("Hoje é aniversário de " + nome + " e ele ganhou um presente! Ele ficou feliz!");
                felicidade += 20;
                break;
            case 2:
                System.out.println(nome + " caiu numa poça de barro, agora ele está todo sujo!");
        }
    }



//-----------------------------------------------------------------------------------------------------------------------------------------
// AÇÕES DO USUÁRIO
//-----------------------------------------------------------------------------------------------------------------------------------------

    // Método principal que executa o programa
    public static void main(String[] args) {

        // Criação de um objeto Tamagotchi
        Tamagotchi tamagotchi = new Tamagotchi();

        // Loop principal do programa, onde o usuário interage com o Tamagotchi
        while (true) {

           // Exibe as opções de interação com o Tamagotchi
            System.out.println("O que você quer fazer?");
            System.out.println("1 - Status");
            System.out.println("2 - Brincar");
            System.out.println("3 - Alimentar");
            System.out.println("4 - Dormir");
            System.out.println("5 - Dar remédio");
            System.out.println("6 - Dar banho");
            System.out.println("0 - Matar tamachotchi");

            // Obtém a escolha do usuário
            int escolha = scanner.nextInt();

            // Realiza a ação de acordo com a escolha do usuário
            switch (escolha) {
                case 1:
                    tamagotchi.status();
                    break;
                case 2:
                    tamagotchi.brincar();
                    break;
                case 3:
                    tamagotchi.alimentar();
                    break;
                case 4:
                    tamagotchi.dormir();
                    break;
                case 5:
                    tamagotchi.darRemedio();
                    break;
                case 6:
                    tamagotchi.darBanho();
                case 0:
                System.out.println("Você matou seu tamagotchi :'(");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida!");
            }

            // Simula a passagem de tempo após a interação do usuário
            tamagotchi.passarTempo();
        }
    }
}


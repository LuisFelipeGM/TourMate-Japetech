# Tourmate
* [Integrantes](#integrantes)
* [Solução](#solução)
* [Arquitetura](#arquitetura)
* [Beneficios](#benefícios)
* [Pricipais funcionalidades](#funcionalidades)

* [AVISO IMPORTANTE](#aviso)

* <details><summary><a href="#Videos">Videos</a></summary>
   
  * [DevOps](#devops)
  * [Digital Business Enablement](#dbe)
  * [Enterprise Application Development](#enterprise)
  * [Disruptve Arquitectures & AI](#ia)
  
</details>

## Integrantes:
| Nome                        | RM      | Turma      |
|-----------------------------|---------|------------|
| Diogo Giarranti Kahn        | RM92928 | 2TDSPG     |
| Heitor Borba Marini         | 92976   | 2TDSS      |
| Luís Felipe Garcia Menezes  | 94051   | 2TDST      |
| Mateus da Costa Leme        | 93480   | 2TDSPG     |
| Pedro Henrique Chueiri      | 93939   | 2TDSPG     |

## Solução:
O TourMate é um aplicativo para gerenciamento de viagens, que utiliza o Chat GPT para auxiliar o usuário na seleção da viagem ideal, gerando sugestões de viagem personalizadas com base no perfil. Além disso, o aplicativo dará sugestões de rotas para descobrir passeios turísticos, meios de transporte, restaurantes e a proveitar ao máximo que o que o destino tem a oferecer.

## Arquitetura:
![Alt Text](images/diagrama_tourmate.jpg)

Nosso projeto, um aplicativo mobile, se conectará com nossa API, que fará todos os principais CRUDs e a conexão com o a API da OpenAI, possibilitando acesso ao modelo GPT-3.5. As informações serão armazenadas em um banco de dados Oracle SQL, que por sua vex está alocado em uma Nuvem Privada.

## Benefícios:
A Tourmate busca trazer uma maior personalização e facilidade na hora de identificar e planejar a melhor viagem. A tecnologia do GPT possibilita uma experiência personalizável, permitindo uma maior compatibilidade e precisão nas recomendações.<br><br>
Com a enorme base de dados do GPT, é possível trazer à tona uma grande diversidade de opções, dando maior visibilidade para diversos possíveis locais de viagem, trazendo público e movimentando economias locais que, de outra forma, estariam estagnadas, principalmente na baixa temporada.<br><br>
Empresas focadas em viagens, como hotéis, restaurantes e sites, também podem se benefíciar de nossa solução, uma vez que possibilitamos uma integração dessas empresas em nossa aplicação, ajudando o usuário a encontrar as melhores opções, e empresas a encontrar mais clientes e usuários.

## Funcionalidades:
* Nossa aplicação permitirá o cadastro de novos usuários e a autenticação de usuários já cadastrados.<br><br>
* O usuário poderá editar suas informações, visualizar seus dados e apagar sua conta, caso queira.<br><br>
* O sistema exibirá para o usuário opções de viagens com planos personalizados de acordo com suas preferências. Esses planos de viagem irão conter um breve resumo da cidade que poderão visitar, o valor estimado da viagem e o que fazer no local durante o período especificado.<br><br>
* Nossa aplicação também permitirá que parceiros possam se cadastrar e divulgar seus serviços, como hotéis, restaurantes, etc.<br><br>
* O usuário poderá visualizar os serviços disponíveis e escolher os que mais lhe agradam.

## Aviso:
Altere as credenciais do Banco de Dados no arquivo [application.properties](./src/main/resources/application.properties)

# Videos:

## DevOps:
<a href="https://www.youtube.com/watch?v=BQJIQyYJVoc">
  <img src="./images/devops.png" alt="Video DevOps" width="500" height="400">
</a>

## DBE:
<a href="https://youtu.be/kbvSY0UKtvQ">
  <img src="./images/dbe.png" alt="Video DBE" width="500" height="300">
</a>

## Enterprise:
<a href="https://youtu.be/BdVuOiLfv8A">
  <img src="./images/devops.png" alt="Video DBE" width="500" height="300">
</a>

## IA:
<a href="https://youtu.be/Pd5ueUls4ig">
  <img src="./images/dbe.png" alt="Video DBE" width="500" height="300">
</a>

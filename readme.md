# Tourmate
Integrantes Japetech:
| Nome                        | RM      | Turma      |
|-----------------------------|---------|------------|
| Diogo Giarranti Kahn        | RM92928 | 2TDSPG     |
| Heitor Borba Marini         | 92976   | 2TDSS      |
| Luís Felipe Garcia Menezes  | 94051   | 2TDST      |
| Mateus da Costa Leme        | 93480   | 2TDSPG     |
| Pedro Henrique Chueiri      | 93939   | 2TDSS      |

## Nossa Solução:
O TourMate é um aplicativo para gerenciamento de viagens, que, utilizando o Chat GPT, auxilia o usuário na seleção da viagem ideal, por meio de sugestões de viagem personalizadas com base no seu perfil. Além disso, o aplicativo dará sugestões de caminhos para descobrir passeios turísticos, meios de transporte, restaurantes e tudo mais que a região tem a oferecer.

## Arquitetura:
![Alt Text](images/diagrama_tourmate.jpg)
Em nosso projeto, o aplicativo mobile se conectará com nossa API, que fará todos os principais CRUDs e a conexão com o a API da OpenAI, possibilitando acesso ao modelo GPT-3.5. As informações serão armazenadas em um banco de dados Oracle SQL.

## Benefícios:
A Tourmate busca trazer uma maior personalização e facilidade na hora de identificar e planejar a melhor viagem. A tecnologia do GPT possibilita uma experiência personalizável, deixando o usuário muito mais confortável.<br>
Com a enorme base de dados do GPT é possível trazer à tona uma grande diversidade de opções, dando maior visibilidade para diversos possíveis locais de viagem, assim, trazendo público e movimentando economias locais.<br>
Benefícios podem ser alcançados, também, para empresas focadas em viagens como hotéis, restaurantes e sites, pois buscamos trazer uma integração dessas empresas em nossa aplicação, ajudando o usuário a encontrar as melhores opções e empresas a encontrar mais usuários.

## Pricipais funcionalidades:
Nossa aplicação permitirá o cadastro de novos usuários e a autenticação de usuários já cadastrados. O usuário poderá editar suas informações, visualizar seus dados e apagar sua conta, caso queira. O sistema exibirá para o usuário opções de viagens com planos personalizados de acordo com suas preferências, esses planos de viagem irão conter um breve resumo da cidade que poderão visitar, o valor estimado da viagem e o que fazer no local durante o período especificado. Nossa aplicação também permitirá que parceiros possam se cadastrar e divulgar seus serviços, como hotéis, restaurantes, etc. O usuário poderá visualizar os serviços disponíveis e escolher os que mais lhe agradam.

## Como rodar o projeto (Nuvem):
Para rodar o projeto é necessário ter uma conta na Azure Portal, pois utilizaremos o serviço de WebApp para fazer o Deploy do projeto.<br>
Todo o processo será feito via terminal CMD, porém é possível fazer o mesmo processo via Azure Portal.(Importante ressaltar que para fazer o deploy via CMD é necessário ter o [Azure CLI](https://aka.ms/installazurecliwindows) e o [Apache Maven](https://maven.apache.org/download.cgi) instalados em sua máquina.)<br>

#### Clone o respositório:
```bash
git clone https://github.com/LuisFelipeGM/TourMate-Japetech.git
```
#### Fazendo o login na Azure:
```bash
az login
```
#### Fazendo o Deploy:
```bash
mvn clean install
mvn azure-webapp:deploy
```

#### Acessando o projeto:
Após o deploy, acesse o link gerado pelo Azure WebApp para acessar o projeto (o link estará disponível nas informações do Deploy no CMD).
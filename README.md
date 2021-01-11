# Algumas considerações sobre o projeto
- Projeto desenvolvido utilizando as tecnologias e frameworks:
    - Spring boot
    - Java 8
    - Banco em memória h2
    - Spring data Hibernate
    - Swagger 2.0
    - Spring Bean validator
    - ModelMapper
     
- **Instruções para subir a aplicação**
     A api está documentada com o swagger para acessar basta entrar no endereço ```http://localhost:8080/swagger-ui.html```
Para acessar o banco em memória por favor acesse o endereço: ``` http://localhost:8080/h2-console```, abaixo segue as configurações do painel de login do H2: 
``` 
Driverclass: org.h2.Driver
JDBC URL: jdbc:h2:mem:database
User Name: sa
Password:
```

- **Solução aplicada para o problema dos descontos recorrentes**
A solução adotada para lidar com a lógica dos descontos, foi utilizar o design pattern ```*Chain Of Responsability*```, este padrão permite criar uma corrente ou fluxo entre as regras que devem ser aplicadas, ele auxilia na manutenção do código, pois a classe não cresce, e a lógica permanece simples de ser entendida.
Os descontos oriundos do promoCode e quantidadeMaiorQue10Itens foram encasulados em uma classe abstrata com chamada via método statico, isolar as classes com a lógica permite uma melhor manutenção e organização do código.
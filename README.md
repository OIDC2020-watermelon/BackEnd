# 👨‍👧‍👧 OIDC 공모전 (WaterMelone-팀) Backend

<div align="center"> 
이벤트 티켓 예매 서비스를 위한 웹 어플리케이션
</div>

---

## 🛠 기술스택

- Common: [Spring boot](https://www.typescriptlang.org/)

- Build tool: [gradle](https://gradle.org/)

- ORM: [Spring Data JPA](https://spring.io/projects/spring-data-jpa)

- Log: [slf4j](http://www.slf4j.org/), [Elasticsearch](https://www.elastic.co/kr/products/elasticsearch), [Logstash](https://www.elastic.co/kr/products/logstash), [Redis](https://redis.io/)

- Infra: [MSA(Zuul+Eureka)](https://medium.com/@yesesyo/%EA%B0%80%EB%B3%8D%EA%B2%8C-%EB%A7%88%EC%9D%B4%ED%81%AC%EB%A1%9C%EC%84%9C%EB%B9%84%EC%8A%A4-%EA%B5%AC%EC%B6%95%ED%95%B4%EB%B3%B4%EA%B8%B0-2-6b417aedaec)

- Test: [Testcontainers](https://testcontainers.org/), [Junit5](https://junit.org/junit5/docs/current/user-guide/)

---

# Get Started

ubuntu-18.04와 jdk1.8환경에서 실행하였습니다.

### Gateway server
```bash
cd gateway
./gradlew build
java -jar ./build/libs/gateway-0.0.1-SNAPSHOT.jar
```

### Eureka server
```bash
cd eureka
./gradlew build
java -jar ./build/libs/eureka-0.0.1-SNAPSHOT.jar
```

### Member server
```bash
# database type: mysql, database: userdb, username: root, password: oidc2020
cd member
./gradlew build
java -jar ./build/libs/member-0.0.1-SNAPSHOT.jar
```

### Reservation server
```bash
# database type: mysql, database: reservation, username: root, password: oidc2020
cd reservation
./gradlew build
java -jar ./build/libs/reservation-0.0.1-SNAPSHOT.jar
```

### Show server
```bash
# database type: postgresql, database: postgres, username: postgres, password: oidc2020
# 테스트 진행시 docker가 설치되어있어야 하고 이로 인해 빌드가 실패해도  서버는 실행가능합니다.
cd show
./gradlew build
java -jar ./build/libs/show-0.0.1-SNAPSHOT.jar
```

### Elasticsearch server
```bash
wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | apt-key add -
echo "deb https://artifacts.elastic.co/packages/6.x/apt stable main" | tee -a /etc/apt/sources.list.d/elastic-6.x.list
apt-get update
apt-get install elasticsearch
service elasticsearch start
```
### Logstash server
```bash
wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | sudo apt-key add -
echo "deb https://artifacts.elastic.co/packages/7.x/apt stable main" | sudo tee -a /etc/apt/sources.list.d/elastic-7.x.list
apt-get install logstash
vi /etc/logstash/conf.d/spring.conf
//아래 코드를 elkr.conf에 입력
input {
    tcp {
        port => 4560
        codec => json_lines
    }
}
output {
    elasticsearch {
        hosts => ["localhost:9200"]    
        index => "logstash-%{+YYYY.MM.dd}"
    }
}

service logstash start
```
> 실제 배포 서버는 ELK+redis 아키텍처로 구성되어있습니다.
# mini-event-driven

이 프로젝트는 Kafka를 활용한 간단한 이벤트 기반 아키텍처의 예제입니다. 주요 구성 요소는 다음과 같습니다:

## 인프라 구성
- **Zookeeper**: Kafka 클러스터 관리를 담당합니다 (포트: 2181)
- **Kafka**: 메시지 브로커 역할을 하며 이벤트를 저장하고 전달합니다 (포트: 9092)

## 애플리케이션 구성
1. **Producer 서비스** (포트: 8080)
    - Spring Boot 애플리케이션으로 구현되었습니다
    - REST API를 통해 메시지를 받아 Kafka 토픽으로 전송합니다
    - Spring Cloud Stream의 StreamBridge를 사용하여 메시지를 "example-topic" 토픽으로 전송합니다
    - `/api/publish` 엔드포인트를 통해 메시지를 발행할 수 있습니다

2. **Consumer 서비스** (포트: 8081)
    - Spring Boot 애플리케이션으로 구현되었습니다
    - "example-topic" 토픽에서 메시지를 구독하고 처리합니다
    - Spring Cloud Stream의 함수형 인터페이스를 사용하여 메시지를 소비합니다
    - 수신된 메시지는 콘솔에 출력됩니다 (`[Received] 메시지내용`)
    - Consumer 그룹 "my-consumer-group"으로 설정되어 있습니다

## 기술 스택
- **Spring Boot**: 애플리케이션 프레임워크
- **Spring Cloud Stream**: 메시지 기반 마이크로서비스 구현을 위한 프레임워크
- **Kafka**: 이벤트 스트리밍 플랫폼
- **Docker & Docker Compose**: 인프라 구성 및 관리

## 작동 방식
1. 사용자가 Producer 서비스의 API를 통해 메시지를 전송합니다
2. Producer는 이 메시지를 Kafka의 "example-topic" 토픽으로 발행합니다
3. Consumer 서비스는 이 토픽을 구독하고 있다가 새 메시지가 도착하면 처리합니다
4. 처리된 메시지는 Consumer 서비스의 콘솔에 출력됩니다

이 프로젝트는 이벤트 기반 아키텍처의 기본 개념을 이해하고 실습해볼 수 있는 간단한 예제입니다. 실제 프로덕션 환경에서는 더 복잡한 구성과 추가적인 기능이 필요할 수 있습니다.
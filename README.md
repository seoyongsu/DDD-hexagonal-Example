# DDD-hexagonal-Example


## 목표
* Hexagonal Architecture(육각형)의 장단점 확인
* Adapter Pattern 과 CQRS Pattern 적용 전 연습


## 설명
* 해당 서비스는 주문에 대해서만 책임을 진다!


## 육각형 아키텍처 구조
### Domain : 도메인 계층
| 패키지  명       | 유형        | 비고              |
|--------------|-----------|-----------------|
| core         | Interface | 도메인 주도 설계 개념 모델 |
| order        | class     | 도메인 모델 구현       |

### Application : 응용 계층

| 구분    | 패키지  명 | 비고                    |
|-------|--------|------------------------------|
| ports | in     |  Adapter Pattern의 Input 선언    |
|       | out    |  Adapter Pattern의 Output 선언   | 

### Infrastructure (Outbound Adapter)
* persistence : 자기 자신에 대한 영속성 인프라 구현 영역
* event : 자기 자신에 대한 event 구현 영역


### Interfaces (Inbound Adapter)
* input에 대한 구현 영역
* Presentation 및 event, message 수신등




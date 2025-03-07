## MySQL 데이터베이스를 이용하는 방법

### Pessimistic Lock(비관적 락)

* 이름 그대로 트랜잭션의 충돌이 발생한다고 가정하고 우선 락을 걸고 보는 방법이다.
* 이것은 데이터베이스가 제공하는 락 기능을 사용한다.
* 여러 서버가 있다고 가정하고 만약 서버1이 락을 획득하게 되면 나머지 서버들은 락을 획득할 때까지 대기해야 한다.

* 장점
  * 충돌이 빈번하게 일어난다면(공유 자원에 대한 접근이 잦은 경우) 낙관적 락보다 성능이 좋을 수 있다.
  * 락을 통해 업데이트를 제어하기 때문에 데이터의 정합성이 보장된다.

* 단점
  * 별도의 락을 점유하기 때문에 성능 저하 이슈가 발생할 수 있다.

### Optimistic Lock(낙관적 락)

* 이름 그대로 트랜잭션의 충돌이 발생하지 않는다고 가정하는 방법이다.
* 이것은 데이터베이스가 제공하는 락 기능이 아니라 JPA가 제공하는 버전 관리 기능을 사용한다.
* 낙관적 락의 경우 트랜잭션을 커밋하기 전까지는 트랜잭션의 충돌을 알 수 없다는 특징이 있다.

* 장점
  * 별도의 락을 점유하지 않기 때문에 성능 저하 이슈가 발생하지 않는다.

* 단점
  * 다만, 업데이트 실패 시의 재시도 로직을 개발자가 작성해야 한다.

### Named Lock(네임드 락)

* 이름을 가진 메타 데이터 락이다. 이름을 가진 Lock을 획득한 후 해제할 때까지 다른 세션은 이 Lock을 획득할 수 없다.
* 주의사항으로는 트랜잭션이 종료될 때 이 Lock을 자동으로 해제하지 않는다는 점이다. 별도의 명령어로 해당 Lock 해제를 수행해주거나 선점시간이 끝나야 해제된다.

## Redis 인메모리 데이터베이스를 이용하는 방법

* Redis - Lettuce
  * 구현이 간단하다.
  * Spring Data Redis를 활용하면 Lettuce가 기본이기 때문에 별도의 라이브러리를 사용하면 된다.
  * spin lock 방식이기 때문에 동시에 많은 쓰레드가 lock 획득 대기 상태라면 Redis에 많은 부하가 갈 수 있다.

* Redis - Redisson
  * 락 획득 재시도를 기본으로 제공한다.
  * publish-subscribe(발행-구독 관게) 방식으로 구현이 되어있기 때문에 Lettuce와 비교했을 때, Redis에 부하가 덜 간다.
  * 별도의 라이브러리를 사용해야 한다.
  * 락을 라이브러리 차원에서 제공하기 때문에 사용법을 별도로 공부해야만 한다.

## MySQL vs Redis

* MySQL 
  * MySQL을 사용하고 있다면 별도의 비용없이 사용 가능하다.
  * 어느 정도 트래픽까지는 문제없이 사용이 가능하다.
  * Redis보다는 성능이 좋지 않다.

* Redis
  * 활용 중인 Redis가 없다면 별도의 구축 비용과 인프라 관리 비용이 발생한다.
  * MySQL보다는 성능이 좋지 않다.
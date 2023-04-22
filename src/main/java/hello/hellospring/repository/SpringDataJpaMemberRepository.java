package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
    // select m from Member m where m.name = ?

    //Spring Data JPA에서는 인터페이스만 정의하고 구현체는 자동으로 생성해주기 때문에,
    // 인터페이스에서 정의한 메소드를 구현체에서 직접 구현하지 않아도 됩니다.
    //
    //하지만, 위의 SpringDataJpaMemberRepository 인터페이스에서
    // findByName 메소드가 정의되어 있지만 구현체에서 해당 메소드가 구현되어 있지 않다면,
    // 해당 메소드를 직접 구현해주어야 합니다.
}
//jpa가 구현체를 만들어서 등록해줌
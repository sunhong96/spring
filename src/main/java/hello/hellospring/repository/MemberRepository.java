package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    //Optional은 Wrapper클래스로 참조하더라도 NullPointerException 이 발생하지 않도록 해줌
    Optional<Member> findByName(String name);
    List<Member> findAll();
}

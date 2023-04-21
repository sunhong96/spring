package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
    //EntityManager 는 데이터베이스와의 연결을 관리하며,
    // 엔티티 객체를 데이터베이스에 저장하거나 조회하고,
    // 엔티티 객체와 데이터베이스 간의 변화를 추적하는 등의 작업을 수행합니다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    } //이렇게하면 insert 쿼리만들어서 db에 집어넣고 아이디까지 멤버에다가 setid 해줌

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}

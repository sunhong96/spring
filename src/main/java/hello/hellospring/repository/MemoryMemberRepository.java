package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository 어노테이션은 컴포넌트 스캔(Component Scan)과 함께 사용되며,
// 스프링 컨테이너가 해당 어노테이션이 붙은 클래스를 스캔하여 스프링 컨테이너에서 관리할 수 있는 빈(Bean)으로 등록합니다.
// 이렇게 등록된 빈은 다른 빈에서 주입(Dependency Injection)하여 사용할 수 있습니다.
//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //시퀀스는 0,1,2 키값을 생성해 주는거
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));//null값이 들어온다고해도 감쌀수있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name))
                .findAny(); // 돌려서 같은걸 찾으면 반환함
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}

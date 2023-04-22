package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service 어노테이션은 컴포넌트 스캔(Component Scan)과 함께 사용되며,
// 스프링 컨테이너가 해당 어노테이션이 붙은 클래스를 스캔하여 스프링 컨테이너에서 관리할 수 있는 빈(Bean)으로 등록합니다.
// 이렇게 등록된 빈은 다른 빈에서 주입(Dependency Injection)하여 사용할 수 있습니다.

//@Service
@Transactional  // jpa쓸때는 써줘야댐
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member) {

            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
            return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}


package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


//통합 테스트 스프링 db 까지 다합쳐서하는테스트
@SpringBootTest
@Transactional  //테스트를 실행할때 트랜잭션을 실행하고 db 데이터를 insert 하고 테스트가 끝나면 롤백을 해줌
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
//    @Commit // 쓰면 데이터를 넣어줌
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("Hello2");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    //테스트는 한글로도 가능 실제코드에 포함이 안되기떄문

    @Test
     public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");


        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //assertThrows는 첫 번째 매개변수로 예외 클래스를, 두 번째 매개변수로 예외가 발생할 코드를 람다식으로 전달합니다.

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");



//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


        //then
    }

}
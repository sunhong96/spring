package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat; //assertThat 써줄수있는 import문

//두개다 돌릴수있음 test코드의 장점

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }//test는 순서가 보장되지않음 그래서 실행될때마다 clear해줘야댐

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + (result == member));//그냥 확인
        Assertions.assertEquals(member, result);
        //Assertions.assertEquals 둘이 같다면 정상적으로 돌아감
        assertThat(member).isEqualTo(result); //import문을 써서 assertThat을 바로써줄수 있음
        //같다면 정상적으로 돌아감 member가 result랑 같다면
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result =repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}

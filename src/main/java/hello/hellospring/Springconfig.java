package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 직접등록하는법
@Configuration
public class Springconfig {

    private final MemberRepository memberRepository;

    @Autowired //생성자가 한개인경우 생략가능
    public Springconfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


//    @PersistenceContext
//    private EntityManager em;
//
//    public Springconfig(EntityManager em){
//        this.em = em;
//    }
//    private DataSource dataSource;
//
//    @Autowired
//    public Springconfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    } //spring bean에 등록해줌

//    @Bean
//    public TimeTraceAop TimeTraceAop() {
//        return new TimeTraceAop();
//    } 이거 대신 @Component 써줘두댐

//    @Bean
//    public MemberRepository memberRepository()  {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JDBCTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(em);
//
//    }


}

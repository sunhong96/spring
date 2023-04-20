package hello.hellospring;

import hello.hellospring.repository.JDBCTemplateMemberRepository;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


// 직접등록하는법
@Configuration
public class Springconfig {
    private DataSource dataSource;

    @Autowired
    public Springconfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    } //spring bean에 등록해줌

    @Bean
    public MemberRepository memberRepository()  {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JDBCTemplateMemberRepository(dataSource);
    }
}

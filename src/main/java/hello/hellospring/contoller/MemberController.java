package hello.hellospring.contoller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private  final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    //@Autowired 어노테이션이 MemberController 클래스 생성자에 붙어 있으므로,
    // 스프링 컨테이너는 MemberController 객체를 생성할 때 해당 생성자에 필요한 의존 객체를 자동으로 주입합니다.
    //MemberService 타입의 매개변수 memberService를 받는 생성자를 정의합니다.
    //this.memberService = memberService; 코드를 통해 매개변수로 전달된 memberService 인스턴스를
    // MemberController 클래스의 인스턴스 변수 memberService에 할당합니다.
    //이렇게 생성된 MemberController 객체는 스프링 컨테이너에서 관리되며,
    // memberService 인스턴스를 사용하여 원하는 로직을 처리할 수 있습니다.
    // @Autowired 어노테이션을 사용하면 의존성 주입을 자동으로 처리해주기 때문에,
    // 객체 간의 결합도를 낮추고 유지보수성을 높일 수 있습니다.

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")  //members/createMemberForm 여기서 넘어옴
    public String create(MemberForm form){ //MemberForm 여기의 name 값에 입력갑이 들어감
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}

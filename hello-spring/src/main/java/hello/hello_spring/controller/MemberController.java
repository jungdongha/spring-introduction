package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;
    //스프링 컨테이너에 하나만 만듬

    //멤버서비스를 연결을 시켜둠. 의존관계를 주입.
    //멤버 서비스로 들어가면 그냥 자바 코드로 되어있으니 스프링에서 관리할수 있게 어노테이션을 넣어준다.

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // get은 전달할떄 쓴다
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMembersForm";
    }

    @PostMapping(value = "/members/new")
    //데이터를 form으로 전달할때 쓴다.
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberlist";
    }
}
package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //조회만 하고 수정은 하지 않는다는 의미
@RequiredArgsConstructor // final이나 @NonNull이 붙은 필드들을 갖는 생성자가 자동으로 생성 AllArgsConstructor보다 나은듯
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    @Transactional(readOnly = false) //회원 가입은 읽기 전용이 아닌 트랜잭션에서 실행
    public Long join(Member member) { // 회원 객체를 저장하고 생성된 회원의 ID를 반환

        validateDuplicateMember(member); //중복 회원 검출
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());// DB 유니크제약조건으로 설정 권장
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}

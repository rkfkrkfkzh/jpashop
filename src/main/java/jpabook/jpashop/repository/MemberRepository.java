package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository //데이터 액세스 계층의 구성 요소임을 나타내는 어노테이션, 이를 통해 Spring은 해당 클래스를 스캔하고 필요한 의존성 주입(Dependency Injection)을 처리
@RequiredArgsConstructor //Spring Data Jpa가 있어서 가능함
public class MemberRepository { //데이터베이스와의 상호작용을 담당, Member 엔티티의 저장 및 조회에 사용

//    @PersistenceContext //JPA의 EntityManager를 주입받기 위한 어노테이션, @PersistenceContext 어노테이션을 통해 EntityManager를 주입받아서 em 필드에 할당
//    @Autowired //다음 버전에서는 지원하도록 해준다는 소문
    private final EntityManager em; //EntityManager는 JPA에서 엔티티를 영속화하고 관리하는 역할

    public void save(Member member) { //전달받은 Member 객체를 EntityManager를 사용하여 영속화
        em.persist(member); //em.persist(member)를 호출하여 Member 엔티티를 저장
    }

    public Member findOne(Long id) { //주어진 id를 사용하여 EntityManager를 통해 Member 엔티티를 조회
        return em.find(Member.class, id); //em.find(Member.class, id)를 호출하여 해당 id에 해당하는 Member 엔티티를 반환
    }

    public List<Member> findAll() { //JPQL(JPA Query Language)을 사용하여 모든 Member 엔티티를 조회
        return em.createQuery("select m from Member m", Member.class).getResultList();
        //em.createQuery를 호출하여 JPQL 쿼리를 작성, getResultList()를 호출하여 결과를 리스트로 반환
    }

    public List<Member> findByName(String name) { // JPQL을 사용하여 이름에 해당하는 Member 엔티티를 조회
        return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
        //em.createQuery를 호출하여 JPQL 쿼리를 작성, setParameter()을 사용하여 파라미터를 설정한 뒤, getResultList()를 호출하여 결과를 리스트로 반환
    }
}

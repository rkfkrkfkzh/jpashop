package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;

    private String street;

    private String zipcode;

    //JPA 특성상 엔티티나 임베디드 타입 자바 기본 생성자는 public 보다 protected로 설정하는게 그나마 더 안전하다
    //JPA 구현 라이브러리가 객체를 생성할 떄 리플렉션 같은 기술을 사용할 수 있도록 지원해야하기 때문이다.
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}

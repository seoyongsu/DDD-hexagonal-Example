package org.example.order.domain.vo;


import org.example.order.domain.Vo;

/**
 * 주소
 * @param zipCode       우편 번호
 * @param region        광역시/도
 * @param dept_1        시/군/구
 * @param dept_2        도로명 or 읍/면/동
 * @param dept_3        건물번호 or 통/리
 * @param description   기타 주소
 *
 * @author seoyongsu
 */
public record Address(
        String zipCode,
        String region,
        String dept_1,
        String dept_2,
        String dept_3,
        String description
) implements Vo<Address> {
    @Override
    public boolean sameValueAs(Address other) {
        return this.equals(other);
    }
}

package org.example.order.domain.vo;

import org.example.order.domain.Identity;

import java.util.Objects;
import java.util.UUID;

/**
 * 주문 번호
 *
 * @author seoyongsu
 */
public class OrderNo implements Identity {
    private final String no;

    public OrderNo() {
        this.no = UUID.randomUUID().toString();
    }

    public OrderNo(String no) {
        if(no == null || no.isEmpty())
            throw new IllegalArgumentException("OrderNo cannot be null or empty!");
        this.no = no;
    }
    public String getNo() {
        return no;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderNo orderNo = (OrderNo) o;
        return Objects.equals(no, orderNo.no);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no);
    }

}

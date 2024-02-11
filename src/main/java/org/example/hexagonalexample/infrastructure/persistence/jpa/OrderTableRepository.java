package org.example.hexagonalexample.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTableRepository extends JpaRepository<OrderTable, String> {
}

package ru.clevertec.ecl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.ecl.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Override
    @EntityGraph(attributePaths = {"user", "giftCertificate"})
    Optional<Order> findById(Long id);

    @Override
    @EntityGraph(attributePaths = {"user", "giftCertificate"})
    Page<Order> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"user", "giftCertificate"})
    List<Order> findAllByUserId(Long userId, Pageable pageable);
}

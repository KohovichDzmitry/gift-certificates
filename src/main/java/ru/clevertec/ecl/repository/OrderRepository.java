package ru.clevertec.ecl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = "select last_value from orders_id_seq", nativeQuery = true)
    Integer findLastSequenceValue();

    @Query(value = "select SETVAL('orders_id_seq', :sequenceValue)", nativeQuery = true)
    Integer setSequenceValue(@Param("sequenceValue") Integer sequenceValue);
}

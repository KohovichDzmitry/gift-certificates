package ru.clevertec.ecl.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;
import ru.clevertec.ecl.entity.GiftCertificate;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public interface GiftCertificateRepository extends JpaRepository<GiftCertificate, Long> {

    @EntityGraph(attributePaths = {"tags"})
    @Query("select gc from GiftCertificate gc join gc.tags t where lower(t.name) = lower(:tagName)")
    List<GiftCertificate> findAllByTagName(@Param("tagName") String tagName, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"tags"})
    Optional<GiftCertificate> findById(Long id);

    @Override
    @EntityGraph(attributePaths = {"tags"})
    Page<GiftCertificate> findAll(Example example, Pageable pageable);

    @EntityGraph(attributePaths = {"tags"})
    Optional<GiftCertificate> findByNameIgnoreCase(String giftCertificateName);
}

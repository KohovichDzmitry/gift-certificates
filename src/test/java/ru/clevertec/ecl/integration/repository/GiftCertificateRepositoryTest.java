package ru.clevertec.ecl.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import ru.clevertec.ecl.entity.GiftCertificate;
import ru.clevertec.ecl.integration.IntegrationTestBase;
import ru.clevertec.ecl.repository.GiftCertificateRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static ru.clevertec.ecl.dataForTest.GiftCertificateForTest.*;

@RequiredArgsConstructor
public class GiftCertificateRepositoryTest extends IntegrationTestBase {

    private final GiftCertificateRepository giftCertificateRepository;

    @Test
    void findAllTest() {
        Example<GiftCertificate> example = Example.of(GiftCertificate.builder().build(), matcher());
        List<GiftCertificate> actual = giftCertificateRepository.findAll(example, pageable()).getContent();
        assertEquals(giftCertificates(), actual);
    }

    @Test
    void findAllByPartOfNameTest() {
        Example<GiftCertificate> example = Example.of(GiftCertificate.builder()
                .name("spo")
                .build(), matcher());
        List<GiftCertificate> actual = giftCertificateRepository.findAll(example, pageable()).getContent();
        assertEquals(singletonList(giftCertificate2()), actual);
    }

    @Test
    void findAllByPartOfNameTest2() {
        Example<GiftCertificate> example = Example.of(GiftCertificate.builder()
                .name("in")
                .build(), matcher());
        List<GiftCertificate> actual = giftCertificateRepository.findAll(example, pageable()).getContent();
        assertEquals(asList(giftCertificate4(), giftCertificate5()), actual);
    }

    @Test
    void findAllByPartOfDescriptionTest() {
        Example<GiftCertificate> example = Example.of(GiftCertificate.builder()
                .description("rip")
                .build(), matcher());
        List<GiftCertificate> actual = giftCertificateRepository.findAll(example, pageable()).getContent();
        assertEquals(giftCertificates(), actual);
    }

    @Test
    void findAllByTagNameTest() {
        List<GiftCertificate> actual = giftCertificateRepository.findAllByTagName("mood", pageable());
        assertEquals(asList(giftCertificate1(), giftCertificate2(),
                giftCertificate3(), giftCertificate5()), actual);
    }

    @Test
    void findAllByTagNameTest2() {
        List<GiftCertificate> actual = giftCertificateRepository.findAllByTagName("nATurE", pageable());
        assertEquals(asList(giftCertificate3(), giftCertificate5()), actual);
    }

    @Test
    void findByIdTest() {
        Optional<GiftCertificate> optional = giftCertificateRepository.findById(1L);
        optional.ifPresent(actual -> assertEquals(giftCertificate1(), actual));
    }

    @Test
    void findByNameTest() {
        Optional<GiftCertificate> optional = giftCertificateRepository.findByNameIgnoreCase("sport");
        optional.ifPresent(actual -> assertEquals(giftCertificate2(), actual));
    }

    @Test
    void findByNameIgnoreCaseTest() {
        Optional<GiftCertificate> optional = giftCertificateRepository.findByNameIgnoreCase("TRavEl");
        optional.ifPresent(actual -> assertEquals(giftCertificate3(), actual));
    }

    @Test
    void saveTest() {
        GiftCertificate actual = giftCertificateRepository.save(giftCertificateForSave());
        giftCertificateRepository.flush();
        assertEquals(giftCertificateForSave(), actual);
    }

    @Test
    void deleteTest() {
        giftCertificateRepository.deleteById(1L);
        Optional<GiftCertificate> optional = giftCertificateRepository.findById(1L);
        giftCertificateRepository.flush();
        assertFalse(optional.isPresent());
    }
}

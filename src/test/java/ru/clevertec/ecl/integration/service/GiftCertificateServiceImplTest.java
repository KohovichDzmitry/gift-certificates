package ru.clevertec.ecl.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.exception.EntityNotFoundException;
import ru.clevertec.ecl.exception.EntityWithNameExistsException;
import ru.clevertec.ecl.integration.IntegrationTestBase;
import ru.clevertec.ecl.repository.GiftCertificateRepository;
import ru.clevertec.ecl.service.GiftCertificateService;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.clevertec.ecl.dataForTest.GiftCertificateForTest.*;

@RequiredArgsConstructor
public class GiftCertificateServiceImplTest extends IntegrationTestBase {

    private final GiftCertificateService giftCertificateService;
    private final GiftCertificateRepository giftCertificateRepository;

    @Test
    void findAllTest() {
        List<GiftCertificateDto> actual = giftCertificateService.findAll(pageable());
        assertEquals(giftCertificatesDto(), actual);
    }

    @Test
    void findAllByPartOfNameTest() {
        List<GiftCertificateDto> actual = giftCertificateService
                .findAllByPartOfNameOrDescription("nes", null, pageable());
        assertEquals(singletonList(giftCertificateDto1()), actual);
    }

    @Test
    void findAllByPartOfDescriptionTest() {
        List<GiftCertificateDto> actual = giftCertificateService
                .findAllByPartOfNameOrDescription(null, "fav", pageable());
        assertEquals(singletonList(giftCertificateDto2()), actual);
    }

    @Test
    void findAllByTagNameTest() {
        List<GiftCertificateDto> actual = giftCertificateService.findAllByTagName("summer", pageable());
        assertEquals(singletonList(giftCertificateDto4()), actual);
    }

//    @Test
//    void findAllByTagNameTest() {
//        List<GiftCertificateDto> actual = giftCertificateService.findAllByTagName("mood", pageable());
//        assertEquals(asList(giftCertificateDto1(), giftCertificateDto2(),
//                giftCertificateDto3(), giftCertificateDto5()), actual);
//    }

    @Test
    void findByIdTest() {
        GiftCertificateDto actual = giftCertificateService.findById(1L);
        assertEquals(giftCertificateDto1(), actual);
    }

    @Test
    void findByIdNegativeTest() {
        assertThrows(EntityNotFoundException.class, () -> giftCertificateService.findById(8L));
    }

    @Test
    void findAllBySeveralTagNamesTest() {
        List<String> tagNames = singletonList("summer");
        List<GiftCertificateDto> actual = giftCertificateService.findAllBySeveralTagNames(tagNames, pageable());
        List<GiftCertificateDto> expected = singletonList(giftCertificateDto4());
        assertEquals(expected, actual);
    }

//    @Test
//    void findAllBySeveralTagNamesTest() {
//        List<String> tagNames = Arrays.asList("summer", "fun", "nature");
//        List<GiftCertificateDto> actual = giftCertificateService.findAllBySeveralTagNames(tagNames, pageable());
//        List<GiftCertificateDto> expected = Arrays.asList(giftCertificateDto2(), giftCertificateDto3(),
//                giftCertificateDto4(), giftCertificateDto5());
//        assertEquals(expected, actual);
//    }

    @Test
    void saveTest() {
        GiftCertificateDto actual = giftCertificateService.save(readGiftCertificateDtoForSave());
        giftCertificateRepository.flush();
        assertEquals(giftCertificateDtoForSave(), actual);
    }

    @Test
    void saveWithExistsNameTest() {
        assertThrows(EntityWithNameExistsException.class,
                () -> giftCertificateService.save(readGiftCertificateDto1()));
    }

    @Test
    void updateNameTest() {
        GiftCertificateDto actual = giftCertificateService.update(readGiftCertificateDtoUpdatedName(), 1L);
        giftCertificateRepository.flush();
        assertEquals(giftCertificateDtoUpdatedName(), actual);
    }

    @Test
    void updatePriceTest() {
        GiftCertificateDto actual = giftCertificateService.update(readGiftCertificateDtoUpdatedPrice(), 1L);
        giftCertificateRepository.flush();
        assertEquals(giftCertificateDtoUpdatedPrice(), actual);
    }

    @Test
    void updateDescriptionTest() {
        GiftCertificateDto actual = giftCertificateService.update(readGiftCertificateDtoUpdatedDescription(), 1L);
        giftCertificateRepository.flush();
        assertEquals(giftCertificateDtoUpdatedDescription(), actual);
    }

    @Test
    void updateDurationTest() {
        GiftCertificateDto actual = giftCertificateService.update(readGiftCertificateDtoUpdatedDuration(), 1L);
        giftCertificateRepository.flush();
        assertEquals(giftCertificateDtoUpdatedDuration(), actual);
    }

    @Test
    void updateTagsTest() {
        GiftCertificateDto actual = giftCertificateService.update(readGiftCertificateDtoUpdatedTags(), 1L);
        giftCertificateRepository.flush();
        assertEquals(giftCertificateDtoUpdatedTags(), actual);
    }

    @Test
    void updateWithNotFoundIdTest() {
        assertThrows(EntityNotFoundException.class,
                () -> giftCertificateService.update(readGiftCertificateDtoUpdatedPrice(), 8L));
    }

    @Test
    void updateWithExistsNameTest() {
        assertThrows(EntityWithNameExistsException.class,
                () -> giftCertificateService.update(readGiftCertificateDtoForUpdateWithExistsName(), 2L));
    }

    @Test
    void deleteTest() {
        giftCertificateService.delete(1L);
        giftCertificateRepository.flush();
        assertThrows(EntityNotFoundException.class, () -> giftCertificateService.findById(1L));
    }

    @Test
    void deleteWithNotFoundIdTest() {
        assertThrows(EntityNotFoundException.class, () -> giftCertificateService.delete(8L));
    }
}

package ru.clevertec.ecl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.dto.ReadGiftCertificateDto;
import ru.clevertec.ecl.service.GiftCertificateService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("v1/gift-certificates")
@RequiredArgsConstructor
@Validated
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    @GetMapping
    public ResponseEntity<List<GiftCertificateDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(giftCertificateService.findAll(pageable));
    }

    @GetMapping("/part")
    public ResponseEntity<List<GiftCertificateDto>> findAllBy(@Pattern(regexp = "[A-Z, a-z]+")
                                                              @RequestParam(required = false) String name,
                                                              @RequestParam(required = false) String description,
                                                              Pageable pageable) {
        return ResponseEntity.ok(giftCertificateService.findAllByPartOfNameOrDescription(name, description, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftCertificateDto> findById(@Positive @PathVariable Long id) {
        return ResponseEntity.ok(giftCertificateService.findById(id));
    }

    @GetMapping("/tag/{tagName}")
    public ResponseEntity<List<GiftCertificateDto>> findAllByTagName(@NotBlank @PathVariable String tagName,
                                                                     Pageable pageable) {
        return ResponseEntity.ok(giftCertificateService.findAllByTagName(tagName, pageable));
    }

    @GetMapping("/tags/")
    public ResponseEntity<List<GiftCertificateDto>> findAllBySeveralTagNames(
            @RequestParam(required = false) List<String> tagNames,
            Pageable pageable) {
        return ResponseEntity.ok(giftCertificateService.findAllBySeveralTagNames(tagNames, pageable));
    }

    @PostMapping
    public ResponseEntity<GiftCertificateDto> save(@Valid @RequestBody ReadGiftCertificateDto readGiftCertificateDto) {
        return new ResponseEntity<>(giftCertificateService.save(readGiftCertificateDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GiftCertificateDto> update(@RequestBody ReadGiftCertificateDto readGiftCertificateDto,
                                                     @Positive @PathVariable Long id) {
        return ResponseEntity.ok(giftCertificateService.update(readGiftCertificateDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Positive @PathVariable Long id) {
        giftCertificateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

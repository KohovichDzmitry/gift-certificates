package ru.clevertec.ecl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.ecl.dto.ReadTagDto;
import ru.clevertec.ecl.dto.TagDto;
import ru.clevertec.ecl.service.TagService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("v1/tags")
@RequiredArgsConstructor
@Validated
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<TagDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(tagService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDto> findById(@Positive @PathVariable Long id) {
        return ResponseEntity.ok(tagService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TagDto> save(@Valid @RequestBody ReadTagDto readTagDto) {
        return new ResponseEntity<>(tagService.save(readTagDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TagDto> update(@RequestBody ReadTagDto readTagDto,
                                         @Positive @PathVariable Long id) {
        return ResponseEntity.ok(tagService.update(readTagDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Positive @PathVariable Long id) {
        tagService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

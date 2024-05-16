package io.nqa.menetlus.controller;

import io.nqa.menetlus.model.CustomResponse;
import io.nqa.menetlus.model.MenetlusDTO;
import io.nqa.menetlus.service.IMenetlusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menetlus")
@RequiredArgsConstructor
public class MenetlusController {
    private final IMenetlusService service;

    @GetMapping()
    ResponseEntity<CustomResponse> getAll() {
        return ResponseEntity.ok(this.service.getAllDto());
    }

    @GetMapping(value = "{id}")
    ResponseEntity<CustomResponse> getById(@PathVariable final long id) {
        return ResponseEntity.ok(this.service.getByIdDto(id));
    }

    @PostMapping()
    ResponseEntity<CustomResponse> addMenetlus(@RequestBody final MenetlusDTO menetlus) {
        return ResponseEntity.ok(this.service.save(menetlus));
    }
}

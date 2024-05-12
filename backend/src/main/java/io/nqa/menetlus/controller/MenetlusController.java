package io.nqa.menetlus.controller;

import io.nqa.menetlus.model.MenetlusDTO;
import io.nqa.menetlus.service.IMenetlusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api/menetlus")
@RequiredArgsConstructor
public class MenetlusController {
    private final IMenetlusService service;

    @GetMapping(value = "/")
    ResponseEntity<List<MenetlusDTO>> getAll() {
        return ResponseEntity.ok(this.service.getAllDtos());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<MenetlusDTO> getById(@PathVariable final long id) {
        return ResponseEntity.ok(this.service.getDtoById(id));
    }

    @PostMapping(value = "/")
    ResponseEntity<MenetlusDTO> addMenetlus(@RequestBody final MenetlusDTO menetlus) {
        return ResponseEntity.ok(this.service.save(menetlus));
    }
}

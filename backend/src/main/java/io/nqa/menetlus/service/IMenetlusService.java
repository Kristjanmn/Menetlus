package io.nqa.menetlus.service;

import io.nqa.menetlus.entity.Menetlus;
import io.nqa.menetlus.model.MenetlusDTO;

import java.util.List;

public interface IMenetlusService {
    List<Menetlus> getAll();
    List<MenetlusDTO> getAllDtos();
    Menetlus getById(final long id);
    MenetlusDTO getDtoById(final long id);
    void save(final Menetlus menetlus);
}

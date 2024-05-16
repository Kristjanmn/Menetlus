package io.nqa.menetlus.service;

import io.nqa.menetlus.entity.Menetlus;
import io.nqa.menetlus.model.CustomResponse;
import io.nqa.menetlus.model.MenetlusDTO;

import java.util.List;

public interface IMenetlusService {
    List<Menetlus> getAll();
    CustomResponse getAllDto();
    Menetlus getById(final long id);
    CustomResponse getByIdDto(final long id);
    Menetlus save(final Menetlus menetlus);
    CustomResponse save(final MenetlusDTO menetlus);
    void sendEmail(final String email);
    boolean validateInfo(Menetlus menetlus);
    Menetlus setEmailDelivered(long id, boolean value);
}

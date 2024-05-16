package io.nqa.menetlus.service;

import io.nqa.menetlus.entity.Menetlus;
import io.nqa.menetlus.model.CustomResponse;
import io.nqa.menetlus.model.MenetlusDTO;
import io.nqa.menetlus.repository.MenetlusRepository;
import io.nqa.menetlus.util.MenetlusUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenetlusService implements IMenetlusService {
    private final IRabbitMqService rabbitMqService;
    private final MenetlusRepository repository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<Menetlus> getAll() {
        return repository.findAll();
    }

    @Override
    public CustomResponse getAllDto() {
        List<Menetlus> menetlusList = this.getAll();
        List<MenetlusDTO> dtoList = new ArrayList<>();
        for (Menetlus menetlus : menetlusList) {
            dtoList.add(modelMapper.map(menetlus, MenetlusDTO.class));
        }
        return new CustomResponse(true, dtoList);
    }

    @Override
    public Menetlus getById(long id) {
        Optional<Menetlus> menetlus = repository.findById(id);
        return menetlus.orElse(null);
    }

    @Override
    public CustomResponse getByIdDto(long id) {
        Menetlus menetlus = this.getById(id);
        if (menetlus == null)
            return new CustomResponse(false, null);
        return new CustomResponse(true, modelMapper.map(menetlus, MenetlusDTO.class));
    }

    @Override
    public Menetlus save(Menetlus menetlus) {
        if (!this.validateInfo(menetlus))
            return null;
        if (!menetlus.isEmailDelivered())
            this.sendEmail(menetlus.getEmail());
        return this.repository.save(menetlus);
    }

    @Override
    public CustomResponse save(MenetlusDTO dto) {
        Menetlus menetlus = modelMapper.map(dto, Menetlus.class);
        menetlus = this.save(menetlus);
        if (menetlus == null)
            return new CustomResponse(false, null);
        return new CustomResponse(true, modelMapper.map(menetlus, MenetlusDTO.class));
    }

    @Override
    public void sendEmail(String email) {
        this.rabbitMqService.sendViaDirectExchange(email);
    }

    @Override
    public boolean validateInfo(Menetlus menetlus) {
        return MenetlusUtils.isEmailValid(menetlus.getEmail()) && MenetlusUtils.isPersonalCodeValid(menetlus.getPersonalCode());
    }

    @Override
    public Menetlus setEmailDelivered(long id, boolean value) {
        Optional<Menetlus> optMenetlus = this.repository.findById(id);
        if (optMenetlus.isPresent()) {
            Menetlus menetlus = optMenetlus.get();
            menetlus.setEmailDelivered(value);
            return this.save(menetlus);
        } return null;
    }
}

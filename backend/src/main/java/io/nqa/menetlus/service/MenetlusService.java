package io.nqa.menetlus.service;

import io.nqa.menetlus.entity.Menetlus;
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
    public List<MenetlusDTO> getAllDtos() {
        List<Menetlus> menetlusList = this.getAll();
        List<MenetlusDTO> dtoList = new ArrayList<>();
        for (Menetlus menetlus : menetlusList) {
            dtoList.add(modelMapper.map(menetlus, MenetlusDTO.class));
        }
        return dtoList;
    }

    @Override
    public Menetlus getById(long id) {
        Optional<Menetlus> menetlus = repository.findById(id);
        return menetlus.orElse(null);
    }

    @Override
    public MenetlusDTO getDtoById(long id) {
        Menetlus menetlus = this.getById(id);
        if (menetlus == null) return null;
        return modelMapper.map(menetlus, MenetlusDTO.class);
    }

    @Override
    public Menetlus save(Menetlus menetlus) {
        return this.repository.save(menetlus);
    }

    @Override
    public MenetlusDTO save(MenetlusDTO menetlus) {
        return modelMapper.map(
                this.save(modelMapper.map(
                        menetlus,
                        Menetlus.class)),
                MenetlusDTO.class);
    }

    @Override
    public void sendEmail(String email) {
        this.rabbitMqService.sendViaDirectExchange(email);
    }

    @Override
    public boolean validateInfo(Menetlus menetlus) {
        return !MenetlusUtils.isEmailValid(menetlus.getEmail()) || !MenetlusUtils.isIsikukoodValid(menetlus.getIsikukood());
    }
}

package io.nqa.menetlus.service;

import io.nqa.menetlus.entity.Menetlus;
import io.nqa.menetlus.model.MenetlusDTO;
import io.nqa.menetlus.repository.MenetlusRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenetlusService implements IMenetlusService {
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
    public void save(Menetlus menetlus) {
        this.repository.save(menetlus);
    }
}

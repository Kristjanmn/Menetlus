package io.nqa.menetlus.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nqa.menetlus.configuration.ApplicationProperties;
import io.nqa.menetlus.entity.Menetlus;
import io.nqa.menetlus.model.CustomResponse;
import io.nqa.menetlus.model.MenetlusDTO;
import io.nqa.menetlus.model.MqEmailPayload;
import io.nqa.menetlus.repository.MenetlusRepository;
import io.nqa.menetlus.util.MenetlusUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenetlusService implements IMenetlusService {
    private final MenetlusRepository repository;
    private final ApplicationProperties properties;
    private final RabbitTemplate template;
    private final ModelMapper modelMapper = new ModelMapper();
    private final ObjectMapper objectMapper = new ObjectMapper();

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
            return new CustomResponse(false, "Menetlust ei leitud");
        return new CustomResponse(true, modelMapper.map(menetlus, MenetlusDTO.class));
    }

    @Override
    public Menetlus save(Menetlus menetlus) {
        if (!this.validateInfo(menetlus)) {
            return null;
        }
        menetlus = this.repository.save(menetlus);
        if (!menetlus.isEmailDelivered())
            this.sendEmail(menetlus);
        return menetlus;
    }

    @Override
    public CustomResponse save(MenetlusDTO dto) {
        Menetlus menetlus = modelMapper.map(dto, Menetlus.class);
        if (!this.validateInfo(menetlus)) {
            if (!MenetlusUtils.isEmailValid(menetlus.getEmail()))
                return new CustomResponse(false, "Vigane e-kirja aadress");
            if (!MenetlusUtils.isPersonalCodeValid(menetlus.getPersonalCode()))
                return new CustomResponse(false, "Vigane isikukood");
        }
        menetlus = this.save(menetlus);
        if (menetlus == null)
            return new CustomResponse(false, "Salvestamine ebaõnnestus");
        return new CustomResponse(true, modelMapper.map(menetlus, MenetlusDTO.class));
    }

    @Override
    public void sendEmail(Menetlus menetlus) {
        if (menetlus.isEmailDelivered()) return;
        try {
            MqEmailPayload payload = new MqEmailPayload(menetlus.getId(), menetlus.getEmail(), menetlus.isEmailDelivered());
            String json = objectMapper.writeValueAsString(payload);
            Message message = MessageBuilder.withBody(json.getBytes()).build();
            template.convertAndSend(properties.getRabbitMq().getExchangeName(), properties.getRabbitMq().getRoutingKey(), message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
        }
        return null;
    }
}

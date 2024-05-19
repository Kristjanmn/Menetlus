package io.nqa.menetlus.service;

import io.nqa.menetlus.entity.Menetlus;
import io.nqa.menetlus.model.MenetlusDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MenetlusServiceTest {
    @Autowired
    private IMenetlusService menetlusService;

    @Test
    void saveMenetlusTest_success() {
        Menetlus menetlus = Menetlus.builder()
                .name("Kristjan Männimets")
                .personalCode(39312034227L)
                .email("kristjan.mannimets@gmail.com")
                .reason("Omab liiga palju kasse").build();
        Menetlus expectedMenetlus = menetlus;
        expectedMenetlus.setId(1);
        Menetlus actualMenetlus = menetlusService.save(menetlus);
        assertEquals(expectedMenetlus, actualMenetlus);
    }

    @Test
    void saveMenetlusTest_DTO_success() {
        MenetlusDTO dto = MenetlusDTO.builder()
                .name("Kadri Karu")
                .personalCode(47701256541L)
                .email("kadri@ka.ru")
                .reason("Ei meeldi kassid").build();
        MenetlusDTO expectedDto = dto;
        expectedDto.setId(1);
        MenetlusDTO actualDto = (MenetlusDTO) menetlusService.save(dto).getData();
        assertEquals(expectedDto, actualDto);
    }

    @Test
    void validateInfo_success() {
        Menetlus menetlus = Menetlus.builder()
                .email("kiisu@miisu.ee")
                .personalCode(39312034227L).build();
        assertTrue(menetlusService.validateInfo(menetlus));
    }

    @Test
    void validateInfo_failure_email() {
        Menetlus menetlus = Menetlus.builder()
                .email("kiisu@mii@su.ee")
                .personalCode(39312034227L).build();
        assertFalse(menetlusService.validateInfo(menetlus));
    }

    @Test
    void validateInfo_failure_code() {
        Menetlus menetlus = Menetlus.builder()
                .email("kiisu@miisu.ee")
                .personalCode(39312034220L).build();
        assertFalse(menetlusService.validateInfo(menetlus));
    }

    @Test
    void setEmailDeliveredTest() {
        Menetlus menetlus = Menetlus.builder()
                .name("Madis Maalt")
                .personalCode(39312034227L)
                .email("madis@maa.lt")
                .emailDelivered(true)
                .reason("Maakas").build();
        menetlus = menetlusService.save(menetlus);
        menetlus = menetlusService.setEmailDelivered(menetlus.getId(), false);
        assertFalse(menetlus.isEmailDelivered());
    }
}

package io.nqa.menetlus.service;

import io.nqa.menetlus.entity.Menetlus;
import io.nqa.menetlus.model.CustomResponse;
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
    void saveMenetlusTest_failure_email() {
        Menetlus menetlus = Menetlus.builder()
                .name("Kristjan Männimets")
                .personalCode(39312034227L)
                .email("kristjan@mannimets@gmail")
                .reason("Omab liiga palju kasse").build();
        assertNull(menetlusService.save(menetlus));
    }

    @Test
    void saveMenetlusTest_failure_code() {
        Menetlus menetlus = Menetlus.builder()
                .name("Kristjan Männimets")
                .personalCode(39312034229L)
                .email("kristjan.mannimets@gmail.com")
                .reason("Omab liiga palju kasse").build();
        assertNull(menetlusService.save(menetlus));
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
        CustomResponse expecterResponse = new CustomResponse(true, expectedDto);
        CustomResponse actualResponse = menetlusService.save(dto);
        assertEquals(expecterResponse, actualResponse);
    }

    @Test
    void saveMenetlusTest_DTO_failure_email() {
        MenetlusDTO dto = MenetlusDTO.builder()
                .name("Kadri Karu")
                .personalCode(47701256547L)
                .email("kadri@karu")
                .reason("Ei meeldi kassid").build();
        CustomResponse expectedResponse = new CustomResponse(false, "Vigane e-kirja aadress");
        CustomResponse actualResponse = menetlusService.save(dto);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void saveMenetlusTest_DTO_failure_code() {
        MenetlusDTO dto = MenetlusDTO.builder()
                .name("Kadri Karu")
                .personalCode(47701256458L)
                .email("kadri@ka.ru")
                .reason("Ei meeldi kassid").build();
        CustomResponse expectedResponse = new CustomResponse(false, "Vigane isikukood");
        CustomResponse actualResponse = menetlusService.save(dto);
        assertEquals(expectedResponse, actualResponse);
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
    void setEmailDeliveredTest_success() {
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

    @Test
    void setEmailDeliveredTest_failure() {
        Menetlus menetlus = Menetlus.builder()
                .name("Madis Maalt")
                .personalCode(39312034227L)
                .email("madis@maa.lt")
                .emailDelivered(true)
                .reason("Maakas").build();
        menetlus = menetlusService.setEmailDelivered(menetlus.getId(), false);
        assertNull(menetlus);
    }
}

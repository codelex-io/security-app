package io.codelex.securityapp;


import io.codelex.securityapp.api.AddUnitRequest;
import io.codelex.securityapp.repository.RepositoryUnitService;
import io.codelex.securityapp.repository.models.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@WebMvcTest(UnitController.class)
class UnitControllerTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositoryUnitService service;

    @Test
    public void should_get_200_when_adding_unit() throws Exception {
        //given
        AddUnitRequest request = new AddUnitRequest(
                new BigDecimal(22.2222),
                new BigDecimal(11.1111),
                true
        );
        Unit unit = new Unit(
                new BigDecimal(22.2222),
                new BigDecimal(11.1111),
                true
        );
        String json = MAPPER.writeValueAsString(request);
        Mockito.lenient()
                .when(service.addUnit(any()))
                .thenReturn(unit);
        //expect
        String jsonResponse = mockMvc.perform(
                MockMvcRequestBuilders.post("/unit-api/units")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        Unit responseUnit = MAPPER.readValue(jsonResponse, new TypeReference<Unit>(){});
        
        Assertions.assertEquals(unit, responseUnit);
    }
}
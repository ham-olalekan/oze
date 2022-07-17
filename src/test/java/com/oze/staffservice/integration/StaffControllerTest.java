package com.oze.staffservice.integration;

import com.oze.staffservice.controller.StaffController;
import com.oze.staffservice.dto.StaffDto;
import com.oze.staffservice.services.StaffService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StaffController.class)
public class StaffControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StaffService staffService;

    @Test
    public void handleStaffProfileCreation()throws Exception{
        StaffDto dto = new StaffDto();
        dto.setFirstName("test-firstName");
        dto.setLastName("test-lastName");
        mockMvc.perform(post("/staff"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(staffService, times(1)).addStaff(dto);
    }
}

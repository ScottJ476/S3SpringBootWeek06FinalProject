package com.promineotech.guitar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.attribute.AclEntryPermission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class ImageUploadTest {
  
  private static final String GUITAR_IMAGE = "Martin-HD-28-Ambertone-16.jpg";
  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  void testThatTheServerCorrectlyReveivesAnImageAndReturnsAnOKResponse() 
      throws Exception {
    int numRows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "images");
    Resource image = new ClassPathResource(GUITAR_IMAGE);
    
    if(!image.exists()) {
      fail("Could not find resource %s", GUITAR_IMAGE);
    }
    
    try(InputStream inputStream = image.getInputStream()) {
      MockMultipartFile file = new MockMultipartFile("image", GUITAR_IMAGE, 
          MediaType.TEXT_PLAIN_VALUE, inputStream);
      
      // @formatter:off
      MvcResult result = mockMvc
          .perform(MockMvcRequestBuilders
              .multipart("/guitars/3/image")
              .file(file))
          .andDo(print())
          .andExpect(status().is(201))
          .andReturn();
      // @formatter:on
      
      String content = result.getResponse().getContentAsString();
      assertThat(content).isNotEmpty();
      assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "images"))
      .isEqualTo(numRows + 1);
    }
  }

}

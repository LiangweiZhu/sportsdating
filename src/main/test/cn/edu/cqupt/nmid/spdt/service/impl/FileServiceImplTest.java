package cn.edu.cqupt.nmid.spdt.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

/**
 * Created by Lawrence on 2017/11/11.
 */
public class FileServiceImplTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        FileServiceImpl fileService = new FileServiceImpl();
        mockMvc = MockMvcBuilders.standaloneSetup(fileService).build();
    }

    @Test
    public void upLoadPic() throws Exception {
        String uri = "http://localhost:8080/pic/upLoadActivePic?activeId=13";
//        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST,uri)).andDo()
    }

    @Test
    public void getActivePic() throws Exception {
    }

}
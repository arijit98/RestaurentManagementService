package com.arijit.restaurant.menu;

import com.arijit.restaurant.security.JWTAuthenticationFilter;
import com.arijit.restaurant.security.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

       import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MenuControllerApiTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private FilterChainProxy springSecurityFilterChain; // Ensures security filters are applied

    @MockitoBean
    private MenuService menuService;

    private String userToken;
    private String adminToken;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(new JWTAuthenticationFilter(jwtUtil)) // Register JWT Filter
                .addFilters(springSecurityFilterChain) // Ensure Spring Security is applied
                .build();

        // Generate JWT tokens
        userToken = "Bearer " + jwtUtil.generateToken("user", "ROLE_USER");
        adminToken = "Bearer " + jwtUtil.generateToken("admin", "ROLE_ADMIN");
    }


    @Test
    public void testGetAllMenuItems() throws Exception {

        var request = MockMvcRequestBuilders
                .get("/api/v1/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", userToken);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void testGetAllMenuItemsAsAdmin() throws Exception {

        var request = MockMvcRequestBuilders
                .get("/api/v1/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", adminToken);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void testAddMenuItemAsAdmin() throws Exception {
        var menuItemJson = """
                {
                    "name": "Burger",
                    "price": 5.99
                }
                """;

        var request = post("/api/v1/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", adminToken)
                .content(menuItemJson);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUnauthenticatedAccess() throws Exception {
        var request = MockMvcRequestBuilders
                .get("/api/v1/menu")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    @Test
    public void testAccessDeniedAsUser() throws Exception {
        var menuItemJson = """
                {
                    "name": "Pasta",
                    "price": 7.99
                }
                """;

        var request = post("/api/v1/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", userToken)
                .content(menuItemJson);

        mockMvc.perform(request)
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    public void testServerError() throws Exception {
        var menuItemJson = """
                {
                    "name": "Pizza",
                    "price": 8.99
                }
                """;

        // Simulate a server error
        Mockito.when(menuService.addMenuItem(Mockito.any())).thenThrow(new RuntimeException("Server error"));

        var request = post("/api/v1/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", adminToken)
                .content(menuItemJson);

        mockMvc.perform(request)
                .andExpect(status().isInternalServerError())
                .andReturn();
    }
    
    
}

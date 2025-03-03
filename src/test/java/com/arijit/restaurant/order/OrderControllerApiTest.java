package com.arijit.restaurant.order;

import com.arijit.restaurant.security.JWTAuthenticationFilter;
import com.arijit.restaurant.security.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class OrderControllerApiTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private FilterChainProxy springSecurityFilterChain; // Ensures security filters are applied

    @MockitoBean
    private OrderService menuService;

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
    public void testGetAllOrdersForTable_return200() throws Exception {

        // Test for status 200 - OK
        var okRequest = MockMvcRequestBuilders
                .get("/api/v1/orders/1")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(okRequest)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }
    
    
    @Test
    public void testAddOrder() throws Exception {
        var orderJson = """
                {
                    "tableId": 1,
                    "items": [
                        {
                            "menuItemId": 1,
                            "quantity": 2
                        },
                        {
                            "menuItemId": 2,
                            "quantity": 1
                        }
                    ]
                }
                """;

        var request = post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderJson);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUpdateOrder() throws Exception {
        var updateOrderJson = """
                {
                    "orderId": 1,
                    "items": [
                        {
                            "menuItemId": 1,
                            "quantity": 3
                        }
                    ]
                }
                """;

        var request = MockMvcRequestBuilders
                .put("/api/v1/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateOrderJson);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

}

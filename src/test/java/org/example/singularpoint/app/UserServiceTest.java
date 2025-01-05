package org.example.singularpoint.app;

import lombok.extern.slf4j.Slf4j;
import org.example.singularpoint.domain.User;
import org.example.singularpoint.domain.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeAll
    static void beforeAll() {
        log.info("beforeAll");
    }

    @AfterAll
    static void afterAll() {
        log.info("afterAll");
    }

    @BeforeEach
    void setUp() {
        log.info("setUp");
    }

    @AfterEach
    void tearDown() {
        log.info("tearDown");
    }

    @Test
    @DisplayName("create")
    void create() {
        Mockito.when(userRepository.insert(Mockito.any())).thenReturn(1);
        User user = new User();
        userService.create(user);
    }
}
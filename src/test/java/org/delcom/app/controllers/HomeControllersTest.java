package org.delcom.app.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    @DisplayName("Mengembalikan pesan selamat datang yang benar")
    void hello_ShouldReturnWelcomeMessage() {
        // Arrange
        HomeController controller = new HomeController();

        // Act
        String result = controller.hello();

        // Assert
        assertEquals("Hay, selamat datang di Spring Boot!", result);
    }
}
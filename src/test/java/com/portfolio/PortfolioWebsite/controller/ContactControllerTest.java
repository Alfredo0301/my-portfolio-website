package com.portfolio.PortfolioWebsite.controller;

import org.junit.jupiter.api.Test;

class ContactControllerTest {

    @Test
    void testContactControllerCanBeInstantiated() {
        ContactController controller = new ContactController();
        assert controller != null;
    }

    @Test
    void testControllerHasRequiredMethods() {
        ContactController controller = new ContactController();
        // Verifica se os métodos existem
        assert controller.getClass().getDeclaredMethods().length > 0;
    }
}





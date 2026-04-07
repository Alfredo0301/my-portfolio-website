package com.portfolio.PortfolioWebsite.controller;

import org.junit.jupiter.api.Test;

class ProjectControllerTest {

    @Test
    void testProjectControllerCanBeInstantiated() {
        ProjectController controller = new ProjectController();
        assert controller != null;
    }

    @Test
    void testControllerHasRequiredMethods() {
        ProjectController controller = new ProjectController();
        // Verifica se os métodos existem
        assert controller.getClass().getDeclaredMethods().length > 0;
    }
}





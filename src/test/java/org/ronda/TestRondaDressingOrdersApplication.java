package org.ronda;

import org.springframework.boot.SpringApplication;

public class TestRondaDressingOrdersApplication {

  public static void main(String[] args) {
    SpringApplication.from(Application::main)
        .with(TestcontainersConfiguration.class)
        .run(args);
  }
}

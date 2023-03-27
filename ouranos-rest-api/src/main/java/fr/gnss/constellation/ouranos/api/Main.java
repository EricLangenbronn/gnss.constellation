package fr.gnss.constellation.ouranos.api;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain(name = "OuranosApp")
public class Main {

  public static void main(String... args) {
    Quarkus.run(OuranosApp.class, args);
  }

  public static class OuranosApp implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
      System.out.println("Do startup logic here");
      Quarkus.waitForExit();
      return 0;
    }
  }
}

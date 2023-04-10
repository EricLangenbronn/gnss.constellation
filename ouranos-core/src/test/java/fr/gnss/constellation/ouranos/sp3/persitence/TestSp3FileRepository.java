package fr.gnss.constellation.ouranos.sp3.persitence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TestSp3FileRepository {

  @Test
  public void whenInitClassAndSp3DirectoryDoesNotExist_thenError() {

    Sp3FileDao sp3FileRepository = new Sp3FileDao(new Sp3StorageDirectory(new File("Z:\\zzzz")));

    assertThrows(RuntimeException.class, sp3FileRepository::init);
  }

  @Test
  public void whenSp3DirectoryContainsSp3File_thenReturnIt() {
    Sp3FileDao sp3FileRepository = new Sp3FileDao(new Sp3StorageDirectory(Paths.get("src", "test", "resources", "sp3File").toFile()));

    List<Sp3File> sp3FilesInSp3Directory = sp3FileRepository.getListSp3File();

    assertNotNull(sp3FilesInSp3Directory);
    assertTrue(sp3FilesInSp3Directory.size() > 0);
  }
}
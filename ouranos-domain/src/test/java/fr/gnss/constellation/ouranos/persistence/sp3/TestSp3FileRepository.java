package fr.gnss.constellation.ouranos.persistence.sp3;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import fr.gnss.constellation.ouranos.persistence.sp3.file.Sp3FileRepository;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSp3FileRepository {

    @Test
    public void whenInitClassAndSp3DirectoryDoesNotExist_thenError() {
        Sp3FileRepository sp3FileRepository = new Sp3FileRepository(new File("Z:\\zzzz"));

        assertThrows(RuntimeException.class, sp3FileRepository::init);
    }

    @Test
    public void whenSp3DirectoryContainsSp3File_thenReturnIt() {
        Sp3FileRepository sp3FileRepository = new Sp3FileRepository(Paths.get("src", "test", "resources", "sp3File").toFile());

        List<Sp3File> sp3FilesInSp3Directory = sp3FileRepository.getListSp3File();

        assertNotNull(sp3FilesInSp3Directory);
        assertTrue(sp3FilesInSp3Directory.size() > 0);
    }
}



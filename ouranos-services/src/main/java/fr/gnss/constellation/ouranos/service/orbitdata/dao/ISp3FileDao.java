package fr.gnss.constellation.ouranos.service.orbitdata.dao;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;

import java.io.File;
import java.util.List;

public interface ISp3FileDao {

    List<String> getListSp3FileName(String pathSp3Dir);

    List<File> getListSp3File(String pathSp3Dir);

    File getFile(String sp3Repertoire, Sp3FileName sp3FileName);
}

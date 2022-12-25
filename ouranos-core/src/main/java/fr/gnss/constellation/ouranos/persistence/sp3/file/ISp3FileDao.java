package fr.gnss.constellation.ouranos.persistence.sp3.file;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;

import java.io.InputStream;
import java.util.List;

public interface ISp3FileDao {


    List<Sp3File> getListSp3File();

    Sp3File getFile(Sp3FileName sp3FileName);

    Sp3File saveSp3File(Sp3FileName sp3FileName, InputStream inputStream);
}

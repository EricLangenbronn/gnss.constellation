package fr.gnss.constellation.ouranos.persistence.sp3.stream;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ISp3InputStreamRepository {

    InputStream downloadSp3File(Sp3FileName sp3FileName, boolean unCompressSp3File);

    Map<Sp3FileName, InputStream> downloadSp3File(List<Sp3FileName> sp3FileNames, boolean unCompressSp3File);

}

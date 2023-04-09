package fr.gnss.constellation.ouranos.orbitdata.sp3.service;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import java.util.List;

public interface ISp3Service {

  void downloadsAndStoresIfNotExist(List<Sp3FileName> sp3FileNames);

  boolean isSp3FileExisting(Sp3FileName sp3FileName);

  Sp3File getSp3File(Sp3FileName sp3FileName);
}

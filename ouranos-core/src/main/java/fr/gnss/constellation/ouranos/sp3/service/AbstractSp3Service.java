package fr.gnss.constellation.ouranos.sp3.service;

import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.sp3.persitence.ISp3FileDao;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractSp3Service implements ISp3Service {

  private final ISp3FileDao sp3FileRepository;


  public abstract void downloadsAndStoresIfNotExist(List<Sp3FileName> sp3FileNames);

  @Override
  public boolean isSp3FileExisting(Sp3FileName sp3FileName) {
    return sp3FileRepository.getFile(sp3FileName) != null;
  }

  @Override
  public Sp3File getSp3File(Sp3FileName sp3FileName) {
    return sp3FileRepository.getFile(sp3FileName);
  }
}

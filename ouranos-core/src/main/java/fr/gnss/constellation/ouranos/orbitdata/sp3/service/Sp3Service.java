package fr.gnss.constellation.ouranos.orbitdata.sp3.service;


import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3File;
import fr.gnss.constellation.ouranos.librairy.almanach.sp3.Sp3FileName;
import fr.gnss.constellation.ouranos.orbitdata.sp3.infrastructure.ISp3InputStreamDao;
import fr.gnss.constellation.ouranos.orbitdata.sp3.persitence.ISp3FileDao;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class Sp3Service implements ISp3Service {

  // -------------------- Services --------------------

  private final ISp3FileDao sp3FileRepository;
  private final ISp3InputStreamDao sp3InputStreamRepository;
  private final AuthorizedNewSp3Download authorizedNewSp3Download;
  private final AuthorizedParallelSp3Download authorizedParallelSp3Download;

  // -------------------- Methodes de l'interface --------------------

  @Override
  public void downloadsAndStoresIfNotExist(List<Sp3FileName> sp3FileNames) {

    if (authorizedNewSp3Download.isAuthorized()) {
      if (authorizedParallelSp3Download.isAuthorized()) {
        log.info("Parallel download Sp3 files");
        Optional.ofNullable(sp3FileNames).stream().parallel().flatMap(Collection::parallelStream)
            .forEach(sp3FileName -> {
              if (!isSp3FileExisting(sp3FileName)) {
                sp3FileRepository.saveSp3File(sp3FileName, sp3InputStreamRepository.downloadSp3File(sp3FileName, true));
              }
            });
      } else {
        log.info("Consecutive download Sp3 files");
        Optional.ofNullable(sp3FileNames).stream().flatMap(Collection::stream)
            .forEach(sp3FileName -> {
              if (!isSp3FileExisting(sp3FileName)) {
                sp3FileRepository.saveSp3File(sp3FileName, sp3InputStreamRepository.downloadSp3File(sp3FileName, true));
              }
            });
      }
    } else {
      log.warn("Download new SP3 file not authorized");
    }
  }

  @Override
  public boolean isSp3FileExisting(Sp3FileName sp3FileName) {
    return sp3FileRepository.getFile(sp3FileName) != null;
  }

  @Override
  public Sp3File getSp3File(Sp3FileName sp3FileName) {
    return sp3FileRepository.getFile(sp3FileName);
  }

}

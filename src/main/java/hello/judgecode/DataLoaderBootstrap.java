package hello.judgecode;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 존재하지 않는 챌린지를 추가하기 위한 초기화 과정입니다. 실제 상용 애플리케이션에서는 사용하지 않습니다.
 */
@Component
public class DataLoaderBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private DataLoader dataLoader;

  public DataLoaderBootstrap(DataLoader dataLoader) {
    this.dataLoader = dataLoader;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    dataLoader.loadData();
  }

}

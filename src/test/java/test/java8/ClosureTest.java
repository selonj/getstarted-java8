package test.java8;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 2016-04-27.
 */
public class ClosureTest {
  @Test public void implementsInterfaceWithNoArgs() throws Exception {
    final AtomicBoolean execution = new AtomicBoolean(false);
    Runnable runner = () -> {
      execution.set(true);
    };

    runner.run();

    assertTrue(execution.get());
  }

  @Test public void implementsInterfaceWithArgs() throws Exception {
    final AtomicReference<String> typeCollector = new AtomicReference<>(null);
    View view = typeCollector::set;

    view.render("json");

    assertThat(typeCollector.get(), equalTo("json"));
  }

  public interface View {
    void render(String type);
  }
}

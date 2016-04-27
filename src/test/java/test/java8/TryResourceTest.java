package test.java8;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Administrator on 2016-04-27.
 */
public class TryResourceTest {
  @Test public void closeResourceAutomatically() throws Exception {
    AtomicBoolean closed = new AtomicBoolean(false);

    try (Closeable resource = () -> closed.set(true)) {
    }

    assertTrue(closed.get());
  }

  @Test public void reportsErrorsWhenClosingFailed() throws Exception {
    IOException error = new IOException();

    Closeable throwsExceptionWhenClosed = () -> {
      throw error;
    };

    try {
      try (Closeable candidate = throwsExceptionWhenClosed) {
      }
      fail("should failed");
    } catch (IOException occurred) {
      assertThat(occurred,equalTo(error));
    }
  }
}

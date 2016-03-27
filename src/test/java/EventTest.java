import io.github.linead.nametags.domain.Event;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EventTest {

    @Test
    public void ensureDateFormat() {
        Event event = new Event();

        event.setTime(1459931400000l);

        assertThat(event.getDate(), is("2016-04-06"));

    }

}

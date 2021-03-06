package demo;

import com.gemstone.gemfire.cache.Region;
import org.junit.*;

import java.util.List;

import static demo.GemfireConnectionUtil.getRegionConnection;
import static demo.GemfireTestingUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class FooRegionRepositoryTest {
    private static Region<String, Long> fooRegion;

    private FooRegionRepository repository;

    @BeforeClass
    public static void beforeAll() {
        // We don't get to use @Value from our application.yml file, since JUnit has not got any of the spring boot
        // context loaded (including the yaml reader)
        fooRegion = getRegionConnection("FooRegion", gemfireLocatorHost(), gemfireLocatorPort());
    }

    @Before
    public void setup() {
        repository = new FooRegionRepository(fooRegion);

        emptyRegion(fooRegion);
    }

    @Test
    public void testGetAll() {
        fooRegion.put("hello", 3L);
        fooRegion.put("world", 11L);
        fooRegion.put("etc", 14L);

        List<Long> allValues = repository.getAllValues();

        assertThat(allValues, containsInAnyOrder(3L, 11L, 14L));
    }
}
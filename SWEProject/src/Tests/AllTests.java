package Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({
	TestSearching.class,
	TestPlaying.class,
	TestManager.class,
	TestUsers.class,
	TestSubscribing.class,
	TestLikeDownload.class
})

public class AllTests {

}
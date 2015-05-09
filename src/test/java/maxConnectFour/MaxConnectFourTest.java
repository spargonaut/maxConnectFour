package maxConnectFour;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MaxConnectFourTest {

	MaxConnectFour maxConnectFour;
	
	@Before
	public void setUp() {
		maxConnectFour = new MaxConnectFour();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowAnExceptionWhenCLIArgumentIsNotInteractiveOrOneMove() {
		String playMode = "smurf";
		maxConnectFour.validateGameModeArgument(playMode);
	}
	
	@Test
	public void shouldNotThrowAnExceptionWhenCLIArgumentIsInteractive() {
		String playMode = "interactive";
		maxConnectFour.validateGameModeArgument(playMode);
	}

	@Test
	public void shouldNotThrowAnExceptionWhenCLIArgumentIsOneMove() {
		String playMode = "one-move";
		maxConnectFour.validateGameModeArgument(playMode);
	}
	
	@Test
	public void shouldParseToInteractiveModeWhenInteractiveIsGivenAsAnArgument() {
		String modeString = "interactive";
		PlayMode actualPlayMode = maxConnectFour.parsePlayMode(modeString);
		PlayMode expectedPlayMode = PlayMode.INTERACTIVE;
		assertEquals(expectedPlayMode, actualPlayMode);
	}
	
	@Test
	public void shouldParseToONE_MOVEModeWhenOneMoveIsGivenAsAnArgument() {
		String modeString = "one-move";
		PlayMode actualPlayMode = maxConnectFour.parsePlayMode(modeString);
		PlayMode expectedPlayMode = PlayMode.ONE_MOVE;
		assertEquals(expectedPlayMode, actualPlayMode);
	}
}

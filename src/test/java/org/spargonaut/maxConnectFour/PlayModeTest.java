package org.spargonaut.maxConnectFour;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PlayModeTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldParseThePlayModeFromAStringToAnEnum() {
        String modeString = "InterActive";
        PlayMode playMode = PlayMode.getEnum(modeString);
    }

    @Test
    public void shouldThrowAnExceptionWhenUnableToParseToPlayMode() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Unable to determine PlayMode\n");

        String badPlayMode = "foo";
        PlayMode.getEnum(badPlayMode);
    }
}
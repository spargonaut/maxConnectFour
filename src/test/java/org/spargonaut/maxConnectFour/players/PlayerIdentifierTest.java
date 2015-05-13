package org.spargonaut.maxConnectFour.players;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PlayerIdentifierTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldParseMixedCaseStringToComputer() {
        String computer = "CoMpuTer";
        PlayerIdentifier playerIdentifier = PlayerIdentifier.getEnum(computer);
        assertThat(playerIdentifier, is(PlayerIdentifier.COMPUTER));
    }

    @Test
    public void shouldThrowAnIllegalArgumentExceptionWhenTheStringDoesNotMatchAValue() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Houston we have a problem!\n" +
                "I can't tell whos turn it is next\n\n" +
                "you're going to have to try again.\n" +
                "next time, please indicate if it is the human's turn next or the computer's turn" +
                "\n\n\n\n");

        String inputValue = "foo";
        PlayerIdentifier.getEnum(inputValue);
    }
}
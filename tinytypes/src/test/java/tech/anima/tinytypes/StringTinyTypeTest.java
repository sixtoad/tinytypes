package tech.anima.tinytypes;

import org.junit.Test;
import org.junit.Assert;

public class StringTinyTypeTest {

    @Test
    public void isEqualToSameTypeWithSameValue() {
        final Samples.Str lhs = Samples.Str.of("1");
        final Samples.Str rhs = Samples.Str.of("1");
        Assert.assertEquals(lhs, rhs);
    }

    @Test
    public void isDifferentThanSameTypeWithDifferentValue() {
        final Samples.Str lhs = Samples.Str.of("1");
        final Samples.Str rhs = Samples.Str.of("2");
        Assert.assertNotEquals(lhs, rhs);
    }

    @Test
    public void isDifferentThanDifferentSiblingTypeWithSameValue() {
        final Samples.Str lhs = Samples.Str.of("1");
        final Samples.OtherStr rhs = Samples.OtherStr.of("1");
        Assert.assertNotEquals(lhs, rhs);
    }

    @Test
    public void toStringContainsTTName() {
        final String expected = "Str";
        final String toString = Samples.Str.of("1").toString();
        Assert.assertTrue(toString.contains(expected));
    }

    @Test
    public void toStringContainsTTValue() {
        final String expected = "1";
        final String toString = Samples.Str.of("1").toString();
        Assert.assertTrue(toString.contains(expected));
    }

}

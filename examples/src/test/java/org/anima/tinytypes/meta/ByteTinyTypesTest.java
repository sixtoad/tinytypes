package org.anima.tinytypes.meta;

import org.anima.tinytypes.Samples;
import org.junit.Test;
import org.junit.Assert;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class ByteTinyTypesTest {

    public static class IsMetaOf {

        @Test
        public void yieldsTrueWhenCandidateSuperclassIsByteTT() {
            final boolean got = new ByteTinyTypes().isMetaOf(Samples.Byte.class);
            Assert.assertTrue(got);
        }

        @Test
        public void yieldsTrueWhenCandidateSuperclassIsNotByteTT() {
            final boolean got = new ByteTinyTypes().isMetaOf(Samples.class);
            Assert.assertFalse(got);
        }

        @Test
        public void yieldsTrueWhenAncestorOfCandidateIsByteTTButNotDirectSuperclass() {
            final boolean got = new ByteTinyTypes().isMetaOf(Samples.ByteIndirectAncestor.class);
            Assert.assertFalse(got);
        }

    }

    public static class Includes {

        @Test
        public void yieldsTrueWhenCandidateSuperclassIsByteTT() {
            final boolean got = ByteTinyTypes.includes(Samples.Byte.class);
            Assert.assertTrue(got);
        }

        @Test
        public void yieldsTrueWhenCandidateSuperclassIsNotByteTT() {
            final boolean got = ByteTinyTypes.includes(Samples.class);
            Assert.assertFalse(got);
        }

        @Test
        public void yieldsTrueWhenAncestorOfCandidateIsByteTTButNotDirectSuperclass() {
            final boolean got = ByteTinyTypes.includes(Samples.ByteIndirectAncestor.class);
            Assert.assertFalse(got);
        }
    }

    public static class NewInstance {

        @Test
        public void yieldsNewInstanceOfSpecifiedTTWrappingGivenValue() {
            final Samples.Byte expected = new Samples.Byte((byte) 1);
            final Samples.Byte got = new ByteTinyTypes().newInstance(Samples.Byte.class, (byte) 1);
            Assert.assertEquals(expected, got);
        }

        @Test(expected = IllegalArgumentException.class)
        public void throwsForNonTT() {
            new ByteTinyTypes().newInstance(Samples.ByteIndirectAncestor.class, (byte) 1);
        }

        @Test(expected = IllegalArgumentException.class)
        public void throwsForNonByteCastableValue() {
            new ByteTinyTypes().newInstance(Samples.Byte.class, (short) 1);
        }

        @Test(expected = IllegalArgumentException.class)
        public void throwsForAbstractTT() {
            new ByteTinyTypes().newInstance(Samples.AbstractByte.class, (byte) 1);
        }

        @Test(expected = IllegalArgumentException.class)
        public void throwsForTTWithNoOneargCtor() {
            new ByteTinyTypes().newInstance(Samples.NoOneArgCtorByte.class, (byte) 1);
        }

    }

    public static class Stringify {

        @Test
        public void yieldsStringificationOfValue() {
            final String expected = "1";
            final String got = new ByteTinyTypes().stringify(new Samples.Byte((byte) 1));
            Assert.assertEquals(expected, got);
        }
    }

}

package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;
import java.util.stream.*;
class ArrayUtilsTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("generator")
    void reverse(String description, int[] arrayOfInts, int startIndex, int endIndex, int[] expectedArray) {
        ArrayUtils.reverse(arrayOfInts, startIndex, endIndex);
        assertThat(arrayOfInts).isEqualTo(expectedArray);
    }

    private static Stream<Arguments> generator() {
        //Boundary #1 - differences in startIndex and endIndex
        Arguments tc1 = Arguments.of("startIndex < endIndex", ints(1, 2, 3, 4, 5), 1, 4, ints(1, 4, 3, 2, 5));
        Arguments tc2 = Arguments.of("startIndex > endIndex", ints(1, 2, 3, 4, 5), 4, 1, ints(1, 2, 3, 4, 5));
        Arguments tc3 = Arguments.of("startIndex == endIndex", ints(1, 2, 3, 4, 5), 1, 1, ints(1, 2, 3, 4, 5));
        Arguments tc4 = Arguments.of("startIndex + 1 == endIndex", ints(1, 2, 3, 4, 5), 1, 2, ints(1, 2, 3, 4, 5));
        Arguments tc5 = Arguments.of("startIndex + 2 == endIndex", ints(1, 2, 3, 4, 5), 1, 3, ints(1, 3, 2, 4, 5));

        //Special cases - zeros
        Arguments tc6 = Arguments.of("startIndex zero", ints(1, 2, 3, 4, 5), 0, 4, ints(4, 3, 2, 1, 5));
        Arguments tc7 = Arguments.of("endIndex zero", ints(1, 2, 3, 4, 5), 0, 0, ints(1, 2, 3, 4, 5));

        //Boundary #2 - endIndex near end size
        Arguments tc8 = Arguments.of("endIndex larger than array size", ints(1, 2, 3, 4, 5), 1, 10,
                ints(1, 5, 4, 3, 2));
        Arguments tc9 = Arguments.of("endIndex precisely array size", ints(1, 2, 3, 4, 5), 1, 5, ints(1, 5, 4, 3, 2));
        Arguments tc10 = Arguments.of("endIndex last index of array", ints(1, 2, 3, 4, 5), 1, 4, ints(1, 4, 3, 2, 5));

        //Special cases - null
        Arguments tc11 = Arguments.of("null array", null, 0, 1, null);

        //Special cases - negatives
        Arguments tc12 = Arguments.of("negative start index", ints(1, 2, 3, 4, 5), -1, 3, ints(3, 2, 1, 4, 5));
        Arguments tc13 = Arguments.of("negative end index", ints(1, 2, 3, 4, 5), 1, -1, ints(1, 2, 3, 4, 5));

        //Boundary #3 - startIndex larger than array
        Arguments tc14 = Arguments.of("startIndex larger than array size", ints(1, 2, 3, 4, 5), 10, 3,
                ints(1, 2, 3, 4, 5));

        //Special cases - empty
        Arguments tc15 = Arguments.of("empty array", ints(), 0, 0, ints());
        return Stream.of(tc1, tc2, tc3, tc4, tc5, tc6, tc7, tc8, tc9, tc10, tc11, tc12, tc13, tc14, tc15);
    }

    private static int[] ints(int... nums) {
        return nums;
    }
}
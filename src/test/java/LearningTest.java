import org.example.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Executable;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LearningTest {
    Calculator calculator;

    @BeforeEach
    public void setUp(){
        this.calculator = new Calculator();
    }

    @AfterEach
    public void tearDown(){
        this.calculator = null;
    }

    @BeforeAll
    public static void init(){
        System.out.println("initialize");
    }

    @AfterAll
    public static void fin(){
        System.out.println("finalize");
    }

    @Test
    void testAdd() {
        assertEquals(100, this.calculator.add(100));
        assertEquals(150, this.calculator.add(50));
        assertEquals(130, this.calculator.add(-20));
    }

    @Test
    void testSub() {
        assertEquals(-100, this.calculator.sub(100));
        assertEquals(-150, this.calculator.sub(50));
        assertEquals(-130, this.calculator.sub(-20));
    }

    @Test
    void testThrowsException(){
        assertThrows(IllegalArgumentException.class, () ->{
            this.calculator.divide(0);
        });
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testOnWindows(){
        System.out.println("Testing on windows os");
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.MAC})
    void testOnUnix(){
        System.out.println("Testing on unix os");
    }

    @ParameterizedTest
    @MethodSource
//    @CsvSource({ "abc, Abc", "APPLE, Apple", "gooD, Good" })
//    @CsvFileSource(resources = { "/test-capitalize.csv" })
    void TestString(String input, String res){
        assertNotEquals(input, res);
    }

    public static List<Arguments> TestString() {
        return List.of( // arguments:
                Arguments.of("abc", "Abc"), //
                Arguments.of("APPLE", "Apple"), //
                Arguments.of("gooD", "Good"));
    }


}

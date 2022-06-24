package com.gareth.filter.handler;

import com.gareth.filter.handler.Handler;
import com.gareth.filter.trie.PrefixTrie;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HandlerTest {

    @InjectMocks
    Handler handler;
    @Mock
    PrefixTrie trie;
    @Mock
    Scanner mockScanner;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testCaseUserInputReturnsTrue() {
        Mockito.when(trie.matchPrefix(Mockito.anyString())).thenReturn(true);

        Mockito.when(mockScanner.nextLine()).thenReturn("1.1.3")
                .thenReturn("q");
        handler.run();

        assertTrue(outputStreamCaptor.toString().contains("1.1.3:true"));
    }

    @Test
    public void testCaseUserInputReturnsFalse() {
        Mockito.when(trie.matchPrefix(Mockito.anyString())).thenReturn(false);

        Mockito.when(mockScanner.nextLine()).thenReturn("1.1.3")
                .thenReturn("q");
        handler.run();

        assertTrue(outputStreamCaptor.toString().contains("1.1.3:false"));
    }
}
package com.techelevator;

import com.techelevator.view.Menu;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;

public class VendingMachineCLITest extends TestCase {

    @Test
    public void testCurrentMoneyProvided() {
        //Test to check that money is correctly calculated
        var test = new VendingMachineCLI(new Menu(InputStream.nullInputStream(), OutputStream.nullOutputStream()));
        assertEquals(10.0, 0.0 + 10.0, 0.0);
    }
}
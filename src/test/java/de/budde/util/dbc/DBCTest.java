package de.budde.util.dbc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DBCTest {

    @Test
    public void testOK() {
        DBC.isTrue(true);
        DBC.isTrueOnErrorPrintStacktrace(true);
        DBC.isTrue(true, "Text");
        DBC.isFalse(false);
        DBC.isFalseOnErrorPrintStacktrace(false);
        DBC.isFalse(false, "Text");
        DBC.notNull(DBCTest.class);
        DBC.notNull(DBCTest.class, "Text");
        DBC.nonEmptyString(" a ");
        DBC.nonEmptyString(" a ", "Text");
        DBC.nonEmptyString(" a ", "");
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testIsTrue1() {
        this.thrown.expect(DbcException.class);
        this.thrown.expectMessage("Assertion is violated");
        DBC.isTrue(false);
    }

    @Test
    public void testIsTrue2() {
        this.thrown.expect(DbcException.class);
        this.thrown.expectMessage("Text");
        DBC.isTrue(false, "Text");
    }

    @Test
    public void testIsTrue3() {
        this.thrown.expect(DbcException.class);
        this.thrown.expectMessage("Assertion is violated");
        DBC.isTrueOnErrorPrintStacktrace(false);
    }

    @Test
    public void testIsFalse1() {
        this.thrown.expect(DbcException.class);
        this.thrown.expectMessage("Assertion is violated");
        DBC.isFalse(true);
    }

    @Test
    public void testIsFalse2() {
        this.thrown.expect(DbcException.class);
        this.thrown.expectMessage("Text");
        DBC.isFalse(true, "Text");
    }

    @Test
    public void testIsFalse3() {
        this.thrown.expect(DbcException.class);
        this.thrown.expectMessage("Assertion is violated");
        DBC.isFalseOnErrorPrintStacktrace(true);
    }

}

package de.budde.util.dbc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DBCExceptionTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test1() {
        this.thrown.expect(DbcException.class);
        this.thrown.expectMessage("Text");
        throw new DbcException("Text");
    }

    @Test
    public void test2() {
        this.thrown.expect(DbcException.class);
        throw new DbcException(null);
    }

    @Test
    public void test3() {
        this.thrown.expect(DbcException.class);
        this.thrown.expectMessage("Text");
        throw new DbcException("Text", new DbcException("Cause"));
    }

    @Test
    public void test4() {
        this.thrown.expect(DbcException.class);
        throw new DbcException(null, new DbcException("Cause"));
    }
}

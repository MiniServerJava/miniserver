package de.budde.util.dbc;

/**
 * Assertion-Checker<br>
 * - for programming DBC (design by contract), used for pre- and post-conditions.<br>
 * - everywhere to validate assumptions. <br>
 * If an assertion is violated, a <b>DbcException</b> is thrown. <br>
 * The optional error message must state, that the assertion is <i>violated</i>, it must <i>not</i> express, what the assertion expects: <br>
 * <b>OK</b>: <code>DBC.isTrue(p == 4,"p is not equal to 4")</code><br>
 * <b>bad</b>: <code>DBC.isTrue(p == 4,"p equals 4")</code>. <br>
 * <b>often OK</b>: <code>DBC.isTrue(p == 4,"p should be equal to 4")</code> <br>
 * <br>
 * Extensions to the class are welcome.
 */
public final class DBC {
    private static final String ASSERTION_VIOLATED = "Assertion is violated";

    private DBC() {
        // only static methods
    }

    /**
     * <b>DBC:</b> asserts, that a condition is <code>true</code>. If the assertion is violated, a {@link DbcException} is thrown.
     *
     * @param b the condition to be checked
     */
    public static void isTrue(boolean b) {
        if ( !b ) {
            throw new DbcException(ASSERTION_VIOLATED);
        }
    }

    /**
     * <b>DBC:</b> asserts, that a condition is <code>true</code>. If the assertion is violated, a {@link DbcException} is thrown. The error message must state,
     * that the assertion is <i>violated</i>
     *
     * @param b the condition to be checked
     * @param msg the error message; states, that the assertion is <i>violated</i>
     */
    public static void isTrue(boolean b, String msg) {
        if ( !b ) {
            throw new DbcException(msg);
        }
    }

    /**
     * <b>DBC:</b> asserts, that a condition is <code>true</code>. If the assertion is violated, a stacktrace is printed, then a {@link DbcException} is
     * thrown.<br>
     * <br>
     * Use in rare cases. If you have written a class using the DBC paradigm and you suspect, that caller of your class catch DBC exceptions and do not react on
     * DBC exceptions as required, a printed stack trace may help unveiling such a problem.
     *
     * @param b the condition to be checked
     */
    public static void isTrueOnErrorPrintStacktrace(boolean b) {
        if ( !b ) {
            try {
                throw new DbcException(ASSERTION_VIOLATED);
            } catch ( DbcException e ) {
                e.printStackTrace(); //NOSONAR
                throw e;
            }
        }
    }

    /**
     * <b>DBC:</b> asserts, that a condition is <code>false</code>. If the assertion is violated, a {@link DbcException} is thrown.
     *
     * @param b the condition to be checked
     */
    public static void isFalse(boolean b) {
        if ( b ) {
            throw new DbcException(ASSERTION_VIOLATED);
        }
    }

    /**
     * <b>DBC:</b> asserts, that a condition is <code>false</code>. If the assertion is violated, a {@link DbcException} is thrown. The error message must
     * state, that the assertion is <i>violated</i>
     *
     * @param b the condition to be checked
     * @param msg the error message; states, that the assertion is <i>violated</i>
     */
    public static void isFalse(boolean b, String msg) {
        if ( b ) {
            throw new DbcException(msg);
        }
    }

    /**
     * <b>DBC:</b> asserts, that a condition is <code>false</code>. If the assertion is violated, a stacktrace is printed, then a {@link DbcException} is
     * thrown.<br>
     * <br>
     * Use in rare cases. If you have written a class using the DBC paradigm and you suspect, that caller of your class catch DBC exceptions and do not react on
     * DBC exceptions as required, a printed stack trace may help unveiling such a problem.
     *
     * @param b the condition to be checked
     */
    public static void isFalseOnErrorPrintStacktrace(boolean b) {
        if ( b ) {
            try {
                throw new DbcException(ASSERTION_VIOLATED);
            } catch ( DbcException e ) {
                e.printStackTrace(); //NOSONAR
                throw e;
            }
        }
    }

    /**
     * <b>DBC:</b> an assertion <i>is</i> violated, thus a {@link DbcException} is thrown.
     */
    public static void fail() {
        throw new DbcException(ASSERTION_VIOLATED);
    }

    /**
     * <b>DBC:</b> an assertion <i>is</i> violated, thus a {@link DbcException} is thrown.
     *
     * @param msg the error message; states, why the callers fails to fulfill the contract
     */
    public static void fail(String msg) {
        throw new DbcException(msg);
    }

    /**
     * <b>DBC:</b> asserts, that an object reference is <i>not</i> <code>null</code>. If the assertion is violated, a {@link DbcException} is thrown.<br>
     *
     * @param o expected to be not null
     */
    public static void notNull(Object o) {
        if ( o == null ) {
            throw new DbcException("Assertion notNull is violated");
        }
    }

    /**
     * <b>DBC:</b> asserts, that an object reference is <i>not</i> <code>null</code>. If the assertion is violated, a {@link DbcException} is thrown.
     *
     * @param o expected to be not null
     * @param msg the error message; states, that the assertion is <i>violated</i>
     */
    public static void notNull(Object o, String msg) {
        if ( o == null ) {
            throw new DbcException(msg);
        }
    }

    /**
     * <b>DBC:</b> asserts, that a reference is a non-empty string. If the assertion is violated, a {@link DbcException} is thrown.
     *
     * @param s expected to be a non-empty string
     */
    public static void nonEmptyString(String s) {
        if ( s == null || s.isEmpty() ) {
            throw new DbcException("Assertion nonEmptyString violated");
        }
    }

    /**
     * <b>DBC:</b> asserts, that a reference is a non-empty string. If the assertion is violated, a {@link DbcException} is thrown.
     *
     * @param s expected to be a non-empty string
     * @param msg the error message; states, that the assertion is <i>violated</i>
     */
    public static void nonEmptyString(String s, String msg) {
        if ( s == null || s.isEmpty() ) {
            throw new DbcException(msg);
        }
    }
}
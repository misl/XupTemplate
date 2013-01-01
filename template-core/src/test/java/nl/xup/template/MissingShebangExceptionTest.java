package nl.xup.template;

import junit.framework.TestCase;

/**
 * @author Minto van der Sluis
 */
public class MissingShebangExceptionTest extends TestCase {

  // ----------------------------------------------------------------------
  // Test cases
  // ----------------------------------------------------------------------

  public void testConstructors() {
    assertNotNull( new MissingShebangException() );
  }
}

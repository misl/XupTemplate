package nl.xup.template;

import junit.framework.TestCase;

/**
 * @author Minto van der Sluis
 */
public class UnknownEngineExceptionTest extends TestCase {

  // ----------------------------------------------------------------------
  // Test cases
  // ----------------------------------------------------------------------

  public void testConstructors() {
    assertNotNull( new UnknownEngineException( "" ) );
  }

  public void testGetterSetter() {
    UnknownEngineException ueEx = new UnknownEngineException( "name" );

    // No property set yet.
    assertEquals( "name", ueEx.getEngineName() );
  }
}

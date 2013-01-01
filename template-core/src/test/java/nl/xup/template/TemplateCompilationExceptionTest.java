package nl.xup.template;

import junit.framework.TestCase;

/**
 * @author Minto van der Sluis
 */
public class TemplateCompilationExceptionTest extends TestCase {

  // ----------------------------------------------------------------------
  // Test cases
  // ----------------------------------------------------------------------

  public void testConstructors() {
    assertNotNull( new TemplateCompilationException( null ) );
    assertNotNull( new TemplateCompilationException( 1, 3, null ) );
  }

  public void testGetterSetter() {
    TemplateCompilationException tcEx = new TemplateCompilationException( null );

    // No error location so still the default.
    assertEquals( -1, tcEx.getLine() );
    assertEquals( -1, tcEx.getColumn() );

    // This time with error location.
    tcEx = new TemplateCompilationException( 1, 2, null );
    assertEquals( 1, tcEx.getLine() );
    assertEquals( 2, tcEx.getColumn() );
  }
}

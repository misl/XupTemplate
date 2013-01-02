package nl.xup.template.osgi;

import junit.framework.TestCase;

/**
 * @author Minto van der Sluis
 */
public class TemplateEngineServiceImplTest extends TestCase {

  // ----------------------------------------------------------------------
  // Test cases
  // ----------------------------------------------------------------------

  public void testConstructors() {
    // Can't instantiate directly, but we can use the singleton
    TemplateEngineServiceImpl instance = new TemplateEngineServiceImpl();
    assertNotNull( instance );
  }

  public void testExtractEngineName() {
    String engineName = null;

    // Test with no line to extract the engine name from.
    engineName = TemplateEngineServiceImpl.extractEngineName( null );
    assertNull( engineName );

    // Test with empty line to extract the engine name from.
    engineName = TemplateEngineServiceImpl.extractEngineName( "" );
    assertNull( engineName );

    // Test with missing shebang to extract the engine name from.
    engineName = TemplateEngineServiceImpl.extractEngineName( "ajkhf ahfqpfhp ahadhfa hfda" );
    assertNull( engineName );

    // Test with empty shebang start.
    engineName = TemplateEngineServiceImpl.extractEngineName( "#! empty shebang" );
    assertNull( engineName );

    // Test with shebang start.
    engineName = TemplateEngineServiceImpl.extractEngineName( "#!shebang" );
    assertNotNull( engineName );
    assertEquals( "shebang", engineName );

    // Test with shebang end.
    engineName = TemplateEngineServiceImpl.extractEngineName( "lkaf ahfa#!shebang" );
    assertNotNull( engineName );
    assertEquals( "shebang", engineName );

    // Test with shebang in line.
    engineName = TemplateEngineServiceImpl.extractEngineName( "lkaf ahfa#!shebang al;hfaha" );
    assertNotNull( engineName );
    assertEquals( "shebang", engineName );
  }
}

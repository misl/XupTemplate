package nl.xup.template;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;

import junit.framework.TestCase;
import nl.xup.template.core.SampleTemplate;

/**
 * @author Minto van der Sluis
 */
public class TemplateEngineFactoryTest extends TestCase {

  // ----------------------------------------------------------------------
  // Test cases
  // ----------------------------------------------------------------------

  public void testGetEngine() {
    // Retrieve valid test engine
    TemplateEngine engine = TemplateEngineFactory.getEngine( "test" );
    assertNotNull( engine );

    // Retrieve invalid engine
    engine = TemplateEngineFactory.getEngine( "unknown" );
    assertNull( engine );
  }

  public void testGetEngines() {
    // Retrieve valid test engine
    Collection<TemplateEngine> engines = TemplateEngineFactory.getEngines();
    assertNotNull( engines );
    assertEquals( 1, engines.size() );
    assertEquals( "test", engines.iterator().next().getName() );
  }

  public void testGetTemplate() throws Exception {
    Reader templateReader = null;

    // Template with a missing shebang
    templateReader = getTemplateReader( "missing-shebang.template" );
    try {
      TemplateEngineFactory.getTemplate( templateReader );
      assertFalse( "Expected exception", true );
    } catch( MissingShebangException e ) {
      assertTrue( "Expected exception", true );
    }

    // Template with a shebang to an unknown engine.
    templateReader = getTemplateReader( "unknown-shebang.template" );
    try {
      TemplateEngineFactory.getTemplate( templateReader );
      assertFalse( "Expected exception", true );
    } catch( UnknownEngineException e ) {
      assertTrue( "Expected exception", true );
    }

    // Template with test shebang
    templateReader = getTemplateReader( "test-shebang.template" );
    Template template = TemplateEngineFactory.getTemplate( templateReader );
    assertNotNull( template );
    assertTrue( template instanceof SampleTemplate );
  }

  private Reader getTemplateReader( String templateName ) {
    InputStream stream = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream( templateName );
    return new InputStreamReader( stream );
  }
}

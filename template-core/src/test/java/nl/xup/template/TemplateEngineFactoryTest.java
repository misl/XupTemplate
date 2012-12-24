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

	public void testConstructors() {
		// Can't instantiate directly, but we can use the singleton
		TemplateEngineFactory instance = TemplateEngineFactory.getInstance();
		assertNotNull( instance );
		
		// Getting instance twice should result in the same instance.
		TemplateEngineFactory instance2 = TemplateEngineFactory.getInstance();
		assertSame( instance, instance2);

		// After dispose the retrieved instance should be different.
		instance.dispose();
		instance2 = TemplateEngineFactory.getInstance();
		assertNotSame( instance, instance2);

		// Disposing an already disposed instance should not be 
		// a problem.
		instance2.dispose();
		instance2.dispose();
	}

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
	
	public void testExtractEngineName() {
		String engineName = null;

		// Test with no line to extract the engine name from.
		engineName = TemplateEngineFactory.extractEngineName( null );
		assertNull( engineName );

		// Test with empty line to extract the engine name from.
		engineName = TemplateEngineFactory.extractEngineName( "" );
		assertNull( engineName );

		// Test with missing shebang to extract the engine name from.
		engineName = TemplateEngineFactory.extractEngineName( "ajkhf ahfqpfhp ahadhfa hfda" );
		assertNull( engineName );

		// Test with empty shebang start.
		engineName = TemplateEngineFactory.extractEngineName( "#! empty shebang" );
		assertNull( engineName );

		// Test with shebang start.
		engineName = TemplateEngineFactory.extractEngineName( "#!shebang" );
		assertNotNull( engineName );
		assertEquals( "shebang", engineName );

		// Test with shebang end.
		engineName = TemplateEngineFactory.extractEngineName( "lkaf ahfa#!shebang" );
		assertNotNull( engineName );
		assertEquals( "shebang", engineName );

		// Test with shebang in line.
		engineName = TemplateEngineFactory.extractEngineName( "lkaf ahfa#!shebang al;hfaha" );
		assertNotNull( engineName );
		assertEquals( "shebang", engineName );
	}
	
	public void testGetTemplate() throws Exception {
		Reader templateReader = null;
		
		// Template with a missing shebang
		templateReader = getTemplateReader( "missing-shebang.template" );
		try {
			TemplateEngineFactory.getTemplate( templateReader );
			assertFalse( "Expected exception", true);
		} catch (MissingShebangException e) {
			assertTrue( "Expected exception", true);
		}
		
		// Template with a shebang to an unknown engine.
		templateReader = getTemplateReader( "unknown-shebang.template" );
		try {
			TemplateEngineFactory.getTemplate( templateReader );
			assertFalse( "Expected exception", true);
		} catch (UnknownEngineException e) {
			assertTrue( "Expected exception", true);
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

/**
 * 
 */
package nl.xup.template.groovy;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import junit.framework.TestCase;
import nl.xup.template.Bindings;
import nl.xup.template.SimpleBindings;
import nl.xup.template.Template;
import nl.xup.template.TemplateCompilationException;
import nl.xup.template.TemplateEngine;
import nl.xup.template.TemplateEngineFactory;
import nl.xup.template.UnboundPropertyException;

/**
 * @author Minto van der Sluis
 */
public class GroovyTemplateEngineTest extends TestCase {

	// ----------------------------------------------------------------------
	// Attributes
	// ----------------------------------------------------------------------
	TemplateEngine _engine = null;
	
	// ----------------------------------------------------------------------
	// Overloaded methods
	// ----------------------------------------------------------------------

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Get the engine from the factory.
		_engine = TemplateEngineFactory.getEngine( "groovy" );
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		// Clean up the engine.
		_engine = null;
	}
	
	// ----------------------------------------------------------------------
	// Test cases
	// ----------------------------------------------------------------------

	public void testGetGroovyTemplate() throws IOException, TemplateCompilationException, UnboundPropertyException {
		assertNotNull( _engine );
		assertTrue( _engine instanceof GroovyTemplateEngine );
		
		// Read template resource.
		String templateName = "nl/xup/template/groovy/valid.groovy";
		InputStream stream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream( templateName );
		Reader reader = new InputStreamReader( stream );
		
		Template template = _engine.createTemplate( reader );
		assertNotNull( template );
		assertTrue( template instanceof GroovyTemplate );
		
		// Set up the proper binding.
		Bindings bindings = new SimpleBindings();
		bindings.put( "naam", "aap" );
		bindings.put( "x", "mij" );
		bindings.put( "notUsed", "x" );
		
		// Check the results.
		assertEquals( "Hallo aap, met mij!", template.execute( bindings ) );
		
		// Same check but with the bindings passed in differently.
		template.setBindings( bindings );
		assertEquals( "Hallo aap, met mij!", template.execute() );
		template.setBindings( null );
		
		// Same template bound differently
		bindings.put( "naam", "noot" );
		bindings.remove( "notUsed" );
		
		// Check the results again.
		assertEquals( "Hallo noot, met mij!", template.execute( bindings ) );
		
		// Undo all binding.
		bindings.clear();
		
		// Check results without any binding.
		try {
			template.execute( bindings );
			assertTrue( "Exception expected", false );
		} catch ( UnboundPropertyException ex ) {
			assertEquals( "naam", ex.getPropertyName() );
		}
	}

	public void testInvalidTemplate() throws IOException {
		// Read template resource.
		String templateName = "nl/xup/template/groovy/invalid.groovy";
		InputStream stream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream( templateName );
		Reader reader = new InputStreamReader( stream );

		try {
			_engine.createTemplate( reader );
			assertTrue( "Exception expected", false );
		} catch ( TemplateCompilationException ex ) {
			assertTrue( true );
		}
	}
}

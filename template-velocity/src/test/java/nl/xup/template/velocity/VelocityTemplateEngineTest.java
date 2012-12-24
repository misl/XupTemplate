/**
 * 
 */
package nl.xup.template.velocity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import junit.framework.TestCase;
import nl.xup.template.Bindings;
import nl.xup.template.SimpleBindings;
import nl.xup.template.Template;
import nl.xup.template.TemplateCompilationException;
import nl.xup.template.TemplateEngine;
import nl.xup.template.TemplateEngineFactory;
import nl.xup.template.UnboundPropertyException;
import nl.xup.template.velocity.VelocityTemplate;
import nl.xup.template.velocity.VelocityTemplateEngine;

/**
 * @author amd32857 (Minto van der Sluis)
 */
public class VelocityTemplateEngineTest extends TestCase {

	// ----------------------------------------------------------------------
	// Object Attributes
	// ----------------------------------------------------------------------

	TemplateEngine _engine = null;
	
	// ----------------------------------------------------------------------
	// Overloaded methods
	// ----------------------------------------------------------------------

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// Retrieve the engine from the factory.
		_engine = TemplateEngineFactory.getEngine( "Velocity" );
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

	public void testGetVelocityTemplate() throws IOException, TemplateCompilationException, UnboundPropertyException {
		assertNotNull( _engine );
		assertTrue( _engine instanceof VelocityTemplateEngine );
		
		// Read template resource.
		String templateName = "nl/xup/template/velocity/valid.vm";
		InputStream stream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream( templateName );
		Reader reader = new InputStreamReader( stream );
		
		Template template = _engine.createTemplate( reader );
		assertNotNull( template );
		assertTrue( template instanceof VelocityTemplate );
		
		// Set up the proper binding.
		Bindings bindings = new SimpleBindings();
		bindings.put( "naam", "aap" );
		bindings.put( "x", "mij" );
		bindings.put( "notUsed", "x" );
		
		// Check results.
		assertEquals( "Hallo aap, met mij!", template.execute( bindings ) );
		
		// Same check but with the bindings passed in differently.
		template.setBindings( bindings );
		assertEquals( "Hallo aap, met mij!", template.execute() );
		template.setBindings( null );

		// Same template bound differently
		bindings.put( "naam", "noot" );
		bindings.remove( "notUsed" );
		
		// Check results again.
		assertEquals( "Hallo noot, met mij!", template.execute( bindings ) );
		
		// Remove all bindings.
		bindings.clear();
		
		// Check results without any binding.
//		try {
//			template.execute( bindings );
//			fail( "Exception expected" );
//		} catch ( UnboundPropertyException ex ) {
//			assertEquals( "naam", ex.getPropertyName() );
//		}
	}

	public void testInvalidTemplate() throws Exception {
		// Read template resource.
		String templateName = "nl/xup/template/velocity/invalid.vm";
		InputStream stream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream( templateName );
		Reader reader = new InputStreamReader( stream );

		try {
			Template template = _engine.createTemplate( reader );
			template.execute();
			fail( "Exception expected" );
		} catch ( TemplateCompilationException ex ) {
			assertTrue( true );
		}

//		try {
//			Template template = _engine.createTemplate( new StringReader( "${var.missingMethod()}" ) );
//			Bindings bindings = template.getBindings();
//			bindings.put( "var", Boolean.TRUE );
//			template.execute( bindings);
//			fail( "Exception expected" );
//		} catch ( TemplateCompilationException ex ) {
//			assertTrue( true );
//		}
	}
}

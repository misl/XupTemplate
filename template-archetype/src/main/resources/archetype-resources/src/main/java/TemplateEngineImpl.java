package ${groupId}.${artifactId};

import groovy.text.SimpleTemplateEngine;

import java.io.IOException;
import java.io.Reader;

import org.codehaus.groovy.control.CompilationFailedException;

import nl.xup.template.Template;
import nl.xup.template.TemplateCompilationException;
import nl.xup.template.TemplateEngine;


/**
 * TODO:
 */
public class GroovyTemplateEngine implements TemplateEngine {

	// ----------------------------------------------------------------------
	// Attributes
	// ----------------------------------------------------------------------

	// TODO: place here a reference to the wrapped template engine.
	
	// ----------------------------------------------------------------------
	// Constructors
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// Implementing: TemplateEngine Interface
	// ----------------------------------------------------------------------

	/* (non-Javadoc)
	 * @see nl.xup.template.TemplateEngine#getName()
	 */
	public String getName() {
		// TODO: Place native template engine name here.
		return "native";
	}

	/* (non-Javadoc)
	 * @see nl.xup.template.TemplateEngine#createTemplate(java.io.Reader)
	 */
	public Template createTemplate(Reader reader) throws IOException {
		Template template = null;
		
		try {
			// TODO: Construct the native template and pass it to
			//       your wrapped implementation.
			template = new TemplateImpl( reader.toString() );
		} catch ( Exception ex ) {
			// TODO: Transform native exception into generic exceptions.
			throw new TemplateCompilationException( ex );
		}
		
		return template;
	}

}

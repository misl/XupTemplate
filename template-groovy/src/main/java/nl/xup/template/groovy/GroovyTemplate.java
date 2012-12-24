package nl.xup.template.groovy;

import groovy.lang.MissingPropertyException;
import nl.xup.template.AbstractTemplate;
import nl.xup.template.Bindings;
import nl.xup.template.TemplateEngine;
import nl.xup.template.UnboundPropertyException;

/**
 * Template wrapper to turn a Groovy based template into a
 * Template API Template.
 * 
 * @author Minto van der Sluis
 */
public final class GroovyTemplate extends AbstractTemplate {

	// ----------------------------------------------------------------------
	// Attributes
	// ----------------------------------------------------------------------

	private groovy.text.Template groovyTemplate = null;
	
	// ----------------------------------------------------------------------
	// Constructors
	// ----------------------------------------------------------------------

	/**
	 * Constructor to create a Template wrapper around a Groovy template 
	 * instance.
	 * 
	 * @param engine the TemplateEngine that created this instance.
	 * @param template the wrapped Groovy Template instance.
	 */
	GroovyTemplate ( TemplateEngine engine, groovy.text.Template template ) {
		super( engine );
		groovyTemplate = template;
	}
	
	// ----------------------------------------------------------------------
	// Implemented abstract methods from AbstractTemplate
	// ----------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	public String execute() throws UnboundPropertyException {
		return execute( null );
	}

	/**
	 * {@inheritDoc}
	 */
	public String execute(Bindings bindings) throws UnboundPropertyException {
		String result = null;
		
		// Build a map with all bindings, making sure the specified bindings
		// take precedence.
		Bindings usedBindings = getBindings();
		if ( bindings != null ) {
			usedBindings.putAll( bindings );
		} 
		
		try {
			result = groovyTemplate.make( usedBindings ).toString();
		} catch ( MissingPropertyException ex) {
			// Transform native exception in to generic template exceptions.
			throw new UnboundPropertyException( ex.getProperty(),
					"Unboud property: " + ex.getProperty(), ex );
		}
		
		return result;
	}
}

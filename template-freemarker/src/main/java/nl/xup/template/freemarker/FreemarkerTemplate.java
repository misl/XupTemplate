package nl.xup.template.freemarker;

import java.io.StringWriter;

import nl.xup.template.AbstractTemplate;
import nl.xup.template.Bindings;
import nl.xup.template.TemplateCompilationException;
import nl.xup.template.TemplateEngine;
import nl.xup.template.UnboundPropertyException;
import freemarker.core.InvalidReferenceException;

/**
 * Template wrapper to turn a Freemarker based template into a Template API
 * Template.
 * 
 * @author Minto van der Sluis
 */
public final class FreemarkerTemplate extends AbstractTemplate {

  private static final String EXCEPTION_REGEXP = "Expression ([^\\s]*) is undefined.*";

  // ----------------------------------------------------------------------
  // Attributes
  // ----------------------------------------------------------------------

  private freemarker.template.Template freemarkerTemplate = null;

  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  /**
   * Constructor to create a Template wrapper around a Freemarker based template
   * instance.
   * 
   * @param engine
   *          the TemplateEngine that created this instance.
   * @param template
   *          the wrapped Freemarker template instance.
   */
  FreemarkerTemplate( TemplateEngine engine, freemarker.template.Template template ) {
    super( engine );
    freemarkerTemplate = template;
  }

  // ----------------------------------------------------------------------
  // Implemented abstract methods from AbstractTemplate
  // ----------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  public String execute() throws UnboundPropertyException, TemplateCompilationException {
    return execute( null );
  }

  /**
   * {@inheritDoc}
   */
  public String execute( Bindings bindings ) throws UnboundPropertyException,
      TemplateCompilationException {
    // Build a map with all bindings, making sure the specified bindings
    // take precedence.
    final Bindings usedBindings = getBindings();
    if( bindings != null ) {
      usedBindings.putAll( bindings );
    }

    final StringWriter writer = new StringWriter();
    try {
      freemarkerTemplate.process( usedBindings, writer );
    } catch( InvalidReferenceException e ) {
      // Filter the property name out of the error message.
      final String propertyName = e.getMessage().replaceFirst( EXCEPTION_REGEXP, "$1" );
      // Transform native exception in to generic template exceptions.
      throw new UnboundPropertyException( propertyName, "Unboud property: " + propertyName, e );
    } catch( Exception e ) {
      // Mold the exception into something that is supported by
      // the interface.
      throw new TemplateCompilationException( e );
    }
    return writer.toString();
  }
}

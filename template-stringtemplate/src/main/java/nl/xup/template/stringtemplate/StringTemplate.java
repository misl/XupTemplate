package nl.xup.template.stringtemplate;

import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import nl.xup.template.AbstractTemplate;
import nl.xup.template.Bindings;
import nl.xup.template.TemplateCompilationException;
import nl.xup.template.TemplateEngine;

import org.stringtemplate.v4.NoIndentWriter;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STWriter;
import org.stringtemplate.v4.misc.STMessage;

/**
 * Template wrapper to turn a StringTemplate based template into a Template API
 * Template.
 * 
 * @author Minto van der Sluis
 */
public final class StringTemplate extends AbstractTemplate {

  // ----------------------------------------------------------------------
  // Attributes
  // ----------------------------------------------------------------------

  private ST stringTemplate = null;

  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  /**
   * Constructor to create a Template wrapper around a StringTemplate instance.
   * 
   * @param engine
   *          the TemplateEngine that created this instance.
   * @param template
   *          the wrapped StringTemplate instance.
   */
  StringTemplate( TemplateEngine engine, ST template ) {
    super( engine );
    stringTemplate = template;
  }

  // ----------------------------------------------------------------------
  // Implemented abstract methods from AbstractTemplate
  // ----------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  public String execute() throws TemplateCompilationException {
    return execute( null );
  }

  /**
   * {@inheritDoc}
   */
  public String execute( Bindings bindings ) throws TemplateCompilationException {
    // Build a map with all bindings, making sure the specified bindings
    // take precedence.
    Bindings usedBindings = getBindings();
    if( bindings != null ) {
      usedBindings.putAll( bindings );
    }

    // Need to remove existing bindings to prevent double assigning values,
    // which StringTemplate supports.
    Map<String, Object> attributes = stringTemplate.getAttributes(); 
    if( attributes != null ) {
      for( String key : attributes.keySet() ) {
        stringTemplate.remove( key );
      }
    }

    for( Entry<String, Object> entry : usedBindings.entrySet() ) {
      stringTemplate.add( entry.getKey(), entry.getValue() );
    }
    return renderTemplate( stringTemplate );
  }

  // -------------------------------------------------------------------------
  // Private methods
  // -------------------------------------------------------------------------

  /**
   * Renders the given template and returns the rendered string.
   * 
   * @param template
   *          the template to render
   * @return String with the rendering results.
   */
  public static String renderTemplate( ST template ) throws TemplateCompilationException {
    StringWriter out = new StringWriter();
    STWriter wr = new NoIndentWriter( out );

    wr.setLineWidth( STWriter.NO_WRAP );
    try {
      ST workingTemplate = new ST( template );
      workingTemplate.write( wr, Locale.getDefault(), new StringTemplateErrorListener() );
    } catch( InternalStringTemplateException e ) {
      STMessage msg = e.getStringTemplateMessage();
      throw new TemplateCompilationException( msg.toString(), msg.cause );
    }
    return out.toString();
  }
}

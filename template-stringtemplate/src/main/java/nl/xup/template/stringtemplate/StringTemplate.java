package nl.xup.template.stringtemplate;

import nl.xup.template.AbstractTemplate;
import nl.xup.template.Bindings;
import nl.xup.template.TemplateEngine;
import nl.xup.template.UnboundPropertyException;

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

  private org.antlr.stringtemplate.StringTemplate stringTemplate = null;

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
  StringTemplate( TemplateEngine engine, org.antlr.stringtemplate.StringTemplate template ) {
    super( engine );
    stringTemplate = template;
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
  public String execute( Bindings bindings ) throws UnboundPropertyException {
    String resultaat = null;

    // Build a map with all bindings, making sure the specified bindings
    // take precedence.
    Bindings usedBindings = getBindings();
    if( bindings != null ) {
      usedBindings.putAll( bindings );
    }

    stringTemplate.setAttributes( usedBindings );
    resultaat = stringTemplate.toString();

    return resultaat;
  }
}

package nl.xup.template.freemarker;

import java.io.IOException;
import java.io.Reader;

import nl.xup.template.Template;
import nl.xup.template.TemplateCompilationException;
import nl.xup.template.TemplateEngine;
import freemarker.core.ParseException;
import freemarker.template.Configuration;

/**
 * Engine for creating Freemarker based templates.
 * 
 * @author Minto van der Sluis
 */
public final class FreemarkerTemplateEngine implements TemplateEngine {
  // ----------------------------------------------------------------------
  // Class Attributes
  // ----------------------------------------------------------------------

  private static final String TEMPLATE_ENGINE_NAME = "Freemarker";

  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  // ----------------------------------------------------------------------
  // Implementing: TemplateEngine Interface
  // ----------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  public String getName() {
    return TEMPLATE_ENGINE_NAME;
  }

  /**
   * {@inheritDoc}
   */
  public Template createTemplate( Reader reader ) throws IOException, TemplateCompilationException {
    Template template = null;

    try {
      Configuration configuration = new Configuration();
      freemarker.template.Template freemarkerTemplate = new freemarker.template.Template( null,
          reader, configuration );
      template = new FreemarkerTemplate( this, freemarkerTemplate );
    } catch( ParseException e ) {
      // Transform into an implementation independent exception.
      throw new TemplateCompilationException( e.lineNumber, e.columnNumber, e );
    }

    return template;
  }
}

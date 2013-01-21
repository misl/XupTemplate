package nl.xup.template.freemarker;

import java.io.IOException;
import java.io.Reader;

import nl.xup.template.Template;
import nl.xup.template.TemplateCompilationException;
import nl.xup.template.TemplateEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

  private static Logger logger = LoggerFactory.getLogger( FreemarkerTemplateEngine.class );

  private static final String TEMPLATE_ENGINE_NAME = "Freemarker";

  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  // -------------------------------------------------------------------------
  // OSGi Blueprint lifecycle
  // -------------------------------------------------------------------------

  public void init() {
    logger.info( "Initializaing 'Freemarker' based TemplateEngine" );
  }

  public void destroy() {
    logger.info( "Removed 'Freemarker' based TemplateEngine" );
  }

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

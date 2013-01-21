package nl.xup.template.groovy;

import groovy.lang.GroovyRuntimeException;
import groovy.text.SimpleTemplateEngine;

import java.io.IOException;
import java.io.Reader;

import nl.xup.template.Template;
import nl.xup.template.TemplateCompilationException;
import nl.xup.template.TemplateEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Engine for creating Groovy based templates.
 * 
 * @author Minto van der Sluis
 */
public final class GroovyTemplateEngine implements TemplateEngine {

  // ----------------------------------------------------------------------
  // Class Attributes
  // ----------------------------------------------------------------------

  private static Logger logger = LoggerFactory.getLogger( GroovyTemplateEngine.class );

  private static final String TEMPLATE_ENGINE_NAME = "groovy";

  // ----------------------------------------------------------------------
  // Object Attributes
  // ----------------------------------------------------------------------

  private SimpleTemplateEngine groovyEngine = new SimpleTemplateEngine();

  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  // -------------------------------------------------------------------------
  // OSGi Blueprint lifecycle
  // -------------------------------------------------------------------------

  public void init() {
    logger.info( "Initializaing 'groovy' based TemplateEngine" );
  }

  public void destroy() {
    logger.info( "Removed 'groovy' based TemplateEngine" );
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
      template = new GroovyTemplate( this, groovyEngine.createTemplate( reader ) );
    } catch( GroovyRuntimeException e ) {
      // Omvormen tot implementatie onafhankelijke fout.
      throw new TemplateCompilationException( e );
    }

    return template;
  }
}

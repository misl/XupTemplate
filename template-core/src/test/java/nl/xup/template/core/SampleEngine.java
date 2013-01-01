package nl.xup.template.core;

import java.io.IOException;
import java.io.Reader;

import nl.xup.template.Template;
import nl.xup.template.TemplateCompilationException;
import nl.xup.template.TemplateEngine;

/**
 * Engine for unittest purposes.
 * 
 * @author Minto van der Sluis
 */
public class SampleEngine implements TemplateEngine {

  // ----------------------------------------------------------------------
  // Class Attributes
  // ----------------------------------------------------------------------

  private static final String TEMPLATE_ENGINE_NAME = "test";

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
    return new SampleTemplate( this );
  }
}

package nl.xup.template.core;

import nl.xup.template.AbstractTemplate;
import nl.xup.template.Bindings;
import nl.xup.template.TemplateCompilationException;
import nl.xup.template.TemplateEngine;
import nl.xup.template.UnboundPropertyException;

public class SampleTemplate extends AbstractTemplate {

  protected SampleTemplate( TemplateEngine parentEngine ) {
    super( parentEngine );
  }

  public String execute() throws UnboundPropertyException, TemplateCompilationException {
    return null;
  }

  public String execute( Bindings bindings ) throws UnboundPropertyException,
      TemplateCompilationException {
    return null;
  }
}

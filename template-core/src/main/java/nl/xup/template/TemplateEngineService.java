/*
 * This file is part of XupTemplate.
 *
 * XupTemplate is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * XupTemplate is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with XupTemplate.  If not, see <http://www.gnu.org/licenses/>.
 */

package nl.xup.template;

import java.io.IOException;
import java.io.Reader;
import java.util.Collection;

/**
 * The Template API OSGi based entry service that gives access to available
 * Template Engines.
 * 
 * @author Minto van der Sluis
 */
public interface TemplateEngineService {

  /**
   * Get the EngineFactory for the template engine name given.
   * 
   * @param name
   *          of the TemplateEngine to be retrieved.
   * @return TemplateEngine for the given engine name or null for an unknown
   *         engine name.
   */
  public TemplateEngine getEngine( String name );

  /**
   * Gets the collection with all available template engines.
   * 
   * @return A collection of available template engines. If there are no
   *         template engines availabel the collection will be empty.
   */
  public Collection<TemplateEngine> getEngines();

  /**
   * Convenience method that immediately creates a Template based on the actual
   * template file/reader. To accomplish this the TemplateEngine is determined
   * by the contents of the template file/reader. That particular template
   * engine is used to create the Template.
   * 
   * TODO: describe shebang
   * 
   * @param reader
   *          The reader to be used for the Template
   * @return A valid Template object for the given reader.
   * @throws IOException
   *           when using the reader fails
   * @throws TemplateCompilationException
   *           when compiling the template fails.
   * @throws MissingShebangException
   *           when the template lacks a shebang.
   * @throws UnknownEngineException
   *           when the shebang refers to an engine that is not known to the
   *           engine factory.
   */
  public Template getTemplate( Reader reader ) throws IOException, TemplateCompilationException,
      MissingShebangException, UnknownEngineException;

}
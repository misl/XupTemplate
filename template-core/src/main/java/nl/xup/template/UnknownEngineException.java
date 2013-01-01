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

/**
 * This exception indicates that the input reader refers to an unknown
 * TemplateEngine implementation. In other words, the shebang in the reader
 * points to an unknown engine. This indicates that either the shebang contains
 * a typo or the engine is missing from the classpath.
 * 
 * @author Minto van der Sluis
 */
@SuppressWarnings( "serial" )
public class UnknownEngineException extends Exception {

  // ----------------------------------------------------------------------
  // Attributes
  // ----------------------------------------------------------------------

  private String engineName;

  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  /**
   * Contructs an UnknownEngineException with the given name as the unknown
   * engine name.
   * 
   * @param name
   *          the name of the unknown engine.
   */
  public UnknownEngineException( String name ) {
    engineName = name;
  }

  // ----------------------------------------------------------------------
  // Getters and Setters
  // ----------------------------------------------------------------------

  /**
   * Gives the supposed name of a template engine which is not known to the
   * TemplateEngineFactory.
   * 
   * @return String with the supposed name of a template engine.
   */
  public final String getEngineName() {
    return engineName;
  }
}

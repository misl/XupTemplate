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
 * This exception indicate that the input reader has no shebang. Due to the
 * missing shebang in the reader, the TemplateEngineFactory is unable to
 * determine which engine to use for turning that reader into a Template.
 * 
 * @author Minto van der Sluis
 */
@SuppressWarnings( "serial" )
public class MissingShebangException extends Exception {

  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  public MissingShebangException() {
  }
}

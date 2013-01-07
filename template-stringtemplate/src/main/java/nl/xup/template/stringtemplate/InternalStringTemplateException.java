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

package nl.xup.template.stringtemplate;

import org.stringtemplate.v4.misc.STMessage;

/**
 * Internal temporary Exception to be used within this component only.
 * 
 * @author Minto van der Sluis
 */
@SuppressWarnings( "serial" )
public class InternalStringTemplateException extends RuntimeException {

  // ----------------------------------------------------------------------
  // Object attributes
  // ----------------------------------------------------------------------
  
  private STMessage message;
  
  // ----------------------------------------------------------------------
  // Constructors
  // ----------------------------------------------------------------------

  public InternalStringTemplateException( STMessage msg ) {
    message = msg;
  }

  // ----------------------------------------------------------------------
  // Getter / Setters
  // ----------------------------------------------------------------------
  
  public STMessage getStringTemplateMessage() {
    return message;
  }
}

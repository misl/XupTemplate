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

import java.util.Map;

/**
 * A mapping of key/value pairs, all of whose keys are Strings. These
 * keys are not allowed to be either null or empty.
 * 
 * Resembles javax.script.Bindings (see jrs 223 Java Scripting API)
 * 
 * @author Minto van der Sluis
 */
public interface Bindings extends Map< String, Object >{

  /**
   * Set a named value.
   *
   * @param key The name associated with the value.
   * @param value The value associated with the name.
   *
   * @return The value previously associated with the given name.
   * Returns null if no value was previously associated with the name.
   *
   * @throws NullPointerException if the name is null.
   * @throws IllegalArgumentException if the name is empty String.
   */
  Object put( String key, Object value );
  
  /**
   * Adds all the mappings in a given <code>Map</code> to this 
   * <code>Bindings</code>.
   * @param merge The <code>Map</code> to merge with this one.
   *
   * @throws NullPointerException
   *         if toMerge map is null or if some key in the map is null.
   * @throws IllegalArgumentException
   *         if some key in the map is an empty String.
   */
  void putAll( Map<? extends String, ? extends Object> merge );
  
  /**
   * Returns <tt>true</tt> if this map contains a mapping for the specified
   * key.  More formally, returns <tt>true</tt> if and only if
   * this map contains a mapping for a key <tt>k</tt> such that
   * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
   * at most one such mapping.)
   *
   * @param key key whose presence in this map is to be tested.
   * @return <tt>true</tt> if the map contains a mapping for the specified
   *         key.
   *
   * @throws NullPointerException if key is null
   * @throws ClassCastException if key is not String
   * @throws IllegalArgumentException if key is empty String
   */
  boolean containsKey( Object key  );
  
  /**
   * Returns the value to which this map maps the specified key.  Returns
   * <tt>null</tt> if the map contains no mapping for this key.  A return
   * value of <tt>null</tt> does not <i>necessarily</i> indicate that the
   * map contains no mapping for the key; it's also possible that the map
   * explicitly maps the key to <tt>null</tt>.  The <tt>containsKey</tt>
   * operation may be used to distinguish these two cases.
   *
   * <p>More formally, if this map contains a mapping from a key
   * <tt>k</tt> to a value <tt>v</tt> such that <tt>(key==null ? k==null :
   * key.equals(k))</tt>, then this method returns <tt>v</tt>; otherwise
   * it returns <tt>null</tt>.  (There can be at most one such mapping.)
   *
   * @param key key whose associated value is to be returned.
   * @return the value to which this map maps the specified key, or
   *         <tt>null</tt> if the map contains no mapping for this key.
   *
   * @throws NullPointerException if key is null
   * @throws ClassCastException if key is not String
   * @throws IllegalArgumentException if key is empty String
   */
  Object get( Object key );
  
  /**
   * Removes the mapping for this key from this map if it is present
   * (optional operation).   More formally, if this map contains a mapping
   * from key <tt>k</tt> to value <tt>v</tt> such that
   * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping
   * is removed.  (The map can contain at most one such mapping.)
   *
   * <p>Returns the value to which the map previously associated the key, or
   * <tt>null</tt> if the map contained no mapping for this key.  (A
   * <tt>null</tt> return can also indicate that the map previously
   * associated <tt>null</tt> with the specified key if the implementation
   * supports <tt>null</tt> values.)  The map will not contain a mapping for
   * the specified  key once the call returns.
   *
   * @param key key whose mapping is to be removed from the map.
   * @return previous value associated with specified key, or <tt>null</tt>
   *         if there was no mapping for key.
   *
   * @throws NullPointerException if key is null
   * @throws ClassCastException if key is not String
   * @throws IllegalArgumentException if key is empty String
   */
  Object remove( Object key );
}

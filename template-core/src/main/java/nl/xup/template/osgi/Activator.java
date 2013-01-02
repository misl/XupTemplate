package nl.xup.template.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

  // ----------------------------------------------------------------------
  // Class attributes
  // ----------------------------------------------------------------------

  private static boolean inOsgiContainer = false;

  // ----------------------------------------------------------------------
  // Implementing BundleActivator
  // ----------------------------------------------------------------------

  @Override
  public void start( BundleContext context ) throws Exception {
    inOsgiContainer = true;
  }

  @Override
  public void stop( BundleContext context ) throws Exception {
    inOsgiContainer = false;
  }

  // ----------------------------------------------------------------------
  // Interface
  // ----------------------------------------------------------------------
  
  public static boolean isRunningInsideOsgi() {
    return inOsgiContainer;
  }
}

/*
 * StAXResolver.java
 *
 * Created on February 12, 2007, 2:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.xmlresolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.xml.stream.XMLResolver;
import javax.xml.stream.XMLStreamException;

/** Implements {@link javax.xml.stream.XMLResolver}.
 *
 * @author ndw
 */
public class StAXResolver implements XMLResolver {
    protected static Logger logger = LoggerFactory.getLogger(StAXResolver.class);
    ResourceResolver resolver = null;
    
    /** Creates a new instance of StAXResolver.
     *
     * The default resolver is a new ResourceResolver that uses a static catalog shared by all threads.
     */
    public StAXResolver() {
        resolver = new ResourceResolver();
        Resolver ordinaryResolver = new Resolver(resolver);
        resolver.setEntityResolver(ordinaryResolver);
    }

    /** Creates a new instance of a StAXResolver.
     *
     * Creates a resolver using a specific Catalog.
     *
     * @param catalog The catalog to use.
     */
    public StAXResolver(Catalog catalog) {
        resolver = new ResourceResolver(catalog);
        Resolver ordinaryResolver = new Resolver(resolver);
        resolver.setEntityResolver(ordinaryResolver);
    }

    /** Creates a new instance of a StAXResolver.
     *
     * Creates a resolver using a specific underlying ResourceResolver.
     *
     * @param resolver The resource resolver to use.
     */
    public StAXResolver(ResourceResolver resolver) {
        this.resolver = resolver;
    }
    
    /** Get the Catalog used by this resolver. */
    public Catalog getCatalog() {
        return resolver.getCatalog();
    }

    /** Implements the {@link javax.xml.stream.XMLResolver} interface. */
    public Object resolveEntity(String publicId, String systemId, String baseURI, String namespace) throws XMLStreamException {
        // We can do better than this, but for now...just get an absolute URI
        String absSystem = null;
        
        if (baseURI != null) {
            try {
                URI auri = new URI(baseURI);
                auri = auri.resolve(new URI(systemId));
                absSystem = auri.toURL().toString();
            } catch (URISyntaxException use) {
                // nop;
            } catch (MalformedURLException mue) {
                // nop;
            } catch (IllegalArgumentException iae) {
                // In case someone uses baseURI=null, systemId="../some/local/path"
                // nop;
            }
        }

        logger.trace("resolveEntity(" + publicId + "," + absSystem + "," + namespace + ")");

        Resource rsrc = resolver.resolvePublic(absSystem, publicId);
        if (rsrc == null) {
            logger.trace("  not resolved locally");
            return null;
        } else {
            logger.trace("  resolved locally: "  + rsrc.uri());
            return rsrc.body();
        }
    }
}

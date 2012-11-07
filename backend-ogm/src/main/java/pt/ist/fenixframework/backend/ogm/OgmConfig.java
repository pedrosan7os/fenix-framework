package pt.ist.fenixframework.backend.ogm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.ConfigError;
import pt.ist.fenixframework.DomainObject;
import pt.ist.fenixframework.core.IdentityMap;
import pt.ist.fenixframework.indexes.IndexesConfig;

/**
 * This is the ogm configuration manager used by the fenix-framework-backend-ogm project.
 * 
 * @see Config
 *
 */
public class OgmConfig extends IndexesConfig {
    private static final Logger logger = LoggerFactory.getLogger(OgmDomainObject.class);

    /**
     * This <strong>required</strong> parameter specifies the location of the XML file used to
     * configure Infinispan.  This file should be available in the application's classpath.
     */
    protected String ispnConfigFile = null;

    protected final OgmBackEnd backEnd;

    public OgmConfig() {
        this.backEnd = OgmBackEnd.getInstance();
    }

    // process this config's parameters

    public String getIspnConfigFile() {
        return this.ispnConfigFile;
    }

    @Override
    protected void init() {
        this.backEnd.configOgm(this);
        super.init(); // do this only after having set up transaction manager
    }

    @Override
    protected void checkConfig() {
        super.checkConfig();
        if (ispnConfigFile == null) {
            missingRequired("ispnConfigFile");
        }
    }

    @Override
    public OgmBackEnd getBackEnd() {
        return this.backEnd;
    }
}

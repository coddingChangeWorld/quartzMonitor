package com.common.tool;

import java.util.Properties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.support.PropertiesLoaderSupport;

public class PropertiesCache
  extends PropertiesLoaderSupport
  implements InitializingBean
{
  protected Properties cacheProperties;
  
  public Properties getCacheProperties()
  {
    return this.cacheProperties;
  }
  
  public void setCacheProperties(Properties cacheProperties)
  {
    this.cacheProperties = cacheProperties;
  }
  
  public void afterPropertiesSet()
    throws Exception
  {
    this.cacheProperties = mergeProperties();
  }
}

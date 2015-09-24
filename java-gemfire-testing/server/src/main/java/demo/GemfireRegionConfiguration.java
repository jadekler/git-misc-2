package demo;

import com.gemstone.gemfire.cache.RegionAttributes;
import com.gemstone.gemfire.cache.client.*;
import org.springframework.context.annotation.*;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;

@Configuration
public class GemfireRegionConfiguration {
    @Bean(name = "SomeRegion")
    @DependsOn("gemfireCache")
    public <String, Long> ClientRegionFactoryBean<String, Long> someRegion(ClientCache gemfireCache, RegionAttributes<String, Long> regionAttributes) {
        ClientRegionFactoryBean<String, Long> clientRegionFactoryBean = new ClientRegionFactoryBean<>();

        clientRegionFactoryBean.setAttributes(regionAttributes);
        clientRegionFactoryBean.setCache(gemfireCache);
        clientRegionFactoryBean.setName("SomeRegion");
        clientRegionFactoryBean.setShortcut(ClientRegionShortcut.PROXY);

        return clientRegionFactoryBean;
    }
}

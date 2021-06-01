package com;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.twovet.base.constant.ViewNameConstants;

@SuppressWarnings("deprecation")
@Configuration
public class DefaultViewConfig extends WebMvcConfigurerAdapter{
	
	@Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName(ViewNameConstants.HOME);
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }

}

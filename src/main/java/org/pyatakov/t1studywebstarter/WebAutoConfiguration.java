package org.pyatakov.t1studywebstarter;

import org.pyatakov.t1studywebstarter.aspect.LogTrackingAspect;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import(LogTrackingAspect.class)
public class WebAutoConfiguration {

}

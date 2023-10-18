<<<<<<<< HEAD:src/main/java/com/poly/DATN_BookWorms/ServletInitializer.java
package com.poly.DATN_BookWorms;
========
package com.Poly1.DATN_BookWorms;
>>>>>>>> Active2-branche:src/main/java/com/Poly1/DATN_BookWorms/ServletInitializer.java

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DatnBookWormsApplication.class);
	}

}

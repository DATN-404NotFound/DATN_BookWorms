<<<<<<< HEAD

package com.poly.DATN_BookWorms;

=======
package com.poly.DATN_BookWorms;
>>>>>>> ad2de9c700902e434ea0f07de9c68fd44c19594b

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DatnBookWormsApplication.class);
	}

}

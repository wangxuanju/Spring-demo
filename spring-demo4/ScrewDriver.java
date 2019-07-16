```java
package com.netease.course;

import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.Resource;
public class ScrewDriver {
	//或改成@Autowired
	@Resource
	private Header header;
	
	public void use() {
		System.out.println("Use screwdriver:" + header.getInfo());
		header.doWork();
	}

}

```

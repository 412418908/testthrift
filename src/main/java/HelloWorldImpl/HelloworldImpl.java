package HelloWorldImpl;

import org.apache.thrift.TException;
import com.micmiu.thrift.demo.*;

/**
 * blog http://www.micmiu.com
 *
 * @author Michael
 *
 */
public class HelloworldImpl implements HelloWorldService.Iface {
 
	public HelloworldImpl() {
	}
 
	@Override
	public String sayHello(String username) throws TException {
		return "Hi," + username + " welcome to my blog www.micmiu.com";
	}
 
}
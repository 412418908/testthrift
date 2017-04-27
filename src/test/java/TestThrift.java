import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.micmiu.thrift.demo.HelloWorldService;

public class TestThrift {

	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 8090;
	public static final int TIMEOUT = 30000;
	HelloWorldService.Client client;
	TTransport transport = null;

	@Before
	public void setUp() throws Exception {
		transport = new TFramedTransport(new TSocket(SERVER_IP,
				SERVER_PORT, TIMEOUT));
		// 协议要和服务端一致
		TProtocol protocol = new TBinaryProtocol(transport);
		// TProtocol protocol = new TCompactProtocol(transport);
		// TProtocol protocol = new TJSONProtocol(transport);
		client = new HelloWorldService.Client(protocol);
		transport.open();
		System.out.println("client " + client);

	}

	@After
	public void tearDown() throws Exception {
		if (null != transport) {
			transport.close();
		}
	}

	@Test
	public void test() throws Exception {
		String result = client.sayHello("jerry");
		System.out.println("Thrify client result =: " + result);
	}

}

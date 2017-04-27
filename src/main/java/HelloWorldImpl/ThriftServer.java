package HelloWorldImpl;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;

import com.micmiu.thrift.demo.HelloWorldService;

public class ThriftServer {

	public static final int SERVER_PORT = 8090;
	 
	public void startServer() {
		try {
			System.out.println("HelloWorld TSimpleServer start ....");
 
			TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(
					new HelloworldImpl());
			// HelloWorldService.Processor&lt;HelloWorldService.Iface&gt; tprocessor =
			// new HelloWorldService.Processor&lt;HelloWorldService.Iface&gt;(
			// new HelloWorldImpl());
 
			// 简单的单线程服务模型，一般用于测试
			TNonblockingServerSocket  serverTransport = new TNonblockingServerSocket (SERVER_PORT);
			THsHaServer.Args tArgs = new THsHaServer.Args(serverTransport);
			tArgs.processor(tprocessor);
			tArgs.transportFactory(new TFramedTransport.Factory());
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			// tArgs.protocolFactory(new TCompactProtocol.Factory());
			// tArgs.protocolFactory(new TJSONProtocol.Factory());
			TServer server = new THsHaServer(tArgs);
			server.serve();
 
		} catch (Exception e) {
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThriftServer server = new ThriftServer();
		server.startServer();
	}
}

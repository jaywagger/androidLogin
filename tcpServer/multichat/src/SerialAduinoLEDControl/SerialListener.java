//package android.control;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//
//import gnu.io.SerialPortEvent;
//import gnu.io.SerialPortEventListener;
//
//public class SerialListener  implements SerialPortEventListener{
//	private InputStream in;
//	PrintWriter pw;
//	OutputStream os;
//	public SerialListener(InputStream in) {
//		super();
//		this.in = in;
//	}
//	//�̺�Ʈ�� �߻��Ҷ����� ȣ��Ǵ� �޼ҵ�
//	//�߻��� �̺�Ʈ�� ������ ��� �ִ� ��ü - SerialPortEvent
//	@Override
//	public void serialEvent(SerialPortEvent event) {
//		//���۵� �����Ͱ� �ִ� ��� �����͸� �о �ֿܼ� ���
//		if(event.getEventType()==SerialPortEvent.DATA_AVAILABLE) {
//			try {
//				//���۵Ǵ� �������� ũ�⸦ ����
//				int check_size = in.available();
//				byte[] data = new byte[check_size];
//				in.read(data,0,check_size);
//				System.out.println("���� ������:"+new String(data));
//				os.write(data);
//				pw = new PrintWriter(os,true);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}//
//		}
//	}
//}

//package android.control;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.net.UnknownHostException;
//import java.util.TooManyListenersException;
//
//import gnu.io.CommPort;
//import gnu.io.CommPortIdentifier;
//import gnu.io.NoSuchPortException;
//import gnu.io.PortInUseException;
//import gnu.io.SerialPort;
//import gnu.io.UnsupportedCommOperationException;
//
//public class SerialAduinoLEDControl {
//	InputStream in;
//	InputStream is;
//	InputStreamReader ir;
//	Socket socket;
//	BufferedReader br;
//	OutputStream out;
//	OutputStream os;
//	PrintWriter pw;
//	String ip;
//	int port;
//	
//	
//	public SerialAduinoLEDControl() {
//		
//	}
//	public void connect(String portName) {
//		try {
//			//COM��Ʈ�� ���� �����ϰ� ��밡���� �������� Ȯ��
//			CommPortIdentifier commPortIdentifier =
//					CommPortIdentifier.getPortIdentifier(portName);
//			//��Ʈ�� ��������� üũ
//			if(commPortIdentifier.isCurrentlyOwned()) {
//				System.out.println("��Ʈ ����� �� �����ϴ�.");
//			}else {
//				System.out.println("��Ʈ�� ��� �� �� �ֽ��ϴ�.");
//				//port�� ��� �����ϸ� ��Ʈ�� ���� ��Ʈ��ü�� ������
//				//�Ű�����1 : ��Ʈ�� ���� ����ϴ� ���α׷��� �̸�(���ڿ�)
//				//�Ű�����2 : ��Ʈ�� ���� ����ϱ� ���ؼ� ��ٸ��� �ð�(�и�������)
//				CommPort commPort = commPortIdentifier.open("basic_serial",
//													5000);
//				if(commPort instanceof SerialPort) {
//					System.out.println("SerialPort");
//					SerialPort serialPort = (SerialPort)commPort;
//					serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, 
//							SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
//					connectServer();//��������
//					in = serialPort.getInputStream();
//					//out = serialPort.getOutputStream();
//					System.out.println("indata==>"+in.read());
//					/*System.out.println("out==>"+out.toString());*/
//					//Arduino를 통해서 반복해서 들어오는 데이터를 읽을 수 있도록 
//					//이벤트에 반응하도록 연결
//					SerialListener listener = new SerialListener(in);
//					
//					try {
//						serialPort.addEventListener(listener);
//					} catch (TooManyListenersException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					//시리얼포트로 전송되어 들어오는 데이터가 있다면 noti하며 이벤트
//					//리스너가 실행되도록 처리
//					serialPort.notifyOnDataAvailable(true);
//				}else {
//					System.out.println("SerialPort만 작업할 수 있습니다.");
//				}
//			}
//		} catch (NoSuchPortException e) {
//			e.printStackTrace();
//		
//		} catch (UnsupportedCommOperationException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (PortInUseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};
//	}
//	public void connectServer() {
//		try {
//			socket = new Socket("70.12.230.80",55555);
//			if(socket!=null) {
//				System.out.println("서버연결!!"+socket.getLocalPort());
//				ioWork();
//			}
//					//new ReceiverThread(socket);
//				Thread receiveThread = new Thread(new Runnable() {
//				@Override
//				public void run() {
//					while(true) {
//						System.out.println("daga");
//						String msg;
//						try {
//							//br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//							//System.out.println("tttt"+br.read());
//							//out = socket.getOutputStream();                                                                                                                                        
//							//pw = new PrintWriter(socket.getOutputStream(),true);
//							msg =  br.readLine();
//							if(msg.equals("led_on")) {
//								System.out.println("클라이언트가 보낸 메시지:"+msg);
//								os.write('O');
//							}else {
//								System.out.println("클라이언트가 보낸 메시지:"+msg);
//								os.write('X');
//							}
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			});
//			receiveThread.start();
//				
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	//�ø�������� ���� �ʿ��� OutputStream����
//	/*public OutputStream getOutput() {
//			try {
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return out;
//			}*/
//	public void ioWork() {
//		try {
//			is = socket.getInputStream();
//			ir = new InputStreamReader(is);
//			br = new BufferedReader(ir);
//			
//			os = socket.getOutputStream();
//			pw = new PrintWriter(os,true);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	public static void main(String[] args) {
//		new SerialAduinoLEDControl().connect("COM8");
//		//new SerialAduinoLEDControl().connectServer();
//		//��������
//	}
//	
//
//}
